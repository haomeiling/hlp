package cn.bxd.sip.bxd.model.respond.hisuser;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MedicalPayInfoRespond extends BaseRespond {
    List<PayMoney> payMoneyList;
}
