package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:     1.6.1	查询患者住院信息输出格式
 * User: LiSheng
 * Date: 2018-09-12
 * Time: 17:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPatientInHosInfoRespond extends BaseRespond {

    List<Inhos> inHosList;
}
