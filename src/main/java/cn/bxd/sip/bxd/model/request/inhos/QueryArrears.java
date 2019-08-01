package cn.bxd.sip.bxd.model.request.inhos;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * 接口说明	住院押金补缴查询
 * 函数名：queryArrears
 * 服务器地址URL： http://IP:端口/Client/InHos?wsdl
 * 输入示例：
 * {"synUserName":"","synKey":"",…}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * inHosNo	            String	否	住院号
 * bedNo	            String	否	床号
 * departmentorganId	String	否	科室编码
 * extend	            String	否	扩展字段Json值，如： 			{“key1”：”value1”，”key2”：”value2”}
 **/
@Data
public class QueryArrears {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String inHosNo;
    private String bedNo;
    private String departmentorganId;
    private String extend;
}
