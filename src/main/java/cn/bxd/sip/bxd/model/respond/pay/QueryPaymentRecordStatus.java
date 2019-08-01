package cn.bxd.sip.bxd.model.respond.pay;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@EqualsAndHashCode(callSuper = true)
public class QueryPaymentRecordStatus extends BaseRespond {
    List<HiFeeState> hiFeeStateList;
}
