package cn.bxd.sip.bxd.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：平台账户信息表
 *
 * @author liangshangsong
 *         <p/>
 *         2015年8月13日 下午2:47:11
 */
public class User implements Serializable {
    private Integer userId;//账户ID

    private String userCode;//账户号

    private Short isExternalUser;//是否外部账户

    private String userPasswd;//账户密码

    private Date createdTime;//创建时间

    private Date lastLogonTime;//最近登录时间

    private String userName;//账户名称

    private Short providerId;//支付渠道代号

    private String contactPhone;//联系电话

    private Short certTypeId;//身份证件类型代号

    private Short isCertificated;//是否实名认证

    private String userRealname;//真实姓名

    private String certIdno;//身份证件号码

    private Date certificatedTime;//实名认证时间

    private Short userTypeId;//平台账户类型代号

    private String openId;//第三方平台ID

    private String loginMobile;//以电话登录

    private String loginEmail;//以邮箱登录

    private Short mobileVerified;//手机号码验证标志位

    private Date mobileVerifiedTime;//手机验证时间

    private Short emailVerified;//邮箱验证标志位

    private Date emailVerifiedTime;//邮箱号码验证时间

    private Date firstAppLoginTime;//首次APP登录时间

    private Date lastAppLoginTime;//最近APP登录时间

	private Date verifyCodeExpired;//验证码生成时间

    private String verifyCode;//验证码

    private Integer appLoginCount;//APP登录次数

    public User() {
    	
    }
    
    public User(String userCode, String loginMobile, String loginEmail) {
		super();
		this.userCode = userCode;
		this.loginMobile = loginMobile;
		this.loginEmail = loginEmail;
	}
    
    public User(String userCode, String userPasswd) {
		super();
		this.userCode = userCode;
		this.userPasswd = userPasswd;
	}
    
    

	public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLoginMobile() {
        return loginMobile;
    }

    public void setLoginMobile(String loginMobile) {
        this.loginMobile = loginMobile;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Short getIsExternalUser() {
        return isExternalUser;
    }

    public void setIsExternalUser(Short isExternalUser) {
        this.isExternalUser = isExternalUser;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastLogonTime() {
        return lastLogonTime;
    }

    public void setLastLogonTime(Date lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Short getProviderId() {
        return providerId;
    }

    public void setProviderId(Short providerId) {
        this.providerId = providerId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Short getCertTypeId() {
        return certTypeId;
    }

    public void setCertTypeId(Short certTypeId) {
        this.certTypeId = certTypeId;
    }

    public Short getIsCertificated() {
        return isCertificated;
    }

    public void setIsCertificated(Short isCertificated) {
        this.isCertificated = isCertificated;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    public String getCertIdno() {
        return certIdno;
    }

    public void setCertIdno(String certIdno) {
        this.certIdno = certIdno;
    }

    public Date getCertificatedTime() {
        return certificatedTime;
    }

    public void setCertificatedTime(Date certificatedTime) {
        this.certificatedTime = certificatedTime;
    }

    public Short getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Short userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Short getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Short mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public Date getEmailVerifiedTime() {
        return emailVerifiedTime;
    }

    public void setEmailVerifiedTime(Date emailVerifiedTime) {
        this.emailVerifiedTime = emailVerifiedTime;
    }

    public Date getFirstAppLoginTime() {
        return firstAppLoginTime;
    }

    public void setFirstAppLoginTime(Date firstAppLoginTime) {
        this.firstAppLoginTime = firstAppLoginTime;
    }

    public Date getLastAppLoginTime() {
        return lastAppLoginTime;
    }

    public void setLastAppLoginTime(Date lastAppLoginTime) {
        this.lastAppLoginTime = lastAppLoginTime;
    }

    public Date getMobileVerifiedTime() {
        return mobileVerifiedTime;
    }

    public void setMobileVerifiedTime(Date mobileVerifiedTime) {
        this.mobileVerifiedTime = mobileVerifiedTime;
    }

    public Short getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Short emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Date getVerifyCodeExpired() {
        return verifyCodeExpired;
    }

    public void setVerifyCodeExpired(Date verifyCodeExpired) {
        this.verifyCodeExpired = verifyCodeExpired;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getAppLoginCount() {
        return appLoginCount;
    }

    public void setAppLoginCount(Integer appLoginCount) {
        this.appLoginCount = appLoginCount;
    }
}