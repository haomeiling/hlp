package cn.bxd.sip.bxd.model.respond.common;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-10
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class InsuredPayInfoRespond extends BaseRespond {
    String insuredTime;
    String insuredState;
    String personCategory;
    String personIdentity;

}
