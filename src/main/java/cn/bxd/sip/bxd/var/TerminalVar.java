package cn.bxd.sip.bxd.var;

/**
 * author : cRyann
 *
 * @create 2018-09-10
 **/
public class TerminalVar {
    public static final String SUCCESS_CODE = "00";
    public static final String NO_DATA_FAIL_CODE = "01";
    public static final String SYSTEM_EXCEPTION_CODE = "01";
    public static final String FAIL_CODE = "01";
    public static final String FAIL_CODE_01 = "01";
    public static final short MAN = 1;
    public static final short WOMEN = 2;
    public static final String STATUS_SUCCESS = "成功";
    public static final String STATUS_FAIL = "失败";

    public static final String PAY_STATUS_SUCCESS = "PAY_SUCCESS";
    public static final String PAY_STATUS_FAIL = "PAY_FAIL";
    public static final String PAY_STATUS_WAITE = "WAIT_BUYER_PAY";
    public static final String MEDICARETYPE_OUTPATIENT = "1";
    public static final String MEDICARETYPE_HOSPITALIZATION = "2";
    public static final String MEDICARETYPE_PHYSICAL_EXAMINATION = "3";
    public static final String MEDICARETYPE_OTHER = "4";


    /*FAIL  REASON*/
    public static final String HOSPITAL_NOT_EXIST = "没有此医院信息";


    /*
     *医疗类型 诊疗类型 1：门诊 2：住院 3：体检4：其他
     */
    public static final String medicare_Type_Outpatient = "1";
    public static final String medicare_Type_Hospitalization = "2";
    public static final String medicare_Type_Physical_examination = "3";
    public static final String medicare_Type_Other = "4";

    /**
     * 成功标志位1：成功；0：失败
     */
    public static final int CANCEL_ORDER_SUCCESS = 1;
    public static final int CANCEL_ORDER_FAIL = 0;


    /**
     * @Author cRyann
     * @Description 社保卡 统筹区域号
     */
    public static final String SOCIALSECURIT_AREA_CODE = "||||450900|||";

    /**
     * @Author cRyann
     * @Description 短信接口调用 参数定义
     */
    public static final String MESSAGE_URL = "http://172.16.11.36:8083/SmsGateWayService/smsSend";
    public static final String MESSAGE_USERNAME = "ceshi";
    public static final String MESSAGE_USERPWD = "123456";
    public static final String MESSAGE_TEMPLATE_CODE = "c81fb43e0e09489ea768298d1c27d3f6_YWxp";
    public static final int MESSAGE_DIGIT = 4;

    /**
     * @Author cRyann
     * @Description 用户注册状态
     */
    public static final String REGISTERED_STATUS = "用户已注册";
    public static final String REGISTER_STATUS = "用户未注册";

    /**
     * @Author cRyann
     * @Description 实名认证信息变量
     */
    // 绑定就诊卡号 1：已绑定；0：未绑定
    public static final short REAL_USER_BING = 1;
    public static final short REAL_USER_UNBING = 0;
    //1:终端识别 2：人脸识别
    public static final long REAL_USER_TERMINAL = 1;
    public static final long REAL_USER_HUMANFACE = 2;
    // 是否外部人员
    public static final short NOT_EXTERNAL_USER = 1;
    public static final short IS_EXTERNAL_USER = 0;
    // 用户状态
    public static final short USER_STATUS_AVAILABLE = 1;
    public static final short USER_STATUS_UNAVAILABLE = 0;

    public class TerminalCode {
        public static final String PALM_TERMINAL_CODE_A = "A";      //扫码支付
        public static final String PALM_TERMINAL_CODE_HIS_MICRO = "HIS_MICRO";         //窗口支付

    }
}
