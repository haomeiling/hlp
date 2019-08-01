package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryHospitalizationRecordsRes;
import cn.bxd.sip.his.model.dto.his.QueryPatientInHosInfo;
import cn.bxd.sip.his.model.dto.reservation.GetPatientInHosItemResDatas;
import cn.bxd.sip.his.model.dto.reservation.GetPatientInHosReqDatas;
import cn.bxd.sip.his.model.dto.reservation.GetPatientInHosResDatas;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 住院记录查询 2027 1.6 住院信息
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@Slf4j
@Component
public class GetPatientInHosOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {

        //入参：掌上医院请求入参
        GetPatientInHosReqDatas getPatientInHosReqDatas = JSON.parseObject(reqMsg, GetPatientInHosReqDatas.class);

        //兼容clinicalNo为空的情况
        if (getPatientInHosReqDatas.getClinicalNo() == null) {
            getPatientInHosReqDatas.setClinicalNo("");
        }
        //兼容socialsecurityNO为空的情况
        if (getPatientInHosReqDatas.getSocialsecurityNO() == null) {
            getPatientInHosReqDatas.setSocialsecurityNO("");
        }

        //封装：向HIS发送请求
        String queryPatientInHosInfoStr;
        try {
            //ws客户端：1.6 住院信息

            Object hosWsClient5 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_IN_HOS, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();
            //由于贵港市中医医院住院记录查询出错，将最后一个参数 “其他卡号” 入参改为空字符串   lisheng 2019/7/15
           /* queryPatientInHosInfoStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.QUERY_PATIENT_IN_HOS_INFO, sysUserName, sysKey,
                    getPatientInHosReqDatas.getCardNo(), getPatientInHosReqDatas.getVisitCardNo(), getPatientInHosReqDatas.getSocialsecurityNO(), getPatientInHosReqDatas.getEmpi());*/
            queryPatientInHosInfoStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.QUERY_PATIENT_IN_HOS_INFO, sysUserName, sysKey,
                    getPatientInHosReqDatas.getCardNo(), getPatientInHosReqDatas.getVisitCardNo(), getPatientInHosReqDatas.getSocialsecurityNO(), "");

            log.debug(" 2027 住院记录查询 HIS返回：" + queryPatientInHosInfoStr);
        } catch (Exception e) {
            log.debug(" 2027 住院记录查询 HIS返回有误：" + e.getMessage());
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

        //医院返回来的名称不一致，宝信迪标准文档返回是InHosInfo，旧版本是InHosList
        List<QueryPatientInHosInfo> inHosInfoL = queryHospitalizationRecordsRes.getInHosList();
        if(inHosInfoL==null){
            inHosInfoL=queryHospitalizationRecordsRes.getInHosInfo();
        }
        QueryPatientInHosInfo inHosInfo = inHosInfoL.get(0);
        List<GetPatientInHosItemResDatas> getPatientInHosItemResDatasList = new ArrayList<>();
        GetPatientInHosItemResDatas getPatientInHosItemResDatas = new GetPatientInHosItemResDatas();
        getPatientInHosItemResDatasList.add(getPatientInHosItemResDatas);

        getPatientInHosItemResDatas.setPatientName(inHosInfo.getPatientName());
        getPatientInHosItemResDatas.setDeptName(inHosInfo.getDepartmentName());
        getPatientInHosItemResDatas.setStatus(String.valueOf(Integer.valueOf(inHosInfo.getInHosState()) - 1));
        getPatientInHosItemResDatas.setClinicalNo(inHosInfo.getInHosNo());

        getPatientInHosItemResDatas.setEMPI("");//修改值 由于上林县人民医院的住院记录查询接口不支持patientNo患者编号，而将其传入空字符串    2019/02/25   lisheng
//        getPatientInHosItemResDatas.setEMPI(getPatientInHosReqDatas.getEmpi());//原传值
        getPatientInHosItemResDatas.setAdmissionTime(StringUtils.replaceAll(inHosInfo.getInHosDate(), "-", ""));
        //出院时间 lisheng 2019/7/18
        getPatientInHosItemResDatas.setDischargeTime(StringUtils.replaceAll(inHosInfo.getLeaveHosDate(), "-", ""));
        getPatientInHosItemResDatas.setClinicType("2");
        getPatientInHosItemResDatas.setBedNo(inHosInfo.getBedNo());

        GetPatientInHosResDatas getPatientInHosResDatas = new GetPatientInHosResDatas();
        getPatientInHosResDatas.setOperCode(HisConvertConst.Operation.GET_PATIENT_IN_HOS_INFO_CODE);
        getPatientInHosResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getPatientInHosResDatas.setHosId(getPatientInHosReqDatas.getHosId());
        getPatientInHosResDatas.setData(getPatientInHosItemResDatasList);
        return JSON.toJSONString(getPatientInHosResDatas);
    }


}