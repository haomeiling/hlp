package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetPaymentReqDatas extends BaseReqDates {
    private String poNo;
    private String feeIds;
}