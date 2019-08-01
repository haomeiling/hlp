package cn.bxd.sip.bxd.model.request.common;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * 接口说明	签到和签退
 * 函数名：signOrsignUp
 * 服务器地址URL：http://IP:端口/Client/ICommon?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","medicareType":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * medicareType	    Integer	否	医保类型
 * handleType	    Integer	否	1签到；2签退
 **/
@Data
public class SignOrsignUp {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private Integer medicareType;
    private Integer handleType;

}
