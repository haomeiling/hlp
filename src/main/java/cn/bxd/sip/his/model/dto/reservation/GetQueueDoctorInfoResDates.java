package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: TODO
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetQueueDoctorInfoResDates extends BaseResDates {
    private String clinicDate;
    private String queueId;
    private String queueName;
    private int clinicTypeId;
    private String clinicTypeName;
    private String empNo;
    private String totalAvailable;
    private byte flag;
//    private String msg;
}