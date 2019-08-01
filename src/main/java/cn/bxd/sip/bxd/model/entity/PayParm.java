package cn.bxd.sip.bxd.model.entity;

import java.util.Date;

public class PayParm extends PayParmKey {
    private String providerAppid;//渠道应用标识

    private String providerAppsecret;//渠道应用密码

    private String providerAppkey;//渠道应用秘钥

    private Date createdTime;//开通时间

    private String notes;//说明备注

    private String privateKeyUri;//私钥文件

    private String privateKeyAccesspwd;//私钥访问密码

    private String publicKeyUri;//公钥文件

    private String publicKeyAccesspwd;//公钥访问密码

    private String mchId;//商户号ID

    private String providerPayAccount;//支付账户
    private String providerPaySecret;//支付密码
    private String providerPayKey;//支付秘钥
    private String providerPayCode;//支付编码
    private String providerPaySourceCode;//支付来源

    //===================以下非数据库字段==================================
    private String subMchId;//子商户ID
    private String subProviderAppId;//子应用ID
    private String sessionKey;
    private String token;
    
    
    
    public PayParm() {
	}

	public PayParm(Object appId, Short providerId, Integer hospitalId) {
    	super(appId, providerId, hospitalId);
    }

    public String getProviderAppid() {
        return providerAppid;
    }

    public void setProviderAppid(String providerAppid) {
        this.providerAppid = providerAppid;
    }

    public String getProviderAppsecret() {
        return providerAppsecret;
    }

    public void setProviderAppsecret(String providerAppsecret) {
        this.providerAppsecret = providerAppsecret;
    }

    public String getProviderAppkey() {
        return providerAppkey;
    }

    public void setProviderAppkey(String providerAppkey) {
        this.providerAppkey = providerAppkey;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrivateKeyUri() {
        return privateKeyUri;
    }

    public void setPrivateKeyUri(String privateKeyUri) {
        this.privateKeyUri = privateKeyUri;
    }

    public String getPrivateKeyAccesspwd() {
        return privateKeyAccesspwd;
    }

    public void setPrivateKeyAccesspwd(String privateKeyAccesspwd) {
        this.privateKeyAccesspwd = privateKeyAccesspwd;
    }

    public String getPublicKeyUri() {
        return publicKeyUri;
    }

    public void setPublicKeyUri(String publicKeyUri) {
        this.publicKeyUri = publicKeyUri;
    }

    public String getPublicKeyAccesspwd() {
        return publicKeyAccesspwd;
    }

    public void setPublicKeyAccesspwd(String publicKeyAccesspwd) {
        this.publicKeyAccesspwd = publicKeyAccesspwd;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getSubProviderAppId() {
        return subProviderAppId;
    }

    public void setSubProviderAppId(String subProviderAppId) {
        this.subProviderAppId = subProviderAppId;
    }

    public String getProviderPayAccount() {
        return providerPayAccount;
    }

    public void setProviderPayAccount(String providerPayAccount) {
        this.providerPayAccount = providerPayAccount;
    }

    public String getProviderPaySecret() {
        return providerPaySecret;
    }

    public void setProviderPaySecret(String providerPaySecret) {
        this.providerPaySecret = providerPaySecret;
    }

    public String getProviderPayKey() {
        return providerPayKey;
    }

    public void setProviderPayKey(String providerPayKey) {
        this.providerPayKey = providerPayKey;
    }

    public String getProviderPayCode() {
        return providerPayCode;
    }

    public void setProviderPayCode(String providerPayCode) {
        this.providerPayCode = providerPayCode;
    }

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProviderPaySourceCode() {
		return providerPaySourceCode;
	}

	public void setProviderPaySourceCode(String providerPaySourceCode) {
		this.providerPaySourceCode = providerPaySourceCode;
	}
    
	
    
}