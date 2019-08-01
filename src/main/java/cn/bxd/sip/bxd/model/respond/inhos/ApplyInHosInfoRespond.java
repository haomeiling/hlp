package cn.bxd.sip.bxd.model.respond.inhos;

import lombok.Data;

/**
 * @Author cRyann
 * @Create 2018/9/26
 * <p>
 * applyInHosInfo:
 * inHosSerialNo	String	否	住院流水号
 * inHosDate	String	否	入院日期
 * patientName	String	否	患都姓名
 * inHosNo	String	否	住院号
 * depositMoney	Float	否	押金余额（保留小数点后两位）
 * medicalTypeCode	String	否	医保类型代码
 * medicalTypeNmae	String	否	医保类型名称
 * reminder	String	否	入院温馨提醒
 */
@Data
public class ApplyInHosInfoRespond {
    String inHosSerialNo;//住院流水号
    String inHosDate;//入院日期
    String patientName;//患都姓名
    String inHosNo;//住院号
    Float depositMoney;//押金余额（保留小数点后两位）
    String medicalTypeCode;//医保类型代码
    String medicalTypeNmae;//医保类型名称
    String reminder;//入院温馨提醒
}
