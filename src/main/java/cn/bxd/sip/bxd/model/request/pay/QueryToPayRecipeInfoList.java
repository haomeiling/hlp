package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	待交费列表
 * 函数名：queryToPayRecipeInfoList
 * 服务器地址URL：http://IP:端口/Client/Pay?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","terminalCode":"","hospitalId":"","patientNo":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * patientNo	        String	否	患者编号
 * medicareType	        Integer	否	0，非医保；1,市医保；
 * medicareMess	        String	否	医保个人基本信息（医保读卡交易返回值。根据 medicareType判断返回值格式）
 * extend	            String	否	扩展字段Json值，如： {“key1”：”value1”，”key2”：”value2”}
 **/
@Data
public class QueryToPayRecipeInfoList {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String patientNo;
    private String medicareType;
    private String medicareMess;
    private String extend;
}
