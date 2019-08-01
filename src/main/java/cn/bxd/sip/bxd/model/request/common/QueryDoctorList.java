package cn.bxd.sip.bxd.model.request.common;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * 接口说明	查询医生列表信息
 * 函数名：queryDoctorList
 * 服务器地址URL：http://IP:端口/Client/ICommon?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","departmentorganId":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * departmentId	    String	否	平台科室ID
 **/
@Data
public class QueryDoctorList {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String departmentId;
}
