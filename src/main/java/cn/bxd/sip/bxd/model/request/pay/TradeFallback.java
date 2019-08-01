package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	支付宝微信回退
 * 函数名：tradeFallback
 * 服务器地址URL：http://IP:端口/Client/HisUser?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","poNo ":"","payType":"","patientNo":"","poAllPrice":""}
 * <p>
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * poNo	                String	否	订单号
 * payType	            String	否	支付类型：1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
 * patientNo	        String	否	患者编号
 * poAllPrice	        float	否	现金支付总金额
 **/
@Data
public class TradeFallback {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String poNo;
    private String payType;
    private String patientNo;
    private float poAllPrice;
}
