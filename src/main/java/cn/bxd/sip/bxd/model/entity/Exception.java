package cn.bxd.sip.bxd.model.entity;

import java.util.Date;

/**
 * 描述:T_RHIP_EXCEPTION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-07-11
 */
public class Exception {
    /**
     * null
     */
    private Long excSeqId;

    /**
     * null
     */
    private Date createdTime;

    /**
     * null
     */
    private Short retries;

    /**
     * null
     */
    private Short excTypeId;

    /**
     * null
     */
    private Object handleNotes;

    /**
     * null
     */
    private Date handleTime;

    /**
     * null
     */
    private Object handlerName;

    /**
     * null
     */
    private Long msgSeqId;

    /**
     * null
     */
    private String syncSeqId;

    /**
     * null
     * @return EXC_SEQ_ID null
     */
    public Long getExcSeqId() {
        return excSeqId;
    }

    /**
     * null
     * @param excSeqId null
     */
    public void setExcSeqId(Long excSeqId) {
        this.excSeqId = excSeqId;
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
     * @return RETRIES null
     */
    public Short getRetries() {
        return retries;
    }

    /**
     * null
     * @param retries null
     */
    public void setRetries(Short retries) {
        this.retries = retries;
    }

    /**
     * null
     * @return EXC_TYPE_ID null
     */
    public Short getExcTypeId() {
        return excTypeId;
    }

    /**
     * null
     * @param excTypeId null
     */
    public void setExcTypeId(Short excTypeId) {
        this.excTypeId = excTypeId;
    }

    /**
     * null
     * @return HANDLE_NOTES null
     */
    public Object getHandleNotes() {
        return handleNotes;
    }

    /**
     * null
     * @param handleNotes null
     */
    public void setHandleNotes(Object handleNotes) {
        this.handleNotes = handleNotes;
    }

    /**
     * null
     * @return HANDLE_TIME null
     */
    public Date getHandleTime() {
        return handleTime;
    }

    /**
     * null
     * @param handleTime null
     */
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    /**
     * null
     * @return HANDLER_NAME null
     */
    public Object getHandlerName() {
        return handlerName;
    }

    /**
     * null
     * @param handlerName null
     */
    public void setHandlerName(Object handlerName) {
        this.handlerName = handlerName;
    }

    /**
     * null
     * @return MSG_SEQ_ID null
     */
    public Long getMsgSeqId() {
        return msgSeqId;
    }

    /**
     * null
     * @param msgSeqId null
     */
    public void setMsgSeqId(Long msgSeqId) {
        this.msgSeqId = msgSeqId;
    }

    /**
     * null
     * @return SYNC_SEQ_ID null
     */
    public String getSyncSeqId() {
        return syncSeqId;
    }

    /**
     * null
     * @param syncSeqId null
     */
    public void setSyncSeqId(String syncSeqId) {
        this.syncSeqId = syncSeqId == null ? null : syncSeqId.trim();
    }
}