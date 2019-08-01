package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * 接口说明	号源解锁
 * 函数名：unlockRegisterNo
 * 服务器地址URL：http://IP:端口/Client/Reg?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"",hospitalId":"","phone":"","content":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	String	否	用户名
 * synKey	    String	否	效验码
 * terminalCode	String	否	终端编号
 * hospitalId	String	否	医院ID
 * patientNO	String	否	患者编号
 * proCode	    String	否	时间段中获取的排班唯一标识
 * cardNo	    String	否	身份证号
 * timestypeNo	String	否	时间段标识
 * sourceDate	String 	否	时间格式:yyyy-MM-dd
 **/
@Data
public class UnlockRegisterNo {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String patientNO;
    private String proCode;
    private String cardNo;
    private String timestypeNo;
    private String sourceDate;
}
