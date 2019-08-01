package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 身份证号码查看患者信息
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPatientInfoByIdNORes extends BaseRes {
    private String patientNo;
    private String patientName;
    private String patientIdcardNo;
    private String socialsecurityNO;
    private String patientSex;
    private String patientBirthday;
    private String balance;
    private String patientTelephone;
    private String personCategory;
    private String medicareNO;
    private String clinicNo;
    private String extend;
}