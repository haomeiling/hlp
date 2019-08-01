package cn.bxd.sip.bxd.model.entity;

/**
 * 机构外部账户信息表
 *
 * @author hml
 *         <p/>
 *         2016年2月23日
 */
public class ExtUserKey {
    private Integer hospitalId;//J机构代号

    private Short extUserTypeId;//外部账户类别代号

    private String openId;//外部账户ID

    
    
    public ExtUserKey() {
		super();
	}

	public ExtUserKey(Integer hospitalId, Short extUserTypeId, String openId) {
		super();
		this.hospitalId = hospitalId;
		this.extUserTypeId = extUserTypeId;
		this.openId = openId;
	}

	public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Short getExtUserTypeId() {
        return extUserTypeId;
    }

    public void setExtUserTypeId(Short extUserTypeId) {
        this.extUserTypeId = extUserTypeId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}