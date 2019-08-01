package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.entity.ExtUser;

import java.util.List;

/**
 * 外部账户服务接口
 *
 * @author HML
 * @Date 2016/2/24
 */
public interface IExtUserService {

    /**
     * 查询外部账户
     *
     * @param hospitalId    机构编码
     * @param openId        微信或者支付宝开放ID
     * @param extUserTypeId 外部账户类型：微信或者支付宝
     */
    ExtUser getExtUser(int hospitalId, String openId, short extUserTypeId);

    /**
     * 获取指定用户指定医院指定类型的外部账户信息
     *
     * @param userId     用户ID
     * @param hospitalId 医院ID
     * @param extTypeId  外部账户类型ID
     * @return 账户信息
     */
    ExtUser getExtUser(Integer userId, Integer hospitalId, Short extTypeId);

    /**
     * add---by---lkw  2016/5/31
     * 获取指定用户指定医院的外部账户信息
     *
     * @param userId     用户ID
     * @param hospitalId 医院ID
     * @return List<ExtUser>
     */
    List<ExtUser> getExtUserList(Integer userId, Integer hospitalId);
    
    /**
     * 添加一条记录
     * @Description: 
     * @date:   2018年10月25日 上午8:45:24
     */
    int insert(ExtUser record);
}
