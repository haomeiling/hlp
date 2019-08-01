package cn.bxd.sip.bxd.model.entity;

import java.util.Date;

/**
 * 患者主索引表
 *
 * @author hml
 *         <p/>
 * @date 2015年1月19日
 */
public class Patient {
    private Integer empiId;//患者empi

    private Integer actualEmpi;//有效患者empi

    private String empiNo;//企业主索引号

    private String gmpiNo;//全局主索引号

    private String patientName;//姓名

    private String historicalName;//曾用名

    private Integer birthDate;//出生日期

    private Integer birthTime;//出生时间

    private String birthPlace;//出生地

    private Short nationalityId;//名族代号

    private Short genderId;//性别代号

    private Integer countryId;//国籍代号

    private Short rhBloodTypeId;//RH血型代号

    private Short aboBloodTypeId;//ABO血型代号

    private Integer zoneId;//出生地区代号

    private Short careerId;//执业代号

    private Short eduBackgroundId;//学历代号

    private Short degreeId;//学位代号

    private Short qualificationId;//职称代号

    private String certIdno;//身份证件类型代号

    private Short certTypeId;//身份证件号码

    private Date createdTime;//创建时间

    private Date lastUpdated;//最近更新时间

    private Short disabled;//失效标志位

    public Integer getEmpiId() {
        return empiId;
    }

    public void setEmpiId(Integer empiId) {
        this.empiId = empiId;
    }

    public Integer getActualEmpi() {
        return actualEmpi;
    }

    public void setActualEmpi(Integer actualEmpi) {
        this.actualEmpi = actualEmpi;
    }

    public String getEmpiNo() {
        return empiNo;
    }

    public void setEmpiNo(String empiNo) {
        this.empiNo = empiNo;
    }

    public String getGmpiNo() {
        return gmpiNo;
    }

    public void setGmpiNo(String gmpiNo) {
        this.gmpiNo = gmpiNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getHistoricalName() {
        return historicalName;
    }

    public void setHistoricalName(String historicalName) {
        this.historicalName = historicalName;
    }

    public Integer getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Integer birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(Integer birthTime) {
        this.birthTime = birthTime;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Short getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Short nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Short getGenderId() {
        return genderId;
    }

    public void setGenderId(Short genderId) {
        this.genderId = genderId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Short getRhBloodTypeId() {
        return rhBloodTypeId;
    }

    public void setRhBloodTypeId(Short rhBloodTypeId) {
        this.rhBloodTypeId = rhBloodTypeId;
    }

    public Short getAboBloodTypeId() {
        return aboBloodTypeId;
    }

    public void setAboBloodTypeId(Short aboBloodTypeId) {
        this.aboBloodTypeId = aboBloodTypeId;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Short getCareerId() {
        return careerId;
    }

    public void setCareerId(Short careerId) {
        this.careerId = careerId;
    }

    public Short getEduBackgroundId() {
        return eduBackgroundId;
    }

    public void setEduBackgroundId(Short eduBackgroundId) {
        this.eduBackgroundId = eduBackgroundId;
    }

    public Short getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Short degreeId) {
        this.degreeId = degreeId;
    }

    public Short getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Short qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getCertIdno() {
        return certIdno;
    }

    public void setCertIdno(String certIdno) {
        this.certIdno = certIdno;
    }

    public Short getCertTypeId() {
        return certTypeId;
    }

    public void setCertTypeId(Short certTypeId) {
        this.certTypeId = certTypeId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Short getDisabled() {
        return disabled;
    }

    public void setDisabled(Short disabled) {
        this.disabled = disabled;
    }
}