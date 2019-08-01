package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author：haomeiling
 * @Description：诊疗记录
 * @Date：2018/09/23 9:57.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class GetClinicRecordRes {

    private String ClinicalNo;//诊疗编号(诊疗唯一标识)

    private String ClinicType;//诊疗类型  // 1 门诊， 2 住院， 3 体检， 4 其他

    private String EMPI;//患者主索引

    private String PatientName;//患者姓名

    private Long AdmissionTime;// 接收时间 YYYYMMDDHHMMSS

    private Long DischargeTime;// 出院时间 YYYYMMDDHHMMSS

    private String Diamnose;//诊断名称 (用|分隔)

    private String ICD10Code;//诊断编码 (用|分隔)

    private String SymptomCode;//症状编码 (用|分隔)

    private String DoctorName;//责任医生姓名

    private String DeptName;//科室名称 (当前科室名称)

    private String BedNo;//床位号

    private String PreviousDept;//前序科室名称 ((用|分隔的前序转科路径)

    private Short Status;//状态 1 未结束 2  已结束

    public String getClinicalNo() {
        return ClinicalNo;
    }

    public void setClinicalNo(String clinicalNo) {
        ClinicalNo = clinicalNo;
    }

    public String getClinicType() {
        return ClinicType;
    }

    public void setClinicType(String clinicType) {
        ClinicType = clinicType;
    }

    public String getEMPI() {
        return EMPI;
    }

    public void setEMPI(String EMPI) {
        this.EMPI = EMPI;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public Long getAdmissionTime() {
        return AdmissionTime;
    }

    public void setAdmissionTime(Long admissionTime) {
        AdmissionTime = admissionTime;
    }

    public Long getDischargeTime() {
        return DischargeTime;
    }

    public void setDischargeTime(Long dischargeTime) {
        DischargeTime = dischargeTime;
    }

    public String getDiamnose() {
        return Diamnose;
    }

    public void setDiamnose(String diamnose) {
        Diamnose = diamnose;
    }

    public String getICD10Code() {
        return ICD10Code;
    }

    public void setICD10Code(String ICD10Code) {
        this.ICD10Code = ICD10Code;
    }

    public String getSymptomCode() {
        return SymptomCode;
    }

    public void setSymptomCode(String symptomCode) {
        SymptomCode = symptomCode;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getBedNo() {
        return BedNo;
    }

    public void setBedNo(String bedNo) {
        BedNo = bedNo;
    }

    public String getPreviousDept() {
        return PreviousDept;
    }

    public void setPreviousDept(String previousDept) {
        PreviousDept = previousDept;
    }

    public Short getStatus() {
        return Status;
    }

    public void setStatus(Short status) {
        Status = status;
    }
}
