package cn.bxd.sip.bxd.model.request.pay;

import lombok.Data;

/**
 * author : cRyann
 *
 * @create 2018-09-01
 * <p>
 * criteria	    String	否	Json串
 * * criteria：
 * * patientNo	String	否	患者编号
 * * potype	    Int	否	1，医院产品定单，2，协医产品定单， 3，挂号定单  4,表示问诊定单  5,缴费订单  ,6住院'
 * * poNo	        String	否	订单号，为空时返回三天内缴费信息
 * * cardNo	    String	否	身份证（暂未开通）
 * * extend	    String	否	扩展字段Json值，如： * {“key1”：”value1” ，”key2”：”value2”}
 **/
@Data
public class Criteria {
    private String patientNo;
    private String potype;
    private String poNo;
    private String cardNo;
    private String extend;

}
