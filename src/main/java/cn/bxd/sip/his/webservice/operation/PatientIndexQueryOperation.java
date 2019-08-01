package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.var.GenderType;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryPatientInfoBySocialsecurityNORes;
import cn.bxd.sip.his.model.dto.reservation.GetPatientIndexQueryReqDates;
import cn.bxd.sip.his.model.dto.reservation.GetPatientIndexQueryResDates;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Description: 患者主索引查询接口 2015  身份证号码查看患者信息 1.1 基本信息
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@Slf4j
@Component
public class PatientIndexQueryOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
        //入参：掌上医院请求入参
        GetPatientIndexQueryReqDates getPatientIndexQueryReqDates = JSON.parseObject(reqMsg, GetPatientIndexQueryReqDates.class);

        //封装：向HIS发送请求
        String queryPatientInfoBySocialsecurityNStr = "";
        QueryPatientInfoBySocialsecurityNORes queryPatientInfoBySocialsecurityNORes;
        try {
            //ws客户端：1.5 预约挂号
            Object hosWsClient1 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_HIS_USER, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();
            //查询患者
            String clinicalCardNo = getPatientIndexQueryReqDates.getClinicalCardNo();
            if (getPatientIndexQueryReqDates.getIDNo() != null && !"".equals(getPatientIndexQueryReqDates.getIDNo())) {
                queryPatientInfoBySocialsecurityNStr = HisWSClient.invoke(hosWsClient1, HisFunNameConst.QUERY_PATIENT_INFO_BY_ID_NO,
                        sysUserName, sysKey, getPatientIndexQueryReqDates.getIDNo());
                //解析：his返回成对象
                queryPatientInfoBySocialsecurityNORes = JSON.parseObject(queryPatientInfoBySocialsecurityNStr, QueryPatientInfoBySocialsecurityNORes.class);

                if (clinicalCardNo != null && !"".equals(clinicalCardNo) &&
                        !HisConvertConst.HisCode.SUCCESS_CODE.equals(queryPatientInfoBySocialsecurityNORes.getResultCode())){ //当身份证查询没有返回信息时调用1.1.2.	社保、银行、就诊卡号查看患者信息接口，lisheng 2019/03/12
                    queryPatientInfoBySocialsecurityNStr = HisWSClient.invoke(hosWsClient1, HisFunNameConst.QUERY_PATIENT_INFO_BY_SOCIALSECURITY_NO,
                            sysUserName, sysKey, "", "", clinicalCardNo);
                }
            }else  if (clinicalCardNo != null && !"".equals(clinicalCardNo) ){ //当身份证为空时调用1.1.2.	社保、银行、就诊卡号查看患者信息接口，lisheng 2019/01/26
                queryPatientInfoBySocialsecurityNStr = HisWSClient.invoke(hosWsClient1, HisFunNameConst.QUERY_PATIENT_INFO_BY_SOCIALSECURITY_NO,
                        sysUserName, sysKey, "", "", clinicalCardNo);
            } else {
                log.debug(" 2015 身份证号查看患者信息 身份证和就诊卡号为空：");
                return errMsgReturn("身份证和社保卡号为空");
            }


            log.debug(" 2015 身份证号查看患者信息 返回：" + queryPatientInfoBySocialsecurityNStr);
        } catch (Exception e) {
            log.error("", e);
            log.debug(" 2015 身份证号查看患者信息 返回：" + e.getMessage());
            return errMsgReturn("连接有误");
        }

        //解析：his返回成对象
        queryPatientInfoBySocialsecurityNORes = JSON.parseObject(queryPatientInfoBySocialsecurityNStr, QueryPatientInfoBySocialsecurityNORes.class);

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryPatientInfoBySocialsecurityNORes.getResultCode())) {
            return errMsgReturn(queryPatientInfoBySocialsecurityNORes.getResultMsg());
        }

        //转换：进行参数转换
        String patientSexID = queryPatientInfoBySocialsecurityNORes.getPatientSex();
        String patientSexName;
        if ("0".equals(patientSexID)) {
            patientSexName = "女";
        } else if ("1".equals(patientSexID)) {
            patientSexName = "男";
        } else {
            patientSexName = "未知";
        }

        //由 医院返回的性别类型格式 转 成sip genderID的格式   Lisheng 2019/03/05
        String genderID;
        if ("0".equals(patientSexID)) {
            genderID = GenderType.WOMAN.getGenderId() + "";//女性
        } else if ("1".equals(patientSexID)) {
            genderID = GenderType.MAN.getGenderId() + "";//男性
        } else {
            genderID = GenderType.UNKNOW.getGenderId() + "";//未知的性别
        }

        GetPatientIndexQueryResDates getPatientIndexQueryResDates = new GetPatientIndexQueryResDates();
        getPatientIndexQueryResDates.setOperCode(HisConvertConst.Operation.GET_PATIENT_INDEX_REQ_CODE);
        getPatientIndexQueryResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getPatientIndexQueryResDates.setHosId(getPatientIndexQueryReqDates.getHosId());
        getPatientIndexQueryResDates.setPatientNo(queryPatientInfoBySocialsecurityNORes.getPatientNo());
        getPatientIndexQueryResDates.setPatientName(queryPatientInfoBySocialsecurityNORes.getPatientName());
        getPatientIndexQueryResDates.setIDNo(queryPatientInfoBySocialsecurityNORes.getPatientIdcardNo());
        getPatientIndexQueryResDates.setGenderID(genderID);
        getPatientIndexQueryResDates.setGenderName(patientSexName);
        getPatientIndexQueryResDates.setContactPhone(queryPatientInfoBySocialsecurityNORes.getPatientTelephone());
        getPatientIndexQueryResDates.setBirthDate(StringUtils.replaceAll(queryPatientInfoBySocialsecurityNORes.getPatientBirthday(), "-", ""));
        getPatientIndexQueryResDates.setClinicalCardNo(queryPatientInfoBySocialsecurityNORes.getMedicareNO());
        getPatientIndexQueryResDates.setEMPI(queryPatientInfoBySocialsecurityNORes.getPatientNo());
        TypeUtils.compatibleWithFieldName = true;
        return JSON.toJSONString(getPatientIndexQueryResDates);
    }

}