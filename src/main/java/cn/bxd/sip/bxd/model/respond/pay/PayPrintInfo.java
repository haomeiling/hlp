package cn.bxd.sip.bxd.model.respond.pay;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-26
 **/
@Data
public class PayPrintInfo {
    String hiFeeNo;
    String patientNo;
    String patientName;
    String poAllPrice;
    Integer payStatus;
    String poCreateTime;
    String medicalCode;
    String clearingNO;
    String transactionTime;
    String dispensaryWin;
    String guidelinesInfo;
    HiFee hiFee;
}
