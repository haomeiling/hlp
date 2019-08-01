package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	处方支付
 * 函数名：doPay
 * 服务器地址URL：http://IP:端口/Client/Pay?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","hiFeeNos":"","payType":"","payRecord":"","payMoney":"","socialsecurityNO":"","hospitalNO":"","operatorNo":"","cycleNo":"","dynamic":","bankInformation":"","isOverall":"","poNo":"","buyerAccount":"","userNo":""}
 * 医保支付
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","hiFeeNos":"724719","payType":"4","payRecord":"","payMoney":"0.02","socialsecurityNO":"A00000185","hospitalNO":""450113003,"operatorNo":"155","cycleNo":"00013003-00000155-201509172026443915","dynamic":"3C970EDEABC5|50|","bankInformation":"","isOverall":"0","poNo":"2015112453344567423","buyerAccount":"","userNo":"11592242"}
 * 银行卡支付
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","hiFeeNos":"724810","payType":"6","payRecord":"000367","payMoney":"0.02","socialsecurityNO":"","hospitalNO":"","operatorNo":"","cycleNo":"","dynamic":","bankInformation":"00||6222620760001746786|000367||105450180620109|00065865|000052|20151207|164503|534116441887|","isOverall":"","poNo":"201512745245776625","buyerAccount":"6222620760001746786","userNo":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * hiFeeNos	        String	否	缴费编号
 * payType	        String	否	1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
 * payRecord	    String	否	支付交易流水号
 * payMoney	        String	否	个账支付金额
 * socialsecurityNO	String	是	社保卡号
 * hospitalNO			        医院编号
 * operatorNo			        操作人员编号
 * cycleNo			            周期编号
 * dynamic			            动态库参数（签到交易为：客户端MAC地址|用户数目|）
 * bankInformation	String	否	银行返回信息
 * isOverall	    String	是	是否进行医保支付或统筹（0，否，1，是）
 * poNo			                订单号
 * buyerAccount		        	银行卡号
 * userNo	        String	是	个人编号
 * medicareType	    Integer	否	0，非医保；1,市医保；
 * extend	        String	否	扩展字段Json值，如： * {“key1”：”value1” ，”key2”：”value2”}
 **/
@Data
public class DoPay {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String hiFeeNos;
    private String payType;
    private String payRecord;
    private String payMoney;
    private String socialsecurityNO;
    private String hospitalNO;
    private String operatorNo;
    private String cycleNo;
    private String dynamic;
    private String bankInformation;
    private String isOverall;
    private String poNo;
    private String buyerAccount;
    private String userNo;
    private Integer medicareType;
    private String extend;
}
