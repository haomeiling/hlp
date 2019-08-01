package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	获取挂号医保信息
 * 函数名：doRegMedicareInfo
 * 服务器地址URL：http://qkbtest.17lf.com:8080/services/Reg?wsdl
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	String	否	用户名
 * synKey	    String	否	效验码
 * terminalCode	String	否	终端编号
 * hospitalId	String	否	医院ID
 * patientNo	String	否	患者编号
 * doRegIn	    String	否	Json字符串
 **/
@Data
public class DoRegMedicareInfo {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String patientNo;
    private DoRegIn doRegIn;
};
