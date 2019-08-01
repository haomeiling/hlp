package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.util.TimeUtils;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryExaminationRecordListHisReportInfo;
import cn.bxd.sip.his.model.dto.his.QueryExaminationRecordListRes;
import cn.bxd.sip.his.model.dto.reservation.GetClinicRecordReqData;
import cn.bxd.sip.his.model.dto.reservation.ReportListItem;
import cn.bxd.sip.his.model.dto.reservation.ReportListRes;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.bxd.sip.bxd.util.TimeUtils.getLongDateTime;

/**
 * 2.9.1 3014 检验检查记录查询
 * Description:
 * User: HaoMeiLing
 * Date: 2018-09-23
 * Time: 09:53
 */
@Slf4j
@Component
public class GetClinicRecordOperation extends AbstractOperationProcessor {
    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        GetClinicRecordReqData getClinicRecordReqData = JSON.parseObject(reqMsg, GetClinicRecordReqData.class);
        //封装：向HIS发送请求
        String queryClinicRecordInfoStr;
        try {
            //ws客户端：1.1.6.获取检查,检验数据 queryExaminationRecordList
            Object hosWsClient5 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_HIS_USER, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();
            //封装数据查询
            String startTime = TimeUtils.transDateInt2Str(getClinicRecordReqData.getStartDate());
            String endTime = TimeUtils.transDateInt2Str(getClinicRecordReqData.getEndDate());
            if(tRhipConnectParm.getHospitalId() == 1932){// 1932 上林县人民医院入参不传时间，因此做兼容判断   lisheng 2019/7/15
                queryClinicRecordInfoStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.QUERY_EXAMINATION_RECORD_LIST, sysUserName, sysKey,
                        getClinicRecordReqData.getEMPI(), "", "");
            }else {
                queryClinicRecordInfoStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.QUERY_EXAMINATION_RECORD_LIST, sysUserName, sysKey,
                        getClinicRecordReqData.getEMPI(), startTime, endTime);
            }

            log.debug(" 3014 1.1.6.获取检查,检验数据 queryExaminationRecordList" + queryClinicRecordInfoStr);
            QueryExaminationRecordListRes queryExaminationRecordListRes = JSON.parseObject(queryClinicRecordInfoStr, QueryExaminationRecordListRes.class);
            //判断：是否为空
            if (StringUtils.equals(HisConvertConst.HisCode.NO_DATA_CODE, queryExaminationRecordListRes.getResultCode())) {
                return noDataMsgReturn(queryExaminationRecordListRes.getResultCode(), queryExaminationRecordListRes.getResultMsg());
            }
            //判断：是否返回有错误
            if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryExaminationRecordListRes.getResultCode())) {
                return errMsgReturn(queryExaminationRecordListRes.getResultMsg());
            }
            List<QueryExaminationRecordListHisReportInfo> listResHisReport = queryExaminationRecordListRes.getHisReport();
            Map<String, String> reportListItemMap = new HashMap<>();
            List<ReportListItem> reportListItemList = new ArrayList<>();
            for (QueryExaminationRecordListHisReportInfo info : listResHisReport) {
                if (reportListItemMap.get(info.getReportNo()) == null) {
                    if (HisConvertConst.Report.LIS.equals(getClinicRecordReqData.getType())) {   //当报告类型为LIS（检查报告）时
                        if (!HisConvertConst.Report.LIS_TYPE.equals(info.getReportType())) {continue;}     //将不是1（LIS、检查报告）类型的报告去掉
                    } else {                                            //当报告类型不为检查报告时
                        if (HisConvertConst.Report.LIS_TYPE.equals(info.getReportType())) {continue;}  //将是1（LIS、检查报告）类型的报告去掉
                    }
                    ReportListItem reportListItem = new ReportListItem();
                    reportListItem.setRID(info.getReportNo());//报告单编号，报告单唯一标示
                    reportListItem.setSID(info.getReportNo());//申请单编号，申请单唯一标示
                    reportListItem.setApplyTime(getLongDateTime(info.getCreateTime()));//申请时间 YYYYMMDDHHMMSS
                    reportListItem.setPurpose("无");//申请目的，申请检查目的
                    reportListItem.setParts(info.getCheckName());//检查部位，检查部位或方式
                    reportListItem.setSampleType(info.getReportType());//标本类型
                    reportListItem.setSampleCode(info.getReportNo());//标本编号
                    reportListItem.setClinicalNo("");//诊疗编号
                    reportListItem.setDeliverTime(getLongDateTime(info.getCreateTime()));//签发时间
                    reportListItem.setDeptName(info.getDoctorName());//签发科室名称
                    if (HisConvertConst.Report.REPORT_STATUS.equals(info.getReportStatus())){//适配掌医出参，sip报告状态0：未出报告，1：已出报告,掌医0：未出报告，2：已出报告
                        reportListItem.setStatus(HisConvertConst.Report.STATUS);
                    }else {
                        reportListItem.setStatus(info.getReportStatus());
                    }
                    reportListItem.setEMPI(info.getPatientNo());//	患者主索引
                    reportListItem.setPatientName(info.getPatientName());//	患者姓名
                    reportListItem.setQATime(getLongDateTime(info.getReportDate()));//	复核时间
                    reportListItem.setContent(info.getCheckName());//	申请检查内容，申请项目名称
                    reportListItem.setDoctorName(info.getDoctorName());//	签发医师姓名
                    reportListItem.setQAName(info.getChekckdoctorName());//	复合医师姓名
                    reportListItemMap.put(String.valueOf(info.getReportNo()), String.valueOf(info.getReportNo()));
                    reportListItemList.add(reportListItem);
                }
            }
            //封装返回数据
            ReportListRes reportListRes = new ReportListRes();
            reportListRes.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
            reportListRes.setHosId(String.valueOf(getClinicRecordReqData.getHosId()));
            reportListRes.setData(reportListItemList);
            /* return JSON.toJSONString(reportListRes);  会将首字母的大写转成小写*/
            return new Gson().toJson(reportListRes);
        } catch (Exception e) {
            log.debug(" 3014 1.1.6.获取检查,检验数据 queryExaminationRecordList 错误" + e.getMessage());
            log.error("", e);
            return errMsgReturn("系统错误");
        }
    }
}
