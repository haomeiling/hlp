package cn.bxd.sip.bxd.model.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Description：平台账户人员信息表
 *
 * @author liangshangsong
 *         <p/>
 *         2015年7月27日 上午11:51:22
 */
public class UserPerson extends UserPersonKey {
    private String personName;//姓名

    @JSONField(format = "yyyy-MM-dd")
    private Date birthDate;//出生日期

    private Short certTypeId;//身份证件类型代号

    private String certIdno;//身份证件号码

    private Short genderId;//性别代号

    private String contactPhone;//联系电话

    private String wechartAccount;//微信号

    private String alipayAccount;//支付宝账号

    private Long empiId;//患者empi

    private short patientType;//患者类型 1本人，2他人，3儿童

    private short ishaveCard;//是否有就诊卡 0否，1是

    private String homeAddress;//家庭地址

    private Short maritalStatus;//婚姻状况 0否，1是

    private String Nationality;//国籍

    private String parentsName;//家长姓名

    private String parentsPhone;//联系电话

    private String parentsIDNO;//家长身份证

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Short getCertTypeId() {
        return certTypeId;
    }

    public void setCertTypeId(Short certTypeId) {
        this.certTypeId = certTypeId;
    }

    public String getCertIdno() {
        return certIdno;
    }

    public void setCertIdno(String certIdno) {
        this.certIdno = certIdno;
    }

    public Short getGenderId() {
        return genderId;
    }

    public void setGenderId(Short genderId) {
        this.genderId = genderId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getWechartAccount() {
        return wechartAccount;
    }

    public void setWechartAccount(String wechartAccount) {
        this.wechartAccount = wechartAccount;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public Long getEmpiId() {
        return empiId;
    }

    public void setEmpiId(Long empiId) {
        this.empiId = empiId;
    }

    public short getPatientType() {
        return patientType;
    }

    public void setPatientType(short patientType) {
        this.patientType = patientType;
    }

    public short getIshaveCard() {
        return ishaveCard;
    }

    public void setIshaveCard(short ishaveCard) {
        this.ishaveCard = ishaveCard;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Short getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Short maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getParentsPhone() {
        return parentsPhone;
    }

    public void setParentsPhone(String parentsPhone) {
        this.parentsPhone = parentsPhone;
    }

    public String getParentsIDNO() {
        return parentsIDNO;
    }

    public void setParentsIDNO(String parentsIDNO) {
        this.parentsIDNO = parentsIDNO;
    }
}