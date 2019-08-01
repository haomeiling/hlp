package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-08-30
 * <p>
 * 属性	             类型 可否为空    备注
 * synUserName	    String	否   	用户名
 * synKey	        String	否   	效验码
 * terminalCode	    String	否   	终端编号
 * hospitalId	    String	否   	医院ID
 * cardNo	        String	否   	身份证号码
 * socialsecurityNO	String	是   	社保卡号、医保号
 * medicareNO	    String	是   	就诊卡号
 * bankCardNo	    String	是   	银行卡号
 * phone	        String	否   	手机号码
 * THiUser	        String	否   	Json字符串
 * reportNos	    String	否   	报告编号（注多个可以逗号分开如 112，2222）
 * extend	        String	否   	扩展字段Json值，如：{“key1”：”value1”，”key2”：”value2”}
 * patientNo	    String	否   	患者编号
 * startDate	    String	否   	查询开始时间
 * endDate	        String	否   	查询结束时间
 * bankCardNumber	String	是   	银行卡号
 * visitCardNo	    String	是   	就诊卡号
 * mobileNo	        String	否   	手机号码
 * password	        String	否   	密码
 * patientName	    String	否   	患者姓名
 * cardId	        String	否   	身份证
 * userNo	        String	否   	人员编号
 * medicareType	    Integer	否   	0，非医保；1,市医保；
 * cardinfo	        String	否   	卡信息（如读卡交易入参）
 * hospitalNO		String	否       医院编号
 * operatorNo		String	否       操作人员编号
 * cycleNo			String  否       周期编号
 * dynamic			String  否       动态库参数（签到交易为：客户端MAC地址|用户数目|）
 **/
@Data
public class HisUserReq {
    private String synUserName;
    private String synKey;
    private String terminalCode;
    private String hospitalId;
    private String cardNo;
    private String socialsecurityNO;
    private String medicareNO;
    private String bankCardNo;
    private String phone;
    private String hiUser;
    private String reportNos;
    private String extend;
    private String patientNo;
    private String startDate;
    private String endDate;
    private String bankCardNumber;
    private String visitCardNo;
    private String mobileNo;
    private String password;
    private String patientName;
    private String cardId;
    private String userNo;
    private String medicareType;
    private String cardinfo;
    private String hospitalNO;
    private String operatorNo;
    private String cycleNo;
    private String dynamic;
}
