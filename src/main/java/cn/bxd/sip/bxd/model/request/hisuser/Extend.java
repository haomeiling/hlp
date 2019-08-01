package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@Data
public class Extend {
    /**
     * 1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行，8 招行（可选）
     */
    String payType;
    /**
     * 支付流水号（可选）
     **/
    String payNo;
    /**
     * 已支金额（可选）
     **/
    String payAmount;
    /**
     * 密码（可选）
     **/
    String passWord;
    /**
     * 制卡类型(1新建 2补卡) （可选）
     **/
    String doType;

}
