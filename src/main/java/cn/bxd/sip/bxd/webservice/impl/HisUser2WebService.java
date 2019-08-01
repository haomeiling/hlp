package cn.bxd.sip.bxd.webservice.impl;

import cn.bxd.sip.bxd.model.respond.hisuser.*;
import cn.bxd.sip.bxd.util.ReflectUtils;
import cn.bxd.sip.bxd.webservice.IHisUserWebService;
import cn.bxd.sip.bxd.webservice.impl.base.HisUserCommon;
import cn.bxd.sip.bxd.webservice.invoke.hisUser.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-10-09
 * Time: 10:15
 */
@Service
@Slf4j
@WebService(targetNamespace = "http://webservice.bxd.sip.bxd.cn/", endpointInterface = "cn.bxd.sip.bxd.webservice.IHisUserWebService")
public class HisUser2WebService extends HisUserCommon implements IHisUserWebService {

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
        THisUserService tHisUserService = THisUserService.getInstance();
        TIHisUser hisUserPort = tHisUserService.getHisUserPort();
        TGetSMSCodeRespond smsCode = hisUserPort.getSMSCode(synUserName, synKey, terminalCode, hospitalId, mobileNo);
        log.debug(smsCode.toString());
        //获取返回的类
        GetSMSCodeRespond getSMSCodeRespond = new GetSMSCodeRespond();
        //转换类
        ReflectUtils.copyFieldValue(smsCode, getSMSCodeRespond);
        log.debug(getSMSCodeRespond.toString());
        return getSMSCodeRespond;
    }

    public static void main(String[] args) {
        HisUser2WebService hisUser2 = new HisUser2WebService();
        hisUser2.getSMSCode("", "", "", "14037", "");
        hisUser2.getSMSCode("", "", "", "14037", "");
    }

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param mobileNo         手机号码
     * @param password         密码
     * @param socialsecurityNO 医保号
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TRegisteredConfirmsExistRespond
     * @Description 1.2.2    已注册判断手机号码和社保卡是否绑定
     */
    @Override
    public RegisteredConfirmsExistRespond registeredConfirmsExist(String synUserName,
                                                                  String synKey,
                                                                  String terminalCode,
                                                                  String hospitalId,
                                                                  String mobileNo,
                                                                  String password,
                                                                  String socialsecurityNO) {

        THisUserService tHisUserService = THisUserService.getInstance();
        TIHisUser hisUserPort = tHisUserService.getHisUserPort();
        TRegisteredConfirmsExistRespond tRegisteredConfirmsExistRespond = hisUserPort.registeredConfirmsExist(synUserName,
                synKey, terminalCode, hospitalId, mobileNo, password, socialsecurityNO);
        log.debug(tRegisteredConfirmsExistRespond.toString());
        //获取返回的类
        RegisteredConfirmsExistRespond registeredConfirmsExistRespond = new RegisteredConfirmsExistRespond();
        //转换类
        ReflectUtils.copyFieldValue(tRegisteredConfirmsExistRespond, registeredConfirmsExistRespond);
        log.debug(registeredConfirmsExistRespond.toString());
        return registeredConfirmsExistRespond;
    }

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param mobileNo         手机号码
     * @param password         密码
     * @param socialsecurityNO 医保号
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TRegisterConfirmsExistRespond
     * @Description 1.2.3    未注册判断手机号码和社保卡是否绑定
     */
    @Override
    public RegisterConfirmsExistRespond registerConfirmsExist(String synUserName,
                                                              String synKey,
                                                              String terminalCode,
                                                              String hospitalId,
                                                              String mobileNo,
                                                              String password,
                                                              String socialsecurityNO) {
        THisUserService tHisUserService = THisUserService.getInstance();
        TIHisUser hisUserPort = tHisUserService.getHisUserPort();
        TRegisterConfirmsExistRespond tRegisterConfirmsExistRespond = hisUserPort.registerConfirmsExist(synUserName, synKey,
                terminalCode, hospitalId, mobileNo, password, socialsecurityNO);
        log.debug(tRegisterConfirmsExistRespond.toString());
        //获取返回的类
        RegisterConfirmsExistRespond registerConfirmsExistRespond = new RegisterConfirmsExistRespond();
        //转换类
        ReflectUtils.copyFieldValue(tRegisterConfirmsExistRespond, registerConfirmsExistRespond);
        log.debug(registerConfirmsExistRespond.toString());
        return registerConfirmsExistRespond;
    }

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param mobileNo         手机号码
     * @param password         密码
     * @param socialsecurityNO 医保号
     * @param patientName      患者姓名
     * @param cardId           身份证
     * @param userNo           人员编号
     * @param medicareType     0，非医保；1,市医保；
     * @param cardinfo         卡信息（如读卡交易入参）  例如：V00113641|2000062142|0.0
     * @param overallArea      统筹区号
     * @param bankCardInfo     银行卡信息
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TCertificationUserRespond
     * @Description 1.2.4    已注册用户认证
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
        THisUserService tHisUserService = THisUserService.getInstance();
        TIHisUser hisUserPort = tHisUserService.getHisUserPort();
        TCertificationUserRespond tCertificationUserRespond = hisUserPort.certificationUser(synUserName, synKey,
                terminalCode, hospitalId, mobileNo, password, socialsecurityNO, patientName, cardId, userNo, medicareType, cardinfo, overallArea, bankCardInfo);
        log.debug(tCertificationUserRespond.toString());
        //获取返回的类
        CertificationUserRespond certificationUserRespond = new CertificationUserRespond();
        //转换类
        ReflectUtils.copyFieldValue(tCertificationUserRespond, certificationUserRespond);
        log.debug(certificationUserRespond.toString());
        return certificationUserRespond;
    }


    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param socialsecurityNO 医保号
     * @param hospitalNO       医院编号
     * @param operatorNo       操作人员编号
     * @param cycleNo          周期编号
     * @param dynamic          动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @param medicareType     0，非医保；1,市医保；
     * @param cardinfo         卡信息（如读卡交易入参）
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TGetUserNoRespond
     * @Description 1.2.5    获取医保个人编号
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
        THisUserService tHisUserService = THisUserService.getInstance();
        TIHisUser hisUserPort = tHisUserService.getHisUserPort();
        TGetUserNoRespond userNo = hisUserPort.getUserNo(synUserName, synKey, terminalCode, hospitalId,
                socialsecurityNO, hospitalNO, operatorNo, cycleNo, dynamic, medicareType, cardinfo);
        log.debug(userNo.toString());
        //获取返回的类
        GetUserNoRespond getUserNoRespond = new GetUserNoRespond();
        //转换类
        ReflectUtils.copyFieldValue(userNo, getUserNoRespond);
        log.debug(getUserNoRespond.toString());
        return getUserNoRespond;
    }

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param socialsecurityNO 医保号
     * @param hospitalNO       医院编号
     * @param operatorNo       操作人员编号
     * @param cycleNo          周期编号
     * @param dynamic          动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @param medicareType     0，非医保；1,市医保；
     * @param cardinfo         卡信息（如读卡交易入参）
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TGetCardInfoRespond
     * @Description 1.2.6    获取医保卡信息
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
        THisUserService tHisUserService = THisUserService.getInstance();
        TIHisUser hisUserPort = tHisUserService.getHisUserPort();
        TGetCardInfoRespond cardInfo = hisUserPort.getCardInfo(synUserName, synKey, terminalCode, hospitalId,
                socialsecurityNO, hospitalNO, operatorNo, cycleNo, dynamic, medicareType, cardinfo);
        log.debug(cardInfo.toString());
        //获取返回的类
        GetCardInfoRespond getCardInfoRespond = new GetCardInfoRespond();
        //转换类
        ReflectUtils.copyFieldValue(cardInfo, getCardInfoRespond);
        log.debug(getCardInfoRespond.toString());
        return getCardInfoRespond;
    }

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param mobileNo     手机号码
     * @param password     密码
     * @param hiUser       hiUser实体对象
     * @param userNo       人员编号
     * @param medicareType 0，非医保；1,市医保；
     * @param cardinfo     卡信息（如读卡交易入参）例如：V00113641|2000062142|0.0
     * @param overallArea  统筹区号
     * @param bankCardInfo 银行卡信息
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TRegisterUserRespond
     * @Description 1.2.7    未注册用户认证
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
        THiUser tHiUser = new THiUser();
        //转换类
        ReflectUtils.copyFieldValue(hiUser, tHiUser);

        THisUserService tHisUserService = THisUserService.getInstance();
        TIHisUser hisUserPort = tHisUserService.getHisUserPort();
        TRegisterUserRespond tRegisterUserRespond = hisUserPort.registerUser(synUserName, synKey, terminalCode,
                hospitalId, mobileNo, password, tHiUser, userNo, medicareType, cardinfo, overallArea, bankCardInfo);
        log.debug(tRegisterUserRespond.toString());
        //获取返回的类
        RegisterUserRespond registerUserRespond = new RegisterUserRespond();
        //转换类
        ReflectUtils.copyFieldValue(tRegisterUserRespond, registerUserRespond);
        log.debug(registerUserRespond.toString());
        return registerUserRespond;
    }

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param hospitalNO   医院编号
     * @param operatorNo   操作人员编号
     * @param cycleNo      周期编号
     * @param dynamic      动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @param cardinfo     卡信息（如读卡交易入参）
     * @param medicareType 0，非医保；1,市医保；
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TGetOverallAreaRespond
     * @Description 1.2.8    获取社保统筹区号
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
        THiUser tHiUser = new THiUser();
        //转换类
        ReflectUtils.copyFieldValue(dynamic, tHiUser);

        THisUserService tHisUserService = THisUserService.getInstance();
        TIHisUser hisUserPort = tHisUserService.getHisUserPort();
        TGetOverallAreaRespond overallArea = hisUserPort.getOverallArea(synUserName, synKey, terminalCode,
                hospitalId, hospitalNO, cycleNo, tHiUser, cardinfo, medicareType);
        log.debug(overallArea.toString());
        //获取返回的类
        GetOverallAreaRespond getOverallAreaRespond = new GetOverallAreaRespond();
        //转换类
        ReflectUtils.copyFieldValue(overallArea, getOverallAreaRespond);
        log.debug(getOverallAreaRespond.toString());
        return getOverallAreaRespond;
    }

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param socialsecurityNO 医保卡号
     * @param password         密码
     * @return returns cn.bxd.sip.bxd.webservice.invoke.hisUser.TSiCheckPassWordRespond
     * @Description 1.2.9    检验医保老卡密码
     */
    @Override
    public SiCheckPassWordRespond siCheckPassWord(String synUserName,
                                                  String synKey,
                                                  String terminalCode,
                                                  String hospitalId,
                                                  String socialsecurityNO,
                                                  String password) {
        THisUserService tHisUserService = THisUserService.getInstance();
        TIHisUser hisUserPort = tHisUserService.getHisUserPort();
        TSiCheckPassWordRespond tSiCheckPassWordRespond = hisUserPort.siCheckPassWord(synUserName, synKey, terminalCode, hospitalId, socialsecurityNO, password);
        log.debug(tSiCheckPassWordRespond.toString());
        //获取返回的类
        SiCheckPassWordRespond siCheckPassWordRespond = new SiCheckPassWordRespond();
        //转换类
        ReflectUtils.copyFieldValue(tSiCheckPassWordRespond, siCheckPassWordRespond);
        log.debug(siCheckPassWordRespond.toString());
        return siCheckPassWordRespond;
    }
}
