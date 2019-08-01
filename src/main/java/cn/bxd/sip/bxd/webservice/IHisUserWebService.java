package cn.bxd.sip.bxd.webservice;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.common.InsuredPayInfoRespond;
import cn.bxd.sip.bxd.model.respond.hisuser.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author : cRyann
 * @create 2018-08-29
 **/
@WebService
public interface IHisUserWebService {
    //1.1	基本信息

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param cardNo       身份证号码
     * @author cRyann
     * @Description 1.1.1    身份证号码查看患者信息
     **/
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryPatientInfoByIdNO")
    @WebResult(name = "queryPatientInfoByIdNOResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    HiUserModel queryPatientInfoByIdNO(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo
    );

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param socialsecurityNO 社保卡号
     * @param medicareNO       银行卡号
     * @param bankCardNo       就诊卡号
     * @author cRyann
     * @Description 1.1.2    社保、银行、就诊卡号查看患者信息
     **/
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryPatientInfoBySocialsecurityNO")
    @WebResult(name = "queryPatientInfoBySocialsecurityNOResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    HiUserModel queryPatientInfoBySocialsecurityNO(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "medicareNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareNO,
            @WebParam(name = "bankCardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bankCardNo
    );

    /**
     * @param synUserName  用户名
     * @param synKey       同步信息
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param phone        手机号码
     * @author cRyann
     * @Description 1.1.3    手机号码获取患者信息
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryPatientInfoByPhone")
    @WebResult(name = "queryPatientInfoByPhoneResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    HiUserModel queryPatientInfoByPhone(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "phone", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String phone

    );

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
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doCreatCardInfo")
    @WebResult(name = "doCreatCardInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    HiUserModel doCreatCardInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "hisUser", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hisUser);

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
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryExaminationRecordStatus")
    @WebResult(name = "queryExaminationRecordStatusResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryExaminationRecordStatusRespond queryExaminationRecordStatus(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "reportNos", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String reportNos,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

    /**
     * @param synUserName  用户名
     * @param synKey       同步信息
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param reportNos    报告编号     * （注多个可以逗号分开如 112，2222）
     * @param reportType    报告类型1：检验，2：B超，3：放射影像，4：内镜，5：病理，6：心电图
     * @param extend       扩展字段Json值，如：     * {“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.1.6    更新检查,检验数据打印状态
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryExaminationRecordStatus")
    @WebResult(name = "queryExaminationRecordStatusResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    BaseRespond updateExaminationRecordStatus(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "reportNos", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String reportNos,
            @WebParam(name = "reportType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String reportType,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

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
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryExaminationRecordList")
    @WebResult(name = "queryExaminationRecordStatusResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryExaminationRecordListRespond queryExaminationRecordList(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "startDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String startDate,
            @WebParam(name = "endDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String endDate);

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
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryHisUserRecordList")
    @WebResult(name = "queryHisUserRecordListResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryHisUserRecordListRespond queryHisUserRecordList(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "startDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String startDate,
            @WebParam(name = "endDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String endDate);

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
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/updatePatientInfo")
    @WebResult(name = "updatePatientInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    HiUserModel updatePatientInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "bankCardNumber", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bankCardNumber,
            @WebParam(name = "visitCardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String visitCardNo
    );

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
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/insuredPayInfo")
    @WebResult(name = "insuredPayInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    InsuredPayInfoRespond insuredPayInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO
    );

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
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/treatmentInfo")
    @WebResult(name = "treatmentInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    TreatmentInfoRespond treatmentInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO
    );

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
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/medicalPayInfo")
    @WebResult(name = "medicalPayInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    MedicalPayInfoRespond medicalPayInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO
    );

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
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/medicalPayInfo")
    @WebResult(name = "medicalPayInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    RemoteInfoResond remoteInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO
    );

    // 1.2	实名认证

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param mobileNo     手机号码
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.2.1    获取验证码
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/getSMSCode")
    @WebResult(name = "getSMSCodeResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    GetSMSCodeRespond getSMSCode(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "mobileNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String mobileNo
    );


    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param mobileNo         手机号码
     * @param password         密码
     * @param socialsecurityNO 医保号
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.2.2    已注册判断手机号码和社保卡是否绑定
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/registeredConfirmsExist")
    @WebResult(name = "registeredConfirmsExistResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    RegisteredConfirmsExistRespond registeredConfirmsExist(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "mobileNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String mobileNo,
            @WebParam(name = "password", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String password,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO
    );


    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param mobileNo         手机号码
     * @param password         密码
     * @param socialsecurityNO 医保号
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.2.3    未注册判断手机号码和社保卡是否绑定
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/registerConfirmsExist")
    @WebResult(name = "registerConfirmsExistResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    RegisterConfirmsExistRespond registerConfirmsExist(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "mobileNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String mobileNo,
            @WebParam(name = "password", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String password,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO
    );


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
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.2.4    已注册用户认证
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/certificationUser")
    @WebResult(name = "certificationUserResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    CertificationUserRespond certificationUser(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "mobileNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String mobileNo,
            @WebParam(name = "password", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String password,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "patientName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientName,
            @WebParam(name = "cardId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardId,
            @WebParam(name = "userNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String userNo,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "cardinfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardinfo,
            @WebParam(name = "overallArea", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String overallArea,
            @WebParam(name = "bankCardInfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bankCardInfo
    );

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
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.2.5    获取医保个人编号
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/getUserNo")
    @WebResult(name = "getUserNoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    GetUserNoRespond getUserNo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "hospitalNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalNO,
            @WebParam(name = "operatorNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String operatorNo,
            @WebParam(name = "cycleNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cycleNo,
            @WebParam(name = "dynamic", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String dynamic,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "cardinfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardinfo
    );

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
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.2.6    获取医保卡信息
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/getCardInfo")
    @WebResult(name = "getCardInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    GetCardInfoRespond getCardInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "hospitalNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalNO,
            @WebParam(name = "operatorNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String operatorNo,
            @WebParam(name = "cycleNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cycleNo,
            @WebParam(name = "dynamic", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String dynamic,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "cardinfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardinfo
    );

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
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.2.7    未注册用户认证
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/registterUser")
    @WebResult(name = "registterUserResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    RegisterUserRespond registerUser(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "mobileNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String mobileNo,
            @WebParam(name = "password", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String password,
            @WebParam(name = "THiUser", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") HiUser hiUser,
            @WebParam(name = "userNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String userNo,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "cardinfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardinfo,
            @WebParam(name = "overallArea", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String overallArea,
            @WebParam(name = "bankCardInfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bankCardInfo
    );

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
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.2.8    获取社保统筹区号
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/getOverallArea")
    @WebResult(name = "getOverallAreaResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    GetOverallAreaRespond getOverallArea(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "hospitalNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalNO,
            @WebParam(name = "cycleNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cycleNo,
            @WebParam(name = "dynamic", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") HiUser dynamic,
            @WebParam(name = "cardinfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardinfo,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType
    );

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param socialsecurityNO 医保卡号
     * @param password         密码
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.2.9    检验医保老卡密码
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/siCheckPassWord")
    @WebResult(name = "siCheckPassWordResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    SiCheckPassWordRespond siCheckPassWord(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "password", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String password
    );
}
