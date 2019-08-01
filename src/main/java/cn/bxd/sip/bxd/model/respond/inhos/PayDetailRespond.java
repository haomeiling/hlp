package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:    1.6.5	住院押金补缴
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 10:47
 * <p>
 * payList	String	否	Json数据
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PayDetailRespond extends BaseRespond {

    List<Pay> payList;

}
