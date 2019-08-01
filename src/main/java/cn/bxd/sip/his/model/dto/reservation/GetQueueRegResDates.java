package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description: 查询号源时段
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetQueueRegResDates extends BaseResDates {
    private int deptTypeId;
    private int clinicTypeId;
    private List<GetQueueRegDateListItemResDates> dateList;
}