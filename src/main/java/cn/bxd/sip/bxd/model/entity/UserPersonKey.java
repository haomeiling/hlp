package cn.bxd.sip.bxd.model.entity;

/**
 * Description：平台账户人员信息表主键
 *
 * @author liangshangsong
 *         <p/>
 *         2015年7月27日 上午11:50:30
 */
public class UserPersonKey {
    private Integer userId;//账户ID

    private Short personNo;//人员序号

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getPersonNo() {
        return personNo;
    }

    public void setPersonNo(Short personNo) {
        this.personNo = personNo;
    }

    public UserPersonKey(Integer userId, Short personNo) {
        // TODO Auto-generated constructor stub
        this.userId = userId;
        this.personNo = personNo;
    }

    public UserPersonKey() {
        // TODO Auto-generated constructor stub
    }
}