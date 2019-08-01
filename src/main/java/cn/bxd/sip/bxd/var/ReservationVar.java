package cn.bxd.sip.bxd.var;

import ch.qos.logback.core.pattern.Converter;
import oracle.net.aso.f;

/**
 * @author hml
 * @Description: 业务平台常量定义
 * @date 2015/7/2
 */
public class ReservationVar {

    public static class Result {
        public static final short RESULT_FAIL = 0;
        public static final short RESULT_SUCCESS = 1;
        public static final short RESULT_EXCEPTION = 2;
        public static final short RESULT_NONE = 3;
        public static final short RESULT_EXIST = 4;
        public static final short RESULT_CODE_ERROR = 5;
        public static final short RESULT_NO_RIGHT = 6;
        public static final short RESULT_DATA_ERROR = 7;
        public static final short RESULT_SERVICE_ERROR = 8;
        public static final short RESULT_NOT_LOGIN = 9;
        public static final short RESULT_NOT_CONFIGURE = 10;
        public static final short RESULT_HAS_CANCEL = 11;//已经取消

        public static final String RESULT_STR_FAIL = "FAIL";//失败
        public static final String RESULT_STR_SUCCESS = "SUCCESS";//成功
        public static final String RESULT_STR_EXCEPTION = "EXCEPTION";//异常
        public static final String RESULT_STR_NONE = "NONE";//不存在
        public static final String RESULT_STR_EXIST = "EXIST";//存在
        public static final String RESULT_STR_CODE_ERROR = "CODE_ERROR";//验证码错误
        public static final String RESULT_STR_NO_RIGHT = "NO_RIGHT";//没有权限
        public static final String RESULT_STR_DATA_ERROR = "DATA_ERROR";//参数错误或者数据错误
        public static final String RESULT_STR_SERVICE_ERROR = "SERVICE_ERROR";//服务异常
        public static final String RESULT_STR_NOT_LOGIN = "NOT_LOGIN";//未登陆
        public static final String RESULT_STR_NOT_CONFIGURE = "NOT_CONFIGURE";//未配置
        public static final String RESULT_STR_BIND_ERROR = "BIND_ERROR";//号绑定错误
        public static final String RESULT_STR_HAS_CANCEL = "HAS_CANCEL";//已经取消
    }

    /*
    消息类型
     */
    public static class MsgType {
        /*
      群发
       */
        public static final short ANYONE = 0;
        /*
        单发
         */
        public static final short ONE = 1;

    }

    //服务接口
    public static class Service {
        //号源服务
        public static final String APPOINTMENT_SERVICE = "appointmentService";//号源服务

        //消息服务
        public static final String MSG_SERVICE = "sendMsg";//发送端消息

    }

    public static class Order {
        //订单状态 待确认、已确认、已取消、已完成、已改签、已签到
        /*
       待确认(开启了在线支付，但是还未发起支付)
         */
        public static final short ORDER_STATUS_WAIT_CONFIRMED = 1;
        /*
        已确认
         */
        public static final short ORDER_STATUS_CONFIRMED = 2;//已确认
        /*
        已取消
         */
        public static final short ORDER_STATUS_CANCEL = 3;//已取消
        /*
        已完成
         */
        public static final short ORDER_STATUS_COMPLETE = 4;//已完成
        /*
        已改签
         */
        public static final short ORDER_STATUS_CHANGE = 5;//已改签
        /*
        已签到
         */
        public static final short ORDER_STATUS_ARRIVAL = 6;//已签到


        //订单支付状态
        /*
        未支付
         */
        public static final short PAY_FLAG_NOT_PAY = 0;
        /*
        支付中
         */
        public static final short PAY_FLAG_PAYING = 1;
        /*
        已支付
         */
        public static final short PAY_FLAG_HAS_PAYED = 2;


        //订单类型:预约订单、缴费订单(诊间支付)、预交订单(预交金)、改签订单、取消订单
        /**
         * 预约订单
         */
        public static final short ORDER_TYPE_APPOINTMENT = 1;
        /**
         * 缴费订单(诊间支付)
         */
        public static final short ORDER_TYPE_OUTPATIENT = 2;
        /**
         * 预交订单(预交金（住院押金支付）)
         */
        public static final short ORDER_TYPE_ADVANCE = 3;
        /**
         * 改签订单
         */
        public static final short ORDER_TYPE_ENDORSE = 4;
        /**
         * 取消订单(该类型为前置机定义的类型，平台暂时不使用该类型)
         */
        public static final short ORDER_TYPE_CANCEL = 5;

        /**
         * 签到订单（预约转挂号）      20171117     新增订单类型
         */
        public static final short ORDER_TYPE_ARRIVAL = 6;

        /**
         * 挂号订单(挂号订单)        20171117     新增订单类型
         */
        public static final short ORDER_TYPE_REGISTRATION = 7;

        /**
         * 停车缴费        20180613     江滨医院提出与停止智能系统对接
         */
        public static final short ORDER_TYPE_PARKING =8;

        //订单来源
        /**
         * 来源类型:微信
         */
        public static final short SOURCE_WEI_XIN = 1;
        /**
         * 来源类型 :支付宝
         */
        public static final short SOURCE_ALI = 2;
        /**
         * 来源类型  PC
         */
        public static final short SOURCE_WEB = 3;
        /**
         * 来源类型 APP
         */
        public static final short SOURCE_APP = 4;
        /**
         * 来源类型  现场
         */
        public static final short SOURCE_LOCALE = 5;
        /**
         * 来源类型  HIS
         */
        public static final short SOURCE_HIS = 11;
        /**
         * 来源类型  第三方应用
         */
        public static final short SOURCE_OTHER_APP = 12;

        /**
         * 来源类型  自助机
         */
        public static final short SOURCE_ZD = 13;


    }

    /** tangliang
     * 支付方式  11自费，12社保，20混合
     */

    public class PayPattern{
        /**
         * 自费
         */
        public static final int SELF_FEE = 11;
        /**
         * 社保支付
         */
        public static final int SOSIAL_FEE = 12;
        /**
         * 混合支付
         */
        public static final int MIX_FEE = 20;
    }

    /** tangliang
     *  医保支付状态  医保支付状态：0,未支付，1，支付成功，2失败
     */

    public class MedicarePayState {
        /**
        未支付
         */
        public static final short NOT_PAY = 0;
        /**
         已支付
         */
        public static final short HAS_PAYED = 1;
        /**
        支付失败
         */
        public static final short PAY_FAULT = 2;
    }


    public static class Trans {
        //交易状态
        public static final short STATUS_TRANS_WAITING = 0;//待处理
        public static final short STATUS_TRANS_DEALING = 1;//处理中（支付中、退款中）
        public static final short STATUS_TRANS_COMPLETE = 2;//已处理

        //交易类型
        public static final short TYPE_ID_PAY = 1;//支付公众号/生活号支付
        public static final short TYPE_ID_TRANSFER = 2;//转账
        public static final short TYPE_ID_REFUND = 3;//退款
        public static final short TYPE_ID_PAY_H5 = 4;//网页支付
        public static final short TYPE_ID_PAY_SCAN = 5;//扫码支付
        public static final short TYPE_ID_PAY_MICRO = 6;//刷卡支付
        public static final short TYPE_ID_PAY_NATIVE=7;//app支付

        //交易的支付类型
        /**
         * 支付类型:微信
         */
        public static final short PROVIDER_WEI_XIN = 1;
        /**
         * 支付类型 :支付宝
         */
        public static final short PROVIDER_ALIPAY = 2;
        /**
         * 支付类型  PC
         */
        public static final short PROVIDER_WEB = 3;
        /**
         * 支付类型 APP
         */
        public static final short PROVIDER_APP = 4;
        /**
         * 支付类型  现场
         */
        public static final short PROVIDER_LOCALE = 5;
    }

    public static class HospitalId {
        public static final int HM = 0;
    }


    public static class AppID {
        /**
         * 健康信息惠民平台
         */
        public static final String APPID_BPHIV10 = "BPHIV10";
        /**
         * 掌上医院系统
         */
        public static final String APPID_PALMHV10 = "PALMHV10";
        /**
         * 统一预约挂号平台
         */
        public static final String APPID_URSPV10 = "URSPV10";
        /**
         * 医疗统一支付平台
         */
        public static final String APPID_UMPAYV10 = "UMPAYV10";
        /**
         * 基于信息惠民平台的排班、号源及预约管理系统
         */
        public static final String APPID_OPSAASV10 = "OPSAASV10";
        /**
         * 终端系统
         */
        public static final String APPID_ZDIV10 = "ZDIV10";
        
        /**
                  * 云pos
         */
        public static final String APPID_YZDIV10 = "YZDIV10";

    }

    public static class OnlinePay {
        /**
         * 关闭在线支付
         */
        public static final short close = 0;
        /**
         * 启用在线支付
         */
        public static final short open = 1;
    }

    public static class Is {
        public static final short FALSE = 0;
        public static final short TRUE = 1;
    }



    /*
    异步事务状态类型
     */
    public static class SyncStatusCode {
        /*.
        未处理状态
         */
        public static final String UNDO = "UNDO";

    }


    public static class Code {
        public static final String SUCCESS = "SUCCESS";
        public static final String FAIL = "FAIL";
    }

    public static class Count {
        public static final int TYPE_ORDER = 1;//预约数
        public static final int TYPE_BREAK_ORDER = 2;//爽约数
    }

    /*
   外部账户类别代码表
    */
    public static class ExtUserType {
        /*
        微信
         */
        public static final short WECHAT = 1;
        /*
        阿里云
         */
        public static final short ALI = 2;

    }

    /**
     * @author：liangshangsong
     * @Description：短信用户类型
     * @Date：2016年11月24日 下午4:46:55
     */
    public static class SmsUserType {
        /**
         * 卫计委
         */
        public static final short GXWS = 0;

        /**
         * 平台自身
         */
        public static final short SELF = 1;
    }


    /*
    阿里消息格式类型
     */
    public static class AliMsgType {
        public static final String ALI_TEXT_MSG = "text";  //文本消息
        public static final String ALI_IMG_MSG = "image-text";  //图文消息
    }

    public static class SmgOperateCode {
        //预约挂号成功通知
        public static final int RESERVATION_SUCCESS_NOTIFICATION = 161201;
        //支付成功通知
        public static final int PAY_SUCCESS_NOTIFICATION = 161202;
        //退费通知
        public static final int REFUND_SUCCESS_NOTIFICATION = 161203;
        //就诊通知
        public static final int VISIT_DOCTOR_NOTIFICATION = 161204;
        //预约自动取消通知
        public static final int RESERVATION_AUTO_CANCEL_NOTIFICATION = 161205;
        //挂号取消通知
        public static final int RESERVATION_CANCEL_NOTIFICATION = 161206;
        //候诊排队通知
        public static final int WAITING_QUEUE_NOTIFICATION = 161207;
        //取药通知
        public static final int GET_DRUG_NOTIFICATION = 161208;
        //停诊通知
        public static final int CLOSE_SERVICE_NOTIFICATION = 161209;
        //体检报告通知
        public static final int MEDECAL_EXAMINATION_REPORT = 161210;
        //缴费提醒
        public static final int PAY_REMINDER_NOTIFICATION = 161211;

        //预约成功通知
        public static final int RESERVATION_SUCCESS_NOTIFICATION2 = 161212;
        //挂号成功通知
        public static final int REGISTRATION_SUCCESS_NOTIFICATION = 161213;

    }

    /**
     * 错误代码定义
     */
    public static class ErrorCode {
        /**
         * 系统错误
         */
        public static final String SYSTEM_ERROR = "-20000";
        /**
         * 医院ID为空
         */
        public static final String INVALID_HOSPITAL_ID = "-20001";
        /**
         * 队列ID为空
         */
        public static final String INVALID_QUEUE_ID = "-20002";
        /**
         * 时段序号为空
         */
        public static final String INVALID_PERIOD_NO = "-20003";
        /**
         * 无操作权限
         */
        public static final String HAS_NO_RIGHT = "-20004";
        /**
         * 退款请求错误
         */
        public static final String ERROR_REFUND_REQUEST = "-20005";
        /**
         * 创建订单异常
         */
        public static final String ERROR_CREATE_ORDER = "-20006";
        /**
         * 订单不存在
         */
        public static final String ORDER_NO_EXIST = "-20007";

        /**
         * 订单已经取消,不能重复取消
         */
        public static final String ORDER_HAS_CANCEL = "-20008";

    }

    /**
     * 通讯返回标识
     */
    public static class ReturnCode {
        public static final String SUCCESS = "SUCCESS";
        public static final String FAIL = "FAIL";
    }

    /**
     * 业务返回，业务处理成功或者失败
     */
    public static class ResultCode {
        public static final String SUCCESS = "SUCCESS";
        public static final String FAIL = "FAIL";
    }

    /**
     * 支付类型
     * 数据库： 1微信，2支付宝 , 3现场支付, 4医保账户，5银联，6建行，7中行,8招行,9、交通银行
     */
    public static class PayType {
        /*
        微信
         */
        public static final short PAY_TYPE_WXPAY = 1;
        /*
        支付宝
         */
        public static final short PAY_TYPE_ALIPAY = 2;
        /*
        现场支付
         */
        public static final short PAY_TYPE_SPOT = 3;
        /*
        医保支付
         */
        public static final short PAY_TYPE_SI = 4;
        /*
        银联
         */
        public static final short PAY_TYPE_BACK = 5;
        /*
        建行
         */
        public static final short PAY_TYPE_JIANHANG = 6;
        /*
        中行
      */
        public static final short PAY_TYPE_ZHONGHANG = 7;
        /*
      招行
       */
        public static final short PAY_TYPE_ZHAOHANG = 8;
        /*
        交行
         */
        public static final short PAY_TYPE_JIAOHANG=9;
        
        public static String convert(String hPayType) {
        	return String.valueOf(ReservationVar.HPayType.PAY_TYPE_WXPAY).equals(hPayType) ? 
        			String.valueOf(PAY_TYPE_WXPAY) : String.valueOf(ReservationVar.HPayType.PAY_TYPE_ALIPAY).equals(hPayType) ?
        			String.valueOf(PAY_TYPE_ALIPAY) : hPayType;
        }
        
    }


    /**
     * 1银联，2支付宝 3，现场支付 4、医保，5、微信，6、建行，7、中行 ，8 招行，9、交通银行
     */

    public static class HPayType {
        /*
        微信
         */
        public static final short PAY_TYPE_WXPAY = 5;
        /*
        支付宝
         */
        public static final short PAY_TYPE_ALIPAY = 2;
        /*
        现场支付
         */
        public static final short PAY_TYPE_SPOT = 3;
        /*
        医保支付
         */
        public static final short PAY_TYPE_SI = 4;
        /*
        银联
         */
        public static final short PAY_TYPE_BACK = 1;
        /*
        建行
         */
        public static final short PAY_TYPE_JIANHANG = 6;
        /*
        中行
      */
        public static final short PAY_TYPE_ZHONGHANG = 7;
        /*
      招行
       */
        public static final short PAY_TYPE_ZHAOHANG = 8;
        /*
        交行
         */
        public static final short PAY_TYPE_JIAOHANG=9;
    }

    public static class Gender{
        public static final short UNKNOW=1;//未知的性别
        public static final short MAN=2;//男性
        public static final short WOMAN=3;//女性
        public static final short NO=4;//未说明的性别


        public static final String MAN_CH="男";
        public static final String WOMAN_CH="女";

    }


}
