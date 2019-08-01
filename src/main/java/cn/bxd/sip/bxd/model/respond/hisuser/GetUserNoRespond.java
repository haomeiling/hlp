package cn.bxd.sip.bxd.model.respond.hisuser;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-17
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class GetUserNoRespond extends BaseRespond {
    String userNo;
}
