package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Lisheng
 * @version 1.0.0  2018-09-26
 */
@Data
public class GetHospitalizationRecordsItemResDatas {


    private String RID;
    private String SID;
    private String AdmissionTime;
    private String Type;
    private String Content;
    private String Purpose;
    private String Parts;
    private String SampleType;
    private String SampleCode;
    private String ClinicalNo;
    private String EMPI;
    private String PatientName;
    private String DeliverTime;
    private String DoctorName;
    private String DeptName;
    private String QATime;
    private String QAName;
    private String Status;
    private String BedNo;


}