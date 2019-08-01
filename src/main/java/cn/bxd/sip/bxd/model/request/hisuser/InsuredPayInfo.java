package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-30
 * <p>
 * 接口说明	基本医疗保险参保缴费情况
 * 函数名：insuredPayInfo
 * 服务器地址URL：http://IP:端口/Client/HisUser?wsdl
 * 入参示例：
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode 	String	否	终端编号
 * hospitalId	    String	否	医院ID
 * cardNo	        String	否	身份证号码
 * socialsecurityNO	String	是	社保号
 **/
@Data
public class InsuredPayInfo {

    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String cardNo;
    private String socialsecurityNO;

}
