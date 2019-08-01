package cn.bxd.sip.bxd.model.entity;

import java.util.Date;

/**
 * 机构外部账户信息表
 *
 * @author hml
 *         <p/>
 *         2016年2月23日
 */
public class ExtUser extends ExtUserKey {
    private Date lastLoginTime;//最近登录时间

    private Integer userId;//平台账户ID

    private Date boundedTime;//平台账户绑定时间

    
    public ExtUser() {
		super();
	}
    
	public ExtUser(Integer hospitalId, Short extUserTypeId, String openId, Date lastLoginTime, Integer userId, Date boundedTime) {
		super(hospitalId, extUserTypeId, openId);
		this.lastLoginTime = lastLoginTime;
		this.userId = userId;
		this.boundedTime = boundedTime;
	}

	public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getBoundedTime() {
        return boundedTime;
    }

    public void setBoundedTime(Date boundedTime) {
        this.boundedTime = boundedTime;
    }
}