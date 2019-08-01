package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Description:    1.6.5	住院押金补缴
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 10:47
 * <p>
 * patientName	String	否	患都姓名
 * departmentName	String	否	科室名称
 * bedNo	String	否	床号
 * depositMoney	Float	否	押金余额（保留小数点后两位）
 * medicalTypeNmae	String	否	医保类型名称
 * extend	String	否	扩展字段Json值，如：
 * {“key1”：”value1” ，”key2”：”value2”}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InpatientPaymentRespond extends BaseRespond {

    private String patientName;//	患都姓名
    private String departmentName;//	科室名称
    private String bedNo;//床号
    private BigDecimal depositMoney;//押金余额（保留小数点后两位）
    private String medicalTypeNmae;//医保类型名称
    private String extend;//扩展字段Json值，如：{“key1”：”value1” ，”key2”：”value2”}

}
