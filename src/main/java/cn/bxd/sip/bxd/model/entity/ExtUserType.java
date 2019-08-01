package cn.bxd.sip.bxd.model.entity;

/**
 * 外部账户类型代码表
 *
 * @author hml
 *         <p/>
 *         2016年2月23日
 */
public class ExtUserType {
    private Short extUserTypeId;//外部账户类别代号

    private Object extUserTypeCode;//外部账户类别代码

    private Object extUserTypeName;//外部账户列别名称

    private Short displayOrder;//显示顺序

    private Object authenticationUrl;//用户认证URL

    public Short getExtUserTypeId() {
        return extUserTypeId;
    }

    public void setExtUserTypeId(Short extUserTypeId) {
        this.extUserTypeId = extUserTypeId;
    }

    public Object getExtUserTypeCode() {
        return extUserTypeCode;
    }

    public void setExtUserTypeCode(Object extUserTypeCode) {
        this.extUserTypeCode = extUserTypeCode;
    }

    public Object getExtUserTypeName() {
        return extUserTypeName;
    }

    public void setExtUserTypeName(Object extUserTypeName) {
        this.extUserTypeName = extUserTypeName;
    }

    public Short getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Short displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Object getAuthenticationUrl() {
        return authenticationUrl;
    }

    public void setAuthenticationUrl(Object authenticationUrl) {
        this.authenticationUrl = authenticationUrl;
    }
}