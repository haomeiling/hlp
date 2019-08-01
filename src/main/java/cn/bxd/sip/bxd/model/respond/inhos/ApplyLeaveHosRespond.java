package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-20
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ApplyLeaveHosRespond extends BaseRespond {
    String applyState;
    String applyResult;
    Float totalMoney;
    Float depositMoney;
    Float medicalMoney;
    Float payMoney;
    Float cashMoney;
    Float backMoney;

}
