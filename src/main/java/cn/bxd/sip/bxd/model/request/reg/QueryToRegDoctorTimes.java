package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	查询医生号源时间段
 * 函数名：queryToRegDoctorTimes
 * 服务器地址URL：http://IP:端口/Client/Reg?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"",hospitalId":"","sourceDate":"","sourceTimeType":"""organdoctorId":"","departmentorganId":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * sourceDate	        String	否	号源日期
 * sourceTimeType	    String	否	1.上午\2.下午\3.晚上
 * organdoctorId	    String	否	医生编号
 * departmentorganId	String	否	科室编号
 **/
@Data
public class QueryToRegDoctorTimes {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String sourceDate;
    private String sourceTimeType;
    private String organdoctorId;
    private String departmentorganId;
}
