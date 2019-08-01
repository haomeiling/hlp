package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-30
 * <p>
 * 函数名：doCreatCardInfo
 * 服务器地址URL：http: http://IP:端口/Client/HisUser?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","THiUser"}
 * 输入格式：
 * "THiUser":{"patientNo":"","patientName":"","patientIdcardNo":"","socialsecurityNO":"","patientSex":"","patientBirthday":"","balance":"","patientTelephone ":""," personCategory ":""}
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * THiUser	        String	否	Json字符串
 **/
@Data
public class DoCreatCardInfo {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private HiUser hiUser;
}
