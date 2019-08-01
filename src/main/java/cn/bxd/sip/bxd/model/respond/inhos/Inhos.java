package cn.bxd.sip.bxd.model.respond.inhos;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.math.BigDecimal;


/**
 * Description:    1.6.1 患者住院信息
 * User: LiSheng
 * Date: 2018-09-12
 * Time: 17:55
 * <p>
 * patientName	String	否	患者姓名
 * patientSex	String	否	0,女性。1，男性
 * cardNo	String	否	身份证号
 * inHosNo	String	否	住院号
 * inHosSerialNo	String	否	医保住院流水号
 * inHosDate	String	否	住院日期
 * inHosState	Int	否	0无住院，1申请中，2入院中，3已出院
 * bedNo	String	否	床号
 * departmentorganId	String	否	科室编码
 * departmentName	String	否	科室名称
 * medicalTypeCode	String	否	医保类型代码
 * medicalTypeName	String	否	医保类型名称
 * overMoney	Float	否	医保统筹金额（保留小数点后两位）
 * payMoney	Float	否	医保记账自费金额（保留小数点后两位）
 * cashMoney	Float	否	纯自费金额（保留小数点后两位）
 * depositMoney	Float	否	押金余额（保留小数点后两位）
 * totalMoney	Float	否	总费用（保留小数点后两位）
 */
@Data
public class Inhos {
    private String patientName;//患者姓名
    private String patientSex;//0,女性。1，男性
    private String cardNo;//身份证号
    private String inHosNo;//住院号
    private String inHosSerialNo;//医保住院流水号
    private String inHosDate;//住院日期
    private Integer inHosState;//0无住院，1申请中，2入院中，3已出院
    private String bedNo;//床号
    private String departmentorganId;//科室编码
    private String departmentName;//科室名称
    private String medicalTypeCode;//医保类型代码
    private String medicalTypeName;//医保类型名称
    private BigDecimal overMoney;//医保统筹金额（保留小数点后两位）
    private BigDecimal payMoney;//医保记账自费金额（保留小数点后两位）
    private BigDecimal cashMoney;//纯自费金额（保留小数点后两位）
    private BigDecimal depositMoney;//押金余额（保留小数点后两位）
    private BigDecimal totalMoney;//总费用（保留小数点后两位）
    private String leaveHosDate;//出院日期
    private String diagnose;//出院诊断
    private String extend;//扩展字段
}
