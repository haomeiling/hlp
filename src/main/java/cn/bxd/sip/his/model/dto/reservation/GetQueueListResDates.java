package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description: 查询队列列表
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetQueueListResDates extends BaseResDates {
    private Integer signPayFlag;//订单签到是否需要支付标志位，0：否 1： 是
    private List<GetQueueListItemResDates> queueList;
}