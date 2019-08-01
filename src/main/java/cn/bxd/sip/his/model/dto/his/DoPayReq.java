package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

import java.util.Map;

/**
 * Description: 处方支付 doPay
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-16
 */
@Data
public class DoPayReq {

    private String hiFeeNo;
    private String terminalCode;
    private String payType;
    private String totalMoney;
    private String payRecord;
    private String bankReturn;
    private String extend;
    private String hasMPay;
    //当hasMPay为1时，medicareData需传以下字段
    private Map<String,String> medicareData;

}