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
public class QueryPaymentRecordListHiFeeItem {
    private String feeItemName;
    private String feeItemAmount;
    private String feeItemNum;
    private String feeItemUnit;
    private String feeItemAllAmount;
    private String feeItemStandard;
}