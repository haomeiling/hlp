package cn.bxd.sip.bxd.model.respond.inhos;

import lombok.Data;

/**
 * Description:     1.6.3	自助入院申请办理
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 9:25
 * inHosNo	String	否	住院号
 * bedNo	String	否	床号
 * visitCardNo	String	是	就诊卡号
 * patientNo	String	否	患者编号
 * departmentorganId	String	否	科室编码
 * departmentName	String	否	科室名称
 * medicareType	Integer	否	医保类型（0非医保\1市医保\2区医保\3市统一结算）
 * payType	Integer	否	1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行，8 柳州，9、招行
 * payRecord	String	否	支付交易流水号
 * payMoney	String	否	缴纳押金金额
 * socialsecurityNO	String	是	社保卡号
 * medicareInfo	String	否	医保传入信息
 * （来源于住院医保信息查询，格式见附表:就诊登记交易入参）
 * payReturn	String	是	支付交易返回
 * extend	String	是	扩展字段Json值，如：
 * {“key1”：“value1” ，“key2”：“value2”}
 */
@Data
public class ApplyInHosInfo {
    private String patientName;//患都姓名

    private String inHosNo;//住院号
    private String bedNo;//床号
    private String visitCardNo;//就诊卡号
    private String patientNo;//患者编号
    private String departmentorganI;//科室编码
    private String departmentName;//科室名称
    private Integer medicareType;//医保类型（0非医保\1市医保\2区医保\3市统一结算）
    private Integer payType;//1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行，8 柳州，9、招行
    private String payRecord;//支付交易流水号
    private String payMoney;//缴纳押金金额
    private String socialsecurityNO;//社保卡号
    private String medicareInfo;//医保传入信息（来源于住院医保信息查询，格式见附表:就诊登记交易入参）
    private String payReturn;//支付交易返回
    private String extend;//扩展字段Json值，如：{“key1”：“value1” ，“key2”：“value2”}


}
