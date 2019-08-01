package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	状态查询
 * 函数名：queryPaymentRecordStatus
 * 服务器地址URL：http://IP:端口/Client/Pay?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","hiFeeNos":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * hiFeeNos	        String	否	缴费编号 * (注多个可以逗号分开如 112，2222)
 * extend	        String	否	扩展字段Json值，如： * {“key1”：”value1”，”key2”：”value2”}
 **/
@Data
public class QueryPaymentRecordStatus {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String hiFeeNos;
    private String extend;
}
