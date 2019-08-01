package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Description:    1.6.10	查询出院清单
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 10:47
 * <p>
 * totalMoney	Float	否	住院总费用
 * inHosSerialNo	String	否	住院流水号
 * medicalTypeCode	String	否	医保类型代码
 * medicalTypeNmae	String	否	医保类型名称
 * inHosList	String	否	Json数据
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryLeaveHosDetailRespond extends BaseRespond {

    BigDecimal totalMoney;//住院总费用
    String inHosSerialNo;//住院流水号
    String medicalTypeCode;//医保类型代码
    String medicalTypeNmae;//医保类型名称
    ArrayList<LeaveHosDetail> inHosList = new ArrayList<>();//Json数据
}
