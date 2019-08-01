package cn.bxd.sip.his.comm;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 常量
 * Package: com.bxd.sip.reservation.comm
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Slf4j
@Component
public class HisConvertConst {

    public static final String RETRUN_CODE = "returnCode";
    public static final String RESULT_CODE = "resultCode";
    public static final String RETRUN_MSG = "returnMsg";
    public static final String RETRUN_DATA = "returnData";
    public static final String RETRUN_SUCCESS_CODE = "SUCCESS";
    public static final String RESULT_SUCCESS_CODE = "SUCCESS";
    public static final String RETRUN_FAIL_CODE = "FAIL";
    public static final String RESULT_FAIL_CODE = "FAIL";

    @Deprecated
    /** ws地址 */
    public static String hisWSUrl;

    /**
     * 医院配置信息
     */
    public static Map<String, ConnectParm> allHosWsMap = new HashMap<>();

    /**
     * 医院配置信息
     */
    public static Map<String, ConnectParm> allHosWsMap2;

    /**
     * 医院配置信息
     */
    public static Map<String, ConnectParm> allHosWsMap3;

/*    public static final String SYS_USER_NAME = "ZL_DATAUSER";

    public static final String SYS_KEY = "123456";*/

    public static final String SYS_USER_NAME = "0002";

    public static final String SYS_KEY = "5oCd5o236Ieq5Yqp5o6l5YWl";

    /**
     * 启用ws地址
     */
    public static final int USE_WS_URL = 1;

    /**
     * 对应后台ws 1
     */
    public static final String WS_CLIENT_KEY_HIS_USER = "1";

    /**
     * 对应后台ws 2
     */
    public static final String WS_CLIENT_KEY_HIS_COM = "2";

    /**
     * 对应后台ws 3
     */
    public static final String WS_CLIENT_KEY_PAY = "3";

    /**
     * 对应后台ws 4
     */
    public static final String WS_CLIENT_KEY_REG = "4";

    /**
     * 对应后台ws 5
     */
    public static final String WS_CLIENT_KEY_IN_HOS = "5";


    public static class Operation {
        /**
         * 操作成功
         */
        public static final String OPERATION_SUCCESS = "1";
        /**
         * 操作失败
         */
        public static final String OPERATION_FAIL = "0";
        /**
         * 查询最早排班日期
         */
        public static final String GET_EARLY_ARRANGE_REQ_CODE = "2020";
        /**
         * 查询最晚排班日期
         */
        public static final String GET_LAST_ARRANGE_REQ_CODE = "1";
        /**
         * 查询号源时段
         */
        public static final String GET_QUEUE_REG_REQ_CODE = "2003";
        /**
         * 查询号类信息
         */
        public static final String GET_CLINIC_TYEP_INFO_REQ_CODE = "1000";
        /**
         * 查询医生队列号源
         */
        public static final String GET_QUEUE_DOCTOR_INFO_REQ_CODE = "3011";
        /**
         * 查询队列列表
         */
        public static final String GET_QUEUE_LIST_REQ_CODE = "2021";
        /**
         * 患者主索引查询接口
         */
        public static final String GET_PATIENT_INDEX_REQ_CODE = "2015";
        /**
         * 患者主索引查询接口
         */
        public static final String GET_LOCK_REG_REQ_CODE = "2004";
        /**
         * 社保预结算
         */
        public static final String PRE_SETTLEMENT = "5000";
        /**
         * 社保结算
         */
        public static final String SETTLE_ACCOUNTS = "5001";
        /**
         * 查询待签到订单列表
         */
        public static final String GET_ARRIVAL_ORDER_LIST_CODE = "2024";
        /**
         * 患者住院信息
         */
        public static final String GET_PATIENT_IN_HOS_INFO_CODE = "2027";
        /**
         * 门诊结算详情列表
         */
        public static final String GET_CLINIC_INFO_LIST_REQ_CODE = "2028";
        /**
         * 门诊结算详情
         */
        public static final String GET_CLINIC_INFO_REQ_CODE = "2029";
        /**
         * 获取社保信息
         */
        public static final String GET_SI_INFO_REQ_CODE = "4000";
        /**
         * 查询用户是否存在
         */
        public static final String JUDGE_USER_IS_EXIST = "4005";
        /**
         * 查询第三方用户是否存在
         */
        public static final String JUDGE_THIRD_USER_IS_EXIST = "4002";
        /**
         * 登录社保系统
         */
        public static final String LOGIN = "4003";
        /**
         * 社保退款
         */
        public static final String REFUND_SOCIAL = "4006";
        /**
         * 查询付款信息
         */
        public static final String GET_PAYMENT = "4007";
        /**
         * 社保政策查询
         */
        public static final String GET_SOCIAL_POLICY = "6000";
        /**
         * 取消订单
         */
        public static final String CANCEL_ORDER = "2012";
        /*诊疗记录查询*/
        public final static String GET_CLINIC_RECORD = "3017";
        /*获取报告单列表*/
        public final static String GET_REPORT_LIST = "3014";
        /*获取报告单列表 lsiheng 2019/4/23 */
        public final static String GET_REPORT_INFO = "3015";
        /* 获取排队信息 */
        public static final String GET_QUEUE_WAIT_INFO = "4001";
        /* 取号 */
        public static final String DO_TAKE_THE_NO = "2022";
        /* 查询挂号费 lisheng 20190214*/
        public static final String DOREG_FEE_INTERFACE = "2030";
        /* 获取挂号医保信息 lisheng 20190215*/
        public static final String DO_REG_MEDICARE_INFO = "2031";

    }

    public static class HisCode {
        /**
         * 成功标志
         */
        public static final String SUCCESS_CODE = "00";
        public static final String NO_DATA = "无数据";

        public static final String NO_DATA_CODE = "01";

    }

    public static class ReservationCode {
        /**
         * 门诊类型
         */
        public static final int CLINIC_TYPE_CODE = 1;
        /**
         * 住院类型
         */
        public static final int CLINIC_TYPE_IN_HOS_CODE = 2;
        /**
         * 未缴费
         */
        public static final int BILL_STAUTS_NO_PAY_CODE = 1;
        /**
         * 已缴费
         */
        public static final int BILL_STAUTS_PAYED_STAUTS_CODE = 2;
        /**
         * 住院费用清单
         */
        public static final int BILL_TYPE_ID_IN_HOS_CODE = 1;
        /**
         * 住院押金
         */
        public static final int BILL_TYPE_ID_DEPOSIT_CODE = 2;

    }

    /**
     * SOAP名称
     */
    public static class Soap {
        public static final String HIS_INTERFACE_QKB_SOAP = "HISInterfaceQkbSoap";
        public static final String SERVICE_SOAP = "ServiceSoap";
        public static final String HIS_COM_SOAP = "HisComSoap";
        public static final String HIS_USER_SOAP = "HisUserSoap";
        public static final String IN_HOS_SOAP = "InHosSoap";
        public static final String PAY_SOAP = "PaySoap";
        public static final String REG_SOAP = "RegSoap";
    }


    /**
     * 测试随机Int
     */
    public static String randomTestInt;

    @Value("${his.ws_url:classpath:schema/HISInterface_qkb.wsdl}")
    private String his_ws_url;

    public void inti() {
        hisWSUrl = this.his_ws_url;
    }

    /**
     * 检验检查报告类型
     */
    public static class Report {
        public static final String LIS = "LIS"; //检查检验，B超，放射影像，内镜，病理，心电图

        public static final String CT = "CT";//B超
        public static final String MRI = "MRI";//核磁共振
        public static final String US = "US";//超声
        public static final String DR = "DR";//放射影像

        public static final String LIS_TYPE = "1";
        public static final String CT_TYPE = "2";
        public static final String MRI_TYPE = "3";
        public static final String US_TYPE = "4";
        public static final String DR_TYPE = "5";

        public static final String REPORT_STATUS = "1";//sip未成功报告状态
        public static final String STATUS = "2";//reservation报告状态未成功报告状态

    }

}