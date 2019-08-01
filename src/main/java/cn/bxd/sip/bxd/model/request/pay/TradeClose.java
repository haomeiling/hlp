package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	交易关闭
 * 函数名：trade_close
 * 服务器地址URL：http://IP:端口/Client/HisUser?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"" ,"terminalCode":"","hospitalId":""","poNo":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * poNo	            String	否	订单号
 * payType	        String	否	支付方式，2支付宝 5、微信
 **/
@Data
public class TradeClose {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String poNo;
    private String payType;
}
