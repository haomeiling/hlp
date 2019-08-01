package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

import java.util.Map;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-16
 */
@Data
public class DoRegPaymentDataMapReq {
    private String payType;
    private String payRecord;
    private String totalMoney;
    private String hasMPay;
    //hasMPay为1时，medicareData需传以下字段
    private Map<String,String> medicareData;
}