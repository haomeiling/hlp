package cn.bxd.sip.bxd.model.respond.pay;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.resultmessage.DoRegPayMgs;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-26
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class DoRegPayRespond extends BaseRespond {
    DoRegPayMgs resultmessage;
}
