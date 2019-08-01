package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	生成订单
 * 函数名：makeProductOrder
 * 服务器地址URL：http://IP:端口/Client/Pay?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","hiFeeNos":"","payType":"","socialsecurityNO":"","poAllPrice":"","payMoney":"","isOverall":"","patientNo":"","patientName":"","cardId":"","sex":"",}
 * 医保支付
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","hiFeeNos":"724719","payType":"4","socialsecurityNO":"","poAllPrice":"0.02","payMoney":"0.02","isOverall":"0","patientNo":"75430295","patientName":"陆永","cardId":"450881198512066014","sex":"1"}
 * 银行支付
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","hiFeeNos":"724810","payType":"6","socialsecurityNO":"","poAllPrice":"0.02","payMoney":"0.02","isOverall":"0","patientNo":"75430295","patientName":"陆永","cardId":"450881198512066014","sex":"1"}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * hiFeeNos	            String	否	缴费编号 * (注多个可以逗号分开如 112，2222)
 * payType	            String	否	支付方式1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行,8、柳行,9、招商
 * socialsecurityNO	    String	否	社保卡号
 * poAllPrice	        float	否	总金额
 * payMoney	            float	否	现金支付金额
 * isOverall	        String	否	是否进行医保个账支付或统筹（0否，1是）
 * patientNo			            患者编号
 * patientName			            患者姓名
 * cardId			                身份证
 * sex			                    性别
 * medicareType	        Integer	否	0，非医保；1,市医保；
 * cardinfo	            String	否	卡信息
 * medicareMess	        String	否	医保个人基本信息（医保读卡交易返回值。根据 medicareType判断返回值格式）
 * originalPrice	    Float	否	原价（小数后两位）
 * extend	            String	否	扩展字段Json值，如： * {“key1”：”value1” ，”key2”：”value2”}
 **/
@Data
public class MakeProductOrder {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String hiFeeNos;
    private String payType;
    private String socialsecurityNO;
    private float poAllPrice;
    private float payMoney;
    private String isOverall;
    private String patientNo;
    private String patientName;
    private String cardId;
    private String sex;
    private Integer medicareType;
    private String cardinfo;
    private String medicareMess;
    private String originalPrice;
    private String extend;
}
