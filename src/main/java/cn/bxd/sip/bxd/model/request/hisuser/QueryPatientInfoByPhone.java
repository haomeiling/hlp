package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-30
 * <p>
 * 接口说明	手机号码获取患者信息
 * 函数名：queryPatientInfoByPhone
 * 服务器地址URL：http://IP:端口/Client/HisUser?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","phone:"""}
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	同步信息
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * phone	        String	否	手机号码
 **/
@Data
public class QueryPatientInfoByPhone {

    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String phone;
}
