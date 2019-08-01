package cn.bxd.sip.bxd.model.entity;

import java.util.Date;

/**
 * 描述:T_RHIP_SYNC_EVT_PENDING表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-07-11
 */
public class SyncEvtPending {
    /**
     * null
     */
    private Long syncSeqId;

    /**
     * null
     */
    private Date nextTryTime;

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
     * @return NEXT_TRY_TIME null
     */
    public Date getNextTryTime() {
        return nextTryTime;
    }

    /**
     * null
     * @param nextTryTime null
     */
    public void setNextTryTime(Date nextTryTime) {
        this.nextTryTime = nextTryTime;
    }
}