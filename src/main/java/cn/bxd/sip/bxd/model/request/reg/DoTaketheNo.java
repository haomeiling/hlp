package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	取号
 * 函数名：doTaketheNo
 * 服务器地址URL：http://IP:端口/Client/Reg?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"",hospitalId":"","sourceMark":"","patientNo":"","sourceDate":"","departmentorganId":"","payType":"","payRecord":"","payMoney":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * sourceMark	        String	否	号源编号
 * patientNo	        String	否	患者编号
 * sourceDate	        String	否	号源日期
 * departmentorganId	String	否	科室编号
 * payType	            String	否	1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
 * payRecord	        String	是	支付交易流水号
 * payMoney	            String	是	实际支付金额
 * extend	            String	否	扩展字段Json值，如： * {“key1”：”value1” ，”key2”：”value2”} * 支付宝微信医保支付这里要传订单号（poNo）
 **/
@Data
public class DoTaketheNo {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String sourceMark;
    private String patientNo;
    private String sourceDate;
    private String departmentorganId;
    private String payType;
    private String payRecord;
    private String payMoney;
    private String extend;
}
