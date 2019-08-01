package cn.bxd.sip.bxd.webservice;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.reg.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * author : cRyann
 *
 * @create 2018-08-29
 * <p>
 **/

@WebService
public interface IRegWebService {

    /**
     * @Param synUserName
     * @Param synKey
     * @Param terminalCode
     * @Param hospitalId
     * @Param doRegIn
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.8    预约当天并取号
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doRegAnddoTaketheNo")
    @WebResult(name = "doRegAnddoTaketheNoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    DoRegAnddoTaketheNoRespond doRegAnddoTaketheNo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "doRegIn", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String doRegIn
    );

    /**
     * @Param synUserName    用户名
     * @Param synKey    效验码
     * @Param terminalCode    终端编号
     * @Param hospitalId    医院ID
     * @Param doRegIn    Json字符串     * @Param synUserName    用户名
     * @Param synUserName    用户名
     * @Param synKey    效验码
     * @Param terminalCode    终端编号
     * @Param hospitalId    医院ID
     * @Param doRegIn    Json字符串
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.3    预约挂号
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doReg")
    @WebResult(name = "doRegResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    DoRegOut doReg(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "doRegIn", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String doRegIn
    );

    /**
     * @Param synUserName    用户名
     * @Param synKey    效验码
     * @Param terminalCode    终端编号
     * @Param hospitalId    医院ID
     * @Param startDate    起始时间
     * @Param endDate    结束时间
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.1    查询号源
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryToRegDoctorList")
    @WebResult(name = "queryToRegDoctorListResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryToRegDoctorListRespond queryToRegDoctorList(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "startDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String startDate,
            @WebParam(name = "endDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String endDate
    );

    /**
     * @Param synUserName    用户名
     * @Param synKey    效验码
     * @Param terminalCode    终端编号
     * @Param hospitalId    医院ID
     * @Param startDate    起始时间
     * @Param endDate    结束时间
     * @Param doctorId    平台医生ID
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.2    根据医生查询号源
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryToRegDoctorListByDoctorId")
    @WebResult(name = "queryToRegDoctorListByDoctorIdResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryToRegDoctorListByDoctorIdRespond queryToRegDoctorListByDoctorId(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "startDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String startDate,
            @WebParam(name = "endDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String endDate,
            @WebParam(name = "doctorId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String doctorId
    );



    /**
     * @Param synUserName               用户名
     * @Param synKey                    效验码
     * @Param terminalCode              终端编号
     * @Param hospitalId                医院ID
     * @Param sourceMark                号源编号
     * @Param patientNo                 患者编号
     * @Param sourceDate                号源日期
     * @Param departmentorganId         科室编号
     * @Param extend                    扩展字段Json值，如：{“key1”：”value1” ，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.4    取消预约
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doRegCancel")
    @WebResult(name = "doRegCancelResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    BaseRespond doRegCancel(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "sourceMark", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceMark,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate,
            @WebParam(name = "departmentorganId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentorganId,
            @WebParam(name = "orderNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String orderNo,
            @WebParam(name = "cancelReason", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cancelReason,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

    /**
     * @Param synUserName               用户名
     * @Param synKey                    效验码
     * @Param terminalCode              终端编号
     * @Param hospitalId                医院ID
     * @Param sourceMark                号源编号
     * @Param patientNo                 患者编号
     * @Param sourceDate                号源日期
     * @Param departmentorganId         科室编号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.5    取号查询接口
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryTaketheNo")
    @WebResult(name = "queryTaketheNoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryTaketheNoRespond queryTaketheNo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "sourceMark", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceMark,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate,
            @WebParam(name = "departmentorganId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentorganId
    );

    /**
     * @Param synUserName               用户名
     * @Param synKey                    效验码
     * @Param terminalCode              终端编号
     * @Param hospitalId                医院ID
     * @Param sourceMark                号源编号
     * @Param patientNo                 患者编号
     * @Param sourceDate                号源日期
     * @Param departmentorganId         科室编号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.6    排取人数
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryRegWaitNum")
    @WebResult(name = "queryRegWaitNumResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryRegWaitNumRespond queryRegWaitNum(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "sourceMark", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceMark,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate,
            @WebParam(name = "departmentorganId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentorganId
    );

    /**
     * @Param synUserName               用户名
     * @Param synKey                效验码
     * @Param terminalCode                  终端编号
     * @Param hospitalId                医院ID
     * @Param sourceMark                号源编号
     * @Param patientNo                 患者编号
     * @Param sourceDate                号源日期
     * @Param departmentorganId                 科室编号
     * @Param payType               1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
     * @Param payRecord                 支付交易流水号
     * @Param payMoney                  实际支付金额
     * @Param extend                扩展字段Json值，如：{“key1”：”value1” ，”key2”：”value2”}     * 支付宝微信医保支付这里要传订单号（poNo）
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.7    门诊取号
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doTaketheNo")
    @WebResult(name = "doTaketheNoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    DoTaketheNoRespond doTaketheNo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "sourceMark", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceMark,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate,
            @WebParam(name = "departmentorganId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentorganId,
            @WebParam(name = "payType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payType,
            @WebParam(name = "payRecord", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payRecord,
            @WebParam(name = "payMoney", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payMoney,
            @WebParam(name = "orderNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String orderNo,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );



    /**
     * @Param synUserName
     * @Param synKey
     * @Param patientNo
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.9    查询未取号的预约挂号
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryRegBypatientNo")
    @WebResult(name = "queryRegBypatientNoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryRegBypatientNoRespond queryRegBypatientNo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId
    );

    /**
     * @Param synUserName           用户名
     * @Param synKey                效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param patientNo             串者编号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.10    查询预约挂号记录
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doRegRecord")
    @WebResult(name = "doRegRecordResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    String doRegRecord(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo
    );

    /**
     * @Param synUserName           用户名
     * @Param synKey            效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param cardNo            患者身份证号
     * @Param startDate         开始时间
     * @Param endDate           结束时间
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.11    查询挂号记录
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doRegAllRecord")
    @WebResult(name = "doRegAllRecordResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    DoRegAllRecordRespond doRegAllRecord(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "startDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String startDate,
            @WebParam(name = "endDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String endDate
    );

    /**
     * @Param synUserName           用户名
     * @Param synKey                效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param sourceDate            号源日期
     * @Param sourceTimeType        1.上午\2.下午\3.晚上
     * @Param organdoctorId         医生编号
     * @Param departmentorganId     科室编号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.12    查询医生号源时间段
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryToRegDoctorTimes")
    @WebResult(name = "queryToRegDoctorTimesResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryToRegDoctorTimesRespond queryToRegDoctorTimes(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate,
            @WebParam(name = "sourceTimeType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceTimeType,
            @WebParam(name = "organdoctorId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String organdoctorId,
            @WebParam(name = "departmentorganId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentorganId
    );

    /**
     * @Param synUserName       String	否	用户名
     * @Param synKey            String	否	效验码
     * @Param terminalCode      String	否	终端编号
     * @Param hospitalId        String	否	医院ID
     * @Param phone             String	否	接收手机号码
     * @Param content           String	否	消息内容
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.13    短信接口
     */
    @WebMethod
    SendMessageRespond sendMessage(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "phone", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String phone,
            @WebParam(name = "content", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String content
    );

    /**
     * @Param synUserName    String	否	用户名
     * @Param synKey    String	否	效验码
     * @Param terminalCode    String	否	终端编号
     * @Param hospitalId    String	否	医院ID
     * @Param organdoctorId    String	否	HIS医生编号(多个医生用逗号隔开)
     * @Param sourceDate    String	否	号源日期
     * @Param departmentorganId    String	否	科室编号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doctorSurNumLine")
    @WebResult(name = "doctorSurNumLineResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    DoctorSurNumLineRespond doctorSurNumLine(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "organdoctorId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String organdoctorId,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate,
            @WebParam(name = "departmentorganId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentorganId
    );

    /**
     * @Param synUserName    String	否	用户名
     * @Param synKey    String	否	效验码
     * @Param terminalCode    String	否	终端编号
     * @Param hospitalId    String	否	医院ID
     * @Param organdoctorId    String	否	医生编号
     * @Param departmentorganId    String	否	科室编号
     * @Param patientNo    Date	否	患者编号
     * @Param sourceDate    Date	否	取号日期（号源日期）
     * @Param timestypeNo    String	否	时间段标识 0表示无时间段
     * @Param sourceTimeType    String	否	1,上午，2，下午 3，晚上
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.15    查询挂号费
     */
    @WebMethod
    DoregFeeInterfaceRespond doregFeeInterface(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "organdoctorId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String organdoctorId,
            @WebParam(name = "departmentorganId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentorganId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate,
            @WebParam(name = "timestypeNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String timestypeNo,
            @WebParam(name = "sourceTimeType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceTimeType
    );

    /**
     * @Param synUserName    String	否	用户名
     * @Param synKey    String	否	效验码
     * @Param terminalCode    String	否	终端编号
     * @Param hospitalId    String	否	医院ID
     * @Param patientNo    String	否	患者编号
     * @Param doRegIn    String	否	Json字符串
     * @Author cRyann
     * @Create 2018/9/12
     * @Description
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doRegMedicareInfo")
    @WebResult(name = "doRegMedicareInfoResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    DoRegMedicareInfoRespond doRegMedicareInfo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "doRegIn", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String doRegIn
    );

    /**
     * @Param synUserName	String	否	用户名
     * @Param synKey	    String	否	效验码
     * @Param terminalCode	String	否	终端编号
     * @Param hospitalId	String	否	医院ID
     * @Param patientNO	    String	否	患者编号
     * @Param proCode	    String	否	时间段中获取的排班唯一标识
     * @Param cardNo	    String	否	身份证号
     * @Param timestypeNo	String	否	时间段标识
     * @Param sourceDate	String 	否	时间格式:yyyy-MM-dd
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.17    号源锁号
     */
    @WebMethod
    BaseRespond lockRegisterNo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "proCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String proCode,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "timestypeNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String timestypeNo,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate
    );

    /**
     * @Param synUserName	String	否	用户名
     * @Param synKey	    String	否	效验码
     * @Param terminalCode	String	否	终端编号
     * @Param hospitalId	String	否	医院ID
     * @Param patientNO	    String	否	患者编号
     * @Param proCode	    String	否	时间段中获取的排班唯一标识
     * @Param cardNo	    String	否	身份证号
     * @Param timestypeNo	String	否	时间段标识
     * @Param sourceDate	String 	否	时间格式:yyyy-MM-dd
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.18    号源解锁
     */
    @WebMethod
    BaseRespond unlockRegisterNo(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "proCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String proCode,
            @WebParam(name = "cardNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardNo,
            @WebParam(name = "timestypeNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String timestypeNo,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate
    );

}
