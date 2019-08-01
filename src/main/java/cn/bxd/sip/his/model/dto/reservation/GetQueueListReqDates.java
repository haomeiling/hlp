package cn.bxd.sip.his.model.dto.reservation;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 查询队列列表 2021
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetQueueListReqDates extends BaseReqDates {
    private int deptTypeId;
    private int clinicTypeId;
    private String startTime;
    private String endTime;
    private String clinicDate;
    private String deptCode;
    private String deptIds;
}