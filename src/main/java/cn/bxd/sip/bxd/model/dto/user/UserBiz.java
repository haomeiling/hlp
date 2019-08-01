package cn.bxd.sip.bxd.model.dto.user;


import cn.bxd.sip.bxd.model.entity.User;
import cn.bxd.sip.bxd.model.entity.UserType;

/**
 * Description：平台账户信息表扩展类
 *
 * @author liangshangsong
 *         <p/>
 *         2015年8月13日 下午3:12:43
 */
public class UserBiz extends User {

    private UserType userType;//账户类型

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
