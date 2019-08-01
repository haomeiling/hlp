package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetBillInfoListResDatas extends BaseResDates  {

    private List<GetBillInfoListItemResDatas> data;

    private String medicareMess;//卡串输出信息 haomeiling 20180926
}