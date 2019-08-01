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
public class PayDetailItemRes {
    private String inHosSerialNo;
    private String payMoney;
    private String invoiceNo;
    private String payType;
    private String isSettle;
    private String operatorNo;
    private String payDate;
}