package cn.bxd.sip.bxd.model.respond.resultmessage;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-26
 **/
@Data
public class DoRegPayMgs {
    String medicalCode;
    String clearingNO;
    String transactionTime;
    String dispensaryWin;
    String guidelinesInfo;
    String reserve;

}
