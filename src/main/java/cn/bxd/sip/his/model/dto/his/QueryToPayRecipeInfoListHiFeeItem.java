package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-16
 */
@Data
public class QueryToPayRecipeInfoListHiFeeItem {
    private String feeItemName;
    private double feeItemAmount;
    private double feeItemNum;
    private String feeItemUnit;
    private double feeItemAllAmount;
    private String feeItemStandard;
    private String cateId;
    private String cateName;
    private String extend;
}