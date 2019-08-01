package cn.bxd.sip.bxd.var;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-02-21
 * Time: 09:09
 */
public enum SentFlag {
    NO((short) 0, "未发送"), YES((short) 1, "已发送");

    SentFlag(short sentFlag, String desc) {
        this.sentFlag = sentFlag;
        this.desc = desc;
    }

    private short sentFlag;//是否发送
    private String desc;//描述

    public short getSentFlag() {
        return sentFlag;
    }

    public void setSentFlag(short sentFlag) {
        this.sentFlag = sentFlag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
