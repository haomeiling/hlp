package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.reservation.DoregFeeInterfaceReqDatas;
import cn.bxd.sip.his.model.dto.reservation.DoregFeeInterfaceRes;
import cn.bxd.sip.his.model.dto.reservation.DoregFeeInterfaceResDatas;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Description: 查询挂号费 2030
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Lisheng
 * @version 1.0.0  2019-02-14
 */
@Slf4j
@Component
public class DoregFeeInterfaceOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        DoregFeeInterfaceReqDatas doregFeeInterfaceReqDatas = JSON.parseObject(reqMsg, DoregFeeInterfaceReqDatas.class);

        //分割：入参中的 queueId|deptId
        String[] split = StringUtils.split(doregFeeInterfaceReqDatas.getQueueId(), "|");
        if (split.length != 2) {
            log.info(" 2030 queueId有误：" + doregFeeInterfaceReqDatas.getQueueId());
            return errMsgReturn("queueId有误");
        }
        String queueId = split[0];
        String deptId = split[1];

        //封装：向HIS发送请求
        String doregFeeInterfaceStr = "";
        try {
            //ws客户端：1.5 取号
            Object hosWsClient4 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();
            String doregin = JSON.toJSONString(doregFeeInterfaceReqDatas);
            log.debug("---his doTaketheNo入参 ---:" + doregin);
            doregFeeInterfaceStr = HisWSClient.invoke(hosWsClient4, HisFunNameConst.DOREG_FEE_INTERFACE, sysUserName, sysKey,
                    queueId,deptId,doregFeeInterfaceReqDatas.getPatientNo(),
                    doregFeeInterfaceReqDatas.getSourceDate(),doregFeeInterfaceReqDatas.getTimestypeNo(),doregFeeInterfaceReqDatas.getSourceTimeType(),
                    doregFeeInterfaceReqDatas.getRegType());

            log.debug(" 2030 查询挂号费 HIS返回：" + doregFeeInterfaceStr);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(" 2030 查询挂号费 HIS返回有误：" + e.getMessage());
            log.error("",e);
            return errMsgReturn(doregFeeInterfaceStr);
        }

        DoregFeeInterfaceRes doregFeeInterfaceRes = JSON.parseObject(doregFeeInterfaceStr, DoregFeeInterfaceRes.class);
        DoregFeeInterfaceResDatas doregFeeInterfaceResDatas = new DoregFeeInterfaceResDatas();
        doregFeeInterfaceResDatas.setOperCode(HisConvertConst.Operation.DOREG_FEE_INTERFACE);
        doregFeeInterfaceResDatas.setMsg(doregFeeInterfaceRes.getResultMsg());
        doregFeeInterfaceResDatas.setHosId(doregFeeInterfaceReqDatas.getHosId());
        doregFeeInterfaceResDatas.setConsultationFee(doregFeeInterfaceRes.getConsultationFee());
        //适配出参
        if (HisConvertConst.HisCode.SUCCESS_CODE.equals(doregFeeInterfaceRes.getResultCode())) {
            doregFeeInterfaceResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        } else {
            doregFeeInterfaceResDatas.setSuccess(HisConvertConst.Operation.OPERATION_FAIL);
        }
        return new Gson().toJson(doregFeeInterfaceResDatas);
    }

}