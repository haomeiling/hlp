package cn.bxd.sip.bxd.model.entity;

/**
 * Description：平台账户类型表
 *
 * @author liangshangsong
 *         <p/>
 *         2015年8月13日 下午2:45:51
 */
public class UserType {
    private Short userTypeId;//平台账户类型代号

    private Object userTypeCode;//平台账户类型代码

    private Object userTypeName;//平台账户类型名称

    private Short displayOrder;//显示顺序

    private Short roleId;//角色ID

    public Short getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Short userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Object getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(Object userTypeCode) {
        this.userTypeCode = userTypeCode;
    }

    public Object getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(Object userTypeName) {
        this.userTypeName = userTypeName;
    }

    public Short getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Short displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Short getRoleId() {
        return roleId;
    }

    public void setRoleId(Short roleId) {
        this.roleId = roleId;
    }
}