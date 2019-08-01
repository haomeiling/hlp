package cn.bxd.sip.his.comm;

/**
 * Description:
 * User: LiSheng
 * Date: 2018-09-30
 * Time: 9:00
 */
public class HisFunNameConst {

    /** 身份证号码查看患者信息 */
    public static final String QUERY_PATIENT_INFO_BY_ID_NO = "queryPatientInfoByIdNO";

    /** 社保、银行、就诊卡号查看患者信息 */
    public static final String QUERY_PATIENT_INFO_BY_SOCIALSECURITY_NO = "queryPatientInfoBySocialsecurityNO";

    /** 患者档案建立 */
    public static final String DO_CREAT_CARD_INFO = "doCreatCardInfo";

    /** 查询检查,检验数据打印状态状态 */
    public static final String QUERY_EXAMINATION_RECORD_STATUS = "queryExaminationRecordStatus";

    /** 更新检查,检验数据打印状态 */
    public static final String UPDATE_EXAMINATION_RECORD_STATUS = "updateExaminationRecordStatus";

    /** 获取检查,检验数据 */
    public static final String QUERY_EXAMINATION_RECORD_LIST = "queryExaminationRecordList";

    /** 病历记录查询 */
    public static final String QUERY_HIS_USER_RECORD_LIST = "queryHisUserRecordList";

    /** 更新患者信息 */
    public static final String UPDATE_PATIENT_INFO = "updatePatientInfo";

    /** 消息通知（平台开发，医院发送） */
    public static final String MESSAGE_INTERFACE = "messageInterface";


    /** 查询医生列表信息 */
    public static final String QUERY_DOCTOR_LIST = "queryDoctorList";

    /** 根据科室Id查询科室信息 */
    public static final String QUERY_DEPARTMENT_BY_ID = "queryDepartmentById";

    /** 查询部门列表信息 */
    public static final String QUERY_DEPARTMENT_LIST = "queryDepartmentList";

    /** 查询医生信息 */
    public static final String QUERY_DOCTOR_BY_ID = "queryDoctorById";

    /** 医院简介查询 */
    public static final String QUERY_HOSPITAL_INFO = "queryHospitalInfo";

    /** 收费项目查询 */
    public static final String QUERY_DRUG_LIST = "queryDrugList";


    /** 查询号源 */
    public static final String QUERY_TO_REG_DOCTOR_LIST = "queryToRegDoctorList";

    /** 根据医生查询号源 */
    public static final String QUERY_TO_REG_DOCTOR_LIST_BY_DOCTOR_ID = "queryToRegDoctorListByDoctorId";

    /** 查询医生号源时间段 */
    public static final String QUERY_TO_REG_DOCTOR_TIMES = "queryToRegDoctorTimes";

    /** 查询挂号费（号源挂号费不使用时使用） */
    public static final String DOREG_FEE_INTERFACE = "doregFeeInterface";

    /** 查询医生当前排队人数 */
    public static final String QUERY_DOCTOR_REG_WAIT_NUM = "queryDoctorRegWaitNum";

    /** 获取挂号医保信息 */
    public static final String DOREG_MEDICARE_INFO = "doRegMedicareInfo";

    /** 预约当天并取号 */
    public static final String DO_REG_AND_TAKETHE_NO = "doRegAndTaketheNo";

    /** 预约挂号 */
    public static final String DO_REG = "doReg";

    /** 查询未取号的预约挂号 */
    public static final String QUERY_REG_BYPATIENT_NO = "queryRegBypatientNo";

    /** 查询挂号支付状态 */
    public static final String QUERY_APPOINTMENT_PAY_STATUS = "queryAppointmentPayStatus";

    /** 取消预约 */
    public static final String DO_REG_CANCEL = "doRegCancel";

    /** 取号查询接口 */
    public static final String QUERY_TAKETHE_NO = "queryTaketheNo";

    /** 取号 */
    public static final String DO_TAKETHE_NO = "doTaketheNo";

    /** 排取人数 */
    public static final String QUERY_REG_WAIT_NUM = "queryRegWaitNum";

    /** 医生停诊（医院开发，平台调用） */
    public static final String DOCTOR_STOPPED = "doctorStopped";

    /** 医生停诊（平台开发，医院调用） */
    public static final String STOPWORKING = "stopworking";

    /** 号源锁号 */
    public static final String LOCK_REGISTER_NO = "lockRegisterNo";

    /** 号源解锁 */
    public static final String UNLOCK_REGISTER_NO = "unlockRegisterNo";


    /** 待交费列表 */
    public static final String QUERY_TO_PAY_RECIPE_INFO_LIST = "queryToPayRecipeInfoList";

    /** 已交费列表 */
    public static final String QUERY_PAYMENT_RECORD_LIST = "queryPaymentRecordList";

    /** 根据处方编号获取处方信息 */
    public static final String QUERY_TO_PAY_RECIPE_BY_HI_FEE_NO = "queryToPayRecipeByHiFeeNo";

    /** 更新打票状态 */
    public static final String UPDATE_RECIPE_STATUS_STATUS = "updateRecipeStatusStatus";

    /** 支付 */
    public static final String DO_PAY = "doPay";

    /** 查询支付状态 */
    public static final String QUERY_PAY_STATUS = "queryPayStatus";

    /** 获取发票打印列表 */
    public static final String QUERY_INVOICE_REPORT_LIST = "queryInvoiceReportList";

    /** 获取发票详情 */
    public static final String QUERY_INVOICE_DETAIL = "queryInvoiceDetail";

    /** 更新发票打印状态 */
    public static final String UPDATE_INVOICE_STATUS = "updateInvoiceStatus";


    /** 查询患者住院信息 */
    public static final String QUERY_PATIENT_IN_HOS_INFO = "queryPatientInHosInfo";

    /** 自助入院申请查询 */
    public static final String QUERY_TOIN_HOS = "queryToInHos";

    /** 自助入院申请办理 */
    public static final String APPLY_TOIN_HOS = "applyToInHos";

    /** 住院押金补缴查询 */
    public static final String QUERY_ARREARS = "queryArrears";

    /** 住院押金补缴 */
    public static final String INPATIENT_PAYMENT = "inpatientPayment";

    /** 押金明细查询 */
    public static final String PAY_DETAIL = "payDetail";

    /** 住院清单明细 */
    public static final String IN_HOS_DETAIL = "inHosDetail";

    /** 住院医保信息查询 */
    public static final String IN_HOS_MEDICAL_INFO = "inHosMedicalInfo";

    /** 出院结算申请 */
    public static final String APPLY_LEAVE_HOS = "applyLeaveHos";

    /** 查询出 院清单 */
    public static final String QUERY_LEAVE_HOS_DETAIL = "queryLeaveHosDetail";

    /** 出院结算 */
    public static final String LEAVE_HOS_PAY = "leaveHosPay";

    /** 明细对帐 */
    public static final String BALANCE_ACCOUNT = "balanceAccount";

    /** 医保确认 */
    public static final String MEDICARE_CONFIRM = "medicareConfirm";


    /** 获取现场排队信息 */
    public static final String GET_QUEUE_WAIT_INFO = "queryRegWaitNum";
    
    /** 1.5.14.	更新住院清单打印状态 */
    public static final String UPDATE_INHOS_RECORD_STATUS = "updateInHosRecordStatus";
    
    /** 1.4.10.	更新门诊缴费打印状态 */
    public static final String UPDATE_RECIPE_RECORD_STATUS = "updateRecipeRecordStatus";



}
