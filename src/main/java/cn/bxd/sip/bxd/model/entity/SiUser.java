package cn.bxd.sip.bxd.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * T_SI_USER
 * @author 
 */
public class SiUser implements Serializable {
    private BigDecimal userId;

    private Object userName;

    private Object userPwd;

    private Object name;

    private Object gender;

    private Object idCard;

    private Object phone;

    private Object userCode;

    private Object siCard;

    private Object regionCode;

    private Object email;

    private Date createdTime;

    private Date lastLoginTime;

    private Short status;

    private Object ip;

    private BigDecimal pUserId;

    private Object marks;

    private Object photo;

    private Object easemobuuid;

    private Object mobileNo;

    private Object password;

    private Date registerTime;

    private Object username;

    private Object openid;

    private Short bindFlag;

    private Short isReal;

    private Object secretKey;

    private Short status2;

    private static final long serialVersionUID = 1L;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(Object userPwd) {
        this.userPwd = userPwd;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getIdCard() {
        return idCard;
    }

    public void setIdCard(Object idCard) {
        this.idCard = idCard;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getUserCode() {
        return userCode;
    }

    public void setUserCode(Object userCode) {
        this.userCode = userCode;
    }

    public Object getSiCard() {
        return siCard;
    }

    public void setSiCard(Object siCard) {
        this.siCard = siCard;
    }

    public Object getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Object regionCode) {
        this.regionCode = regionCode;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Object getIp() {
        return ip;
    }

    public void setIp(Object ip) {
        this.ip = ip;
    }

    public BigDecimal getpUserId() {
        return pUserId;
    }

    public void setpUserId(BigDecimal pUserId) {
        this.pUserId = pUserId;
    }

    public Object getMarks() {
        return marks;
    }

    public void setMarks(Object marks) {
        this.marks = marks;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public Object getEasemobuuid() {
        return easemobuuid;
    }

    public void setEasemobuuid(Object easemobuuid) {
        this.easemobuuid = easemobuuid;
    }

    public Object getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Object mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getOpenid() {
        return openid;
    }

    public void setOpenid(Object openid) {
        this.openid = openid;
    }

    public Short getBindFlag() {
        return bindFlag;
    }

    public void setBindFlag(Short bindFlag) {
        this.bindFlag = bindFlag;
    }

    public Short getIsReal() {
        return isReal;
    }

    public void setIsReal(Short isReal) {
        this.isReal = isReal;
    }

    public Object getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Object secretKey) {
        this.secretKey = secretKey;
    }

    public Short getStatus2() {
        return status2;
    }

    public void setStatus2(Short status2) {
        this.status2 = status2;
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
        SiUser other = (SiUser) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPwd() == null ? other.getUserPwd() == null : this.getUserPwd().equals(other.getUserPwd()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getSiCard() == null ? other.getSiCard() == null : this.getSiCard().equals(other.getSiCard()))
            && (this.getRegionCode() == null ? other.getRegionCode() == null : this.getRegionCode().equals(other.getRegionCode()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getLastLoginTime() == null ? other.getLastLoginTime() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getpUserId() == null ? other.getpUserId() == null : this.getpUserId().equals(other.getpUserId()))
            && (this.getMarks() == null ? other.getMarks() == null : this.getMarks().equals(other.getMarks()))
            && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()))
            && (this.getEasemobuuid() == null ? other.getEasemobuuid() == null : this.getEasemobuuid().equals(other.getEasemobuuid()))
            && (this.getMobileNo() == null ? other.getMobileNo() == null : this.getMobileNo().equals(other.getMobileNo()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getRegisterTime() == null ? other.getRegisterTime() == null : this.getRegisterTime().equals(other.getRegisterTime()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getBindFlag() == null ? other.getBindFlag() == null : this.getBindFlag().equals(other.getBindFlag()))
            && (this.getIsReal() == null ? other.getIsReal() == null : this.getIsReal().equals(other.getIsReal()))
            && (this.getSecretKey() == null ? other.getSecretKey() == null : this.getSecretKey().equals(other.getSecretKey()))
            && (this.getStatus2() == null ? other.getStatus2() == null : this.getStatus2().equals(other.getStatus2()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPwd() == null) ? 0 : getUserPwd().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getSiCard() == null) ? 0 : getSiCard().hashCode());
        result = prime * result + ((getRegionCode() == null) ? 0 : getRegionCode().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getpUserId() == null) ? 0 : getpUserId().hashCode());
        result = prime * result + ((getMarks() == null) ? 0 : getMarks().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        result = prime * result + ((getEasemobuuid() == null) ? 0 : getEasemobuuid().hashCode());
        result = prime * result + ((getMobileNo() == null) ? 0 : getMobileNo().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRegisterTime() == null) ? 0 : getRegisterTime().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getBindFlag() == null) ? 0 : getBindFlag().hashCode());
        result = prime * result + ((getIsReal() == null) ? 0 : getIsReal().hashCode());
        result = prime * result + ((getSecretKey() == null) ? 0 : getSecretKey().hashCode());
        result = prime * result + ((getStatus2() == null) ? 0 : getStatus2().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", idCard=").append(idCard);
        sb.append(", phone=").append(phone);
        sb.append(", userCode=").append(userCode);
        sb.append(", siCard=").append(siCard);
        sb.append(", regionCode=").append(regionCode);
        sb.append(", email=").append(email);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", status=").append(status);
        sb.append(", ip=").append(ip);
        sb.append(", pUserId=").append(pUserId);
        sb.append(", marks=").append(marks);
        sb.append(", photo=").append(photo);
        sb.append(", easemobuuid=").append(easemobuuid);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", password=").append(password);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", username=").append(username);
        sb.append(", openid=").append(openid);
        sb.append(", bindFlag=").append(bindFlag);
        sb.append(", isReal=").append(isReal);
        sb.append(", secretKey=").append(secretKey);
        sb.append(", status2=").append(status2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}