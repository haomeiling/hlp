package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:    1.6.8	住院医保信息查询
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 10:47
 * <p>
 * medicalInfo	String	否	医保信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InHosMedicalInfoRespond extends BaseRespond {
    String medicalInfo;//医保信息
}
