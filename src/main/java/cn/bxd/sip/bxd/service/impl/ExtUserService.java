package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.ExtUserMapper;
import cn.bxd.sip.bxd.model.entity.ExtUser;
import cn.bxd.sip.bxd.model.entity.ExtUserKey;
import cn.bxd.sip.bxd.service.IExtUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 外部账户实现类
 *
 * @author HML
 * @Date 2016/2/24
 */
@Service
public class ExtUserService implements IExtUserService {
    private Logger log = Logger.getLogger(ExtUserService.class.getName());
    @Resource
    private ExtUserMapper extUserDao;

    /**
     * 查询外部账户
     *
     * @param hospitalId    机构编码
     * @param openId        微信或者支付宝开放ID
     * @param extUserTypeId 外部账户类型：微信或者支付宝
     */
    @Override
    public ExtUser getExtUser(int hospitalId, String openId, short extUserTypeId) {
        ExtUserKey extUserKey = new ExtUserKey();
        extUserKey.setHospitalId(hospitalId);//机构编码
        extUserKey.setExtUserTypeId(extUserTypeId);//外部用户
        extUserKey.setOpenId(openId);//外部账户ID
        return extUserDao.selectByPrimaryKey(extUserKey);//查询
    }

    /**
     * 获取指定用户指定医院指定类型的外部账户信息
     *
     * @param userId     用户ID
     * @param hospitalId 医院ID
     * @param extTypeId  外部账户类型ID
     * @return 账户信息
     */
    @Override
    public ExtUser getExtUser(Integer userId, Integer hospitalId, Short extTypeId) {
        ExtUser extUser = new ExtUser();
        extUser.setUserId(userId);
        extUser.setHospitalId(hospitalId);
//        extUser.setExtUserTypeId(ReservationVar.ExtUserType.WECHAT);
        //edit---by---lkw 2016/5/27
        extUser.setExtUserTypeId(extTypeId);
        List<ExtUser> extUserList = extUserDao.getExtUserListByCondition(extUser);
        if (extUserList == null || extUserList.size() == 0) return null;

        return extUserList.get(0);
    }

    /**
     * add---by---lkw  2016/5/31
     * 获取指定用户指定医院的外部账户信息
     *
     * @param userId     用户ID
     * @param hospitalId 医院ID
     * @return List<ExtUser>
     */
    @Override
    public List<ExtUser> getExtUserList(Integer userId, Integer hospitalId) {
        ExtUser extUser = new ExtUser();
        extUser.setUserId(userId);
        extUser.setHospitalId(hospitalId);
        return extUserDao.getExtUserListByCondition(extUser);
    }

    /**
     * 添加纪录
     */
	@Override
	public int insert(ExtUser record) {
		return extUserDao.insert(record);
	}


}
