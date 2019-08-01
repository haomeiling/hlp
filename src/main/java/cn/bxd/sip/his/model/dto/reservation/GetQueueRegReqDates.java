package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 查询号源时段 操作码2003
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetQueueRegReqDates extends BaseReqDates {
    private int deptTypeId;
    private int clinicTypeId;
    private String queueId;
    private String startTime;
    private String endTime;
    private String deptCode;
    private String meiNo;
    private String eMeiNo;
    private int periodNo;
}