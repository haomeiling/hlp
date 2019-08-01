package cn.bxd.sip.bxd.model.request.common;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * 接口说明	自定义拓展接口（根据code值判断接口类型）
 * 函数名：commonInterface
 * 服务器地址URL：http://IP:端口/Client/ICommon?wsdl
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode 	String	否	终端编号
 * hospitalId	    String	否	医院ID
 * code	            String	否	接口名称(根据code值判断接口类型)
 * params       	String	否	Json值
 **/
@SuppressWarnings("ALL")
@Data
public class CommonInterface {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String code;
    private String params;
}
