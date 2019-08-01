package cn.bxd.sip.bxd.webservice.impl.base;

import cn.bxd.sip.bxd.dao.*;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.SiWebServiceRespond.SiWebServiceRespond;
import cn.bxd.sip.bxd.model.respond.common.InsuredPayInfoRespond;
import cn.bxd.sip.bxd.model.respond.hisuser.*;
import cn.bxd.sip.bxd.service.impl.SeqService;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.var.SIVar;
import cn.bxd.sip.bxd.var.TerminalVar;
import cn.bxd.sip.bxd.webservice.impl.PayWebService;
import cn.bxd.sip.bxd.webservice.impl.SiWebService;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import cn.bxd.sip.si.model.dto.user.UserInfoReqData;
import cn.bxd.sip.si.service.SiService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.Map;

import static cn.bxd.sip.si.utils.XStreamUtil.generateXML;

/**
 * Description:
 * User: LiSheng
 * Date: 2018-10-12
 * Time: 14:09
 */
@Slf4j
public class HisUserCommon extends UserAssistService {
    //平台医院ID
    private static final String PLATFROM_HOSPITAL_ID = "0";
    private static final String WS_CLIENT_KEY = HisConvertConst.WS_CLIENT_KEY_HIS_USER;
    @Resource
    SimpleQueryDao simpleQueryDao;
    @Autowired
    SiService siService;
    @Autowired
    HospitalSiConfigMapper hospitalSiConfigMapper;
    @Autowired
    SiWebService siWebService;
    @Autowired
    SeqService seqService;
    @Autowired
    SiRealUserMapper siRealUserMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    SiUserMapper siUserMapper;
    @Autowired
    SiFamilyMembersMapper siFamilyMembersMapper;

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param cardNo       身份证号码
     * @author cRyann
     * @Description 1.1.1    身份证号码查看患者信息
     **/
    public HiUserModel queryPatientInfoByIdNO(@NonNull String synUserName,
                                              @NonNull String synKey,
                                              @NonNull String terminalCode,
                                              @NonNull String hospitalId,
                                              String cardNo) {
        HiUserModel respond = new HiUserModel();
        respond.setResultCode(TerminalVar.FAIL_CODE_01);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE_01);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_PATIENT_INFO_BY_ID_NO, synUserName, synKey, cardNo);

            respond = JSON.parseObject(res, HiUserModel.class);

            //检测本地数据
            String telephone = respond.getPatientTelephone();
            if (telephone != null && !telephone.equals("")) {
                Integer userId = saveUserAndPerson(Integer.parseInt(hospitalId), telephone, respond.getPatientName(), respond.getPatientIdcardNo(),
                        respond.getMedicareNO(), respond.getPatientNo(), respond.getAddress());
                respond.setUserNo(String.valueOf(userId));
            }

            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.FAIL_CODE_01);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param socialsecurityNO 社保卡号
     * @param medicareNO       就诊卡号
     * @param bankCardNo       银行卡号
     * @author cRyann
     * @Description 1.1.2    社保、银行、就诊卡号查看患者信息
     **/
    public HiUserModel queryPatientInfoBySocialsecurityNO(@NonNull String synUserName,
                                                          @NonNull String synKey,
                                                          @NonNull String terminalCode,
                                                          @NonNull String hospitalId,
                                                          String socialsecurityNO,
                                                          String medicareNO,
                                                          String bankCardNo) {
        HiUserModel respond = new HiUserModel();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(clieat, HisFunNameConst.QUERY_PATIENT_INFO_BY_SOCIALSECURITY_NO, synUserName, synKey, socialsecurityNO, bankCardNo, medicareNO);
            respond = JsonTools.json2Bean(res, HiUserModel.class);
            if (!respond.getResultCode().equals(TerminalVar.SUCCESS_CODE)) {
                return respond;
            }

            //检测本地数据
            String telephone = respond.getPatientTelephone();
            if (telephone != null && !telephone.equals("")) {
                Integer userId = saveUserAndPerson(Integer.parseInt(hospitalId), telephone, respond.getPatientName(), respond.getPatientIdcardNo(), respond.getMedicareNO(), respond.getPatientNo(), respond.getAddress());
                respond.setUserNo(String.valueOf(userId));
            }
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName  用户名
     * @param synKey       同步信息
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param phone        手机号码
     * @author cRyann
     * @Description 1.1.3    手机号码获取患者信息
     */
    public HiUserModel queryPatientInfoByPhone(@NonNull String synUserName,
                                               @NonNull String synKey,
                                               @NonNull String terminalCode,
                                               @NonNull String hospitalId,
                                               String phone) {
        HiUserModel respond = new HiUserModel();
        respond.setResultCode("");
        respond.setResultMsg("");
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //
            String codeNo = simpleQueryDao.getUserCardNoByPhone(phone);
            codeNo = codeNo == null ? "" : codeNo;
            //获取wsClient
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(clieat, HisFunNameConst.QUERY_PATIENT_INFO_BY_SOCIALSECURITY_NO, synUserName, synKey, terminalCode, hospitalId, phone);
            respond = JsonTools.json2Bean(res, HiUserModel.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName  用户名
     * @param synKey       同步信息
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param hisUser      手机号码
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.4    患者档案建立
     */
    public HiUserModel doCreatCardInfo(@NonNull String synUserName,
                                       @NonNull String synKey,
                                       @NonNull String terminalCode,
                                       @NonNull String hospitalId,
                                       String hisUser) {
        HiUserModel respond = new HiUserModel();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(client, HisFunNameConst.DO_CREAT_CARD_INFO, synUserName, synKey, hisUser);
            //如果不成功，直接返回
            respond = JsonTools.json2Bean(res, HiUserModel.class);
            //判断是否成功，如果不成功，则返回
            if (!respond.getResultCode().equals(TerminalVar.SUCCESS_CODE)) {
                return respond;
            }

            //检测本地数据
            String telephone = respond.getPatientTelephone();
            if (telephone != null && !telephone.equals("")) {
                Integer userId = saveUserAndPerson(Integer.parseInt(hospitalId), telephone, respond.getPatientName(), respond.getPatientIdcardNo(),
                        respond.getMedicareNO(), respond.getPatientNo(), respond.getAddress());
                respond.setUserNo(String.valueOf(userId));
            }

            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.FAIL_CODE_01);
            respond.setResultMsg("---系统错误---" + e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName  用户名
     * @param synKey       同步信息
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param reportNos    报告编号     * （注多个可以逗号分开如 112，2222）
     * @param extend       扩展字段Json值，如：     * {“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.5    查询检查,检验数据打印状态状态
     */
    public QueryExaminationRecordStatusRespond queryExaminationRecordStatus(@NonNull String synUserName,
                                                                            @NonNull String synKey,
                                                                            @NonNull String terminalCode,
                                                                            @NonNull String hospitalId,
                                                                            String reportNos,
                                                                            String extend) {
        QueryExaminationRecordStatusRespond respond = new QueryExaminationRecordStatusRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE_01);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(clieat, HisFunNameConst.QUERY_EXAMINATION_RECORD_STATUS, synUserName, synKey, terminalCode, hospitalId, reportNos, extend);

            respond = JsonTools.json2Bean(res, QueryExaminationRecordStatusRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName  用户名
     * @param synKey       同步信息
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param reportNos    报告编号     * （注多个可以逗号分开如 112，2222）
     * @param extend       扩展字段Json值，如：     * {“key1”：”value1”，”key2”：”value2”}
     * @param reportType   报告类型1：检验，2：B超，3：放射影像，4：内镜，5：病理，6：心电图
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.6    更新检查,检验数据打印状态
     */
    public BaseRespond updateExaminationRecordStatus(@NonNull String synUserName,
                                                     @NonNull String synKey,
                                                     @NonNull String terminalCode,
                                                     @NonNull String hospitalId,
                                                     String reportNos,
                                                     String reportType,
                                                     String extend) {
        BaseRespond respond = new BaseRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(clieat, HisFunNameConst.UPDATE_EXAMINATION_RECORD_STATUS, synUserName, synKey, reportNos,reportType);

            respond = JSONObject.parseObject(res, BaseRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName  用户名
     * @param synKey       同步信息
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param patientNo    患者编号
     * @param startDate    查询开始时间
     * @param endDate      查询结束时间
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.7    获取检查,检验数据
     */
    public QueryExaminationRecordListRespond queryExaminationRecordList(@NonNull String synUserName,
                                                                        @NonNull String synKey,
                                                                        @NonNull String terminalCode,
                                                                        @NonNull String hospitalId,
                                                                        String patientNo,
                                                                        String startDate,
                                                                        String endDate) {
        QueryExaminationRecordListRespond respond = new QueryExaminationRecordListRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_EXAMINATION_RECORD_LIST, synUserName, synKey, patientNo, startDate, endDate);
            respond = JSON.parseObject(res, QueryExaminationRecordListRespond.class);


            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }


    /**
     * @param synUserName  用户名
     * @param synKey       同步信息
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param patientNo    患者编号
     * @param startDate    查询开始时间
     * @param endDate      查询结束时间
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.8    病历记录查询
     */
    public QueryHisUserRecordListRespond queryHisUserRecordList(@NonNull String synUserName,
                                                                @NonNull String synKey,
                                                                @NonNull String terminalCode,
                                                                @NonNull String hospitalId,
                                                                String patientNo,
                                                                String startDate,
                                                                String endDate) {
        QueryHisUserRecordListRespond respond = new QueryHisUserRecordListRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(clieat, HisFunNameConst.QUERY_HIS_USER_RECORD_LIST, synUserName, synKey, terminalCode, hospitalId, patientNo, startDate, endDate);

            respond = JSON.parseObject(String.valueOf(res), QueryHisUserRecordListRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName      用户名
     * @param synKey           同步信息
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param cardNo           身份证号码
     * @param socialsecurityNO 社保号
     * @param bankCardNumber   银行卡号
     * @param visitCardNo      就诊卡号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.9    更新患者信息
     */
    public HiUserModel updatePatientInfo(@NonNull String synUserName,
                                         @NonNull String synKey,
                                         @NonNull String terminalCode,
                                         @NonNull String hospitalId,
                                         String cardNo,
                                         String socialsecurityNO,
                                         String bankCardNumber,
                                         String visitCardNo) {
        HiUserModel respond = new HiUserModel();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(clieat, HisFunNameConst.UPDATE_PATIENT_INFO, synUserName, synKey, terminalCode, hospitalId, cardNo, socialsecurityNO, bankCardNumber, visitCardNo);

            respond = JSON.parseObject(res, HiUserModel.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName      用户名
     * @param synKey           同步信息
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param cardNo           身份证号码
     * @param socialsecurityNO 社保号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.10    基本医疗保险参保缴费情况
     */
    public InsuredPayInfoRespond insuredPayInfo(@NonNull String synUserName,
                                                @NonNull String synKey,
                                                @NonNull String terminalCode,
                                                @NonNull String hospitalId,
                                                String cardNo,
                                                String socialsecurityNO) {
        InsuredPayInfoRespond respond = new InsuredPayInfoRespond();

        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(clieat, HisFunNameConst.QUERY_PATIENT_INFO_BY_SOCIALSECURITY_NO, synUserName, synKey, terminalCode, hospitalId, cardNo, socialsecurityNO);

            Map<String, String> hisUserObj = (Map<String, String>) JSON.parse(res);
            // 获取缴费信息 inputData
            // CardInfo  :  社保卡号||||固定|||身份证ID|姓名|NEW|
            String cardInfo = socialsecurityNO + "||||450900|||"
                    + hisUserObj.get("patientIdcardNo")
                    + "|"
                    + hisUserObj.get("patientName") + "|NEW|";
            HospitalSiConfig hospitalSiConfig =
                    hospitalSiConfigMapper.selectTRhipHospitalSiConfig(hospitalId, TerminalVar.medicare_Type_Outpatient);
            // TODO 类型待确认
            // 读卡信息
            PayInfo payInfo = siService.readCardInfo(hospitalSiConfig, cardInfo);
            UserInfoReqData userInfoReqData = new UserInfoReqData();
            userInfoReqData.setUserNo(payInfo.getUserNo());
            String generateXML = generateXML(SIVar.MsgType.OPERATION_OF_GET_USER_INFO, userInfoReqData);
            String sheBaoOut = siWebService.RecvMsg(generateXML);
            SiWebServiceRespond res110101 = JSON.parseObject(sheBaoOut, SiWebServiceRespond.class);
            generateXML = generateXML(SIVar.MsgType.OPERATION_OF_GET_INSURANCE_TYPE, userInfoReqData);
            sheBaoOut = siWebService.RecvMsg(generateXML);
            SiWebServiceRespond res110102 = JSON.parseObject(sheBaoOut, SiWebServiceRespond.class);

            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            respond.setInsuredTime(res110102.getSIMSG().getBODY().getString("StartTime"));
            respond.setInsuredState(res110102.getSIMSG().getBODY().getString("InsuredStatus"));
            respond.setPersonCategory(res110101.getSIMSG().getBODY().getString("InsCategory"));
            respond.setPersonIdentity("");
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.FAIL_CODE);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName      用户名
     * @param synKey           同步信息
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param cardNo           身份证号码
     * @param socialsecurityNO 社保号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.11    诊特殊慢性病待遇情况
     */
    public TreatmentInfoRespond treatmentInfo(@NonNull String synUserName,
                                              @NonNull String synKey,
                                              @NonNull String terminalCode,
                                              @NonNull String hospitalId,
                                              String cardNo,
                                              String socialsecurityNO) {
        // TODO 社保接口参数未定义，无法获取数据
        TreatmentInfoRespond respond = new TreatmentInfoRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            String res = "";
            respond = JSON.parseObject("", TreatmentInfoRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName      用户名
     * @param synKey           同步信息
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param cardNo           身份证号码
     * @param socialsecurityNO 社保号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.12    医疗费用结算情况
     */
    public MedicalPayInfoRespond medicalPayInfo(@NonNull String synUserName,
                                                @NonNull String synKey,
                                                @NonNull String terminalCode,
                                                @NonNull String hospitalId,
                                                String cardNo,
                                                String socialsecurityNO) {
        // TODO 社保接口参数未定义，无法获取数据
        MedicalPayInfoRespond respond = new MedicalPayInfoRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            String res = "";
            respond = JSON.parseObject("", MedicalPayInfoRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName      用户名
     * @param synKey           同步信息
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param cardNo           身份证号码
     * @param socialsecurityNO 社保号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.13    异地就医备案情况
     */
    public RemoteInfoResond remoteInfo(@NonNull String synUserName,
                                       @NonNull String synKey,
                                       @NonNull String terminalCode,
                                       @NonNull String hospitalId,
                                       String cardNo,
                                       String socialsecurityNO) {
        // TODO 社保接口参数未定义，无法获取数据
        RemoteInfoResond respond = new RemoteInfoResond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            String res = "";
            respond = JSON.parseObject("", RemoteInfoResond.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }


}
