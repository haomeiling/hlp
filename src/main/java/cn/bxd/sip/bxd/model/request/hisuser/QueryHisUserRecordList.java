package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-30
 * <p>
 * 接口说明	病历记录查询
 * 函数名：queryHisUserRecordList
 * 服务器地址URL：http://IP:端口/Client/HisUser?wsdl
 * 入参示例：
 * {"synUserName":"","synKey":"","hospitalId":"","terminalCode":"","patientNo":"","startDate":"","endDate":""}
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	    String	否	用户名
 * synKey	        String	否	效验码
 * terminalCode	    String	否	终端编号
 * hospitalId	    String	否	医院ID
 * patientNo	    String	否	患者编号
 * startDate	    String	否	查询开始时间
 * endDate	        String	否	查询结束时间
 **/
@Data
public class QueryHisUserRecordList {

    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String patientNo;
    private String startDate;
    private String endDate;

}
