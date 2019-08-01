package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	预约挂号
 * 函数名：  doReg
 * 服务器地址URL：http://IP:端口/Client/Reg?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"",hospitalId":"","hospitalId":"","doRegIn"}
 * 输入格式：
 * "doRegIn":{"sourceId":"","doctorId":"","organdoctorId":"","departmentorganId":"","cardNo":"","patientNo":"","socialsecurityNO":"","sourceDate":"","timestypeNo":"","sourceTimeType":"","payType":"","payNo":"","payAmount":""}
 * 属性	类型	可否为空	备注
 * synUserName	String	否	用户名
 * synKey	    String	否	效验码
 * terminalCode	String	否	终端编号
 * hospitalId	String	否	医院ID
 * doRegIn	    Object	否	Json字符串
 **/
@Data
public class DoReg {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private DoRegIn doRegIn;
}
