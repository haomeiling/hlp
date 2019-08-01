package cn.bxd.sip.bxd.webservice;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.inhos.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.math.BigDecimal;

/**
 * author : cRyann
 *
 * @create 2018-08-29
 * <p>
 * 查询患者住院信息	    queryPatientInHosInfo
 * 自助入院申请查询	    queryToInHos
 * 自助入院申请办理	    applyToInHos
 * 住院押金补缴查询	    queryArrears
 * 住院押金补缴	        inpatientPayment
 * 押金明细查询	        payDetail
 * 住院清单明细	        inHosDetail
 * 住院医保信息查询     inHosMedicalInfo
 * 出院结算申请       	applyLeaveHos
 * 查询出院清单	        queryLeaveHosDetail
 * 出院结算	            leaveHosPay
 **/
@WebService
public interface IInHosWebService {

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param cardNo           身份证号
     * @param visitCardNo      诊疗卡号
     * @param socialsecurityNO 社保卡号
     * @param bankCardNumber   其他卡号
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.1    查询患者住院信息
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryPatientInHosInfo")
    @WebResult(name = "queryPatientInHosInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryPatientInHosInfoRespond queryPatientInHosInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "visitCardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String visitCardNo,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "bankCardNumber", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bankCardNumber
    );

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param cardNo           身份证号
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param visitCardNo      诊疗卡号
     * @param socialsecurityNO 社保卡号
     * @param bankCardNumber   其他卡号
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.2    自助入院申请查询
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryToInHos")
    @WebResult(name = "queryToInHosResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryToInHosRespond queryToInHos(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "visitCardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String visitCardNo,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "bankCardNumber", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bankCardNumber
    );

    /**
     * @param synUserName    用户名
     * @param synKey         效验码
     * @param terminalCode   终端编号
     * @param hospitalId     医院ID
     * @param applyInHosInfo Json字符串
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.3    自助入院申请办理
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/applyToInHos")
    @WebResult(name = "applyToInHosResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    ApplyToInHosRespond applyToInHos(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "applyInHosInfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String applyInHosInfo
    );

    /**
     * @param synUserName       用户名
     * @param synKey            效验码
     * @param terminalCode      终端编号
     * @param hospitalId        医院ID
     * @param inHosNo           住院号
     * @param bedNo             床号
     * @param departmentorganId 科室编码
     * @param extend            扩展字段Json值，如：{“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.4    住院押金补缴查询
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryArrears")
    @WebResult(name = "queryArrearsResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryArrearsRespond queryArrears(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "inHosNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String inHosNo,
            @WebParam(name = "bedNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bedNo,
            @WebParam(name = "departmentorganId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentorganId,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param inHosNo      住院号
     * @param bedNo        床号
     * @param payType      1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
     * @param payRecord    支付交易流水号
     * @param payMoney     预交金金额（保留小数点后两位）
     * @param payReturn    支付交易返回
     * @param patientNo    患者编号
     * @param patientName  患者姓名
     * @param extend       扩展字段Json值，如：{“key1”：”value1” ，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.5    住院押金补缴
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/inpatientPayment")
    @WebResult(name = "inpatientPaymentResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    InpatientPaymentRespond inpatientPayment(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "inHosNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String inHosNo,
            @WebParam(name = "bedNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bedNo,
            @WebParam(name = "payType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") Integer payType,
            @WebParam(name = "payRecord", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payRecord,
            @WebParam(name = "payMoney", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") BigDecimal payMoney,
            @WebParam(name = "payReturn", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payReturn,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "patientName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientName,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param patientNo    患者编号
     * @param inHosNo      住院号
     * @param startDate    开始时间（yyyy-MM-dd）
     * @param endDate      结束时间（yyyy-MM-dd）
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.6    押金明细查询
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/payDetail")
    @WebResult(name = "payDetailResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    PayDetailRespond payDetail(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "inHosNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String inHosNo,
            @WebParam(name = "startDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String startDate,
            @WebParam(name = "endDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String endDate
    );

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param patientNo    患者编号
     * @param inHosNo      住院号
     * @param startDate    开始时间（yyyy-MM-dd）
     * @param endDate      结束时间（yyyy-MM-dd）
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.7    住院清单明细
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/inHosDetail")
    @WebResult(name = "inHosDetailResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    InHosDetailRespond inHosDetail(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "inHosNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String inHosNo,
            @WebParam(name = "startDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String startDate,
            @WebParam(name = "endDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String endDate

    );

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param inHosNo      住院号
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.8    住院医保信息查询
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/inHosMedicalInfo")
    @WebResult(name = "inHosMedicalInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    InHosMedicalInfoRespond inHosMedicalInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "inHosNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String inHosNo
    );

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param inHosNo      住院号
     * @param settleType
     * @param medicalInfo
     * @param medicareType 0，非医保；1,市医保；（为0时，返回值medicareInfo、 diagInfo可为空）
     * @param extend       扩展字段Json值，如：{“key1”：”value1” ，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.9    出院结算申请
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/applyLeaveHos")
    @WebResult(name = "applyLeaveHosResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    ApplyLeaveHosRespond applyLeaveHos(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "inHosNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String inHosNo,
            @WebParam(name = "settleType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String settleType,
            @WebParam(name = "medicalInfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicalInfo,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

    /**
     * @param synUserName  用户名
     * @param synKey       效验码
     * @param terminalCode 终端编号
     * @param hospitalId   医院ID
     * @param inHosNo      住院号
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.10    查询出院清单
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryLeaveHosDetail")
    @WebResult(name = "queryLeaveHosDetailResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryLeaveHosDetailRespond queryLeaveHosDetail(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "inHosNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String inHosNo
    );

    /**
     * @param synUserName      用户名
     * @param synKey           效验码
     * @param terminalCode     终端编号
     * @param hospitalId       医院ID
     * @param cardNo           身份证号
     * @param visitCardNo      诊疗卡号
     * @param socialsecurityNO 社保卡号
     * @param bankCardNumber   其他卡号
     * @Author cRyann
     * @Create 2018/9/17
     * @Description 1.6.1    查询患者住院信息
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/leaveHosPay")
    @WebResult(name = "leaveHosPayResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    LeaveHosPayRespond leaveHosPay(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "inHosNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String inHosNo,
            @WebParam(name = "payType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payType,
            @WebParam(name = "payRecord", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payRecord,
            @WebParam(name = "payMoney", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payMoney,
            @WebParam(name = "medicareMoney", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareMoney,
            @WebParam(name = "overMoney", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String overMoney,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "medicareRecord", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareRecord,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "medicareReturn", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareReturn,
            @WebParam(name = "bankReturn", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bankReturn,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "patientName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientName,
            @WebParam(name = "userNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String userNo,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );
    
    /**
     * 1.5.14.	更新住院清单打印状态
     * @Description: 
     * @date:   2019年1月9日 下午5:53:04
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/updateInHosRecordStatus")
    @WebResult(name = "BaseRespondStatusResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    BaseRespond updateInHosRecordStatus(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "inHosNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String inHosNo,
            @WebParam(name = "recordDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String recordDate,
            @WebParam(name = "recordType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String recordType,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );
    
}
