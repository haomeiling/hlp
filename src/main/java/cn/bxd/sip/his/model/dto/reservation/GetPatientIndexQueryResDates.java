package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetPatientIndexQueryResDates extends BaseResDates {
    private String CertTypeCode;
    private String CertTypeName;
    private String IDNo;
    private String PatientName;
    private String GenderID;
    private String GenderName;
    private String BirthDate;
    private String EMPI;
    private String CreatedDate;
    private String ClinicalCardNo;
    private String PatientNo;
    private String ContactPhone;
    private String ContactPerson;
    private String RelationShip;
//    private String msg;
}