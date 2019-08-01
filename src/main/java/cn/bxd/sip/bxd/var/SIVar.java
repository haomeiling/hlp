package cn.bxd.sip.bxd.var;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @Author haomeiling
 * @Date 2018-03-05
 */
@Component
public class SIVar {

    /**
     * 操作码
     */
    public class MsgType {

        /**
         * 【查询业务】 110
         **/
        /* 参保人信息查询 */
        public static final String OPERATION_OF_GET_USER_INFO = "110101";
        /* 参保人险种查询 */
        public static final String OPERATION_OF_GET_INSURANCE_TYPE = "110102";
        /* 社保缴费查询 */
        public static final String OPERATION_OF_GET_PAYMENT_LIST = "110103";
        /* 医保账户费用查询 */
        public static final String OPERATION_OF_GET_ACCOUNT_PAYMENT = "110104";
        /* 就诊费用查询 */
        public static final String OPERATION_OF_GET_MEDICAL_LIST = "110105";
        /* 就诊费用明细查询 */
        public static final String OPERATION_OF_GET_MEDICAL_ITEM_LIST = "110106";
        /* 医保卡信息查询 */
        public static final String OPERATION_OF_GET_CARD_INFO = "110107";
        /* 账户注入 */
        public static final String OPERATION_OF_GET_INCOME = "110108";
        /* 居民医保缴费查询 */
        public static final String OPERATION_OF_GET_PAYMENT_4_RESIDENT = "110109";
        /* 社保政策查询 */
        public static final String OPERATION_OF_GET_POLICY_LIST = "110110";
        /* 政策详细查询 */
        public static final String OPERATION_OF_GET_POLICY_DETAIL = "110111";
        /* 授权账户信息查询 */
        public static final String OPERATION_OF_GET_USER_LIST = "110112";
        /* 个人编码查询 */
        public static final String OPERATION_OF_GET_USER_CODE = "110113";
        /* 二级代码查询 */
        public static final String OPERATION_OF_GET_LEVEL_CODE = "110114";
        /* 读卡信息接口（人脸识别）(110115) */
        public static final String OPERATION_OF_GET_CARD_FACE = "110115";
        /**
         * 【医疗业务】 310
         **/
        /* 医保个账授权添加（310101） */
        public static final String MEDICAL_OF_ACCOUNT_ACCREDIT_ADD = "310101";
        /* 医保个账授权取消（310102） */
        public static final String MEDICAL_OF_ACCOUNT_ACCREDIT_CANCEL = "310102";
        /* 医保个账授权查询（310103） */
        public static final String MEDICAL_OF_GET_ACCOUNT_ACCREDIT_LIST = "310103";
        /* his接口服务，统一交易入口（310999） */
        public static final String SYNTHETICAL_OF_HIS_SERVICE = "310999";
        /* 医保测试入口（310000） */
        public static final String SYNTHETICAL_OF_HIS_TEST = "310000";
        /**
         * 【缴费业务】 610
         **/
        /* 查询可缴费信息（610101） */
        public static final String PAYMENT_OF_GET_PAYMENT_INFO = "610101";
        /* 查询可缴费明细（610102） */
        public static final String PAYMENT_OF_GET_PAYMENT_DETAIL = "610102";
        /* 缴纳保险费（610103） */
        public static final String PAYMENT_OF_PAYMENT = "610103";
        /* 缴费抹账（610104） */
        public static final String PAYMENT_OF_PAYMENT_CANCEL = "610104";
        /* 到账通知（610105） */
        public static final String PAYMENT_OF_PAY_NOTICE = "610105";
        /* 缴费对账（610106） */
        public static final String PAYMENT_OF_PAYMENT_CHECK = "610106";
        /* 查询缴费订单（610107） */
        public static final String PAYMENT_OF_GET_PAYMENT_ORDER = "610107";
        /* 查询未对账信息（610108） */
        public static final String PAYMENT_OF_GET_PAYMENT_NOCHECK_LIST = "610108";
        /* 查询已对账信息（610109） */
        public static final String PAYMENT_OF_GET_PAYMENT_CHECK_LIST = "610109";
        /**
         * 【综合业务】 710
         **/
        /* 社保卡临时挂失（冻结）（710101） */
        public static final String SYNTHETICAL_OF_CARD_REPORT_LOSS = "710101";
        /* 制卡进度查询（710102） */
        public static final String SYNTHETICAL_OF_CARD_SCHEDULE = "710102";
        /* 电子社保卡查询（710103） */
        public static final String SYNTHETICAL_OF_CARD_ELECTRONICS = "710103";
        /* 查询认证信息（710104） */
        public static final String SYNTHETICAL_OF_REAL = "710104";
    }

    /**
     * 结果码
     */
    public class ResultCode {

        /* 90000 事务处理成功 */
        public static final String RESULT_CODE_90000 = "90000";
        /* 90001 消息格式错误，不是合法的SIMSG消息结构 */
        public static final String RESULT_CODE_90001 = "90001";
        /* 90002 数字签名错误,消息已经被非法篡改 */
        public static final String RESULT_CODE_90002 = "90002";
        /* 90003 XML语法错误，通不过消息格式语法检查 */
        public static final String RESULT_CODE_90003 = "90003";
        /* 90004 非法的消息编码 */
        public static final String RESULT_CODE_90004 = "90004";
        /* 90005 社保业务判断处理失败。社保端返回异常信息都用此结果代码。 */
        public static final String RESULT_CODE_90005 = "90005";
        /* 90101 系统处理程序出现异常 */
        public static final String RESULT_CODE_90101 = "90101";
        /* 90104 系统响应超时，申请服务失败 */
        public static final String RESULT_CODE_90104 = "90104";
        /* 90105 没有操作权限 */
        public static final String RESULT_CODE_90105 = "90105";
        /* 成功 */
        public static final String RESULT_CODE_SUCCESS = "SUCCESS";
        /* 失败 */
        public static final String RESULT_CODE_FALI = "FALI";
        public static final String OPERATE_CODE_OF_ADD_OR_EDIT = "addOrEdit";
        public static final String OPERATE_CODE_OF_DEL = "del";
    }

    public class BusinessCode {

        /* 读卡交易(2100) */
        public static final String MEDICAL_OF_HIS_READ_CARD = "2100";
        /* 门诊/住院登记(2210) */
        public static final String MEDICAL_OF_HIS_CHECK_IN = "2210";
        /* 登记撤销(2240) */
        public static final String MEDICAL_OF_HIS_CANCEL_IN = "2240";
        /* 处方明细上报(2310) */
        public static final String MEDICAL_OF_HIS_DETAIL_UPLOAD = "2310";
        /* 处方明细撤销(2320) */
        public static final String MEDICAL_OF_HIS_CANCEL_UPLOAD = "2320";
        /* 费用预结算(2420) */
        public static final String MEDICAL_OF_HIS_PAYBEFORE = "2420";
        /* 费用结算(2410) */
        public static final String MEDICAL_OF_HIS_PAYMENT = "2410";
        /* 费用结算撤销(2430) */
        public static final String MEDICAL_OF_HIS_CANCEL_PAYMENT = "2430";
        /* 结算信息查询(发票补打)(1101) */
        public static final String MEDICAL_OF_HIS_SELECTPAYMENT = "1101";
        /* 成功 */
        public static final String RESULT_CODE_SUCCESS = "0";
        /* 失败 */
        public static final String RESULT_CODE_FALI = "1";
    }


    // webservice接口地址
    @Value("${view.server.address}")
    private String address;

    @Value("${view.server.username}")
    private String username;

    @Value("${view.server.password}")
    private String password;

    /* Lisheng 2018/11/13 */
    @Value("${messagecenter.messagecenterUrl}")
    private String messageCenterURL;
    
    @Value("${paygate.url}")
    private String paygate;
    
    @Value("${payCallBack.url}")
    private String payCallBackUrl;

    public static class WS {
        public static String ADDRESS;
        public static String USERNAME;
        public static String PASSWORD;
        public static String MESSAGE_CENTER_URL;
        public static String paygateUrl;
        public static String payCallBackUrl;
    }

    public void ini() {
        WS.ADDRESS = this.address;
        WS.USERNAME = this.username;
        WS.PASSWORD = this.password;
        WS.MESSAGE_CENTER_URL = this.messageCenterURL;
        WS.paygateUrl = this.paygate;
        WS.payCallBackUrl = this.payCallBackUrl;
    }
}
