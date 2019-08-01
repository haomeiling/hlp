package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.User;
import cn.bxd.sip.bxd.service.IUserService;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.JudgeUserReqDates;
import cn.bxd.sip.his.model.dto.reservation.JudgeUserResDates;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 判断用户是否注册
 * @author Administrator
 *
 */
@Slf4j
@Component
public class JudgeUserOperation extends AbstractOperationProcessor {

	@Autowired
	private IUserService userservice;
	
    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
    	JudgeUserReqDates getQueueRegReqDates = JSON.parseObject(reqMsg, JudgeUserReqDates.class);
    	
    	if(StringUtils.isBlank(getQueueRegReqDates.getUserCode()) && StringUtils.isBlank(
    			getQueueRegReqDates.getLoginMobile())
    			&& StringUtils.isBlank(getQueueRegReqDates.getLoginEmail())) {
    		 return errMsgReturn("请求参数异常");
    	}
    	User user = userservice.selectByCodeMobileEmail(new User(getQueueRegReqDates.getUserCode(), 
    			getQueueRegReqDates.getLoginMobile(), getQueueRegReqDates.getLoginEmail()));
    	
    	JudgeUserResDates resData = new JudgeUserResDates(user != null ,HisConvertConst.Operation.
    			JUDGE_USER_IS_EXIST, HisConvertConst.Operation.OPERATION_SUCCESS
    			, getQueueRegReqDates.getHosId(), user == null ? null : String.valueOf(user.getUserId()));
        TypeUtils.compatibleWithFieldName = true;
        return JSON.toJSONString(resData);
    }
}