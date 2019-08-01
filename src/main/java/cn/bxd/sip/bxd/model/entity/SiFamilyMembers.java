package cn.bxd.sip.bxd.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * T_SI_FAMILY_MEMBERS
 * @author 
 */
public class SiFamilyMembers implements Serializable {
    private Long id;

    private Object mobileNo;

    private Object patientName;

    private Object cardId;

    private Object mobile;

    private Object relation;

    private Date createtime;

    private Short isMain;

    private Short headno;

    private Long isAuthorize;

    private Object userId;

    private Object cardNum;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Object mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Object getPatientName() {
        return patientName;
    }

    public void setPatientName(Object patientName) {
        this.patientName = patientName;
    }

    public Object getCardId() {
        return cardId;
    }

    public void setCardId(Object cardId) {
        this.cardId = cardId;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public Object getRelation() {
        return relation;
    }

    public void setRelation(Object relation) {
        this.relation = relation;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Short getIsMain() {
        return isMain;
    }

    public void setIsMain(Short isMain) {
        this.isMain = isMain;
    }

    public Short getHeadno() {
        return headno;
    }

    public void setHeadno(Short headno) {
        this.headno = headno;
    }

    public Long getIsAuthorize() {
        return isAuthorize;
    }

    public void setIsAuthorize(Long isAuthorize) {
        this.isAuthorize = isAuthorize;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getCardNum() {
        return cardNum;
    }

    public void setCardNum(Object cardNum) {
        this.cardNum = cardNum;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SiFamilyMembers other = (SiFamilyMembers) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMobileNo() == null ? other.getMobileNo() == null : this.getMobileNo().equals(other.getMobileNo()))
            && (this.getPatientName() == null ? other.getPatientName() == null : this.getPatientName().equals(other.getPatientName()))
            && (this.getCardId() == null ? other.getCardId() == null : this.getCardId().equals(other.getCardId()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getRelation() == null ? other.getRelation() == null : this.getRelation().equals(other.getRelation()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getIsMain() == null ? other.getIsMain() == null : this.getIsMain().equals(other.getIsMain()))
            && (this.getHeadno() == null ? other.getHeadno() == null : this.getHeadno().equals(other.getHeadno()))
            && (this.getIsAuthorize() == null ? other.getIsAuthorize() == null : this.getIsAuthorize().equals(other.getIsAuthorize()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCardNum() == null ? other.getCardNum() == null : this.getCardNum().equals(other.getCardNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMobileNo() == null) ? 0 : getMobileNo().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        result = prime * result + ((getCardId() == null) ? 0 : getCardId().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getRelation() == null) ? 0 : getRelation().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getIsMain() == null) ? 0 : getIsMain().hashCode());
        result = prime * result + ((getHeadno() == null) ? 0 : getHeadno().hashCode());
        result = prime * result + ((getIsAuthorize() == null) ? 0 : getIsAuthorize().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCardNum() == null) ? 0 : getCardNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", patientName=").append(patientName);
        sb.append(", cardId=").append(cardId);
        sb.append(", mobile=").append(mobile);
        sb.append(", relation=").append(relation);
        sb.append(", createtime=").append(createtime);
        sb.append(", isMain=").append(isMain);
        sb.append(", headno=").append(headno);
        sb.append(", isAuthorize=").append(isAuthorize);
        sb.append(", userId=").append(userId);
        sb.append(", cardNum=").append(cardNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}