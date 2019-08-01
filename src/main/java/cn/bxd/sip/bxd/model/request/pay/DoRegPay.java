package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	挂号医保支付
 * 函数名：doRegPay
 * 服务器地址URL：http://IP:端口/Client/Pay?wsdl
 * <p>
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * hospitalNO			        医院编号
 * operatorNo			        操作人员编号
 * cycleNo			            周期编号
 * dynamic			            动态库参数（签到交易为：客户端MAC地址|用户数目|）
 * poNo			                订单号
 * payMoney	        String	否	个账支付金额
 * extend	        String	否	扩展字段Json值，如： * {“key1”：”value1”，”key2”：”value2”}
 **/
@Data
public class DoRegPay {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String hospitalNO;
    private String operatorNo;
    private String cycleNo;
    private String dynamic;
    private String poNo;
    private String payMoney;
    private String extend;
}
