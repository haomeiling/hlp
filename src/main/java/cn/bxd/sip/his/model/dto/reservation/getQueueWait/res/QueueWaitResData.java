package cn.bxd.sip.his.model.dto.reservation.getQueueWait.res;

import java.util.List;

/**
 * Description:
 * User: LiSheng
 * Date: 2018-11-01
 * Time: 15:16
 */
public class QueueWaitResData {
    private Short success;
    private String msg;
    private Integer hosId;
    private List<QueueItem> data;//队列列表


    public List<QueueItem> getData() {
        return data;
    }

    public void setData(List<QueueItem> data) {
        this.data = data;
    }
    public Short getSuccess() {
        return success;
    }

    public void setSuccess(Short success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getHosId() {
        return hosId;
    }

    public void setHosId(Integer hosId) {
        this.hosId = hosId;
    }
}
