package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.UserMapper;
import cn.bxd.sip.bxd.model.entity.User;
import cn.bxd.sip.bxd.service.ISeqService;
import cn.bxd.sip.bxd.service.IUserService;
import cn.bxd.sip.bxd.util.TextUtils;
import cn.bxd.sip.bxd.var.ReservationVar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @Author hml
 * @Description: 用户业务服务接口
 * @Date 2015/7/27
 */
@Service
@Slf4j
public class UserService implements IUserService {
    @Resource
    private UserMapper userDao;
    @Resource
    private ISeqService proceduresSeq;

    @Override
    public String login(User user) {
        try {
            String originPassword = user.getUserPasswd();//保留原始密码，以便cookie保存
            String passwordMD5 = TextUtils.MD5Digest(originPassword);
            user.setUserPasswd(passwordMD5);
            User userData = userDao.selectByCodeMobileEmail(user);//搜索到的结果用户
            if (userData == null) return ReservationVar.Result.RESULT_STR_FAIL;//用户不存在，用户或者密码错误
            return ReservationVar.Result.RESULT_STR_SUCCESS;

        } catch (Exception e) {
            //系统错误
            return ReservationVar.Result.RESULT_STR_EXCEPTION;
        }
    }


    @Override
    public String changeUserByUserId(User user) {
        try {
            String password2 = TextUtils.MD5Digest(user.getUserPasswd());
            User user2 = userDao.selectByPrimaryKey(user.getUserId());
            user2.setUserPasswd(password2);
            user2.setUserCode(user.getUserCode());

            userDao.updateByPrimaryKey(user2);

            return ReservationVar.Result.RESULT_STR_SUCCESS;
        } catch (Exception e) {
            //系统错误
            return ReservationVar.Result.RESULT_STR_FAIL;
        }
    }


    @Override
    public String register(User user) {
        String result = checkUserIsExist(user);  //再次确认用户名是否存在，如果已经存在，则不让保存
        if (result.equals(ReservationVar.Result.RESULT_STR_EXIST)) {
            return ReservationVar.Result.RESULT_STR_EXIST;
        }

        try {
            String password = TextUtils.MD5Digest(user.getUserPasswd());
            user.setUserPasswd(password);
            user.setIsExternalUser(ReservationVar.Is.FALSE);
            user.setUserTypeId(user.getUserTypeId());
            user.setCreatedTime(new Date());

            Integer userId = proceduresSeq.getUserId();
            user.setUserId(userId);
            userDao.insert(user);

            return ReservationVar.Result.RESULT_STR_SUCCESS;
        } catch (NoSuchAlgorithmException e) {
            return ReservationVar.Result.RESULT_STR_EXCEPTION;
        }
    }

    @Override
    public String checkUserIsExist(User user) {
        String pwd = user.getUserPasswd();//检查用户不存在不需要密码
        user.setUserPasswd(null);
        User userData = userDao.selectByCodeMobileEmail(user);
        user.setUserPasswd(pwd);//还原密码
        return userData == null ? ReservationVar.Result.RESULT_STR_NONE : ReservationVar.Result.RESULT_STR_EXIST;
    }

    @Override
    public String changePassword(User user) {
        try {
            String passwordMD5 = TextUtils.MD5Digest(user.getUserPasswd());
            user.setUserPasswd(passwordMD5);

            userDao.updateByCodeEmailTelephone(user);

            return ReservationVar.Result.RESULT_STR_SUCCESS;

        } catch (Exception e) {
            return ReservationVar.Result.RESULT_STR_FAIL;
        }
    }


    @Override
    public User selectByCodeMobileEmail(User user) {
        return userDao.selectByCodeMobileEmail(user);
    }


}
