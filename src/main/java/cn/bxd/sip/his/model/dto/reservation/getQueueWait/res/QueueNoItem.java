package cn.bxd.sip.his.model.dto.reservation.getQueueWait.res;

/**
 * @Author Lisheng
 * @Date 2018/11/1
 */
public class QueueNoItem {
    private Long  queueNo;//排队号
    private String  patientName;//就诊人姓名
    private String	displayQueueNo;//显示排队号
    private Long  calledFlag;//叫号标识:
    private String  waitNum;//等待人数     :

    public Long getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(Long queueNo) {
        this.queueNo = queueNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    public Long getCalledFlag() {
        return calledFlag;
    }

    public void setCalledFlag(Long calledFlag) {
        this.calledFlag = calledFlag;
    }

    public String getWaitNum() {
        return waitNum;
    }

    public void setWaitNum(String waitNum) {
        this.waitNum = waitNum;
    }

    public String getDisplayQueueNo() {
        return displayQueueNo;
    }

    public void setDisplayQueueNo(String displayQueueNo) {
        this.displayQueueNo = displayQueueNo;
    }
}
