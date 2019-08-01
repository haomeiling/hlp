package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	获取凭条打印信息（缴费成功）
 * 函数名：printInfo
 * 服务器地址URL：http://IP:端口/Client/Pay?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","patientNo":""}
 * <p>
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	String	否	用户名
 * synKey	    String	否	效验码
 * terminalCode	String	否	终端编号
 * hospitalId	String	否	医院ID
 * criteria	    String	否	Json串
 * criteria：
 * patientNo	String	否	患者编号
 * potype	    Int	否	1，医院产品定单，2，协医产品定单， 3，挂号定单  4,表示问诊定单  5,缴费订单  ,6住院'
 * poNo	        String	否	订单号，为空时返回三天内缴费信息
 * cardNo	    String	否	身份证（暂未开通）
 * extend	    String	否	扩展字段Json值，如： * {“key1”：”value1” ，”key2”：”value2”}
 **/
@Data
public class PrintInfo {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private Criteria criteria;
}
