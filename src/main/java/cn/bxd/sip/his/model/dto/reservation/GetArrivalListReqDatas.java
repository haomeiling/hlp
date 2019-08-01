package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 查询待签到订单列表
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetArrivalListReqDatas extends BaseReqDates {
    private String empi;
    private String startDate;
    private String endDate;
    private String orderSourceId;
    private String orderTypeId;
    private String paidFlag;
    private String arrivalFlag;
}