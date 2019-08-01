package cn.bxd.sip.bxd.model.request.inhos;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 * <p>
 * 接口说明	住院清单明细
 * 函数名：inHosDetail
 * 服务器地址URL： http://IP:端口/Client/InHos?wsdl
 * 输入示例：
 * {"synUserName":"","synKey":"",…}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode 	String	否	终端编号
 * hospitalId	    String	否	医院ID
 * patientNo	    String	否	患者编号
 * inHosNo	        String	否	住院号
 * startDate	    String	否	开始时间    (yyyy-MM-dd）
 * endDate	        String	否	结束时间    (yyyy-MM-dd）
 **/
@Data
public class InHosDetail {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String patientNo;
    private String inHosNo;
    private String startDate;
    private String endDate;
}
