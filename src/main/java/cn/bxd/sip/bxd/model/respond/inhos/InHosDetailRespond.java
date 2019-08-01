package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * Description:    1.6.7	住院清单明细
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 10:47
 * <p>
 * inHosList	String	否	Json数据
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InHosDetailRespond extends BaseRespond {
    ArrayList<HosDetail> inHosList = new ArrayList<>();
}
