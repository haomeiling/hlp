package cn.bxd.sip.bxd.model.respond.hisuser;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPatientInfoBySocialsecurityNORespond extends BaseRespond {
    HiUserModel hiUserModel;
}
