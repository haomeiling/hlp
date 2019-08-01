package cn.bxd.sip.bxd.model.respond.inhos;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:     1.6.6	押金明细查询
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 14:31
 * <p>
 * inHosSerialNo	String	否	住院流水号
 * payMoney	Float	否	预缴金金额（保留小数点后两位）
 * invoiceNo	String	否	发票号
 * payType	Integer	否	1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
 * isSettle	Integer	否	0否，1是
 * operatorNo	String	否	操作员
 * payDate	String	否	缴费时（yyyy-MM-dd HH:mm:ss）
 */
@Data
public class Pay {

    private String inHosSerialNo;//住院流水号
    private String payMoney;//预缴金金额（保留小数点后两位）
    private String invoiceNo;//发票号
    private String payType;//1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
    private String isSettle;//0否，1是
    private String operatorNo;//操作员
    private String payDate;//缴费时（yyyy-MM-dd HH:mm:ss）

}
