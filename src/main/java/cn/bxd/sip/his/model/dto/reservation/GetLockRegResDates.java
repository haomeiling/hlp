package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: TODO
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetLockRegResDates extends BaseResDates {
    private String clinicTypeId;
    private String clinicTypeName;
    private String clinicDate;
    private Integer clinicDateInt;
    private String queueId;
    private String queueName;
    private String periodNo;
    private String periodStartInt;
    private String periodStart;
    private String periodEndInt;
    private String periodEnd;
    private String regPrice;
    private String regNo;
    private String sectionId;
    private String sectionName;
    private Integer timeoutSeconds;
    private String displayQueueNo;
    private String peerOrderNo;
    private String sectionAddr;
}