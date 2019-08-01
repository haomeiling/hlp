package cn.bxd.sip.bxd.model.request.inhos;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * 接口说明	出院结算
 * 函数名：leaveHosPay
 * 服务器地址URL： http://IP:端口/Client/InHos?wsdl
 * 输入示例：
 * {"synUserName":"","synKey":"",…}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * inHosNo	            String	否	住院号
 * payType	            Integer	否	1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
 * payRecord	        String	否	支付交易流水号
 * payMoney	            Float	否	支付金额（保留小数点后两位）
 * medicareMoney	    Float	否	医保个账支付金额
 * overMoney	        Float	是	医保统筹金额
 * socialsecurityNO	    String	是	社保卡号
 * medicareRecord	    String	是	医保结算流水号
 * medicareType	        Integer	否	0，非医保；1,市医保；
 * medicareReturn	    String	是	医保返回信息 * （市医保：门诊登记+处方上传+费用结算）
 * bankReturn	        String	是	银行返回信息（支付类型返回相应银行信息）
 * patientNo	        String	是	患者编号
 * patientName	        String	是	患者姓名
 * userNo	            String	是	个人编号
 * extend	            String	否	扩展字段Json值，如：{“key1”：”value1” ，”key2”：”value2”}
 **/
@Data
public class LeaveHosPay {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String inHosNo;
    private Integer payType;
    private String payRecord;
    private Float payMoney;
    private Float medicareMoney;
    private Float overMoney;
    private String socialsecurityNO;
    private String medicareRecord;
    private Integer medicareType;
    private String medicareReturn;
    private String bankReturn;
    private String patientNo;
    private String patientName;
    private String userNo;
    private String extend;
}
