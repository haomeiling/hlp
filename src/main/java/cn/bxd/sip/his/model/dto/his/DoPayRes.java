package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 处方支付 doPay
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DoPayRes extends BaseRes {

    private String medicalCode;
    private String clearingNO;
    private String transactionTime;
    private String dispensaryWin;
    private String guidelinesInfo;
    private String reserve;
}