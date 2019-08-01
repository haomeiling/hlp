package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetArrivalListResDatas extends BaseResDates {

    private List<GetArrivalListItemResDatas> orderList;
    private String signPayFlag;

}