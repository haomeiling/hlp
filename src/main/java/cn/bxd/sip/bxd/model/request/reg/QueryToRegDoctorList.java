package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	查询号源
 * 函数名：  queryToRegDoctorList
 * 服务器地址URL：http://IP:端口/Client/Reg?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","startDate":"","endDate":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * startDate	    String	否	起始时间
 * endDate	        String	否	结束时间
 **/
@Data
public class QueryToRegDoctorList {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String startDate;
    private String endDate;

}
