package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.dto.user.UserBiz;
import cn.bxd.sip.bxd.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description：平台账户信息Mapper
 *
 * @author liangshangsong
 *         <p/>
 *         2015年8月13日 下午3:18:19
 */
@Mapper
public interface UserMapper {


    /**
     * 根据账户号、等获取信息
     *
     * @param user
     * @return
     */
    UserBiz selectByConditions(UserBiz user);
    
 /*   *//**
     * 根据账号获取对象,该接口仅用于验证接口唯一性，不要作修改
     * @param userCode
     * @return
     *//*
    UserBiz selectByUserCode(String userCode);
*/

    /**
     * 根据账号获取对象,该接口仅用于验证接口唯一性，不要作修改
     *
     * @param user
     * @return
     */
    User selectByCodeMobileEmail(User user);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 根据主键获取
     *
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 修改密码
     * 支持用户名userCode,邮箱email，手机号telephone三种条件来修改密码
     * 不同方式，需要给对应的字段赋值
     *
     * @param record 用户对象
     */
    void updateByCodeEmailTelephone(User record);
}