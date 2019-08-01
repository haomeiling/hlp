package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	短信接口
 * 函数名：sendMessage
 * 服务器地址URL：http://IP:端口/Client/Reg?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"",hospitalId":"","phone":"","content":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	String	否	用户名
 * synKey	    String	否	效验码
 * terminalCode	String	否	终端编号
 * hospitalId	String	否	医院ID
 * phone	    String	否	接收手机号码
 * content	    String	否	消息内容
 **/
@Data
public class SendMessage {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String phone;
    private String content;
}
