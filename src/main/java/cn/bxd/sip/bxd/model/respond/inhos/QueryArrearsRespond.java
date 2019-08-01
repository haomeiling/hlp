package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Description:    1.6.4	住院押金补缴查询
 * User: LiSheng
 * Date: 2018-09-12
 * Time: 17:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryArrearsRespond extends BaseRespond {


    private String patientName;//患都姓名
    private String departmentNam;//科室名称
    private String bedNo;//床号
    private BigDecimal depositMone;//押金余额（保留小数点后两位）
    private BigDecimal uppeLimit;//补缴上限
    private BigDecimal lowerLimit;//补缴下限（上下限一致则为固定金额补缴）

}
