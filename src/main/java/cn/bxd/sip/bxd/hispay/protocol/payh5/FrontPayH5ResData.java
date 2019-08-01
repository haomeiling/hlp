package cn.bxd.sip.bxd.hispay.protocol.payh5;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * H5支付请求返回
 * 
 * @date 20180227
 * @author haomeiling
 */
@XStreamAlias("BODY")
public class FrontPayH5ResData {
	private String OrderRefNo;// 订单参考号
	private Integer RequestDate;// 请求日期
	private String OrderNo = "";// 平台订单编号
	private String ProviderName;// 交易号
	private String PaymentStr;// 支付支付传

	public String getOrderRefNo() {
		return OrderRefNo;
	}

	public void setOrderRefNo(String orderRefNo) {
		OrderRefNo = orderRefNo;
	}

	public Integer getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(Integer requestDate) {
		RequestDate = requestDate;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getProviderName() {
		return ProviderName;
	}

	public void setProviderName(String providerName) {
		ProviderName = providerName;
	}

	public String getPaymentStr() {
		return PaymentStr;
	}

	public void setPaymentStr(String paymentStr) {
		PaymentStr = paymentStr;
	}

}
