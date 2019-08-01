package cn.bxd.sip.his.model.dto.reservation.getQueueWait.res;

import java.util.List;

/**
 * @Author haomeiling
 * @Date 2016/12/30
 */
public class QueueItem {
    private String  queueName;//队列名称
    private String  queueId;//队列ID
    private String  queueAddr;//候诊地址
    private Long orderId;//订单ID
    private List<QueueNoItem> itemList	;


    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getQueueAddr() {
        return queueAddr;
    }

    public void setQueueAddr(String queueAddr) {
        this.queueAddr = queueAddr;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<QueueNoItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<QueueNoItem> itemList) {
        this.itemList = itemList;
    }
}
