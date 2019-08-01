package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.ExtUser;
import cn.bxd.sip.bxd.model.entity.User;
import cn.bxd.sip.bxd.service.IExtUserService;
import cn.bxd.sip.bxd.service.IUserService;
import cn.bxd.sip.bxd.util.TextUtils;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.LoginSIReqDates;
import cn.bxd.sip.his.model.dto.reservation.LoginSIResDates;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Date;


/**
 * 登陆社保系统
 *
 * @author Administrator
 */
@Slf4j
@Component
public class LoginSIOperation extends AbstractOperationProcessor {

    @Autowired
    private IUserService userservice;

    @Autowired
    private IExtUserService extUserService;

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
        LoginSIReqDates reqDatas = JSON.parseObject(reqMsg, LoginSIReqDates.class);
        if (StringUtils.isBlank(reqDatas.getUserCode()) || StringUtils.isBlank(
                reqDatas.getPassword()) || reqDatas.getExtUserTypeId() == null) {
            return errMsgReturn("请求参数异常");
        }
        //登陆社保系统
        String password;
        try {
            password = TextUtils.MD5Digest(reqDatas.getPassword());
        } catch (NoSuchAlgorithmException e) {
            log.info(e.getMessage());
            return ReservationVar.Result.RESULT_STR_FAIL;

        }
        User user = userservice.selectByCodeMobileEmail(new User(reqDatas.getUserCode(), password));
        if (user != null) {
            String openId = reqDatas.getOpenId() + "_" + user.getUserId();
            ExtUser extUser = extUserService.getExtUser(Integer.valueOf(reqDatas.getHosId()), openId, Short.valueOf(String.valueOf(reqDatas.getExtUserTypeId())));
            if (extUser == null) {
                int result = extUserService.insert(new ExtUser(Integer.parseInt(reqDatas.getHosId()),
                        Short.valueOf(String.valueOf(reqDatas.getExtUserTypeId())), openId, new Date(), user.getUserId(), new Date()));
                if (result <= 0) {
                    return ReservationVar.Result.RESULT_STR_FAIL;
                }
            }
        }
        LoginSIResDates resData = new LoginSIResDates(HisConvertConst.Operation.LOGIN, user == null ? HisConvertConst.Operation.OPERATION_FAIL :
                HisConvertConst.Operation.OPERATION_SUCCESS
                , reqDatas.getHosId(), user == null ? "" : String.valueOf(user.getUserId()),
                user == null ? "用户名或者密码不正确" : "");

        TypeUtils.compatibleWithFieldName = true;
        return JSON.toJSONString(resData);
    }


}