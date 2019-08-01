package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:  待交费列表
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryToPayRecipeInfoListRes  extends BaseRes {
    private List<QueryToPayRecipeInfoListItem> hiFee;
}