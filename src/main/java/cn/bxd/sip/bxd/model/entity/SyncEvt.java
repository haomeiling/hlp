package cn.bxd.sip.bxd.model.entity;

import java.util.Date;

/**
 * 描述:T_RHIP_SYNC_EVT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-07-11
 */
public class SyncEvt {
    /**
     * null
     */
    private Long syncSeqId;

    /**
     * null
     */
    private Date createdTime;

    /**
     * null
     */
    private Date firstProcTime;

    /**
     * null
     */
    private Short procCount;

    /**
     * null
     */
    private Date lastProcTime;

    /**
     * null
     */
    private Object statusCode;

    /**
     * null
     */
    private Object errorMessage;

    /**
     * null
     */
    private Short syncTypeId;

    /**
     * null
     */
    private Long orderId;

    /**
     * null
     */
    private Long transId;

    /**
     * null
     */
    private Short periodNo;

    /**
     * null
     */
    private String queueNo;

    /**
     * null
     */
    private Integer clinicDate;

    /**
     * null
     */
    private Integer hospitalId;

    /**
     * null
     */
    private Short rushRequired;

    /**
     * null
     */
    private Object queueId;

    /**
     * null
     * @return SYNC_SEQ_ID null
     */
    public Long getSyncSeqId() {
        return syncSeqId;
    }

    /**
     * null
     * @param syncSeqId null
     */
    public void setSyncSeqId(Long syncSeqId) {
        this.syncSeqId = syncSeqId;
    }

    /**
     * null
     * @return CREATED_TIME null
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * null
     * @param createdTime null
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * null
     * @return FIRST_PROC_TIME null
     */
    public Date getFirstProcTime() {
        return firstProcTime;
    }

    /**
     * null
     * @param firstProcTime null
     */
    public void setFirstProcTime(Date firstProcTime) {
        this.firstProcTime = firstProcTime;
    }

    /**
     * null
     * @return PROC_COUNT null
     */
    public Short getProcCount() {
        return procCount;
    }

    /**
     * null
     * @param procCount null
     */
    public void setProcCount(Short procCount) {
        this.procCount = procCount;
    }

    /**
     * null
     * @return LAST_PROC_TIME null
     */
    public Date getLastProcTime() {
        return lastProcTime;
    }

    /**
     * null
     * @param lastProcTime null
     */
    public void setLastProcTime(Date lastProcTime) {
        this.lastProcTime = lastProcTime;
    }

    /**
     * null
     * @return STATUS_CODE null
     */
    public Object getStatusCode() {
        return statusCode;
    }

    /**
     * null
     * @param statusCode null
     */
    public void setStatusCode(Object statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * null
     * @return ERROR_MESSAGE null
     */
    public Object getErrorMessage() {
        return errorMessage;
    }

    /**
     * null
     * @param errorMessage null
     */
    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * null
     * @return SYNC_TYPE_ID null
     */
    public Short getSyncTypeId() {
        return syncTypeId;
    }

    /**
     * null
     * @param syncTypeId null
     */
    public void setSyncTypeId(Short syncTypeId) {
        this.syncTypeId = syncTypeId;
    }

    /**
     * null
     * @return ORDER_ID null
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * null
     * @param orderId null
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * null
     * @return TRANS_ID null
     */
    public Long getTransId() {
        return transId;
    }

    /**
     * null
     * @param transId null
     */
    public void setTransId(Long transId) {
        this.transId = transId;
    }

    /**
     * null
     * @return PERIOD_NO null
     */
    public Short getPeriodNo() {
        return periodNo;
    }

    /**
     * null
     * @param periodNo null
     */
    public void setPeriodNo(Short periodNo) {
        this.periodNo = periodNo;
    }

    /**
     * null
     * @return QUEUE_NO null
     */
    public String getQueueNo() {
        return queueNo;
    }

    /**
     * null
     * @param queueNo null
     */
    public void setQueueNo(String queueNo) {
        this.queueNo = queueNo;
    }

    /**
     * null
     * @return CLINIC_DATE null
     */
    public Integer getClinicDate() {
        return clinicDate;
    }

    /**
     * null
     * @param clinicDate null
     */
    public void setClinicDate(Integer clinicDate) {
        this.clinicDate = clinicDate;
    }

    /**
     * null
     * @return HOSPITAL_ID null
     */
    public Integer getHospitalId() {
        return hospitalId;
    }

    /**
     * null
     * @param hospitalId null
     */
    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * null
     * @return RUSH_REQUIRED null
     */
    public Short getRushRequired() {
        return rushRequired;
    }

    /**
     * null
     * @param rushRequired null
     */
    public void setRushRequired(Short rushRequired) {
        this.rushRequired = rushRequired;
    }

    /**
     * null
     * @return QUEUE_ID null
     */
    public Object getQueueId() {
        return queueId;
    }

    /**
     * null
     * @param queueId null
     */
    public void setQueueId(Object queueId) {
        this.queueId = queueId;
    }

    @Override
    public String toString() {
        return "TSyncEvt{" +
                "syncSeqId=" + syncSeqId +
                ", createdTime=" + createdTime +
                ", firstProcTime=" + firstProcTime +
                ", procCount=" + procCount +
                ", lastProcTime=" + lastProcTime +
                ", statusCode=" + statusCode +
                ", errorMessage=" + errorMessage +
                ", syncTypeId=" + syncTypeId +
                ", orderId=" + orderId +
                ", transId=" + transId +
                ", periodNo=" + periodNo +
                ", queueNo=" + queueNo +
                ", clinicDate=" + clinicDate +
                ", hospitalId=" + hospitalId +
                ", rushRequired=" + rushRequired +
                ", queueId=" + queueId +
                '}';
    }
}