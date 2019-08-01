package cn.bxd.sip.bxd.model.request.inhos;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * 接口说明	住院押金补缴
 * 函数名：inpatientPayment
 * 服务器地址URL： http://IP:端口/Client/InHos?wsdl
 * 输入示例：
 * {"synUserName":"","synKey":"",…}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * inHosNo	        String	否	住院号
 * bedNo	        String	否	床号
 * payType	        Integer	否	1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
 * payRecord	    String	否	支付交易流水号
 * payMoney	        Float	否	预交金金额（保留小数点后两位）
 * payReturn    	String	是	支付交易返回
 * patientNo	    String	是	患者编号
 * patientName	    String	是	患者姓名
 * extend	        String	否	扩展字段Json值，如：{“key1”：”value1” ，”key2”：”value2”}
 **/
@Data
public class InpatientPayment {

    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String inHosNo;
    private String bedNo;
    private Integer payType;
    private String payRecord;
    private Float payMoney;
    private String payReturn;
    private String patientNo;
    private String patientName;
    private String extend;
}
