package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.RealUser;
import cn.bxd.sip.bxd.model.entity.SiRealUser;
import cn.bxd.sip.bxd.service.IRealUserService;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.GetSIInfoReqDatas;
import cn.bxd.sip.his.model.dto.reservation.GetSIInfoResDatas;
import cn.bxd.sip.his.utils.DateUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 查询社保信息 4000
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-21
 */
@Component
public class GetSIInfoOperation extends AbstractOperationProcessor {

    @Autowired
    private IRealUserService mRealUserMapper;


    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm connectParm) {

        //入参：掌上医院请求入参
        GetSIInfoReqDatas getSIInfoReqDatas = JSON.parseObject(reqMsg, GetSIInfoReqDatas.class);
        //查询数据库
        SiRealUser realUser = mRealUserMapper.findRealUserByUserId(getSIInfoReqDatas.getUserId());

        GetSIInfoResDatas getSIInfoResDatas = new GetSIInfoResDatas();
        getSIInfoResDatas.setOperCode(HisConvertConst.Operation.GET_SI_INFO_REQ_CODE);
        getSIInfoResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getSIInfoResDatas.setHosId(getSIInfoReqDatas.getHosId());
        TypeUtils.compatibleWithFieldName = true;
        if (realUser == null) {
        	return JSON.toJSONString(getSIInfoResDatas);
        }
        getSIInfoResDatas.setUserNo((String)realUser.getUserNo());
        getSIInfoResDatas.setName((String)realUser.getPatientName());
        getSIInfoResDatas.setCardInfo((String)realUser.getCardinfo());
        getSIInfoResDatas.setArea((String)realUser.getOverallArea());
        getSIInfoResDatas.setIDNumber((String)realUser.getCardId());
        getSIInfoResDatas.setMobileNo((String)realUser.getMobileNo());
        getSIInfoResDatas.setSocialNo((String)realUser.getVisitcardNum());
        getSIInfoResDatas.setRealTime(DateUtils.dateFormatter((realUser.getRealTime())));
        return JSON.toJSONString(getSIInfoResDatas);
    }

}