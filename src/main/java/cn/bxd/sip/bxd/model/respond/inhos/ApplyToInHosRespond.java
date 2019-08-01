package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:    1.6.2	自助入院申请查询
 * User: LiSheng
 * Date: 2018-09-12
 * Time: 17:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApplyToInHosRespond extends BaseRespond {

    ApplyInHosInfoRespond applyInHosInfo;
}
