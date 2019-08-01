package cn.bxd.sip.bxd.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * T_SI_REAL_USER
 * @author 
 */
public class SiRealUser implements Serializable {
    private Object userId;

    private Object mobileNo;

    private Object visitcardNum;

    private Short visitcardIsBing;

    private Object financialAccount;

    private Short financialIsBing;

    private Object realPicUrl;

    private Date realTime;

    private Object payPassword;

    private Object userNo;

    private Short medicareType;

    private Object cardinfo;

    private Object overallArea;

    private Long pattern;

    private Object patientName;

    private Object cardId;

    private static final long serialVersionUID = 1L;

    
    public SiRealUser(Object patientName, Object cardId) {
		this.patientName = patientName;
		this.cardId = cardId;
	}

	public SiRealUser() {
	}

	public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Object mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Object getVisitcardNum() {
        return visitcardNum;
    }

    public void setVisitcardNum(Object visitcardNum) {
        this.visitcardNum = visitcardNum;
    }

    public Short getVisitcardIsBing() {
        return visitcardIsBing;
    }

    public void setVisitcardIsBing(Short visitcardIsBing) {
        this.visitcardIsBing = visitcardIsBing;
    }

    public Object getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(Object financialAccount) {
        this.financialAccount = financialAccount;
    }

    public Short getFinancialIsBing() {
        return financialIsBing;
    }

    public void setFinancialIsBing(Short financialIsBing) {
        this.financialIsBing = financialIsBing;
    }

    public Object getRealPicUrl() {
        return realPicUrl;
    }

    public void setRealPicUrl(Object realPicUrl) {
        this.realPicUrl = realPicUrl;
    }

    public Date getRealTime() {
        return realTime;
    }

    public void setRealTime(Date realTime) {
        this.realTime = realTime;
    }

    public Object getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(Object payPassword) {
        this.payPassword = payPassword;
    }

    public Object getUserNo() {
        return userNo;
    }

    public void setUserNo(Object userNo) {
        this.userNo = userNo;
    }

    public Short getMedicareType() {
        return medicareType;
    }

    public void setMedicareType(Short medicareType) {
        this.medicareType = medicareType;
    }

    public Object getCardinfo() {
        return cardinfo;
    }

    public void setCardinfo(Object cardinfo) {
        this.cardinfo = cardinfo;
    }

    public Object getOverallArea() {
        return overallArea;
    }

    public void setOverallArea(Object overallArea) {
        this.overallArea = overallArea;
    }

    public Long getPattern() {
        return pattern;
    }

    public void setPattern(Long pattern) {
        this.pattern = pattern;
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
        SiRealUser other = (SiRealUser) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMobileNo() == null ? other.getMobileNo() == null : this.getMobileNo().equals(other.getMobileNo()))
            && (this.getVisitcardNum() == null ? other.getVisitcardNum() == null : this.getVisitcardNum().equals(other.getVisitcardNum()))
            && (this.getVisitcardIsBing() == null ? other.getVisitcardIsBing() == null : this.getVisitcardIsBing().equals(other.getVisitcardIsBing()))
            && (this.getFinancialAccount() == null ? other.getFinancialAccount() == null : this.getFinancialAccount().equals(other.getFinancialAccount()))
            && (this.getFinancialIsBing() == null ? other.getFinancialIsBing() == null : this.getFinancialIsBing().equals(other.getFinancialIsBing()))
            && (this.getRealPicUrl() == null ? other.getRealPicUrl() == null : this.getRealPicUrl().equals(other.getRealPicUrl()))
            && (this.getRealTime() == null ? other.getRealTime() == null : this.getRealTime().equals(other.getRealTime()))
            && (this.getPayPassword() == null ? other.getPayPassword() == null : this.getPayPassword().equals(other.getPayPassword()))
            && (this.getUserNo() == null ? other.getUserNo() == null : this.getUserNo().equals(other.getUserNo()))
            && (this.getMedicareType() == null ? other.getMedicareType() == null : this.getMedicareType().equals(other.getMedicareType()))
            && (this.getCardinfo() == null ? other.getCardinfo() == null : this.getCardinfo().equals(other.getCardinfo()))
            && (this.getOverallArea() == null ? other.getOverallArea() == null : this.getOverallArea().equals(other.getOverallArea()))
            && (this.getPattern() == null ? other.getPattern() == null : this.getPattern().equals(other.getPattern()))
            && (this.getPatientName() == null ? other.getPatientName() == null : this.getPatientName().equals(other.getPatientName()))
            && (this.getCardId() == null ? other.getCardId() == null : this.getCardId().equals(other.getCardId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMobileNo() == null) ? 0 : getMobileNo().hashCode());
        result = prime * result + ((getVisitcardNum() == null) ? 0 : getVisitcardNum().hashCode());
        result = prime * result + ((getVisitcardIsBing() == null) ? 0 : getVisitcardIsBing().hashCode());
        result = prime * result + ((getFinancialAccount() == null) ? 0 : getFinancialAccount().hashCode());
        result = prime * result + ((getFinancialIsBing() == null) ? 0 : getFinancialIsBing().hashCode());
        result = prime * result + ((getRealPicUrl() == null) ? 0 : getRealPicUrl().hashCode());
        result = prime * result + ((getRealTime() == null) ? 0 : getRealTime().hashCode());
        result = prime * result + ((getPayPassword() == null) ? 0 : getPayPassword().hashCode());
        result = prime * result + ((getUserNo() == null) ? 0 : getUserNo().hashCode());
        result = prime * result + ((getMedicareType() == null) ? 0 : getMedicareType().hashCode());
        result = prime * result + ((getCardinfo() == null) ? 0 : getCardinfo().hashCode());
        result = prime * result + ((getOverallArea() == null) ? 0 : getOverallArea().hashCode());
        result = prime * result + ((getPattern() == null) ? 0 : getPattern().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        result = prime * result + ((getCardId() == null) ? 0 : getCardId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", visitcardNum=").append(visitcardNum);
        sb.append(", visitcardIsBing=").append(visitcardIsBing);
        sb.append(", financialAccount=").append(financialAccount);
        sb.append(", financialIsBing=").append(financialIsBing);
        sb.append(", realPicUrl=").append(realPicUrl);
        sb.append(", realTime=").append(realTime);
        sb.append(", payPassword=").append(payPassword);
        sb.append(", userNo=").append(userNo);
        sb.append(", medicareType=").append(medicareType);
        sb.append(", cardinfo=").append(cardinfo);
        sb.append(", overallArea=").append(overallArea);
        sb.append(", pattern=").append(pattern);
        sb.append(", patientName=").append(patientName);
        sb.append(", cardId=").append(cardId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}