package cn.bxd.sip.bxd.var;


import cn.bxd.sip.bxd.model.entity.PayParm;

import java.util.Map;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-07-20
 * Time: 10:13
 */
public class PayConst {
    public static final String URL_TOKEN=AppVar.Setting.PAY_SERVICE_IP+"Token";
    public static final String URL_PAY=AppVar.Setting.PAY_SERVICE_IP+"Pay";

    public static final String RETRUN_CODE = "returnCode";
    public static final String RESULT_CODE = "resultCode";
    public static final String RETRUN_MSG = "returnMsg";
    public static final String RETRUN_DATA = "returnData";
    public static final String RETRUN_SUCCESS_CODE = "SUCCESS";
    public static final String RESULT_SUCCESS_CODE = "SUCCESS";
    public static final String RETRUN_FAIL_CODE = "FAIL";
    public static final String RESULT_FAIL_CODE = "FAIL";

    public static class Client{
        public static final String APP = "app";
        public static final String H5 = "h5";
    }

    public static class HTTPReq {

        public static final String SUCCESS = "SUCCESS";

        public static final String FAIL = "FAIL";


        /**
         * token
         */
        public static Map<String,PayParm> TOKEN_MAP;

        /**
         * session key
         */
        public static String SESSION_KEY;

        /**
         * 支付回调地址
         */
        //public static String PAY_SUCCESS_RETURN_URL = AppVar.Setting.SELF_IP +"/user/index.do?goBack=1";

    }

    public static class PayCode {
        /**
         * 返回代码
         */
        public static final String RETRUN_CODE = "return_code";
        /**
         * 返回信息
         */
        public static final String RETRUN_MSG = "return_msg";
        /**
         * 返回成功
         */
        public static final String SUCCESS = "SUCCESS";
        /**
         * 返回失败
         */
        public static final String FAIL = "FAIL";
        /**
         * 支付成功
         */
        public static final String NOTIFY_PAY_SUCCESS = "NOTIFY_PAY_SUCCESS";
        /**
         * 退款成功
         */
        public static final String NOTIFY_REFUND_SUCCESS = "NOTIFY_REFUND_SUCCESS";
        /**
         * 支付中
         */
        public static final String NOTIFY_PAYING = "NOTIFY_PAYING";
        /**
         * 微信客户端
         */
        public static final String WX_CLIENT_CODE = "MicroMessenger";
        /**
         * 支付宝客户端
         */
        public static final String ALI_CLIENT_CODE = "AlipayClient";
        /**
         * 支付取消标志
         */
        public static final int PAY_CANCEL_FLAGE = 1;
    }

    public class ErrorCode {
        /**
         * 系统异常
         */
        public static final String SYSTEM_EXCEPTION = "SYSTEM_EXCEPTION";
        /**
         * 账户编码或者认证密码错误
         */
        public static final String ACCOUNT_ERROR = "ACCOUNT_ERROR";
        /**
         * 读取参数异常
         */
        public static final String PARAMETER_EXCEPTION = "PARAMETER_EXCEPTION";
        /**
         * token失效
         */
        public static final String TOKEN_INVALID = "TOKEN_INVALID";
        /**
         * token为空
         */
        public static final String TOKEN_IS_EMPTY = "TOKEN_IS_EMPTY";
        /**
         * authorCode为空
         */
        public static final String AUTHOR_CODE_IS_EMPTY = "AUTHOR_CODE_IS_EMPTY";
        /**
         * 认证异常
         */
        public static final String AUTHORIZE_EXCEPTION = "AUTHORIZE_EXCEPTION";
        /**
         * 认证失败
         */
        public static final String AUTHORIZE_FAIL = "AUTHORIZE_FAIL";
        /**
         * 签名错误
         */
        public static final String SIGN_ERROR = "SIGN_ERROR";
        /**
         * 验签异常
         */
        public static final String SIGN_EXCEPTION = "SIGN_EXCEPTION";
        /**
         * 未知支付渠道
         */
        public static final String UNKNOWN_PROVIDER = "UNKNOWN_PROVIDER";
        /**
         * 证书文件不存在
         */
        public static final String CERT_FILE_IS_NOT_EXIST = "CERT_FILE_IS_NOT_EXIST";
        /**
         * 证书加载失败
         */
        public static final String CERT_FILE_LOAD_FAIL = "CERT_FILE_LOAD_FAIL";
        /**
         * 支付中通知失败
         */
        public static final String NOTIFY_PAYING_FAIL = "NOTIFY_PAYING_FAIL";
        /**
         * 支付完成通知失败
         */
        public static final String NOTIFY_PAY_FAIL = "NOTIFY_PAY_FAIL";
        /**
         * 退款完成通知失败
         */
        public static final String NOTIFY_REFUND_FAIL = "NOTIFY_REFUND_FAIL";
        /**
         * 不存在
         */
        public static final String NOT_EXIST = "NOT_EXIST";
        /**
         * 该笔业务已经支付，不能重复支付
         */
        public static final String HAS_PAY = "HAS_PAY";
        /**
         * 该笔业务已经退款，不能支付/退款
         */
        public static final String HAS_REFUND = "HAS_REFUND";
        /**
         * 该笔业务已经取消，不能支付/取消
         */
        public static final String HAS_CANCEL = "HAS_CANCEL";
        /**
         * 没有原因
         */
        public static final String NO_REASON = "NO_REASON";
        /**
         * 返回token错误代码
         */
        public static final String HAS_TOKEN_ERR_CODE = "TOKEN";

    }

    /**
     * 支付渠道类型
     */
    public static class ProviderTypeCode{
        public static final String PROVIDER_TYPE_ALI="ALIPAY";
        public static final String PROVIDER_TYPE_WXPAY ="WXPAY";
    }

    public static class Trans{
        /**
         * 支付类型:微信
         */
        public static final short PROVIDER_WEI_XIN = 1;
        /**
         * 支付类型 :支付宝
         */
        public static final short PROVIDER_ALIPAY = 2;
        /**
         * 支付类型  网银
         */
        public static final short PROVIDER_BACK = 3;
    }

}
