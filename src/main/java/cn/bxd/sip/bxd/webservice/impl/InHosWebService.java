package cn.bxd.sip.bxd.webservice.impl;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.inhos.*;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.var.TerminalVar;
import cn.bxd.sip.bxd.webservice.IInHosWebService;
import cn.bxd.sip.bxd.webservice.common.WSTools;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.job.SynEvtServerJob;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import cn.bxd.sip.his.webservice.hisws.invoke.HISInterfaceQkbSoap;
import cn.bxd.sip.his.webservice.hisws.invoke2.ServiceSoap;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : cRyann
 * @create 2018-08-31
 **/
@Service
@Slf4j
@WebService(name = "InHos", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
public class InHosWebService implements IInHosWebService {
    private static final String PLATFROM_HOSPITAL_ID = "0";
    private static final String WS_CLIENT_KEY = HisConvertConst.WS_CLIENT_KEY_IN_HOS;

    @Override
    public QueryPatientInHosInfoRespond queryPatientInHosInfo(@NonNull String synUserName,
                                                              @NonNull String synKey,
                                                              @NonNull String terminalCode,
                                                              @NonNull String hospitalId,
                                                              @NonNull String cardNo,
                                                              String visitCardNo,
                                                              String socialsecurityNO,
                                                              String bankCardNumber) {
        QueryPatientInHosInfoRespond respond = new QueryPatientInHosInfoRespond();
        try {

            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //调用HIS服务
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            log.debug("cardNo==" + cardNo);
            log.debug("visitCardNo==" + visitCardNo);
            log.debug("socialsecurityNO==" + socialsecurityNO);
            log.debug("bankCardNumber==" + bankCardNumber);
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_PATIENT_IN_HOS_INFO, synUserName, synKey, cardNo, visitCardNo, socialsecurityNO, bankCardNumber);

            //直接转换，可以解决节点直接做字符返回
            respond = JSON.parseObject(res, QueryPatientInHosInfoRespond.class);
            return respond;


            // return respond;
        } catch (Exception e) {
            log.error("--------++++", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public QueryToInHosRespond queryToInHos(@NonNull String synUserName,
                                            @NonNull String synKey,
                                            @NonNull String cardNo,
                                            @NonNull String terminalCode,
                                            @NonNull String hospitalId,
                                            String visitCardNo,
                                            String socialsecurityNO,
                                            String bankCardNumber) {
        QueryToInHosRespond respond = new QueryToInHosRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            String res;
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            if (clieat instanceof HISInterfaceQkbSoap) {
                HISInterfaceQkbSoap hisInterfaceQkbSoap = (HISInterfaceQkbSoap) clieat;
                res = hisInterfaceQkbSoap.queryPatientInHosInfo(synUserName, synKey, cardNo, visitCardNo, socialsecurityNO, bankCardNumber);
            } else {
                ServiceSoap serviceSoap = (ServiceSoap) clieat;
                res = serviceSoap.queryPatientInHosInfo(synUserName, synKey, cardNo, visitCardNo, socialsecurityNO, bankCardNumber);
            }
            respond = JsonTools.json2Bean(res, QueryToInHosRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public ApplyToInHosRespond applyToInHos(@NonNull String synUserName,
                                            @NonNull String synKey,
                                            @NonNull String terminalCode,
                                            @NonNull String hospitalId,
                                            @NonNull String applyInHosInfo) {
        ApplyToInHosRespond respond = new ApplyToInHosRespond();
        cn.bxd.sip.bxd.model.request.inhos.ApplyInHosInfo inHosInfo = JSON.parseObject(applyInHosInfo, cn.bxd.sip.bxd.model.request.inhos.ApplyInHosInfo.class);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            // 组装参数
            Map<String, Object> map = new HashMap<>();
            map.put("synUserName", synUserName);//用户名
            map.put("synKey", synKey);//效验码
            map.put("appointmentNo", inHosInfo.getAppointmentNo());//预约编号
//            ApplyInHosInfo applyInHosInfoBean = new ApplyInHosInfo();
//            applyInHosInfoBean.setInHosNo(inHosInfo.getInHosNo());
//            applyInHosInfoBean.setBedNo(inHosInfo.getBedNo());
//            applyInHosInfoBean.setVisitCardNo(inHosInfo.getVisitCardNo());
//            applyInHosInfoBean.setPatientNo(inHosInfo.getPatientNo());
//            applyInHosInfoBean.setDepartmentorganI(inHosInfo.getDepartmentorganId());
//            applyInHosInfoBean.setDepartmentName(inHosInfo.getDepartmentName());
//            applyInHosInfoBean.setMedicareType(inHosInfo.getMedicareType());
//            applyInHosInfoBean.setPayType(inHosInfo.getPayType());
//            applyInHosInfoBean.setPayRecord(inHosInfo.getPayRecord());
//            applyInHosInfoBean.setPayMoney(inHosInfo.getPayMoney());
//            applyInHosInfoBean.setSocialsecurityNO(inHosInfo.getSocialsecurityNO());
//            applyInHosInfoBean.setMedicareInfo(inHosInfo.getMedicareInfo());
//            applyInHosInfoBean.setPayReturn(inHosInfo.getPayReturn());
//            applyInHosInfoBean.setExtend(inHosInfo.getExtend());
            map.put("applyInHosInfo", JSON.toJSONString(inHosInfo));//Json字符串

            Object res = WSTools.invoke(clieat, "applyToInHos", map);
            respond = JSON.parseObject(String.valueOf(res), ApplyToInHosRespond.class);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_FAIL);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public QueryArrearsRespond queryArrears(@NonNull String synUserName,
                                            @NonNull String synKey,
                                            @NonNull String terminalCode,
                                            @NonNull String hospitalId,
                                            @NonNull String inHosNo,
                                            @NonNull String bedNo,
                                            @NonNull String departmentorganId,
                                            @NonNull String extend) {
        QueryArrearsRespond respond = new QueryArrearsRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            // 组装 HIS 入参
            Map<String, Object> map = new HashMap<>();
            map.put("synUserName", synUserName);
            map.put("synKey", synKey);
            map.put("inHosNo", inHosNo);
            map.put("bedNo", bedNo);
            map.put("departmentorganId", departmentorganId);
            Object res = WSTools.invoke(clieat, "queryArrears", map);

            respond = JsonTools.json2Bean(String.valueOf(res), QueryArrearsRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public InpatientPaymentRespond inpatientPayment(@NonNull String synUserName,
                                                    @NonNull String synKey,
                                                    @NonNull String terminalCode,
                                                    @NonNull String hospitalId,
                                                    @NonNull String inHosNo,
                                                    @NonNull String bedNo,
                                                    @NonNull Integer payType,
                                                    @NonNull String payRecord,
                                                    @NonNull BigDecimal payMoney,
                                                    String payReturn,
                                                    String patientNo,
                                                    String patientName,
                                                    @NonNull String extend) {
        InpatientPaymentRespond respond = new InpatientPaymentRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            String res = HisWSClient.invoke(client, HisFunNameConst.INPATIENT_PAYMENT, synUserName, synKey, inHosNo, bedNo, String.valueOf(payType), payRecord, String.valueOf(payMoney), payReturn, terminalCode);
            respond = JsonTools.json2Bean(res, InpatientPaymentRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public PayDetailRespond payDetail(@NonNull String synUserName,
                                      @NonNull String synKey,
                                      @NonNull String terminalCode,
                                      @NonNull String hospitalId,
                                      @NonNull String patientNo,
                                      @NonNull String inHosNo,
                                      @NonNull String startDate,
                                      @NonNull String endDate) {
        PayDetailRespond respond = new PayDetailRespond();
        try {
            //从内存获取配置
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //从HIS获取数据
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            String res = HisWSClient.invoke(client, HisFunNameConst.PAY_DETAIL, synUserName, synKey, patientNo, inHosNo, startDate, endDate);
            log.debug("----payDetail----return--" + res);

            //解析返回
            respond = new Gson().fromJson(res, PayDetailRespond.class);

            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.FAIL_CODE);
            respond.setResultMsg("系统错误" + e.getMessage());
            return respond;
        }
    }

    /**
     * 已联调 20181207
     *
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param patientNo    患者编号
     * @param inHosNo      住院号
     * @param startDate    开始时间（yyyy-MM-dd）
     * @param endDate      结束时间（yyyy-MM-dd）
     * @return
     */
    @Override
    public InHosDetailRespond inHosDetail(String synUserName,
                                          String synKey,
                                          String terminalCode,
                                          String hospitalId,
                                          String patientNo,
                                          String inHosNo,
                                          String startDate,
                                          String endDate) {
        InHosDetailRespond respond = new InHosDetailRespond();
        try {

            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //从HIS获取信息
            Object client = connectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            String res = HisWSClient.invoke(client, HisFunNameConst.IN_HOS_DETAIL, synUserName, synKey, patientNo, inHosNo, startDate, endDate);

            //解析返回
            respond = new Gson().fromJson(res, InHosDetailRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.FAIL_CODE);
            respond.setResultMsg("系统异常===" + e.getMessage());
            return respond;
        }
    }

    @Override
    public InHosMedicalInfoRespond inHosMedicalInfo(String synUserName,
                                                    String synKey,
                                                    String terminalCode,
                                                    String hospitalId,
                                                    String inHosNo) {
        InHosMedicalInfoRespond respond = new InHosMedicalInfoRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            // 组装 HIS 入参
            Map<String, Object> map = new HashMap<>();
            map.put("synUserName", synUserName);
            map.put("synKey", synKey);
            map.put("inHosNo", inHosNo);
            Object res = WSTools.invoke(clieat, "inHosMedicalInfo", map);

            respond = JsonTools.json2Bean(String.valueOf(res), InHosMedicalInfoRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public ApplyLeaveHosRespond applyLeaveHos(String synUserName,
                                              String synKey,
                                              String inHosNo,
                                              String settleType,
                                              String medicalInfo,
                                              String medicareType,
                                              String extend) {
        // TODO 对医院接口未实现 确实《hospitalId》关键参数
        return null;
    }

    @Override
    public QueryLeaveHosDetailRespond queryLeaveHosDetail(String synUserName,
                                                          String synKey,
                                                          String terminalCode,
                                                          String hospitalId,
                                                          String inHosNo) {
        QueryLeaveHosDetailRespond respond = new QueryLeaveHosDetailRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            // 组装 HIS 入参
            Map<String, Object> map = new HashMap<>();
            map.put("synUserName", synUserName);
            map.put("synKey", synKey);
            map.put("inHosNo", inHosNo);
            Object res = WSTools.invoke(clieat, "queryLeaveHosDetail", map);

            respond = JsonTools.json2Bean(String.valueOf(res), QueryLeaveHosDetailRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public LeaveHosPayRespond leaveHosPay(String synUserName,
                                          String synKey,
                                          String terminalCode,
                                          String hospitalId,
                                          String inHosNo,
                                          String payType,
                                          String payRecord,
                                          String payMoney,
                                          String medicareMoney,
                                          String overMoney,
                                          String socialsecurityNO,
                                          String medicareRecord,
                                          String medicareType,
                                          String medicareReturn,
                                          String bankReturn,
                                          String patientNo,
                                          String patientName,
                                          String userNo,
                                          String extend) {
        LeaveHosPayRespond respond = new LeaveHosPayRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            // 组装 HIS 入参
            Map<String, Object> map = new HashMap<>();


            map.put("synUserName", synUserName);
            map.put("synKey", synKey);
            map.put("inHosNo", inHosNo);
            map.put("payType", payType);
            map.put("payRecord", payRecord);
            map.put("payMoney", payMoney);
            map.put("medicareMoney", medicareMoney);
            map.put("overMoney", overMoney);
            map.put("socialsecurityNO", socialsecurityNO);
            map.put("medicareRecord", medicareRecord);
            map.put("medicareType", medicareType);
            map.put("medicareReturn", medicareReturn);
            map.put("bankReturn", bankReturn);
            map.put("terminalCode", terminalCode);
            map.put("userNo", userNo);
            map.put("extend", extend);
            Object res = WSTools.invoke(clieat, "leaveHosPay", map);

            respond = JSON.parseObject(String.valueOf(res), LeaveHosPayRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public BaseRespond updateInHosRecordStatus(String synUserName, String synKey, String terminalCode,
                                               String hospitalId, String inHosNo, String recordDate, String recordType, String extend) {
        BaseRespond respond = new BaseRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            String res = HisWSClient.invoke(client, HisFunNameConst.UPDATE_INHOS_RECORD_STATUS, synUserName, synKey, inHosNo, recordDate, recordType, extend);

            //解析返回
            respond = new Gson().fromJson(res, BaseRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

}
