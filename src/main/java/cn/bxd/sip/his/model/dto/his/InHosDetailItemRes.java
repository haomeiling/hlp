package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-08-03
 */
@Data
public class InHosDetailItemRes {
    private String chargeDate;
    private String drugType;
    private String projectCode;
    private String projectName;
    private String feeItemAmount;
    private String feeItemNum;
    private String feeItemUnit;
    private String feeItemAllAmount;
    private String feeItemStandard;
    private String payDate;
    private String amountMoney;
    private String MmedicareMoney;
    private String payMoney;
}