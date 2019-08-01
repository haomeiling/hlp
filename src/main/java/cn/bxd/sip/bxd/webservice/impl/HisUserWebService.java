package cn.bxd.sip.bxd.webservice.impl;

import cn.bxd.sip.bxd.dao.*;
import cn.bxd.sip.bxd.model.SmsSend;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.*;
import cn.bxd.sip.bxd.model.respond.hisuser.*;
import cn.bxd.sip.bxd.service.impl.SeqService;
import cn.bxd.sip.bxd.util.CodeUtils;
import cn.bxd.sip.bxd.util.HttpUtils;
import cn.bxd.sip.bxd.var.TerminalVar;
import cn.bxd.sip.bxd.webservice.IHisUserWebService;
import cn.bxd.sip.bxd.webservice.impl.base.HisUserCommon;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import cn.bxd.sip.si.service.SiService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.lang.Exception;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * author : cRyann
 *
 * @create 2018-08-29
 **/
@Service
@Slf4j
@WebService(targetNamespace = "http://webservice.bxd.sip.bxd.cn/", endpointInterface = "cn.bxd.sip.bxd.webservice.IHisUserWebService")
public class HisUserWebService extends HisUserCommon implements IHisUserWebService {

    //平台医院ID
    private static final String PLATFROM_HOSPITAL_ID = "0";
    private static final String WS_CLIENT_KEY = HisConvertConst.WS_CLIENT_KEY_HIS_USER;

    @Resource
    SimpleQueryDao simpleQueryDao;
    @Autowired
    SiService siService;
    @Autowired
    HospitalSiConfigMapper tRhipHospitalSiConfigMapper;
    @Autowired
    SiWebService siWebService;
    @Autowired
    SeqService seqService;
    @Autowired
    SiRealUserMapper tSiRealUserMapper;
    @Autowired
    UserMapper tRhipUserMapper;
    @Autowired
    SiUserMapper tSiUserMapper;
    @Autowired
    SiFamilyMembersMapper tSiFamilyMembersMapper;






    /**
     * @param
     * @author cRyann
     * @Description 1.2    实名认证
     **/

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param mobileNo     手机号码
     * @Description 1.2.1    获取验证码
     * returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TGetSMSCodeRespond
     */
    @Override
    public GetSMSCodeRespond getSMSCode(String synUserName,
                                        String synKey,
                                        String terminalCode,
                                        String hospitalId,
                                        String mobileNo) {
        GetSMSCodeRespond respond = new GetSMSCodeRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            SmsSend smsSend = new SmsSend();
            smsSend.setUserName(TerminalVar.MESSAGE_USERNAME);
            smsSend.setUserPwd(TerminalVar.MESSAGE_USERPWD);
            smsSend.setContactPhone(mobileNo);
            smsSend.setContentSms(CodeUtils.getVerification(TerminalVar.MESSAGE_DIGIT));
            smsSend.setMsgId(String.valueOf(System.currentTimeMillis()));
            smsSend.setTemplateCode(TerminalVar.MESSAGE_TEMPLATE_CODE);
            String result = HttpUtils.sendPostRequest(TerminalVar.MESSAGE_URL, JSON.toJSONString(smsSend));
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(result);

            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName         用户名
     * @param synKey              效验码
     * @param terminalCode        终端编号
     * @param hospitalId          医院ID
     * @param mobileNo            手机号码
     * @param password            密码
     * @param socialsecurityNO    医保号
     * @Description 1.2.2    已注册判断手机号码和社保卡是否绑定
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TRegisteredConfirmsExistRespond
     */
    @Override
    public RegisteredConfirmsExistRespond registeredConfirmsExist(String synUserName,
                                                                  String synKey,
                                                                  String terminalCode,
                                                                  String hospitalId,
                                                                  String mobileNo,
                                                                  String password,
                                                                  String socialsecurityNO) {
        RegisteredConfirmsExistRespond respond = new RegisteredConfirmsExistRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);
        try {
            // 判断是否注册 未经注册返回信息
            String loginMobile = simpleQueryDao.isRegistered(mobileNo);
            if (loginMobile == null) {
                respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
                return respond;
            }
            // REAL_USER表存在数据即表示绑定
            RealUser realUser = simpleQueryDao.getRealUser(socialsecurityNO, mobileNo);
            if (realUser != null) {
                respond.setResultCode(TerminalVar.SUCCESS_CODE);
                respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            }
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName         用户名
     * @param synKey              效验码
     * @param terminalCode        终端编号
     * @param hospitalId          医院ID
     * @param mobileNo            手机号码
     * @param password            密码
     * @param socialsecurityNO    医保号
     * @Description 1.2.3    未注册判断手机号码和社保卡是否绑定
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TRegisterConfirmsExistRespond
     */
    @Override
    public RegisterConfirmsExistRespond registerConfirmsExist(String synUserName,
                                                              String synKey,
                                                              String terminalCode,
                                                              String hospitalId,
                                                              String mobileNo,
                                                              String password,
                                                              String socialsecurityNO) {
        RegisterConfirmsExistRespond respond = new RegisterConfirmsExistRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);
        try {
            // 判断是否注册 *** 已经注册返回信息   *** 没注册继续执行
            String loginMobile = simpleQueryDao.isRegistered(mobileNo);
            if (loginMobile != null) {
                respond.setResultMsg(TerminalVar.REGISTERED_STATUS);
                return respond;
            }
            // REAL_USER表存在数据即表示绑定
            RealUser realUser = simpleQueryDao.getRealUser(socialsecurityNO, mobileNo);
            if (realUser != null) {
                respond.setResultCode(TerminalVar.SUCCESS_CODE);
                respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            }
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName       用户名
     * @param synKey            效验码
     * @param terminalCode      终端编号
     * @param hospitalId        医院ID
     * @param mobileNo          手机号码
     * @param password          密码
     * @param socialsecurityNO  医保号
     * @param patientName       患者姓名
     * @param cardId            身份证
     * @param userNo            人员编号
     * @param medicareType      0，非医保；1,市医保；
     * @param cardinfo          卡信息（如读卡交易入参）  例如：V00113641|2000062142|0.0
     * @param overallArea       统筹区号
     * @param bankCardInfo      银行卡信息
     * @Description 1.2.4    已注册用户认证
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TCertificationUserRespond
     */
    @Override
    public CertificationUserRespond certificationUser(String synUserName,
                                                      String synKey,
                                                      String terminalCode,
                                                      String hospitalId,
                                                      String mobileNo,
                                                      String password,
                                                      String socialsecurityNO,
                                                      String patientName,
                                                      String cardId,
                                                      String userNo,
                                                      String medicareType,
                                                      String cardinfo,
                                                      String overallArea,
                                                      String bankCardInfo) {
        // 直接认证
        CertificationUserRespond respond = new CertificationUserRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);
        try {
            SiRealUser tSiRealUser = new SiRealUser();
            Long newId = seqService.getKeyId("T_SI_REAL_USER");
            tSiRealUser.setUserId(newId);
            tSiRealUser.setMobileNo(mobileNo);
            tSiRealUser.setVisitcardNum(socialsecurityNO);
            tSiRealUser.setVisitcardIsBing(TerminalVar.REAL_USER_BING);
//        tSiRealUser.setFinancialAccount(new Object()); 金融账号
//        tSiRealUser.setFinancialIsBing((short)0); 绑定金融账号
//        tSiRealUser.setRealPicUrl(new Object()); 认证照片
            tSiRealUser.setRealTime(new Date());
//        tSiRealUser.setPayPassword(new Object()); 支付密码
            tSiRealUser.setUserNo(userNo);
            tSiRealUser.setMedicareType(Short.valueOf(medicareType));
            tSiRealUser.setCardinfo(cardinfo);
            tSiRealUser.setOverallArea(overallArea);
            tSiRealUser.setPattern(TerminalVar.REAL_USER_TERMINAL);
            tSiRealUser.setPatientName(patientName);
            tSiRealUser.setCardId(cardId);
            tSiRealUserMapper.insert(tSiRealUser);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName         用户名
     * @param synKey              效验码
     * @param terminalCode        终端编号
     * @param hospitalId          医院ID
     * @param socialsecurityNO    医保号
     * @param hospitalNO          医院编号
     * @param operatorNo          操作人员编号
     * @param cycleNo             周期编号
     * @param dynamic             动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @param medicareType        0，非医保；1,市医保；
     * @param cardinfo            卡信息（如读卡交易入参）
     * @Description 1.2.5    获取医保个人编号
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TGetUserNoRespond
     */
    @Override
    public GetUserNoRespond getUserNo(String synUserName,
                                      String synKey,
                                      String terminalCode,
                                      String hospitalId,
                                      String socialsecurityNO,
                                      String hospitalNO,
                                      String operatorNo,
                                      String cycleNo,
                                      String dynamic,
                                      String medicareType,
                                      String cardinfo) {
        GetUserNoRespond respond = new GetUserNoRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
           /* // 获取病人信息
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            Map<String, Object> map = new CaseInsensitiveMap();
            map.put("synUserName", synUserName);
            map.put("synKey", synKey);
            map.put("socialsecurityNO", socialsecurityNO);
            map.put("bankCardNumber", "");
            map.put("visitCardNo", "");
            Object resObject = WSTools.invoke(clieat, "queryPatientInfoBySocialsecurityNO", map);*/

            //获取wsClient
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(clieat,HisFunNameConst.QUERY_PATIENT_INFO_BY_SOCIALSECURITY_NO,synUserName, synKey, terminalCode, hospitalId, socialsecurityNO, hospitalNO, operatorNo, cycleNo, dynamic, medicareType, cardinfo);

            Map<String, String> hisUserObj = (Map<String, String>) JSON.parse(res.toString());
            // 获取缴费信息 inputData
            // CardInfo  :  社保卡号||||固定|||身份证ID|姓名|NEW|
            String cardInfo = socialsecurityNO + "||||450900|||"
                    + hisUserObj.get("patientIdcardNo")
                    + "|"
                    + hisUserObj.get("patientName") + "|NEW|";
            HospitalSiConfig hospitalSiConfig =
                    tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(hospitalId, TerminalVar.medicare_Type_Outpatient);
            // TODO 类型待确认
            // 读卡信息
            PayInfo payInfo = siService.readCardInfo(hospitalSiConfig, cardInfo);
            String outPutStr = payInfo.getOutputStr();
            if (StringUtils.isBlank(outPutStr)){
                return respond;
            }
            respond.setUserNo(StringUtils.split(outPutStr,"|")[0]);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName          用户名
     * @param synKey               效验码
     * @param terminalCode         终端编号
     * @param hospitalId           医院ID
     * @param socialsecurityNO     医保号
     * @param hospitalNO           医院编号
     * @param operatorNo           操作人员编号
     * @param cycleNo              周期编号
     * @param dynamic              动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @param medicareType         0，非医保；1,市医保；
     * @param cardinfo             卡信息（如读卡交易入参）
     * @Description 1.2.6    获取医保卡信息
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TGetCardInfoRespond
     */
    @Override
    public GetCardInfoRespond getCardInfo(String synUserName,
                                          String synKey,
                                          String terminalCode,
                                          String hospitalId,
                                          String socialsecurityNO,
                                          String hospitalNO,
                                          String operatorNo,
                                          String cycleNo,
                                          String dynamic,
                                          String medicareType,
                                          String cardinfo) {
        GetCardInfoRespond respond = new GetCardInfoRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            HospitalSiConfig hospitalSiConfig =
                    tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(hospitalId, TerminalVar.medicare_Type_Outpatient);

            PayInfo payInfo = siService.readCardInfo(hospitalSiConfig, cardinfo);
            respond.setCardInfo(payInfo.getOutputStr());
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName             用户名
     * @param synKey                  效验码
     * @param terminalCode            终端编号
     * @param hospitalId              医院ID
     * @param mobileNo                手机号码
     * @param password                密码
     * @param hiUser                  hiUser实体对象
     * @param userNo                  人员编号
     * @param medicareType            0，非医保；1,市医保；
     * @param cardinfo                卡信息（如读卡交易入参）例如：V00113641|2000062142|0.0
     * @param overallArea             统筹区号
     * @param bankCardInfo            银行卡信息
     * @Description 1.2.7    未注册用户认证
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TRegisterUserRespond
     */
    @Override
    public RegisterUserRespond registerUser(String synUserName,
                                            String synKey,
                                            String terminalCode,
                                            String hospitalId,
                                            String mobileNo,
                                            String password,
                                            HiUser hiUser,
                                            String userNo,
                                            String medicareType,
                                            String cardinfo,
                                            String overallArea,
                                            String bankCardInfo) {
        // 直接认证
        RegisterUserRespond respond = new RegisterUserRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);
        try {
            // 注册第一个表
            User tRhipUser = new User();
            int keyId = seqService.getUserId();
            tRhipUser.setUserId(keyId);
//            tRhipUser.setUserCode(new Object());
            tRhipUser.setIsExternalUser(TerminalVar.IS_EXTERNAL_USER);
            tRhipUser.setUserPasswd(""); // TODO
            tRhipUser.setCreatedTime(new Date());
//            tRhipUser.setLastLogonTime(new Date()); 待确定
            tRhipUser.setUserName(hiUser.getPatientName());
//            tRhipUser.setProviderId((short)0); 待确定
            tRhipUser.setContactPhone(mobileNo);
//            tRhipUser.setCertTypeId((short)0);
//            tRhipUser.setIsCertificated((short)0);
            tRhipUser.setUserRealname(hiUser.getPatientName());
//            tRhipUser.setCertIdno(new Object());
            tRhipUser.setCertificatedTime(new Date());
            tRhipUser.setUserTypeId((short) 3); //TODO
//            tRhipUser.setOpenId(new Object()); 待确定
//            tRhipUser.setLoginMobile(new Object());
//            tRhipUser.setLoginEmail(new Object());
//            tRhipUser.setMobileVerified((short)0);
//            tRhipUser.setEmailVerifiedTime(new Date());
//            tRhipUser.setFirstAppLoginTime(new Date());
//            tRhipUser.setLastAppLoginTime(new Date());
//            tRhipUser.setMobileVerifiedTime(new Date());
//            tRhipUser.setEmailVerified((short)0);
//            tRhipUser.setVerifyCodeExpired(new Date());
//            tRhipUser.setVerifyCode(new Object());
//            tRhipUser.setAppLoginCount(0);
            tRhipUserMapper.insert(tRhipUser);
            // 第二个表
            SiUser tSiUser = new SiUser();
            BigDecimal tSiUserId = BigDecimal.valueOf(seqService.getKeyId("T_SI_USER"));
            tSiUser.setUserId(tSiUserId);
            tSiUser.setUserName(hiUser.getPatientName());
            tSiUser.setUserPwd(new Object());
            tSiUser.setName(hiUser.getPatientName());
            tSiUser.setGender(hiUser.getPatientSex());
            tSiUser.setIdCard(hiUser.getPatientIdcardNo());
            tSiUser.setPhone(mobileNo);
//            tSiUser.setUserCode(new Object()); 用户社保编码
            tSiUser.setSiCard(hiUser.getSocialsecurityNO()); //社保卡号
            tSiUser.setRegionCode(new Object());
            tSiUser.setEmail(new Object());
            tSiUser.setCreatedTime(new Date());
//            tSiUser.setLastLoginTime(new Date());
            tSiUser.setStatus((short) 0);
//            tSiUser.setIp(new Object());
            tSiUser.setpUserId(new BigDecimal("0"));
//            tSiUser.setMarks(new Object());
//            tSiUser.setPhoto(new Object());
//            tSiUser.setEasemobuuid(new Object());
            tSiUser.setMobileNo(mobileNo);
            tSiUser.setPassword(new Object());
            tSiUser.setRegisterTime(new Date());
            tSiUser.setUsername(hiUser.getPatientName());
//            tSiUser.setOpenid(new Object()); 微信openid
//            tSiUser.setBindFlag((short)0); 是否绑定社保卡(1:是   0:否)
//            tSiUser.setIsReal((short)0); 是否准实名（0否1是）
//            tSiUser.setSecretKey(new Object()); 密钥
            tSiUser.setStatus2(TerminalVar.USER_STATUS_AVAILABLE); //用户状态，0为未激活（不可用），1为激活（可用）
            tSiUserMapper.insert(tSiUser);

            // 第三个表
            SiFamilyMembers tSiFamilyMembers = new SiFamilyMembers();
            long tSiFamilyMembersId = seqService.getKeyId("T_SI_FAMILY_MEMBERS");

            tSiFamilyMembers.setId(tSiFamilyMembersId);
            tSiFamilyMembers.setMobileNo(mobileNo);
            tSiFamilyMembers.setPatientName(hiUser.getPatientName());
            tSiFamilyMembers.setCardId(hiUser.getPatientIdcardNo());
//            tSiFamilyMembers.setMobile(new Object());电话号码
//            tSiFamilyMembers.setRelation(new Object());关系
            tSiFamilyMembers.setCreatetime(new Date());
//            tSiFamilyMembers.setIsMain((short) 0);//是否主账号（0否1是）
//            tSiFamilyMembers.setHeadno((short) 0);//头像编号
//            tSiFamilyMembers.setIsAuthorize(0L); //是否授权
//            tSiFamilyMembers.setUserId(new Object());
            tSiFamilyMembers.setCardNum(hiUser.getSocialsecurityNO());
            tSiFamilyMembersMapper.insert(tSiFamilyMembers);


            // 认证
            SiRealUser tSiRealUser = new SiRealUser();

            Long newId = seqService.getOrderId();
            tSiRealUser.setUserId(newId);
            tSiRealUser.setMobileNo(mobileNo);
            tSiRealUser.setVisitcardNum(hiUser.getSocialsecurityNO());
            tSiRealUser.setVisitcardIsBing(TerminalVar.REAL_USER_BING);
//        tSiRealUser.setFinancialAccount(new Object()); 金融账号
//        tSiRealUser.setFinancialIsBing((short)0); 绑定金融账号
//        tSiRealUser.setRealPicUrl(new Object()); 认证照片
            tSiRealUser.setRealTime(new Date());
//        tSiRealUser.setPayPassword(new Object()); 支付密码
            tSiRealUser.setUserNo(userNo);
            tSiRealUser.setMedicareType(Short.valueOf(medicareType));
            tSiRealUser.setCardinfo(cardinfo);
            tSiRealUser.setOverallArea(overallArea);
            tSiRealUser.setPattern(TerminalVar.REAL_USER_TERMINAL);
            tSiRealUser.setPatientName(hiUser.getPatientName());
            tSiRealUser.setCardId(hiUser.getPatientIdcardNo());
            tSiRealUserMapper.insert(tSiRealUser);

            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName          用户名
     * @param synKey               效验码
     * @param terminalCode         终端编号
     * @param hospitalId           医院ID
     * @param hospitalNO           医院编号
     * @param       operatorNo           操作人员编号
     * @param cycleNo              周期编号
     * @param dynamic              动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @param cardinfo             卡信息（如读卡交易入参）
     * @param medicareType         0，非医保；1,市医保；
     * @Description 1.2.8    获取社保统筹区号
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TGetOverallAreaRespond
     */
    @Override
    public GetOverallAreaRespond getOverallArea(String synUserName,
                                                String synKey,
                                                String terminalCode,
                                                String hospitalId,
                                                String hospitalNO,
                                                String cycleNo,
                                                HiUser dynamic,
                                                String cardinfo,
                                                String medicareType) {
        GetOverallAreaRespond respond = new GetOverallAreaRespond();

        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            HospitalSiConfig hospitalSiConfig =
                    tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(hospitalId, TerminalVar.medicare_Type_Outpatient);

            PayInfo payInfo = siService.readCardInfo(hospitalSiConfig, cardinfo);
            respond.setOverallArea(payInfo.getArea());
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @param synUserName           用户名
     * @param synKey                效验码
     * @param terminalCode          终端编号
     * @param hospitalId            医院ID
     * @param socialsecurityNO      医保卡号
     * @param password              密码
     * @Description 1.2.9	检验医保老卡密码
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TSiCheckPassWordRespond
     */
    @Override
    public SiCheckPassWordRespond siCheckPassWord(String synUserName,
                                                  String synKey,
                                                  String terminalCode,
                                                  String hospitalId,
                                                  String socialsecurityNO,
                                                  String password) {
        // TODO 社保服务、未提供相关方法，同时没有可用方式查询
        return null;
    }

}
