package cn.bxd.sip.bxd.model.respond.pay;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPaymentRecordStatusRespond extends BaseRespond {
    List<PaymentRecordStatus> hiFeeList = new ArrayList<>();
}
