package cn.bxd.sip.bxd.model.request.common;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * 接口说明	查询部门列表信息
 * 函数名：queryfirstDepartmentList
 * 服务器地址URL：http://IP:端口/Client/ICommon?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 **/
@Data
public class QueryfirstDepartmentList {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
}
