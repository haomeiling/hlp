package cn.bxd.sip.bxd.webservice.impl;


import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.pay.*;
import cn.bxd.sip.bxd.util.ReflectUtils;
import cn.bxd.sip.bxd.webservice.IPayWebService;
import cn.bxd.sip.bxd.webservice.invoke.pay.*;

/**
 * Description:
 * User: LiSheng
 * Date: 2018-10-15
 * Time: 13:52
 */
public class Pay2WebService implements IPayWebService {

    /**
     * @return returns java.lang.String
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param patientNo     患者编号
     * @Param medicareType  0，非医保；1,市医保；
     * @Param medicareMess  医保个人基本信息（医保读卡交易返回值。根据 medicareType判断返回值格式）
     * @Param extend        扩展字段Json值，如：{“key1”：”value1”，”key2”：”value2”}
     * @Description 1.4.1    待交费列表
     */
    @Override
    public QueryToPayRecipeInfoListRespond queryToPayRecipeInfoList(String synUserName,
                                           String synKey,
                                           String terminalCode,
                                           String hospitalId,
                                           String patientNo,
                                           String medicareType,
                                           String medicareMess, String extend) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        return payPort.queryToPayRecipeInfoList(synUserName, synKey, terminalCode, hospitalId, patientNo, medicareType, medicareMess);
    }

    /**
     * @return returns java.lang.String
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param patientNo    患者编号
     * @Param startDate    开始时间
     * @Param endDate      结束时间
     * @Description 1.4.2    已交费列表
     */
    @Override
    public QueryPaymentRecordListRespond queryPaymentRecordList(String synUserName,
                                         String synKey,
                                         String terminalCode,
                                         String hospitalId,
                                         String patientNo,
                                         String startDate,
                                         String endDate) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        return payPort.queryPaymentRecordList(synUserName, synKey, terminalCode, hospitalId, patientNo, startDate, endDate);
    }

    /**
     * @return returns cn.bxd.sip.bxd.webservice.invoke.pay.TQueryPaymentRecordStatusRespond
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
    @Override
    public QueryPaymentRecordStatusRespond queryPaymentRecordStatus(String synUserName,
                                                                    String synKey,
                                                                    String terminalCode,
                                                                    String hospitalId,
                                                                    String patientNo,
                                                                    String hiFeeNos,
                                                                    String extend) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        TQueryPaymentRecordStatusRespond TQueryPaymentRecordStatusRespond = payPort.queryPaymentRecordStatus(synUserName, synKey, terminalCode,
                hospitalId, patientNo, hiFeeNos, extend);
        //获取返回的类
        QueryPaymentRecordStatusRespond queryPaymentRecordStatusRespond = new QueryPaymentRecordStatusRespond();
        //转换类
        ReflectUtils.copyFieldValue(TQueryPaymentRecordStatusRespond, queryPaymentRecordStatusRespond);
        return queryPaymentRecordStatusRespond;
    }

    /**
     * @return returns cn.bxd.sip.bxd.webservice.invoke.pay.TBaseRespond
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param hiFeeNos     缴费编号(注多个可以逗号分开如 112，2222)
     * @Param extend       扩展字段Json值，如：     * {“key1”：”value1”，”key2”：”value2”}
     * @Description 1.4.4    更新打票状态
     */
    @Override
    public BaseRespond updateRecipeStatusStatus(String synUserName,
                                                String synKey,
                                                String terminalCode,
                                                String hospitalId,
                                                String patientNo,
                                                String hiFeeNos,
                                                String extend) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        TBaseRespond tBaseRespond = payPort.updateRecipeStatusStatus(synUserName, synKey, terminalCode,
                hospitalId, patientNo, hiFeeNos, extend);
        //获取返回的类
        BaseRespond baseRespond = new BaseRespond();
        //转换类
        ReflectUtils.copyFieldValue(tBaseRespond, baseRespond);
        return baseRespond;
    }

    /**
     * @return returns java.lang.String
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
     * @Description 1.4.5    预结算
     */
    @Override
    public String preDoPay(String synUserName,
                           String synKey,
                           String terminalCode,
                           String hospitalId,
                           String hiFeeNos,
                           String payType,
                           String socialsecurityNO,
                           String hospitalNO,
                           String operatorNo,
                           String cycleNo,
                           String dynamic,
                           String medicareType,
                           String cardinfo) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        return payPort.preDoPay(synUserName,
                synKey,
                terminalCode,
                hospitalId,
                hiFeeNos,
                payType,
                socialsecurityNO,
                hospitalNO,
                operatorNo,
                cycleNo,
                dynamic,
                medicareType,
                cardinfo);
    }

    /**
     * @return returns java.lang.String
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
     * @Description 1.4.6    生成订单
     */
    @Override
    public MakeProductOrderRespond makeProductOrder(String synUserName,
                                   String synKey,
                                   String terminalCode,
                                   String hospitalId,
                                   String hiFeeNos,
                                   String payType,
                                   String poType,
                                   String socialsecurityNO,
                                   String poAllPrice,
                                   String payMoney,
                                   String isOverall,
                                   String patientNo,
                                   String patientName,
                                   String cardId,
                                   String sex,
                                   String medicareType,
                                   String cardinfo,
                                   String medicareMess,
                                   String originalPrice,
                                   String appId) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        return payPort.makeProductOrder(synUserName,
                synKey,
                terminalCode,
                hospitalId,
                hiFeeNos,
                payType,
                socialsecurityNO,
                poAllPrice,
                payMoney,
                isOverall,
                patientNo,
                patientName,
                cardId,
                sex,
                medicareType,
                cardinfo,
                medicareMess,
                originalPrice);
    }

    /**
     * @return returns java.lang.String
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
     * @Description 1.4.7    处方支付
     */
    @Override
    public DoPayRespond doPay(String synUserName,
                        String synKey,
                        String terminalCode,
                        String hospitalId,
                        String hiFeeNos,
                        String payType,
                        String payRecord,
                        String payMoney,
                        String socialsecurityNO,
                        String hospitalNO,
                        String operatorNo,
                        String cycleNo,
                        String dynamic,
                        String bankInformation,
                        String isOverall,
                        String poNo,
                        String buyerAccount,
                        String userNo,
                        String medicareType) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        return payPort.doPay(synUserName,
                synKey,
                terminalCode,
                hospitalId,
                hiFeeNos,
                payType,
                payRecord,
                payMoney,
                socialsecurityNO,
                hospitalNO,
                operatorNo,
                cycleNo,
                dynamic,
                bankInformation,
                isOverall,
                poNo,
                buyerAccount,
                userNo,
                medicareType);
    }

    /**
     * @Param arg0
     * @Description 1.4.8    获取凭条打印信息
     * returns java.lang.String
     */
    @Override
    public String printInfo(String jsonStr) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        return payPort.printInfo(jsonStr);
    }

    /**
     * @return returns cn.bxd.sip.bxd.webservice.invoke.pay.TTradePrecreateRespond
     * @Param synUserName           用户名
     * @Param synKey                效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param poNo                  订单号
     * @Param payType               1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
     * @Description 1.4.9    生成支付二维码
     */
    @Override
    public TradePrecreateRespond trade_precreate(String synUserName,
                                                 String synKey,
                                                 String terminalCode,
                                                 String hospitalId,
                                                 String poNo,
                                                 String payType) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        TTradePrecreateRespond tTradePrecreateRespond = payPort.tradePrecreate(synUserName, synKey, terminalCode, hospitalId, poNo, payType);
        //获取返回的类
        TradePrecreateRespond tradePrecreateRespond = new TradePrecreateRespond();
        //转换类
        ReflectUtils.copyFieldValue(tTradePrecreateRespond, tradePrecreateRespond);
        return tradePrecreateRespond;
    }

    public static void main(String[] args) {
        Pay2WebService pay2 = new Pay2WebService();
        TradePrecreateRespond tradePrecreateRespond  = pay2.trade_precreate("", "", "", "14037", "","");
        System.out.println(tradePrecreateRespond.toString());
    }

    /**
     * @return returns cn.bxd.sip.bxd.webservice.invoke.pay.TQueryOrderStatusRespond
     * @Param synUserName          用户名
     * @Param synKey          效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId          医院ID
     * @Param poNo          订单号
     * @Param payType          1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
     * @description 1.4.10    查询支付状态
     */
    @Override
    public QueryOrderStatusRespond query_orderStatus(String synUserName,
                                                     String synKey,
                                                     String terminalCode,
                                                     String hospitalId,
                                                     String poNo,
                                                     String payType) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        TQueryOrderStatusRespond tQueryOrderStatusRespond = payPort.queryOrderStatus(synUserName, synKey, terminalCode, hospitalId, poNo, payType);
        //获取返回的类
        QueryOrderStatusRespond queryOrderStatusRespond = new QueryOrderStatusRespond();
        //转换类
        ReflectUtils.copyFieldValue(tQueryOrderStatusRespond, queryOrderStatusRespond);
        return queryOrderStatusRespond;
    }

    /**
     * @return returns cn.bxd.sip.bxd.webservice.invoke.pay.TMakeProductOrderRespond
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
     * @description 1.4.11    挂号生成订单
     */
    @Override
    public MakeProductOrderRespond makeRegProductOrder(String synUserName,
                                                       String synKey,
                                                       String terminalCode,
                                                       String hospitalId,
                                                       String poType,
                                                       String objectId,
                                                       String poAllPrice,
                                                       String patientNo,
                                                       String patientName,
                                                       String cardId,
                                                       String socialsecurityNO,
                                                       String userNo,
                                                       String medicareType,
                                                       String medicareInfo,
                                                       String extend,
                                                       String appId) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        TMakeProductOrderRespond tMakeProductOrderRespond = payPort.makeRegProductOrder(synUserName,
                synKey,
                terminalCode,
                hospitalId,
                poType,
                objectId,
                poAllPrice,
                patientNo,
                patientName,
                cardId,
                socialsecurityNO,
                userNo,
                medicareType,
                medicareInfo,
                extend);
        //获取返回的类
        MakeProductOrderRespond makeProductOrderRespond = new MakeProductOrderRespond();
        //转换类
        ReflectUtils.copyFieldValue(tMakeProductOrderRespond, makeProductOrderRespond);
        return makeProductOrderRespond;
    }

    /**
     * @return returns cn.bxd.sip.bxd.webservice.invoke.pay.TBaseRespond
     * @Param synUserName           用户名
     * @Param synKey                效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param poNo                  订单号
     * @Param socialsecurityNO      社保卡号
     * @Param patientNo             患者编号
     * @Param poAllPrice            医保支付总金额
     * @description 1.4.12    医保回退
     */
    @Override
    public BaseRespond yibaoFallback(String synUserName,
                                     String synKey,
                                     String terminalCode,
                                     String hospitalId,
                                     String poNo,
                                     String socialsecurityNO,
                                     String patientNo,
                                     String poAllPrice) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        TBaseRespond tBaseRespond = payPort.yibaoFallback(synUserName,
                synKey,
                terminalCode,
                hospitalId,
                poNo,
                socialsecurityNO,
                patientNo,
                poAllPrice);
        //获取返回的类
        BaseRespond baseRespond = new BaseRespond();
        //转换类
        ReflectUtils.copyFieldValue(tBaseRespond, baseRespond);
        return baseRespond;
    }

    /**
     * @return returns cn.bxd.sip.bxd.webservice.invoke.pay.TBaseRespond
     * @Param synUserName           用户名
     * @Param synKey                效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param poNo                  订单号
     * @Param socialsecurityNO      社保卡号
     * @Param patientNo             患者编号
     * @Param poAllPrice            医保支付总金额
     * @description 1.4.13    支付宝微信回退
     */
    @Override
    public BaseRespond tradeFallback(String synUserName,
                                     String synKey,
                                     String terminalCode,
                                     String hospitalId,
                                     String poNo,
                                     String socialsecurityNO,
                                     String patientNo,
                                     String poAllPrice) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        TBaseRespond tBaseRespond = payPort.tradeFallback(synUserName,
                synKey,
                terminalCode,
                hospitalId,
                poNo,
                socialsecurityNO,
                patientNo,
                poAllPrice);
        //获取返回的类
        BaseRespond baseRespond = new BaseRespond();
        //转换类
        ReflectUtils.copyFieldValue(tBaseRespond, baseRespond);
        return baseRespond;
    }

    /**
     * @return returns cn.bxd.sip.bxd.webservice.invoke.pay.TDoRegPayRespond
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
    @Override
    public DoRegPayRespond doRegPay(String synUserName,
                                    String synKey,
                                    String terminalCode,
                                    String hospitalId,
                                    String operatorNo,
                                    String cycleNo,
                                    String dynamic,
                                    String poNo,
                                    String payMoney,
                                    String extend) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        TDoRegPayRespond tDoRegPayRespond = payPort.doRegPay(synUserName,
                synKey,
                terminalCode,
                hospitalId,
                operatorNo,
                cycleNo,
                dynamic,
                poNo,
                payMoney,
                extend);
        //获取返回的类
        DoRegPayRespond doRegPayRespond = new DoRegPayRespond();
        //转换类
        ReflectUtils.copyFieldValue(tDoRegPayRespond, doRegPayRespond);
        return doRegPayRespond;
    }

    /**
     * @return returns cn.bxd.sip.bxd.webservice.invoke.pay.TTradeCloseRespond
     * @Param synUserName           用户名
     * @Param synKey            效验码
     * @Param terminalCode          终端编号
     * @Param hospitalId            医院ID
     * @Param poNo          订单号
     * @Param payType           支付方式，2支付宝 5、微信
     * @description 1.4.15        交易关闭
     */
    @Override
    public TradeCloseRespond trade_close(String synUserName,
                                         String synKey,
                                         String terminalCode,
                                         String hospitalId,
                                         String poNo,
                                         String payType) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        TTradeCloseRespond tTradeCloseRespond = payPort.tradeClose(synUserName, synKey, terminalCode, hospitalId, poNo, payType);
        //获取返回的类
        TradeCloseRespond tradeCloseRespond = new TradeCloseRespond();
        //转换类
        ReflectUtils.copyFieldValue(tTradeCloseRespond, tradeCloseRespond);
        return tradeCloseRespond;
    }

	@Override
	public BaseRespond updateRecipeRecordStatus(String synUserName, String synKey, String terminalCode,
			String hospitalId, String hiFeeNos, String extend) {
        TPayService tPayService = TPayService.getInstance();
        TIPay payPort = tPayService.getPayPort();
        BaseRespond baseRespond = payPort.updateRecipeRecordStatus(synUserName, synKey, terminalCode, hospitalId, hiFeeNos, extend);
        //获取返回的类
        BaseRespond tradeCloseRespond = new BaseRespond();
        //转换类
        ReflectUtils.copyFieldValue(baseRespond, tradeCloseRespond);
        return tradeCloseRespond;
	}

    @Override
    public String queryOrderRecords(String synUserName, String synKey,String startTime, String endTime, String orderNo, String hospitalId) {
        return null;
    }
}
