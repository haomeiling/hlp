package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	医保回退
 * 函数名：yibaoFallback
 * 服务器地址URL：http://IP:端口/Client/Pay?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","poNo ":"","poAllPrice":"" ,"patientNo":""}
 * <p>
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * poNo	            String	否	订单号
 * socialsecurityNO	String	否	社保卡号
 * patientNo	    String	否	患者编号
 * poAllPrice	    float	否	医保支付总金额
 **/
@Data
public class YibaoFallback {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String poNo;
    private String socialsecurityNO;
    private String patientNo;
    private float poAllPrice;
}
