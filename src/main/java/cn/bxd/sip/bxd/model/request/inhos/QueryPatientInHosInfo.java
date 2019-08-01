package cn.bxd.sip.bxd.model.request.inhos;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * 接口说明	查询患者住院信息
 * 函数名：queryPatientInHosInfo
 * 服务器地址URL： http://IP:端口/Client/InHos?wsdl
 * 输入示例：
 * {"synUserName":"","synKey":"",…}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * cardNo	            String	否	身份证号
 * visitCardNo	        String	是	诊疗卡号
 * socialsecurityNO 	String	是	社保卡号
 * bankCardNumber	    String	是	其他卡号
 **/
@Data
public class QueryPatientInHosInfo {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String cardNo;
    private String visitCardNo;
    private String socialsecurityNO;
    private String bankCardNumber;
}
