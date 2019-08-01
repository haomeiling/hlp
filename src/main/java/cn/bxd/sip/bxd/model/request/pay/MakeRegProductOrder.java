package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	挂号生成订单
 * 函数名：makeRegProductOrder
 * 服务器地址URL：http://IP:端口/Client/Pay?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","hiFeeNos":"","payType":"","poAllPrice":"" ,"patientNo":"","patientName":"","cardId":""}
 * <p>
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * poType	        String	否	类型：1，医院产品定单，2，协医产品定单， 3，挂号定单  4,表示问诊定单  ,6住院'
 * objectId	        String	否	号源ID
 * poAllPrice	    float	否	总金额
 * patientNo	    String	否	患者编号
 * patientName	    String	否	患者姓名
 * cardId	        String	否	身份证
 * socialsecurityNO	String	否	社保卡号
 * userNo	        String	否	个人编号
 * medicareType	    Integer	否	0，非医保；1,市医保；
 * medicareInfo	    String	否	医保传入信息 * （格式见附表:医保传入信息）
 * extend	        String	否	扩展字段Json值，如： * {“key1”：”value1” ，”key2”：”value2”}
 **/
@Data
public class MakeRegProductOrder {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String poType;
    private String objectId;
    private String poAllPrice;
    private String patientNo;
    private String patientName;
    private String cardId;
    private String socialsecurityNO;
    private String userNo;
    private String medicareType;
    private String medicareInfo;
    private String extend;
}
