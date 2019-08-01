package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	查询未取号的预约挂号
 * 函数名：queryRegBypatientNo
 * 服务器地址URL：http://IP:端口/Client/Reg?wsdl
 * 入参示例：
 * 属性	类型	可否为空	备注
 * synUserName	String	否	用户名
 * synKey	    String	否	效验码
 * patientNo	String	否	终端编号
 **/
@Data
public class QueryRegBypatientNo {
    private String synUserName;
    private String synKey;
    private String patientNo;
}
