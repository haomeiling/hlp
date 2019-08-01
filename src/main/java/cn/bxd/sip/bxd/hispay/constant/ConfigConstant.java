package cn.bxd.sip.bxd.hispay.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:33
 */
public class ConfigConstant {
    // 数字签名信息
    @Value("#{settings['withDsign']}")
    private String withDsign;
    @Value("#{settings['privateDsignDn']}")
    private String privateDsignDn;
    @Value("#{settings['publicDsignDn']}")
    private String publicDsignDn;
    @Value("#{settings['privateFile']}")
    private String privateFile;
    @Value("#{settings['publicFile']}")
    private String publicFile;

    // 医院his信息
    @Value("#{settings['orgID']}")
    private String orgID;
    @Value("#{settings['orgPwd']}")
    private String orgPwd;
    @Value("#{settings['orgTimeoutSeconds']}")
    private int orgTimeoutSeconds;
    @Value("#{settings['orgTimeoutHandShake']}")
    private int orgTimeoutHandShake;
    @Value("#{settings['limitTimeOutSeconds']}")
    private int limitTimeOutSeconds;
    @Value("#{settings['his_database_type']}")
    private String hisDatabaseType;
    @Value("#{settings['connect_plan']}")
    private short connPlan;

    // MQ信息
    @Value("#{settings['mq_url']}")
    private String MQUrl;
    @Value("#{settings['mq_user_name']}")
    private String MQUserName;
    @Value("#{settings['mq_user_pwd']}")
    private String MQUserPwd;
    @Value("#{settings['mq_pl_queue']}")
    private String MQPLQueue;
    @Value("#{settings['mq_reg_queue']}")
    private String MQRegQueue;

    // 惠民平台信息
    @Value("#{settings['platform_invoke_type']}")
    private String platformInvokeType;
    // webservice
    @Value("#{settings['platform_ws_url']}")
    private String platformWsUrl;
    @Value("#{settings['platform_ws_method']}")
    private String platformWsMethod;

    // to his webservice
    @Value("#{settings['his_webservice_url']}")
    private String hisWebserviceUrl;

    // restful
    @Value("#{settings['platform_rest_url']}")
    private String platformRestUrl;
    @Value("#{settings['platform_rest_param']}")
    private String platformRestParam;

    // 查询订单信息
    @Value("#{settings['reservation_order_flag']}")
    private Integer orderFlag;

    // 版本信息
    @Value("#{settings['version']}")
    private String version;

    // 是否前置机设置号源
    @Value("#{settings['pre_setting_source']}")
    private String pre_setting_source;

    // 前置机设置号源类型
    @Value("#{settings['source_type']}")
    private String source_type;

    // sms相关参数短信
    @Value("#{settings['dbIp']}")
    private String dbIp;
    @Value("#{settings['dbName']}")
    private String dbName;
    @Value("#{settings['apiCode']}")
    private String apiCode;
    @Value("#{settings['dbUser']}")
    private String dbUser;
    @Value("#{settings['dbPwd']}")
    private String dbPwd;

    /** 对外提供的变量 **/
    public static class Constant {

        /*** 是否初始化 */
        public static boolean ISINIT = false;

        // 数字签名
        /*** 是否启动数字签名 */
        public static boolean WITHDSIGN = false;
        /** 私钥 */
        public static String PRIVATE_DSIGN;
        /** 公钥 */
        public static String PUBLIC_DSIGN;
        /** 自身私钥文件 */
        public static String PRIVATE_FILE;
        /** 平台公钥 */
        public static String PUBLIC_FILE;

        // 医院登录帐号密码
        /*** 医院编码 */
        public static String ORG_ID;
        /*** 医院密码 */
        public static String ORG_PWD;
        /*** 医院号源超时未绑定时间（s） */
        public static int ORG_QNO_TIMEOUTSECONDS;
        /*** 医院号超时握手时间（s） */
        public static int ORG_HANDSHAKE_TIMEOUTSECONDS;
        /** 医院现场挂号限制多少时间（s）之内不得挂号 **/
        public static int ORG_LIMIT_TIMEOUTSECONDS;
        /** his数据库类型 **/
        public static String HIS_DATABASE_TYPE;
        /** 医院采用的接入方案，分方案1，2，3，4 **/
        public static short CONN_PLAN;

        // MQ信息
        /** MQUrl */
        public static String MQ_URL;
        /** 前置系统MQ用户名 */
        public static String MQ_USERNAME;
        /** 前置系统MQ密码 */
        public static String MQ_USRPWD;
        /** 平台队列 */
        public static String MQ_PL_QUEUE;
        /** 前置系统队列 */
        public static String MQ_REG_QUEUE;

        // 平台相关信息
        /** 惠民平台调用方式 */
        public static String RSV_INVOKETYPE;
        // webservice
        /** 惠民平台调用地址 */
        public static String RSV_WS_URL;
        /** 惠民平台调用函数 */
        public static String RSV_WS_METHOD;
        /** 江滨医院ws地址 */
        public static String HIS_WS_URL;

        // restful
        /** 平台restful地址 */
        public static String RSV_REST_URL;
        /** 平台restful参数 */
        public static String RSV_REST_PARAM;

        // 订单查询配置
        /** 标识签到时获取订单的是那个数据库 */
        public static Integer ORDER_FLAG;

        // 版本信息
        /** 标识版本信息 **/
        public static String VERSION;

        // 号源设置
        /** 是否前置机设置号源 **/
        public static String PRE_SETTING_SOURCE;
        /** 1-统一号源 2-前置机号源，3-his号源，4-统一号源、his双号源并轨，5-前置机、his双号源并轨 **/
        public static String PRE_SETTING_SOURCE_TYPE;
    }

    /**
     *
     * @Description：短信相关
     *
     * @author：liangshangsong
     *
     * 						2016年11月10日 下午4:33:20
     */
    public static class SMSConstant {
        /*** ip或url */
        public static String DB_IP;
        /** 数据库 **/
        public static String DB_NAME;
        /*** API编码 */
        public static String API_CODE;
        /*** 用户名 */
        public static String DB_USER;
        /*** 密码 */
        public static String DB_PWD;
    }

    /**
     *
     * @Description：视图扫描是否开启相关
     *
     * @author：liangshangsong
     *
     * 						2016年11月10日 下午4:33:32
     */
    public static class ScanView {
        public static String NOT_OPEN = "0";
        public static String IS_OPEN = "1";
    }

    /**
     * 初始化变量
     */
    public void init() {
        if (Constant.ISINIT)
            return;

        // 数字签名
        Constant.WITHDSIGN = Boolean.parseBoolean(this.withDsign);
        Constant.PRIVATE_DSIGN = this.privateDsignDn;
        Constant.PUBLIC_DSIGN = this.publicDsignDn;
        Constant.PRIVATE_FILE = this.privateFile;
        Constant.PUBLIC_FILE = this.publicFile;

        // 医院信息
        Constant.ORG_ID = this.orgID;
        Constant.ORG_PWD = this.orgPwd;
        Constant.ORG_QNO_TIMEOUTSECONDS = this.orgTimeoutSeconds;
        Constant.ORG_HANDSHAKE_TIMEOUTSECONDS = this.orgTimeoutHandShake;
        Constant.ORG_LIMIT_TIMEOUTSECONDS = this.limitTimeOutSeconds;
        Constant.HIS_DATABASE_TYPE = this.hisDatabaseType;
        Constant.CONN_PLAN = this.connPlan;

        // MQ
        Constant.MQ_URL = this.MQUrl;
        Constant.MQ_USERNAME = this.MQUserName;
        Constant.MQ_USRPWD = this.MQUserPwd;
        Constant.MQ_PL_QUEUE = this.MQPLQueue;
        Constant.MQ_REG_QUEUE = this.MQRegQueue;

        // 平台
        Constant.RSV_INVOKETYPE = this.platformInvokeType;
        // ws
        Constant.RSV_WS_URL = this.platformWsUrl;
        Constant.RSV_WS_METHOD = this.platformWsMethod;
        Constant.HIS_WS_URL = this.hisWebserviceUrl;
        // rest
        Constant.RSV_REST_URL = this.platformRestUrl;
        Constant.RSV_REST_PARAM = this.platformRestParam;

        // 订单配置
        Constant.ORDER_FLAG = this.orderFlag;

        // 版本信息
        Constant.VERSION = this.version;

        // 前置机设置号源
        Constant.PRE_SETTING_SOURCE = this.pre_setting_source;
        Constant.PRE_SETTING_SOURCE_TYPE = this.source_type;

        // sms信息
        SMSConstant.DB_IP = this.dbIp;
        SMSConstant.DB_NAME = this.dbName;
        SMSConstant.API_CODE = this.apiCode;
        SMSConstant.DB_PWD = this.dbPwd;
        SMSConstant.DB_USER = this.dbUser;
    }
}
