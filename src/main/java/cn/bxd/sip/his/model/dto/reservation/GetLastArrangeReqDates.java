package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 查询最晚排班日期 操作码1
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetLastArrangeReqDates extends BaseReqDates {
    private int deptTypeId;
    private String deptCode;
    private String queueId;
}