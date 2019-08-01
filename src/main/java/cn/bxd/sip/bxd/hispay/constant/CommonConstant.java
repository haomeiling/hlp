package cn.bxd.sip.bxd.hispay.constant;

import java.util.Date;
import java.util.Map;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:25
 */
public class CommonConstant {
    /** 是固定值 **/
    public static final String YES = "yes";
    /** 否固定值 **/
    public static final String NO = "no";

    /** 前置的JDBC **/
    public static final String JDBC_REG = "reg";
    /** HIS的JDBC **/
    public static final String JDBC_HIS = "his";

    /** 连接的数据库 **/
    /** oracle数据库 **/
    public static final String DATABASE_HIS_ORCL = "oracle";
    /** mssql/sqlserver数据库 **/
    public static final String DATABASE_HIS_MSSQL = "mssql";

    /** 接入方案，分为1，2，3 **/
    /** HIS完全实现门诊排班、号源管理、预约管理和挂号收费 **/
    public static final int CONN_PLAN_1 = 1;
    /** HIS实现挂号收费，服务组件实现门诊排班、号源管理和预约管理 **/
    public static final int CONN_PLAN_2 = 2;
    /** HIS只负责收费，服务组件实现门诊排班、号源管理、预约和挂号 **/
    public static final int CONN_PLAN_3 = 3;
    /** 使用统一号源，HIS负责挂号部分 **/
    public static final int CONN_PLAN_4 = 4;

    // 成功失败代码
    /** 失败0 **/
    public static final int FAILED = 0;
    /** 成功1 **/
    public static final int SUCCESS = 1;

    // 诊疗类型
    /** 门诊 */
    public static final int CLINIC_TYPE_OUTPATIENT = 1;
    /** 住院 */
    public static final int CLINIC_TYPE_INPATIENT = 2;
    /** 体检 */
    public static final int CLINIC_TYPE_EXAMINED = 3;
    /** 其他 */
    public static final int CLINIC_TYPE_OTHER = 4;

    /**
     *
     * @Description：任务相关
     *
     * @author：liangshangsong
     *
     * 						2016年10月26日 下午2:01:21
     */
    public static class Task {
        /** 用于记录扫描时间 **/
        public static Map<String, Date> taskTimeMap = null;
        // 定时器扫描
        /** 价目申报 **/
        public static final int ARTICLE_UPLOAD_TIMES = 120;// 1个小时扫描一次

        // 登录
        /** 默认系统延迟登录时间5s **/
        public static final Short LOGIN_TIMES = 5;
        /** 异常重新登录时间120s **/
        public static final Short RE_LOGIN_TIMES = 120;
    }

    /**
     *
     * @Description：报告相关
     *
     * @author：liangshangsong
     *
     * 						2016年10月26日 下午2:01:21
     */
    public static class Report {
        /** 未报告 */
        public static final int REPORT_UNDO_STATUS = 0;
        /** 待审核 */
        public static final int REPORT_WAIT_AUDITE_STATUS = 1;
        /** 已签发 */
        public static final int REPORT_ISSUED_STATUS = 2;
        /** 已作废 */
        public static final int REPORT_SCRAP_STATUS = 3;

        // 报告类型
        /** 报告通知lis检验 */
        public static final String NOTIFY_BZ_TYPE_LIS = "LIS";
        /** 报告通知RIS（CT、MRI）检查 */
        public static final String NOTIFY_BZ_TYPE_RIS = "RIS";

        // 区分住院押金与住院费用
        /*** 住院费用查询 */
        public static final int INHOSPITAL_BILL_TYPE_OF_FEE = 1;
        /*** 住院押金查询 */
        public static final int INHOSPITAL_BILL_TYPE_OF_DEPOSIT = 2;
    }

    /**
     *
     * @Description：通知业务类型
     *
     * @author：liangshangsong
     *
     * 						2016年10月26日 下午2:01:21
     */
    public static class BzType {
        /** 报告通知lis检验 */
        public static final short NOTIFY_BZ_TYPE_LIS = 1;
        /** 报告通知RIS（CT、MRI）检查 */
        public static final short NOTIFY_BZ_TYPE_RIS = 2;

        // 缴费11-20
        /** 待缴费通知 */
        public static final short NOTIFY_BZ_TYPE_TO_PAY = 11;
        /** 退费请求通知 */
        public static final short NOTIFY_BZ_TYPE_REFUND = 12;
        /** 预约请求通知 */
        public static final short NOTIFY_BZ_TYPE_RSV_STATIST = 13;

        // 基础信息通知21-30
        /** 科室信息变更 */
        public static final short NOTIFY_BZ_TYPE_DEPT_INFO = 21;
        /** 人员信息变更 */
        public static final short NOTIFY_BZ_TYPE_EMP_INFO = 22;
    }

    /**
     *
     * @Description：通知类型
     *
     * @author：liangshangsong
     *
     * 						2016年10月26日 下午2:01:21
     */
    public static class NotifyType {
        // 通知类型
        /** 报告通知 */
        public static final String NOTIFY_TYPE_REPORT = "001";
        /** 退费通知 */
        public static final String NOTIFY_TYPE_REFUND = "002";
        /** 待缴费通知 */
        public static final String NOTIFY_TYPE_TO_PAY = "003";
        /** 预约统计通知 */
        public static final String NOTIFY_TYPE_RVS_STATIST = "004";

        /** 科室信息变更通知 */
        public static final String NOTIFY_TYPE_DEPT_CHANGE = "101";
        /** 人员信息变更通知 */
        public static final String NOTIFY_TYPE_EMP_CHANGE = "102";
    }

    /**
     *
     * @Description：His的相关配置
     *
     * @author：liangshangsong
     *
     * 						2016年12月26日 下午4:28:09
     */
    public static class His {
        // 过度时期启用 null-标志位没有值（错误的显示），1-统一号源
        // 2-前置机号源，3-his号源，4-统一号源、his双号源并轨，5-前置机、his双号源并轨
        /** 号源类型 */
        public static String SOURCE_TYPE = null;
        /** 统一号源标志 **/
        public static final String SOURCE_TYPE_UNIT = "1";
        /** 前置机号源标志 **/
        public static final String SOURCE_TYPE_REG = "2";
        /** HIS号源标志 **/
        public static final String SOURCE_TYPE_HIS = "3";
        /** 统一号源-HIS标志 **/
        public static final String SOURCE_TYPE_UNIT_HIS = "4";
        /** 前置机号源-HIS标志 **/
        public static final String SOURCE_TYPE_REG_HIS = "5";

        /** 是否由前置机设置号源 1—是由前置机设置，0-由医院his设置 */
        public static String PRE_SETTING_SOURCE = "1";
    }
}
