package cn.bxd.sip.bxd.webservice;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.pay.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @Author : cRyann
 * @create 2018-08-29
 **/
@WebService
public interface IPayWebService {

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param patientNo     患者编号
     * @Param medicareType  0，非医保；1,市医保；
     * @Param medicareMess  医保个人基本信息（医保读卡交易返回值。根据 medicareType判断返回值格式）
     * @Param extend        扩展字段Json值，如：{“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.4.1    待交费列表
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryToPayRecipeInfoList")
    @WebResult(name = "queryToPayRecipeInfoListResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryToPayRecipeInfoListRespond queryToPayRecipeInfoList(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "medicareMess", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareMess,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend

    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param patientNo    患者编号
     * @Param startDate    开始时间
     * @Param endDate      结束时间
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.4.2    已交费列表
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryPaymentRecordList")
    @WebResult(name = "queryPaymentRecordListResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryPaymentRecordListRespond queryPaymentRecordList(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "startDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String startDate,
            @WebParam(name = "endDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String endDate
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param hiFeeNos     缴费编号(注多个可以逗号分开如 112，2222)
     * @Param extend       扩展字段Json值，如：     * {“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.4.3    状态查询
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryPaymentRecordStatus")
    @WebResult(name = "queryPaymentRecordStatusResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryPaymentRecordStatusRespond queryPaymentRecordStatus(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "hiFeeNos", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hiFeeNos,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param hiFeeNos     缴费编号(注多个可以逗号分开如 112，2222)
     * @Param extend       扩展字段Json值，如：     * {“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.4.4    更新打票状态
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/updateRecipeStatusStatus")
    @WebResult(name = "updateRecipeStatusStatusResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    BaseRespond updateRecipeStatusStatus(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "hiFeeNos", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hiFeeNos,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

    /**
     * @Param synUserName       用户名
     * @Param synKey            效验码
     * @Param terminalCode      终端编号
     * @Param hospitalId        医院ID
     * @Param hiFeeNos          缴费编号(注多个可以逗号分开如 112，2222)
     * @Param payType           支付方式1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
     * @Param socialsecurityNO  社保卡号
     * @Param hospitalNO        医院编号
     * @Param operatorNo        操作人员编号
     * @Param cycleNo           周期编号
     * @Param dynamic           动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @Param medicareType      0，非医保；1,市医保；
     * @Param cardinfo          卡信息
     * @Param overallArea       统筹区号
     * @Param extend            扩展字段Json值，如：     * {“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.4.5    预结算
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/preDoPay")
    @WebResult(name = "preDoPayResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    String preDoPay(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "hiFeeNos", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hiFeeNos,
            @WebParam(name = "payType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payType,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "hospitalNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalNO,
            @WebParam(name = "operatorNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String operatorNo,
            @WebParam(name = "cycleNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cycleNo,
            @WebParam(name = "dynamic", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String dynamic,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "cardinfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardinfo
    );

    /**
     * @Param synUserName                   用户名
     * @Param synKey                        效验码
     * @Param terminalCode                  终端编号
     * @Param hospitalId                    医院ID
     * @Param hiFeeNos                      缴费编号 (注多个可以逗号分开如 112，2222)
     * @Param payType                       支付方式1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
     * @Param socialsecurityNO              社保卡号
     * @Param poAllPrice                    总金额
     * @Param payMoney                      现金支付金额
     * @Param isOverall                     是否进行医保个账支付或统筹（0否，1是）
     * @Param patientNo                     患者编号
     * @Param patientName                   患者姓名
     * @Param cardId                        身份证
     * @Param sex                           性别
     * @Param medicareType                  0，非医保；1,市医保；
     * @Param cardinfo                      卡信息
     * @Param medicareMess                  医保个人基本信息（医保读卡交易返回值。根据 medicareType判断返回值格式）
     * @Param originalPrice                 原价（小数后两位）
     * @Param extend                        扩展字段Json值，如：
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.4.6    生成订单
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/makeProductOrder")
    @WebResult(name = "makeProductOrderResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    MakeProductOrderRespond makeProductOrder(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "hiFeeNos", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hiFeeNos,
            @WebParam(name = "payType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payType,
            @WebParam(name = "poType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poType,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "poAllPrice", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poAllPrice,
            @WebParam(name = "payMoney", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payMoney,
            @WebParam(name = "isOverall", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String isOverall,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "patientName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientName,
            @WebParam(name = "cardId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardId,
            @WebParam(name = "sex", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sex,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "cardinfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardinfo,
            @WebParam(name = "medicareMess", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareMess,
            @WebParam(name = "originalPrice", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String originalPrice,
            @WebParam(name = "appId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String appId
    );

    /**
     * @Param synUserName         用户名
     * @Param synKey              效验码
     * @Param terminalCode        终端编号
     * @Param hospitalId          医院ID
     * @Param hiFeeNos            缴费编号
     * @Param payType             1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
     * @Param payRecord           支付交易流水号
     * @Param payMoney            个账支付金额
     * @Param socialsecurityNO    社保卡号
     * @Param hospitalNO          医院编号
     * @Param operatorNo          操作人员编号
     * @Param cycleNo             周期编号
     * @Param dynamic             动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @Param bankInformation     银行返回信息
     * @Param isOverall           是否进行医保支付或统筹（0，否，1，是）
     * @Param poNo                订单号
     * @Param buyerAccount        银行卡号
     * @Param userNo              个人编号
     * @Param medicareType        0，非医保；1,市医保；
     * @Param extend              扩展字段Json值，如：{“key1”：”value1” ，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.4.7    处方支付
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/doPay")
    @WebResult(name = "doPayResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    DoPayRespond doPay(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "hiFeeNos", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hiFeeNos,
            @WebParam(name = "payType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payType,
            @WebParam(name = "payRecord", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payRecord,
            @WebParam(name = "payMoney", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payMoney,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "hospitalNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalNO,
            @WebParam(name = "operatorNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String operatorNo,
            @WebParam(name = "cycleNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cycleNo,
            @WebParam(name = "dynamic", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String dynamic,
            @WebParam(name = "bankInformation", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String bankInformation,
            @WebParam(name = "isOverall", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String isOverall,
            @WebParam(name = "poNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poNo,
            @WebParam(name = "buyerAccount", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String buyerAccount,
            @WebParam(name = "userNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String userNo,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType
    );

    /**
     * @Param
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.4.8    获取凭条打印信息
     */
    @WebMethod
    String printInfo(String jsonStr);

    /**
     * @Param synUserName           用户名
     * @Param synKey                效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param poNo                  订单号
     * @Param payType               1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.4.9    生成支付二维码
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/trade_precreate")
    @WebResult(name = "tradePrecreateResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    TradePrecreateRespond trade_precreate(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "poNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poNo,
            @WebParam(name = "payType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payType
    );

    /**
     * @Param synUserName          用户名
     * @Param synKey          效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId          医院ID
     * @Param poNo          订单号
     * @Param payType          1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
     * @Author cRyann
     * @Create 2018/9/12
     * @description 1.4.10    查询支付状态
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/query_orderStatus")
    @WebResult(name = "queryOrderStatusResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryOrderStatusRespond query_orderStatus(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "poNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poNo,
            @WebParam(name = "payType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payType
    );

    /**
     * @Param synUserName           用户名
     * @Param synKey            效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param poType            类型：1，医院产品定单，2，协医产品定单， 3，挂号定单  4,表示问诊定单  ,6住院'
     * @Param objectId          号源ID
     * @Param poAllPrice            总金额
     * @Param patientNo         患者编号
     * @Param patientName           患者姓名
     * @Param cardId            身份证
     * @Param socialsecurityNO          社保卡号
     * @Param userNo            个人编号
     * @Param medicareType          0，非医保；1,市医保；
     * @Param medicareInfo          医保传入信息 （格式见附表:医保传入信息）
     * @Param extend            扩展字段Json值，如：     * {“key1”：”value1” ，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @description 1.4.11    挂号生成订单
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/makeRegProductOrder")
    @WebResult(name = "makeRegProductOrderResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    MakeProductOrderRespond makeRegProductOrder(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "poType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poType,
            @WebParam(name = "objectId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String objectId,
            @WebParam(name = "poAllPrice", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poAllPrice,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "patientName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientName,
            @WebParam(name = "cardId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cardId,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "userNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String userNo,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "medicareInfo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareInfo,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend,
            @WebParam(name = "appId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String appId
    );
    

    /**
     * @Param synUserName           用户名
     * @Param synKey                效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param poNo                  订单号
     * @Param socialsecurityNO      社保卡号
     * @Param patientNo             患者编号
     * @Param poAllPrice            医保支付总金额
     * @Author cRyann
     * @Create 2018/9/12
     * @description 1.4.12    医保回退
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/yibaoFallback")
    @WebResult(name = "yibaoFallbackResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    BaseRespond yibaoFallback(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "poNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poNo,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "poAllPrice", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poAllPrice

    );

    /**
     * @Param synUserName           用户名
     * @Param synKey                效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param poNo                  订单号
     * @Param socialsecurityNO      社保卡号
     * @Param patientNo             患者编号
     * @Param poAllPrice            医保支付总金额
     * @description 1.4.13  	支付宝微信回退
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/tradeFallback")
    @WebResult(name = "tradeFallbackResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    BaseRespond tradeFallback(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "poNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poNo,
            @WebParam(name = "socialsecurityNO", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String socialsecurityNO,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "poAllPrice", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poAllPrice
    );

    /**
     * @Param synUserName    String	否	用户名
     * @Param synKey        String	否	效验码
     * @Param terminalCode    String	否	终端编号
     * @Param hospitalId    String	否	医院ID
     * @Param hospitalNO                医院编号
     * @Param operatorNo                操作人员编号
     * @Param cycleNo                    周期编号
     * @Param dynamic                    动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @Param poNo                        订单号
     * @Param payMoney        String	否	个账支付金额
     * @Param extend        String	否	扩展字段Json值，如： {“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/26
     * @Description 1.4.14    挂号医保支付
     */
    @WebMethod
    DoRegPayRespond doRegPay(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "operatorNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String operatorNo,
            @WebParam(name = "cycleNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String cycleNo,
            @WebParam(name = "dynamic", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String dynamic,
            @WebParam(name = "poNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poNo,
            @WebParam(name = "payMoney", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payMoney,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

    /**
     * @Param synUserName           用户名
     * @Param synKey            效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param poNo          订单号
     * @Param payType           支付方式，2支付宝 5、微信
     * @description 1.4.15	    交易关闭
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/trade_close")
    @WebResult(name = "tradeCloseResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    TradeCloseRespond trade_close(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "poNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String poNo,
            @WebParam(name = "payType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String payType

    );
    
    /**
            * 更新门诊缴费打印状态
     * @Description: 
     * @date:   2019年1月9日 下午5:54:53
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/updateRecipeRecordStatus")
    @WebResult(name = "BaseRespondResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    BaseRespond updateRecipeRecordStatus(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "hiFeeNos", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hiFeeNos,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );

    /**
     * 1.4.17	账单查询
     * @Param synUserName           用户名
     * @Param synKey            效验码
     * @Param startTime          交易开始时间。格式：YYYYMMDD
     * @Param endTime            交易结束时间。格式：YYYYMMDD
     * @Param orderNo          订单号
     * @date:   2019年4月12日 下午5:54:53
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/updateRecipeRecordStatus")
    @WebResult(name = "BaseRespondResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    String queryOrderRecords(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "startTime", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String startTime,
            @WebParam(name = "endTime", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String endTime,
            @WebParam(name = "orderNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String orderNo,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId
    );
    

}
