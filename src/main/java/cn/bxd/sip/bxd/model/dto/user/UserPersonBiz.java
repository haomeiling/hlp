package cn.bxd.sip.bxd.model.dto.user;

import cn.bxd.sip.bxd.model.entity.UserPerson;

/**
 * Doc comment here
 *
 * @author HML
 * @Date 2016/1/29
 */
public class UserPersonBiz extends UserPerson {
    private String birthDateStr;
    private String clinicCardNo;//就诊卡号
    private String patientNo;//患者编码
    private String hmpi;//患者院内编码

    //增加性别code和性别名称 20180709添加
    private String genderCode;
    private String genderName;

    //增加医院ID
    private Integer hospitalId;
    private String hospitalName;

    public String getBirthDateStr() {
        return birthDateStr;
    }

    public void setBirthDateStr(String birthDateStr) {
        this.birthDateStr = birthDateStr;
    }

    public String getClinicCardNo() {
        return clinicCardNo;
    }

    public void setClinicCardNo(String clinicCardNo) {
        this.clinicCardNo = clinicCardNo;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getHmpi() {
        return hmpi;
    }

    public void setHmpi(String hmpi) {
        this.hmpi = hmpi;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
