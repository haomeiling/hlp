package cn.bxd.sip.bxd.var;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-02-19
 * Time: 14:30
 */
public enum DoPay {
    NO("0", "未支付"),
    YES("1", "已支付");

    DoPay(String payFlag, String description) {
        this.payFlag = payFlag;
        this.description = description;
    }

    private String payFlag;
    private String description;

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
