package cn.bxd.sip.bxd.model.respond.pay;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class DoPayRespond extends BaseRespond {
    String medicalCode;
    String clearingNO;
    String transactionTime;
    String dispensaryWin;
    String guidelinesInfo;
    String reserve;

}
