package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

import java.util.Date;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 接口说明	查询挂号费（号源挂号费不使用时使用）
 * 函数名： doregFeeInterface
 * 服务器地址URL：http://qkbtest.17lf.com:8080/services/Reg?wsdl
 * 输入参数：
 * 属性	类型	可否为空	备注
 * synUserName	        String	否	用户名
 * synKey	            String	否	效验码
 * terminalCode	        String	否	终端编号
 * hospitalId	        String	否	医院ID
 * organdoctorId	    String	否	医生编号
 * departmentorganId	String	否	科室编号
 * patientNo	        Date	否	患者编号
 * sourceDate	        Date	否	取号日期（号源日期）
 * timestypeNo	        String	否	时间段标识 0表示无时间段
 * sourceTimeType	    String	否	1,上午，2，下午 3，晚上
 **/
@Data
public class DoregFeeInterface {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String organdoctorId;
    private String departmentorganId;
    private Date patientNo;
    private Date sourceDate;
    private String timestypeNo;
    private String sourceTimeType;
}
