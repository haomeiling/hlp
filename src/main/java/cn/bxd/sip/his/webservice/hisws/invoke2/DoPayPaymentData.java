package cn.bxd.sip.his.webservice.hisws.invoke2;

import lombok.Data;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-09-26
 * Time: 14:04
 */
@Data
public class DoPayPaymentData {
    private String hiFeeNo;
    private String terminalCode;
    private String payType;
    private String totalMoney;
    private String payRecord;
    private String bankReturn;
    private DoPayExtend extend;
    private Short hasMPay;
    private DoPayMedicareData medicareData;

}
