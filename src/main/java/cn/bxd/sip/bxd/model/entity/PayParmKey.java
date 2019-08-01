package cn.bxd.sip.bxd.model.entity;

public class PayParmKey {
    private Object appId;//应用程序代号

    private Short providerId;//支付渠道代号

    private Integer hospitalId;//机构代号
    
    

    public PayParmKey() {
	}

	public PayParmKey(Object appId, Short providerId, Integer hospitalId) {
		this.appId = appId;
		this.providerId = providerId;
		this.hospitalId = hospitalId;
	}

	public Object getAppId() {
        return appId;
    }

    public void setAppId(Object appId) {
        this.appId = appId;
    }

    public Short getProviderId() {
        return providerId;
    }

    public void setProviderId(Short providerId) {
        this.providerId = providerId;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }
}