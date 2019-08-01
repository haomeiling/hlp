package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.ExtUser;
import cn.bxd.sip.bxd.service.IExtUserService;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.JudgeThirdUserReqDates;
import cn.bxd.sip.his.model.dto.reservation.JudgeThirdUserResDates;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 判断是否是授权登陆，或者是首次登陆
 * @author Administrator
 *
 */
@Slf4j
@Component
public class JudgeThirdUserOperation extends AbstractOperationProcessor {

	@Autowired
	private IExtUserService extUserService;
	
    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
    	JudgeThirdUserReqDates reqDatas = JSON.parseObject(reqMsg, JudgeThirdUserReqDates.class);
    	if(StringUtils.isBlank(reqDatas.getOpenId()) || StringUtils.isBlank(
    			reqDatas.getExtUserTypeId()) || !Arrays.asList("1", "2").contains(reqDatas.getExtUserTypeId())) {
    		 return errMsgReturn("请求参数异常");
    	}
    	//查询是否是首次登陆
    	ExtUser extUser = extUserService.getExtUser(Integer.valueOf(reqDatas.getHosId()), reqDatas.getOpenId()
    			+ "_"+reqDatas.getUserId(), Short.parseShort(reqDatas.getExtUserTypeId()));
    	
    	JudgeThirdUserResDates resData = new JudgeThirdUserResDates(extUser != null, HisConvertConst.Operation.
    			JUDGE_THIRD_USER_IS_EXIST, HisConvertConst.Operation.OPERATION_SUCCESS
    			, reqDatas.getHosId());
        TypeUtils.compatibleWithFieldName = true;
        return JSON.toJSONString(resData);
    }
}