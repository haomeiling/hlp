package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	预结算
 * 函数名：preDoPay
 * 服务器地址URL：http://IP:端口/Client/Pay?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","hiFeeNos":"724719","payType":"4","socialsecurityNO":"","hospitalNO":"450113003","operatorNo":"155","cycleNo":"00013003-00000155-201509172026443915","dynamic":"3C970EDEABC5|50|"}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * hiFeeNos	            String	否	缴费编号 * (注多个可以逗号分开如 112，2222) * 挂号传患者编号
 * payType	            String	否	支付方式1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
 * socialsecurityNO	    String	否	社保卡号
 * hospitalNO			            医院编号
 * operatorNo			            操作人员编号
 * cycleNo			                周期编号
 * dynamic			                动态库参数（签到交易为：客户端MAC地址|用户数目|）
 * medicareType	        Integer	否	0，非医保；1,市医保；
 * cardinfo	            String	否	卡信息
 * overallArea	        String	否	统筹区号
 * extend	            String	否	扩展字段Json值，如： * {“key1”：”value1”，”key2”：”value2”}
 **/
@Data
public class PreDoPay {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String hiFeeNos;
    private String payType;
    private String socialsecurityNO;
    private String hospitalNO;
    private String operatorNo;
    private String cycleNo;
    private String dynamic;
    private Integer medicareType;
    private String cardinfo;
    private String overallArea;
    private String extend;
}
