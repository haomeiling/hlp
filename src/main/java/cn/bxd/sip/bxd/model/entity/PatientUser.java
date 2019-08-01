package cn.bxd.sip.bxd.model.entity;

/**
 * 患者消息通知账户表
 *
 * @author hml
 *         <p/>
 * @date 2016年10月28日
 */
public class PatientUser {
    private Integer empiId;//患者EMPI

    private Integer userId;//平台账户ID

    public Integer getEmpiId() {
        return empiId;
    }

    public void setEmpiId(Integer empiId) {
        this.empiId = empiId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}