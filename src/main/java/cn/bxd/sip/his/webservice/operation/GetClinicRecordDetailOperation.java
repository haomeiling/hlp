package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.util.TimeUtils;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.ReportDetailRes;
import cn.bxd.sip.his.model.dto.his.QueryExaminationRecordListHisReportInfo;
import cn.bxd.sip.his.model.dto.his.QueryExaminationRecordListHisReportInfoExtend;
import cn.bxd.sip.his.model.dto.his.QueryExaminationRecordListRes;
import cn.bxd.sip.his.model.dto.reservation.Item;
import cn.bxd.sip.his.model.dto.reservation.ReportDetail;
import cn.bxd.sip.his.model.dto.reservation.ReportDetailReq;
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
 * 2.9.1 3015 获取检查,检验数据详细
 * Description:
 * User: Lisheng
 * Date: 2019/4/23
 */
@Slf4j
@Component
public class GetClinicRecordDetailOperation extends AbstractOperationProcessor {
    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        ReportDetailReq getClinicRecordReqData = JSON.parseObject(reqMsg, ReportDetailReq.class);
        //封装：向HIS发送请求
        String queryClinicRecordInfoStr;
        try {
            //ws客户端：1.1.6.获取检查,检验数据 queryExaminationRecordList
            Object hosWsClient5 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_HIS_USER, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();

            //封装数据查询
            String startTime = TimeUtils.transDateInt2Str(Integer.parseInt(getClinicRecordReqData.getReportDate()));
            String endTime = TimeUtils.transDateInt2Str(Integer.parseInt(getClinicRecordReqData.getReportDate()));
            if(tRhipConnectParm.getHospitalId() == 1932) {// 1932 上林县人民医院入参不传时间，因此做兼容判断   lisheng 2019/7/15
                queryClinicRecordInfoStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.QUERY_EXAMINATION_RECORD_LIST, sysUserName, sysKey,
                        getClinicRecordReqData.getEMPI(), "", "");
            }else {
                queryClinicRecordInfoStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.QUERY_EXAMINATION_RECORD_LIST, sysUserName, sysKey,
                        getClinicRecordReqData.getEMPI(), startTime, endTime);
            }

            log.debug(" 3015 获取检查,检验数据详细 queryExaminationRecordList" + queryClinicRecordInfoStr);
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
            ReportDetail reportDetail = new ReportDetail();//检验检查单
            Map<String, String> reportListItemMap = new HashMap<>();//用于过滤重复记录
            List<Item> reportItemList = new ArrayList<>();//
            for (QueryExaminationRecordListHisReportInfo info : listResHisReport) {
                if (info.getReportNo().equals(getClinicRecordReqData.getRID())) {

                    QueryExaminationRecordListHisReportInfoExtend extend = JSON.parseObject(info.getExtend(), QueryExaminationRecordListHisReportInfoExtend.class);
                    if (HisConvertConst.Report.LIS.equals(getClinicRecordReqData.getType())) {   //当报告类型为LIS（检查报告）时
                        if (!HisConvertConst.Report.LIS_TYPE.equals(info.getReportType())) {continue;}     //将不是1（LIS、检查报告）类型的报告去掉
                    } else {                                            //当报告类型不为检查报告时
                        if (HisConvertConst.Report.LIS_TYPE.equals(info.getReportType())) {continue;}  //将是1（LIS、检查报告）类型的报告去掉
                    }
                    if (reportListItemMap.get(info.getReportNo()) == null) {
                        reportDetail.setRID(info.getReportNo());//报告单编号，报告单唯一标示
                        reportDetail.setType(info.getReportType());//报告单类型,1：检验，2：B超，3：放射影像，4：内镜，5：病理，6：心电图
                        reportDetail.setSID(info.getReportNo());//申请单编号，申请单唯一标示
                        reportDetail.setApplyTime(getLongDateTime(info.getCreateTime()));//申请时间 YYYYMMDDHHMMSS
                        reportDetail.setPurpose("无");//申请目的，申请检查目的
//                reportDetail.setParts(info.getCheckName());//检查部位，检查部位或方式
                        reportDetail.setSampleType(info.getReportType());//标本类型
                        reportDetail.setSampleCode(info.getReportNo());//标本编号
                        reportDetail.setClinicalNo("");//诊疗编号
                        reportDetail.setDeliverTime(getLongDateTime(info.getCreateTime()));//签发时间
                        reportDetail.setDeliverDeptName(info.getDoctorName());//签发科室名称
//                reportDetail.setStatus(info.getReportStatus());//报告状态,0：未出报告，1：已出报告
                        reportDetail.setEMPI(info.getPatientNo());//	患者主索引
                        reportDetail.setPatientName(info.getPatientName());//	患者姓名
//                        reportDetail.setQATime(getLongDateTime(info.getReportDate()));//	复核时间

                        reportDetail.setApplyDeptName( extend == null ? "" : extend.getDeptName());//检查科室
                        reportDetail.setApplyDoctorName(info.getDoctorName());//检查医生
                        reportDetail.setContent(info.getCheckName());//	申请检查内容，申请项目名称
                        reportDetail.setDeliverDoctorName(info.getDoctorName());//	签发医师姓名
                        reportDetail.setQAName(info.getChekckdoctorName());//	复合医师姓名
                        if("1".equals(info.getReportType())){
                            reportItemList.addAll(reportItems(info.getReportItems()));
                        }else {
                            reportDetail.setReportAbstract(info.getReportItems());
                            reportDetail.setReportURI( extend== null ? "" :extend.getCheckHint());
                        }

                        reportListItemMap.put(String.valueOf(info.getReportNo()), String.valueOf(info.getReportNo()));
                    } else {
                        if("1".equals(info.getReportType())){
                            reportItemList.addAll(reportItems(info.getReportItems()));
                        }else {
                            reportDetail.setReportAbstract(info.getReportItems());
                        }
                    }
                }
            }
            //封装返回数据
            reportDetail.setItemList(reportItemList);
            ReportDetailRes reportDetailRes = new ReportDetailRes();
            reportDetailRes.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
            reportDetailRes.setHosId(String.valueOf(getClinicRecordReqData.getHosId()));
            reportDetailRes.setData(reportDetail);
            /* return JSON.toJSONString(reportDetailRes);  会将首字母的大写转成小写*/
            return new Gson().toJson(reportDetailRes);
        } catch (Exception e) {
            log.debug(" 3014 1.1.6.获取检查,检验数据 queryExaminationRecordList 错误" + e.getMessage());
            log.error("", e);
            return errMsgReturn("系统错误");
        }
    }

    /**
     * 将检验检查报告项目列表String解析成 List<Item>
     * @param reportItems
     * @return
     * Lisheng 2019/4/26
     */
    public List<Item> reportItems(String reportItems) {
        // 遍历报告详情
        List<Item> itemList = new ArrayList<>();

        if (reportItems != null && !"".equals(reportItems) && !"null".equals(reportItems)) {
            System.out.println("-------------" + reportItems);
            String[] array = reportItems.split("\\^");
            for (String s : array) {
                Item reportItem = new Item();
                String[] split2 = (s + " | | | | | |").split("\\|");
                //序号      |项目名称         |检查结果    |检验状态(↑,↓等)|结果单位   |参考值    |特殊描述
                //1         |嗜碱细胞绝对值   |0.02        |正常             |           |10^9/L    |0--1
                //13        |大型血小板比率   |15.2        |↓               |%          |19.2-47   |           |
                //1         |*白细胞          |11.48       |↑               |10
                reportItem.setItemCode(split2[0]);//项目编码
                reportItem.setItemName(split2[1]);//项目名称
                if (split2[4] != null) {
                    if (split2[4].contains("∧")) {
                        String s1 = split2[4].replace("∧", "^");
                        reportItem.setUnit("*" + s1);//计量单位
                    } else {
                        reportItem.setUnit(split2[4]);//计量单位
                    }
                }
                reportItem.setRefValue(split2[5]); //参考值
                reportItem.setValue(split2[2]);   //结果值
                itemList.add(reportItem);
            }
        }
        return itemList;
    }
}
