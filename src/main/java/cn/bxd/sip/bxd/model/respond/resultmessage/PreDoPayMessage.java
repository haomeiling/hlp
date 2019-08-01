package cn.bxd.sip.bxd.model.respond.resultmessage;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-06
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PreDoPayMessage extends BaseRespond {
    String totalMoney;
    String overMoney;
    String payMoney;
    String cashMoney;
}
