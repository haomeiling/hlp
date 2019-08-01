package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@Data
public class GetPatientInHosItemResDatas {

    private String accDate;
    private String clinicalNo;
    private String clinicType;
    private String eMPI;
    private String patientName;
    private String admissionTime;
    private String dischargeTime;
    private String diagnose;
    private String iCD10Code;
    private String symptomCode;
    private String doctorName;
    private String deptName;
    private String previousDept;
    private String status;
    private String bedNo;

}