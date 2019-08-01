package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryHospitalizationRecordsRes;
import cn.bxd.sip.his.model.dto.his.QueryPatientInHosInfo;
import cn.bxd.sip.his.model.dto.reservation.GetHospitalizationRecordsItemResDatas;
import cn.bxd.sip.his.model.dto.reservation.GetHospitalizationRecordsResDatas;
import cn.bxd.sip.his.model.dto.reservation.GetPatientInHosReqDatas;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 住院记录查询 2027 1.6 住院信息
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Lisheng
 * @version 1.0.0  2018-08-02
 */
@Slf4j
@Component
public class GetHospitalizationRecordsOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {

        //入参：掌上医院请求入参
        GetPatientInHosReqDatas getPatientInHosReqDatas = JSON.parseObject(reqMsg, GetPatientInHosReqDatas.class);

        //封装：向HIS发送请求
        String queryPatientInHosInfoStr;
        try {
            //ws客户端：1.6 住院信息

            Object hosWsClient5 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_IN_HOS, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();
            queryPatientInHosInfoStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.QUERY_PATIENT_IN_HOS_INFO, sysUserName, sysKey,
                    getPatientInHosReqDatas.getCardNo(), "", "551513", getPatientInHosReqDatas.getClinicalNo());
            log.debug(" 3017 住院记录查询 HIS返回：" + queryPatientInHosInfoStr);
        } catch (Exception e) {
            log.debug(" 3017 住院记录查询 HIS返回有误：" + e.getMessage());
            log.error("",e);
            return errMsgReturn("连接有误");
        }

        //判断：是否是json格式
        QueryHospitalizationRecordsRes queryHospitalizationRecordsRes;
        //解析：his返回成对象
        try {
            queryHospitalizationRecordsRes = JSON.parseObject(queryPatientInHosInfoStr, QueryHospitalizationRecordsRes.class);
        } catch (Exception e) {
            log.error("",e);
            return errMsgReturn(queryPatientInHosInfoStr);
        }

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryHospitalizationRecordsRes.getResultCode())) {
            return errMsgReturn(queryHospitalizationRecordsRes.getResultMsg());
        }


        List<QueryPatientInHosInfo> queryPatientInHosInfoList = queryHospitalizationRecordsRes.getInHosInfo();
        GetHospitalizationRecordsResDatas getHospitalizationRecordsResDatas = new GetHospitalizationRecordsResDatas();
        if (queryPatientInHosInfoList.size() > 0) {
            QueryPatientInHosInfo queryPatientInHosInfo = queryPatientInHosInfoList.get(0);
            GetHospitalizationRecordsItemResDatas getHospitalizationRecordsItemResDatas = new GetHospitalizationRecordsItemResDatas();
            getHospitalizationRecordsItemResDatas.setPatientName(queryPatientInHosInfo.getPatientName());
            getHospitalizationRecordsItemResDatas.setDeptName(queryPatientInHosInfo.getDepartmentName());
            getHospitalizationRecordsItemResDatas.setStatus(String.valueOf(Integer.valueOf(queryPatientInHosInfo.getInHosState()) - 1));
            getHospitalizationRecordsItemResDatas.setClinicalNo(queryPatientInHosInfo.getInHosNo());
            getHospitalizationRecordsItemResDatas.setEMPI(queryPatientInHosInfo.getInHosNo());
            getHospitalizationRecordsItemResDatas.setAdmissionTime(queryPatientInHosInfo.getInHosDate().replace("-", ""));
            getHospitalizationRecordsItemResDatas.setBedNo(queryPatientInHosInfo.getBedNo());
            getHospitalizationRecordsResDatas.setData(getHospitalizationRecordsItemResDatas);
            //getPatientInHosItemResDatasList.add(getHospitalizationRecordsItemResDatas);
            //getPatientInHosItemResDatas.setAccDate();
            //getHospitalizationRecordsItemResDatas.setAdmissionTime(StringUtils.replaceAll(inHosInfo.getInHosDate(), "-", ""));
            //getHospitalizationRecordsItemResDatas.setClinicType("2");
            //getHospitalizationRecordsItemResDatas.setBedNo(inHosInfo.getBedNo());
        }

        getHospitalizationRecordsResDatas.setOperCode(HisConvertConst.Operation.GET_CLINIC_RECORD);
        getHospitalizationRecordsResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getHospitalizationRecordsResDatas.setHosId(getPatientInHosReqDatas.getHosId());
        return new Gson().toJson(getHospitalizationRecordsResDatas);
    }

}