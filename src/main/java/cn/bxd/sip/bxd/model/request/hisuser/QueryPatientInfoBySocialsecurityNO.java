package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-30
 * <p>
 * 函数名：queryPatientInfoBySocialsecurityNO
 * 服务器地址URL：http://IP:端口/Client/HisUser?wsdl
 * 注：社保、银行、就诊卡号传其中一个即可）
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","socialsecurityNO":""," medicareNO ":"","bankCardNo ":"""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * socialsecurityNO	String	是	社保卡号
 * medicareNO	    String	是	就诊卡号
 * bankCardNo	    String	是	银行卡号
 **/
@Data
public class QueryPatientInfoBySocialsecurityNO {

    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String cardNo;
    private String socialsecurityNO;
    private String medicareNO;
    private String bankCardNo;
}
