package cn.bxd.sip.bxd.service;


import cn.bxd.sip.bxd.model.entity.User;

/**
 * @Author hml
 * @Description: 用户服务类
 * @Date 2015/7/27
 */
public interface IUserService {

    /**
     * 登录验证
     *
     * @param user 支持userCode、电话号码、电子邮件登录。不同方式登录，需要给对应的字段赋值
     * @return SUCCESS:登录成功，登录成功后直接跳转到首页
     * FAIL:登录失败，用户名或者密码错误，请重新输入
     * EXCEPTION:系统异常
     */
    String login(User user);

    /**
     * 修改用户名和密码
     * 支持指定用户ID的用户名和密码修改
     *
     * @param user 指定用户ID
     * @return SUCCESS:设置成功
     * FAIL:设置失败
     * EXCEPTION:异常
     */
    String changeUserByUserId(User user);

    /**
     * 注册用户
     * 支持用户名userCode,邮箱email，手机号telephone三种账户的注册处理
     * 不同方式，需要给对应的字段赋值
     *
     * @return SUCCESS:注册成功，注册成功后直接跳转到首页
     * EXCEPTION:系统异常
     * CODE_ERROR:验证码错误。
     */
    String register(User user);

    /**
     * 判断用户是否可用
     * 支持用户名userCode,邮箱email，手机号telephone三种账户的检测处理
     * 不同方式，需要给对应的字段赋值
     *
     * @param user 用户对象
     * @return NONE ：用户名不存在
     * EXIST：用户名存在
     */
    String checkUserIsExist(User user);

    /**
     * 修改密码
     * 支持用户名userCode,邮箱email，手机号telephone三种账户来修改密码
     * 不同方式，需要给对应的字段赋值
     *
     * @param user 用户对象
     * @return SUCCESS:设置成功
     * FAIL:设置失败
     * EXCEPTION:异常
     */
    String changePassword(User user);


	/**
	 * 根据账号获取对象,该接口仅用于验证接口唯一性，不要作修改
	 *
	 * @param user
	 * @return
	 */
	User selectByCodeMobileEmail(User user);

}
