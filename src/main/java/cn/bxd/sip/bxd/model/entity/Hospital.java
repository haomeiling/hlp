package cn.bxd.sip.bxd.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * T_RHIP_HOSPITAL
 * @author 
 */
public class Hospital implements Serializable {
    private Integer hospitalId;

    private Object hospitalCode;

    private Object hospitalName;

    private Integer createdDate;

    private Date detroyedDate;

    private Short displayOrder;

    private Integer zoneId;

    private Short registrationStatus;

    private Short restrictLevelId;

    private Short authorizedBeds;

    private Short openedBeds;

    private Short hospitalTypeId;

    private Short hospitalGradeId;

    private Short hospitalLevelId;

    private Short townTypeId;

    private Short ownershipId;

    private Short characterTypeId;

    private Object hospitalAddress;

    private Integer totalEmployee;

    private Integer mtEmployee;

    private Integer lastYearOutPatients;

    private Integer lastYearInPatients;

    private Integer lastYearOperations;

    private Object contactPersonName;

    private Object contactPersonPhone;

    private Object artPersonName;

    private Object artPersonPhone;

    private Object financialHeaderName;

    private Object financialHeaderPhone;

    private Object technicalHeaderName;

    private Object technicalHeaderPhone;

    private Object financialHeaderPosition;

    private Object technicalHeaderPosition;

    private Object introductionUrl;

    private Integer lastdeptno;

    private Integer lastempno;

    private Object logoUrl;

    private Short icLevelId;

    private Object aliasName;

    private Object specialNotes;

    private Object outpatientNoticeUrl;

    private Date lastPriceNotify;

    private Short queueCount;

    private Short isAdministrator;

    private static final long serialVersionUID = 1L;

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Object getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(Object hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public Object getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(Object hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDetroyedDate() {
        return detroyedDate;
    }

    public void setDetroyedDate(Date detroyedDate) {
        this.detroyedDate = detroyedDate;
    }

    public Short getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Short displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Short getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Short registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public Short getRestrictLevelId() {
        return restrictLevelId;
    }

    public void setRestrictLevelId(Short restrictLevelId) {
        this.restrictLevelId = restrictLevelId;
    }

    public Short getAuthorizedBeds() {
        return authorizedBeds;
    }

    public void setAuthorizedBeds(Short authorizedBeds) {
        this.authorizedBeds = authorizedBeds;
    }

    public Short getOpenedBeds() {
        return openedBeds;
    }

    public void setOpenedBeds(Short openedBeds) {
        this.openedBeds = openedBeds;
    }

    public Short getHospitalTypeId() {
        return hospitalTypeId;
    }

    public void setHospitalTypeId(Short hospitalTypeId) {
        this.hospitalTypeId = hospitalTypeId;
    }

    public Short getHospitalGradeId() {
        return hospitalGradeId;
    }

    public void setHospitalGradeId(Short hospitalGradeId) {
        this.hospitalGradeId = hospitalGradeId;
    }

    public Short getHospitalLevelId() {
        return hospitalLevelId;
    }

    public void setHospitalLevelId(Short hospitalLevelId) {
        this.hospitalLevelId = hospitalLevelId;
    }

    public Short getTownTypeId() {
        return townTypeId;
    }

    public void setTownTypeId(Short townTypeId) {
        this.townTypeId = townTypeId;
    }

    public Short getOwnershipId() {
        return ownershipId;
    }

    public void setOwnershipId(Short ownershipId) {
        this.ownershipId = ownershipId;
    }

    public Short getCharacterTypeId() {
        return characterTypeId;
    }

    public void setCharacterTypeId(Short characterTypeId) {
        this.characterTypeId = characterTypeId;
    }

    public Object getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(Object hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public Integer getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(Integer totalEmployee) {
        this.totalEmployee = totalEmployee;
    }

    public Integer getMtEmployee() {
        return mtEmployee;
    }

    public void setMtEmployee(Integer mtEmployee) {
        this.mtEmployee = mtEmployee;
    }

    public Integer getLastYearOutPatients() {
        return lastYearOutPatients;
    }

    public void setLastYearOutPatients(Integer lastYearOutPatients) {
        this.lastYearOutPatients = lastYearOutPatients;
    }

    public Integer getLastYearInPatients() {
        return lastYearInPatients;
    }

    public void setLastYearInPatients(Integer lastYearInPatients) {
        this.lastYearInPatients = lastYearInPatients;
    }

    public Integer getLastYearOperations() {
        return lastYearOperations;
    }

    public void setLastYearOperations(Integer lastYearOperations) {
        this.lastYearOperations = lastYearOperations;
    }

    public Object getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(Object contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public Object getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(Object contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public Object getArtPersonName() {
        return artPersonName;
    }

    public void setArtPersonName(Object artPersonName) {
        this.artPersonName = artPersonName;
    }

    public Object getArtPersonPhone() {
        return artPersonPhone;
    }

    public void setArtPersonPhone(Object artPersonPhone) {
        this.artPersonPhone = artPersonPhone;
    }

    public Object getFinancialHeaderName() {
        return financialHeaderName;
    }

    public void setFinancialHeaderName(Object financialHeaderName) {
        this.financialHeaderName = financialHeaderName;
    }

    public Object getFinancialHeaderPhone() {
        return financialHeaderPhone;
    }

    public void setFinancialHeaderPhone(Object financialHeaderPhone) {
        this.financialHeaderPhone = financialHeaderPhone;
    }

    public Object getTechnicalHeaderName() {
        return technicalHeaderName;
    }

    public void setTechnicalHeaderName(Object technicalHeaderName) {
        this.technicalHeaderName = technicalHeaderName;
    }

    public Object getTechnicalHeaderPhone() {
        return technicalHeaderPhone;
    }

    public void setTechnicalHeaderPhone(Object technicalHeaderPhone) {
        this.technicalHeaderPhone = technicalHeaderPhone;
    }

    public Object getFinancialHeaderPosition() {
        return financialHeaderPosition;
    }

    public void setFinancialHeaderPosition(Object financialHeaderPosition) {
        this.financialHeaderPosition = financialHeaderPosition;
    }

    public Object getTechnicalHeaderPosition() {
        return technicalHeaderPosition;
    }

    public void setTechnicalHeaderPosition(Object technicalHeaderPosition) {
        this.technicalHeaderPosition = technicalHeaderPosition;
    }

    public Object getIntroductionUrl() {
        return introductionUrl;
    }

    public void setIntroductionUrl(Object introductionUrl) {
        this.introductionUrl = introductionUrl;
    }

    public Integer getLastdeptno() {
        return lastdeptno;
    }

    public void setLastdeptno(Integer lastdeptno) {
        this.lastdeptno = lastdeptno;
    }

    public Integer getLastempno() {
        return lastempno;
    }

    public void setLastempno(Integer lastempno) {
        this.lastempno = lastempno;
    }

    public Object getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(Object logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Short getIcLevelId() {
        return icLevelId;
    }

    public void setIcLevelId(Short icLevelId) {
        this.icLevelId = icLevelId;
    }

    public Object getAliasName() {
        return aliasName;
    }

    public void setAliasName(Object aliasName) {
        this.aliasName = aliasName;
    }

    public Object getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(Object specialNotes) {
        this.specialNotes = specialNotes;
    }

    public Object getOutpatientNoticeUrl() {
        return outpatientNoticeUrl;
    }

    public void setOutpatientNoticeUrl(Object outpatientNoticeUrl) {
        this.outpatientNoticeUrl = outpatientNoticeUrl;
    }

    public Date getLastPriceNotify() {
        return lastPriceNotify;
    }

    public void setLastPriceNotify(Date lastPriceNotify) {
        this.lastPriceNotify = lastPriceNotify;
    }

    public Short getQueueCount() {
        return queueCount;
    }

    public void setQueueCount(Short queueCount) {
        this.queueCount = queueCount;
    }

    public Short getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(Short isAdministrator) {
        this.isAdministrator = isAdministrator;
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
        Hospital other = (Hospital) that;
        return (this.getHospitalId() == null ? other.getHospitalId() == null : this.getHospitalId().equals(other.getHospitalId()))
            && (this.getHospitalCode() == null ? other.getHospitalCode() == null : this.getHospitalCode().equals(other.getHospitalCode()))
            && (this.getHospitalName() == null ? other.getHospitalName() == null : this.getHospitalName().equals(other.getHospitalName()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getDetroyedDate() == null ? other.getDetroyedDate() == null : this.getDetroyedDate().equals(other.getDetroyedDate()))
            && (this.getDisplayOrder() == null ? other.getDisplayOrder() == null : this.getDisplayOrder().equals(other.getDisplayOrder()))
            && (this.getZoneId() == null ? other.getZoneId() == null : this.getZoneId().equals(other.getZoneId()))
            && (this.getRegistrationStatus() == null ? other.getRegistrationStatus() == null : this.getRegistrationStatus().equals(other.getRegistrationStatus()))
            && (this.getRestrictLevelId() == null ? other.getRestrictLevelId() == null : this.getRestrictLevelId().equals(other.getRestrictLevelId()))
            && (this.getAuthorizedBeds() == null ? other.getAuthorizedBeds() == null : this.getAuthorizedBeds().equals(other.getAuthorizedBeds()))
            && (this.getOpenedBeds() == null ? other.getOpenedBeds() == null : this.getOpenedBeds().equals(other.getOpenedBeds()))
            && (this.getHospitalTypeId() == null ? other.getHospitalTypeId() == null : this.getHospitalTypeId().equals(other.getHospitalTypeId()))
            && (this.getHospitalGradeId() == null ? other.getHospitalGradeId() == null : this.getHospitalGradeId().equals(other.getHospitalGradeId()))
            && (this.getHospitalLevelId() == null ? other.getHospitalLevelId() == null : this.getHospitalLevelId().equals(other.getHospitalLevelId()))
            && (this.getTownTypeId() == null ? other.getTownTypeId() == null : this.getTownTypeId().equals(other.getTownTypeId()))
            && (this.getOwnershipId() == null ? other.getOwnershipId() == null : this.getOwnershipId().equals(other.getOwnershipId()))
            && (this.getCharacterTypeId() == null ? other.getCharacterTypeId() == null : this.getCharacterTypeId().equals(other.getCharacterTypeId()))
            && (this.getHospitalAddress() == null ? other.getHospitalAddress() == null : this.getHospitalAddress().equals(other.getHospitalAddress()))
            && (this.getTotalEmployee() == null ? other.getTotalEmployee() == null : this.getTotalEmployee().equals(other.getTotalEmployee()))
            && (this.getMtEmployee() == null ? other.getMtEmployee() == null : this.getMtEmployee().equals(other.getMtEmployee()))
            && (this.getLastYearOutPatients() == null ? other.getLastYearOutPatients() == null : this.getLastYearOutPatients().equals(other.getLastYearOutPatients()))
            && (this.getLastYearInPatients() == null ? other.getLastYearInPatients() == null : this.getLastYearInPatients().equals(other.getLastYearInPatients()))
            && (this.getLastYearOperations() == null ? other.getLastYearOperations() == null : this.getLastYearOperations().equals(other.getLastYearOperations()))
            && (this.getContactPersonName() == null ? other.getContactPersonName() == null : this.getContactPersonName().equals(other.getContactPersonName()))
            && (this.getContactPersonPhone() == null ? other.getContactPersonPhone() == null : this.getContactPersonPhone().equals(other.getContactPersonPhone()))
            && (this.getArtPersonName() == null ? other.getArtPersonName() == null : this.getArtPersonName().equals(other.getArtPersonName()))
            && (this.getArtPersonPhone() == null ? other.getArtPersonPhone() == null : this.getArtPersonPhone().equals(other.getArtPersonPhone()))
            && (this.getFinancialHeaderName() == null ? other.getFinancialHeaderName() == null : this.getFinancialHeaderName().equals(other.getFinancialHeaderName()))
            && (this.getFinancialHeaderPhone() == null ? other.getFinancialHeaderPhone() == null : this.getFinancialHeaderPhone().equals(other.getFinancialHeaderPhone()))
            && (this.getTechnicalHeaderName() == null ? other.getTechnicalHeaderName() == null : this.getTechnicalHeaderName().equals(other.getTechnicalHeaderName()))
            && (this.getTechnicalHeaderPhone() == null ? other.getTechnicalHeaderPhone() == null : this.getTechnicalHeaderPhone().equals(other.getTechnicalHeaderPhone()))
            && (this.getFinancialHeaderPosition() == null ? other.getFinancialHeaderPosition() == null : this.getFinancialHeaderPosition().equals(other.getFinancialHeaderPosition()))
            && (this.getTechnicalHeaderPosition() == null ? other.getTechnicalHeaderPosition() == null : this.getTechnicalHeaderPosition().equals(other.getTechnicalHeaderPosition()))
            && (this.getIntroductionUrl() == null ? other.getIntroductionUrl() == null : this.getIntroductionUrl().equals(other.getIntroductionUrl()))
            && (this.getLastdeptno() == null ? other.getLastdeptno() == null : this.getLastdeptno().equals(other.getLastdeptno()))
            && (this.getLastempno() == null ? other.getLastempno() == null : this.getLastempno().equals(other.getLastempno()))
            && (this.getLogoUrl() == null ? other.getLogoUrl() == null : this.getLogoUrl().equals(other.getLogoUrl()))
            && (this.getIcLevelId() == null ? other.getIcLevelId() == null : this.getIcLevelId().equals(other.getIcLevelId()))
            && (this.getAliasName() == null ? other.getAliasName() == null : this.getAliasName().equals(other.getAliasName()))
            && (this.getSpecialNotes() == null ? other.getSpecialNotes() == null : this.getSpecialNotes().equals(other.getSpecialNotes()))
            && (this.getOutpatientNoticeUrl() == null ? other.getOutpatientNoticeUrl() == null : this.getOutpatientNoticeUrl().equals(other.getOutpatientNoticeUrl()))
            && (this.getLastPriceNotify() == null ? other.getLastPriceNotify() == null : this.getLastPriceNotify().equals(other.getLastPriceNotify()))
            && (this.getQueueCount() == null ? other.getQueueCount() == null : this.getQueueCount().equals(other.getQueueCount()))
            && (this.getIsAdministrator() == null ? other.getIsAdministrator() == null : this.getIsAdministrator().equals(other.getIsAdministrator()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHospitalId() == null) ? 0 : getHospitalId().hashCode());
        result = prime * result + ((getHospitalCode() == null) ? 0 : getHospitalCode().hashCode());
        result = prime * result + ((getHospitalName() == null) ? 0 : getHospitalName().hashCode());
        result = prime * result + ((getCreatedDate() == null) ? 0 : getCreatedDate().hashCode());
        result = prime * result + ((getDetroyedDate() == null) ? 0 : getDetroyedDate().hashCode());
        result = prime * result + ((getDisplayOrder() == null) ? 0 : getDisplayOrder().hashCode());
        result = prime * result + ((getZoneId() == null) ? 0 : getZoneId().hashCode());
        result = prime * result + ((getRegistrationStatus() == null) ? 0 : getRegistrationStatus().hashCode());
        result = prime * result + ((getRestrictLevelId() == null) ? 0 : getRestrictLevelId().hashCode());
        result = prime * result + ((getAuthorizedBeds() == null) ? 0 : getAuthorizedBeds().hashCode());
        result = prime * result + ((getOpenedBeds() == null) ? 0 : getOpenedBeds().hashCode());
        result = prime * result + ((getHospitalTypeId() == null) ? 0 : getHospitalTypeId().hashCode());
        result = prime * result + ((getHospitalGradeId() == null) ? 0 : getHospitalGradeId().hashCode());
        result = prime * result + ((getHospitalLevelId() == null) ? 0 : getHospitalLevelId().hashCode());
        result = prime * result + ((getTownTypeId() == null) ? 0 : getTownTypeId().hashCode());
        result = prime * result + ((getOwnershipId() == null) ? 0 : getOwnershipId().hashCode());
        result = prime * result + ((getCharacterTypeId() == null) ? 0 : getCharacterTypeId().hashCode());
        result = prime * result + ((getHospitalAddress() == null) ? 0 : getHospitalAddress().hashCode());
        result = prime * result + ((getTotalEmployee() == null) ? 0 : getTotalEmployee().hashCode());
        result = prime * result + ((getMtEmployee() == null) ? 0 : getMtEmployee().hashCode());
        result = prime * result + ((getLastYearOutPatients() == null) ? 0 : getLastYearOutPatients().hashCode());
        result = prime * result + ((getLastYearInPatients() == null) ? 0 : getLastYearInPatients().hashCode());
        result = prime * result + ((getLastYearOperations() == null) ? 0 : getLastYearOperations().hashCode());
        result = prime * result + ((getContactPersonName() == null) ? 0 : getContactPersonName().hashCode());
        result = prime * result + ((getContactPersonPhone() == null) ? 0 : getContactPersonPhone().hashCode());
        result = prime * result + ((getArtPersonName() == null) ? 0 : getArtPersonName().hashCode());
        result = prime * result + ((getArtPersonPhone() == null) ? 0 : getArtPersonPhone().hashCode());
        result = prime * result + ((getFinancialHeaderName() == null) ? 0 : getFinancialHeaderName().hashCode());
        result = prime * result + ((getFinancialHeaderPhone() == null) ? 0 : getFinancialHeaderPhone().hashCode());
        result = prime * result + ((getTechnicalHeaderName() == null) ? 0 : getTechnicalHeaderName().hashCode());
        result = prime * result + ((getTechnicalHeaderPhone() == null) ? 0 : getTechnicalHeaderPhone().hashCode());
        result = prime * result + ((getFinancialHeaderPosition() == null) ? 0 : getFinancialHeaderPosition().hashCode());
        result = prime * result + ((getTechnicalHeaderPosition() == null) ? 0 : getTechnicalHeaderPosition().hashCode());
        result = prime * result + ((getIntroductionUrl() == null) ? 0 : getIntroductionUrl().hashCode());
        result = prime * result + ((getLastdeptno() == null) ? 0 : getLastdeptno().hashCode());
        result = prime * result + ((getLastempno() == null) ? 0 : getLastempno().hashCode());
        result = prime * result + ((getLogoUrl() == null) ? 0 : getLogoUrl().hashCode());
        result = prime * result + ((getIcLevelId() == null) ? 0 : getIcLevelId().hashCode());
        result = prime * result + ((getAliasName() == null) ? 0 : getAliasName().hashCode());
        result = prime * result + ((getSpecialNotes() == null) ? 0 : getSpecialNotes().hashCode());
        result = prime * result + ((getOutpatientNoticeUrl() == null) ? 0 : getOutpatientNoticeUrl().hashCode());
        result = prime * result + ((getLastPriceNotify() == null) ? 0 : getLastPriceNotify().hashCode());
        result = prime * result + ((getQueueCount() == null) ? 0 : getQueueCount().hashCode());
        result = prime * result + ((getIsAdministrator() == null) ? 0 : getIsAdministrator().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hospitalId=").append(hospitalId);
        sb.append(", hospitalCode=").append(hospitalCode);
        sb.append(", hospitalName=").append(hospitalName);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", detroyedDate=").append(detroyedDate);
        sb.append(", displayOrder=").append(displayOrder);
        sb.append(", zoneId=").append(zoneId);
        sb.append(", registrationStatus=").append(registrationStatus);
        sb.append(", restrictLevelId=").append(restrictLevelId);
        sb.append(", authorizedBeds=").append(authorizedBeds);
        sb.append(", openedBeds=").append(openedBeds);
        sb.append(", hospitalTypeId=").append(hospitalTypeId);
        sb.append(", hospitalGradeId=").append(hospitalGradeId);
        sb.append(", hospitalLevelId=").append(hospitalLevelId);
        sb.append(", townTypeId=").append(townTypeId);
        sb.append(", ownershipId=").append(ownershipId);
        sb.append(", characterTypeId=").append(characterTypeId);
        sb.append(", hospitalAddress=").append(hospitalAddress);
        sb.append(", totalEmployee=").append(totalEmployee);
        sb.append(", mtEmployee=").append(mtEmployee);
        sb.append(", lastYearOutPatients=").append(lastYearOutPatients);
        sb.append(", lastYearInPatients=").append(lastYearInPatients);
        sb.append(", lastYearOperations=").append(lastYearOperations);
        sb.append(", contactPersonName=").append(contactPersonName);
        sb.append(", contactPersonPhone=").append(contactPersonPhone);
        sb.append(", artPersonName=").append(artPersonName);
        sb.append(", artPersonPhone=").append(artPersonPhone);
        sb.append(", financialHeaderName=").append(financialHeaderName);
        sb.append(", financialHeaderPhone=").append(financialHeaderPhone);
        sb.append(", technicalHeaderName=").append(technicalHeaderName);
        sb.append(", technicalHeaderPhone=").append(technicalHeaderPhone);
        sb.append(", financialHeaderPosition=").append(financialHeaderPosition);
        sb.append(", technicalHeaderPosition=").append(technicalHeaderPosition);
        sb.append(", introductionUrl=").append(introductionUrl);
        sb.append(", lastdeptno=").append(lastdeptno);
        sb.append(", lastempno=").append(lastempno);
        sb.append(", logoUrl=").append(logoUrl);
        sb.append(", icLevelId=").append(icLevelId);
        sb.append(", aliasName=").append(aliasName);
        sb.append(", specialNotes=").append(specialNotes);
        sb.append(", outpatientNoticeUrl=").append(outpatientNoticeUrl);
        sb.append(", lastPriceNotify=").append(lastPriceNotify);
        sb.append(", queueCount=").append(queueCount);
        sb.append(", isAdministrator=").append(isAdministrator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}