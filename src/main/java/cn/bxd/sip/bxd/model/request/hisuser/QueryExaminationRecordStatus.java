package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * author : cRyann
 * <p>
 * <p>
 * * @create 2018-08-30
 * 函数名：queryExaminationRecordStatus
 * 服务器地址URL：http://IP:端口/Client/HisUser?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","reportNos":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * reportNos	    String	否	报告编号 （注多个可以逗号分开如 112，2222）
 * extend	        String	否	扩展字段Json值，如：{“key1”：”value1”，”key2”：”value2”}
 **/
@Data
public class QueryExaminationRecordStatus {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String reportNos;
    private String extend;

}
