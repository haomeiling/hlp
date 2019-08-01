package cn.bxd.sip.bxd.model.request.reg;

import lombok.Data;

import java.util.Date;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * 属性	类型	可否为空	备注
 * sourceId	            String		号源编号
 * doctorId	            String	是	平台医生ID
 * organdoctorId	    String	否	医生编号
 * departmentorganId	String	否	科室编号
 * cardNo	            String	否	患者身份证号
 * patientNo	        String	否	患者编号
 * socialsecurityNO	    String	是	社保号
 * sourceDate	        Date	否	取号日期（号源日期）
 * timestypeNo	        int	否	时间段标识 0表示无时间段
 * sourceTimeType	    int	否	1,上午，2，下午 3，晚上
 * timestypeNoName	    String		时间段显示名称
 * payType	            int	否	1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
 * payNo	            String	是	支付流水号
 * payAmount	        String	是	已支金额
 * extend	            String	否	扩展字段Json值，如： * {“key1”：”value1” ，”key2”：”value2”} * 支付宝微信医保支付这里要传订单号（poNo）
 **/
@Data
public class DoRegIn {
    private String sourceId;
    private String doctorId;
    private String organdoctorId;
    private String departmentorganId;
    private String cardNo;
    private String patientNo;
    private String socialsecurityNO;
    private Date sourceDate;
    private Integer timestypeNo;
    private Integer sourceTimeType;
    private String timestypeNoName;
    private Integer payType;
    private String payNo;
    private String payAmount;
    private String extend;
}
