package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * Description: TODO
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Data
public class GetQueueListItemResDates {
    private String queueId;
    private String queueName;
    private int clinicTypeId;
    private String clinicTypeName;
    private String empNo;
    private String empName;
    private String empLogoUrl;
    private int sectionId;
    private String sectionName;
    private String sectionAddr;
    private String jobTitle;
    private String speciality;
    private String mayjor;
    private String headerUrl;
    private String registerPrice;
    private String emergencyAddPrice;
    private String clinicDate;
    private int totalAvailable;
    private byte flag;
    private String planDate;
    private String eMeiNo;
    private String msg;
}