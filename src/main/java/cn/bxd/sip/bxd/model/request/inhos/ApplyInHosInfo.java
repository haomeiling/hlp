package cn.bxd.sip.bxd.model.request.inhos;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * applyInHosInfo:
 * inHosNo	            String	否	住院号
 * bedNo	            String	否	床号
 * visitCardNo	        String	是	就诊卡号
 * patientNo	        String	否	患者编号
 * appointmentNo	    String	否	预约编号
 * departmentorganId	String	否	科室编码
 * departmentName	    String	否	科室名称
 * medicareType	    Integer	否	0，非医保；1,市医保；
 * payType	            Integer	否	1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
 * payRecord	        String	否	支付交易流水号
 * payMoney	        String	否	缴纳押金金额
 * socialsecurityNO	String	是	社保卡号
 * medicareInfo	    String	否	医保传入信息（格式见附表:医保传入信息）
 * payReturn	        String	是	支付交易返回
 * extend	            String	否	扩展字段Json值，如： 			{“key1”：”value1”，”key2”：”value2”}
 **/
@Data
public class ApplyInHosInfo {
    private String inHosNo;
    private String bedNo;
    private String visitCardNo;
    private String patientNo;
    private String appointmentNo;
    private String departmentorganId;
    private String departmentName;
    private Integer medicareType;
    private Integer payType;
    private String payRecord;
    private String payMoney;
    private String socialsecurityNO;
    private String medicareInfo;
    private String payReturn;
    private String extend;
}
