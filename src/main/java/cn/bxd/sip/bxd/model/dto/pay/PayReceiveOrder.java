package cn.bxd.sip.bxd.model.dto.pay;

import cn.bxd.sip.bxd.model.entity.PayParm;

/**
 * Description:
 * Package: com.efcloud.reservation.model.pay
 *
 * @author Leeves
 * @version 1.0.0  2018-07-28
 */
public class PayReceiveOrder extends PayParm {

    public PayReceiveOrder(Object appId, Short providerId, Integer hospitalId) {
		super(appId, providerId, hospitalId);
		// TODO Auto-generated constructor stub
	}
    
	public PayReceiveOrder() {
		
	}



	private Long payPattern;

    @Override
    public String toString() {
        return "PayReceiveOrder{" +
                "payPattern='" + payPattern + '\'' +
                "} " + super.toString();
    }

    public Long getPayPattern() {
        return payPattern;
    }

    public void setPayPattern(Long payPattern) {
        this.payPattern = payPattern;
    }
}