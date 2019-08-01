package cn.bxd.sip.bxd.model.respond.inhos;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:     1.6.2	自助入院申请查询信息
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 9:25
 * patientName	String	否	患都姓名
 * cardNo	String	否	身份证号
 * appointmentNo	String	否	预约编号
 * bedNo	String	否	床号
 * departmentorganId	String	否	科室编码
 * departmentName	String	否	科室名称
 * depositMoney	Float	否	押金余额（保留小数点后两位）
 * payMoney	Float	否	需补缴费金额（保留小数点后两位）
 * inHosState	String	否	0无住院，1申请中，2入院中，3已出院
 * medicalTypeCode	String	否	医保类型代码
 * medicalTypeNmae	String	否	医保类型名称
 */
@Data
public class HosInfo {

    private String patientName;//患都姓名
    private String cardNo;//身份证号
    private String appointmentNo;//预约编号
    private String bedNo;//床号
    private String departmentorganId;//科室编码
    private String departmentName;//科室名称
    private BigDecimal depositMoney;//押金余额（保留小数点后两位）
    private BigDecimal payMoney;//需补缴费金额（保留小数点后两位）
    private String inHosState;//0无住院，1申请中，2入院中，3已出院
    private String medicalTypeCode;//医保类型代码
    private String medicalTypeNmae;//医保类型名称

}
