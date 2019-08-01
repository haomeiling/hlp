package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.reservation.DoTaketheNoReq;
import cn.bxd.sip.his.model.dto.reservation.DoTaketheNoReqDatas;
import cn.bxd.sip.his.model.dto.reservation.DoTaketheNoRes;
import cn.bxd.sip.his.model.dto.reservation.DoTaketheNoResDatas;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description: 取号 2022
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Lisheng
 * @version 1.0.0  2019-01-16
 */
@Slf4j
@Component
public class DoTaketheNoOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        DoTaketheNoReqDatas doTaketheNoReqDatas = JSON.parseObject(reqMsg, DoTaketheNoReqDatas.class);
        DoTaketheNoReq doTaketheNoReq = new DoTaketheNoReq();
        doTaketheNoReq.setOperCode(doTaketheNoReqDatas.getOperCode());
        doTaketheNoReq.setSourceMark(doTaketheNoReqDatas.getPeerOrderNo());
        doTaketheNoReq.setSourceDate(doTaketheNoReqDatas.getClinicDate());
        doTaketheNoReq.setPatientNo(doTaketheNoReqDatas.getPatientNo()); //lisheng 2019/7/25
        //封装：向HIS发送请求
        String doTaketheNoStr = "";
        try {
            //ws客户端：1.5 取号
            Object hosWsClient4 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();
            String doregin = JSON.toJSONString(doTaketheNoReq);
            log.debug("---his doTaketheNo入参 ---:" + doregin);
            doTaketheNoStr = HisWSClient.invoke(hosWsClient4, HisFunNameConst.DO_TAKETHE_NO, sysUserName, sysKey,
                    doTaketheNoReq.getSourceMark(), doTaketheNoReq.getPatientNo(), doTaketheNoReq.getSourceDate(),
                    doTaketheNoReq.getDepartmentorganId(), doTaketheNoReq.getPayType(), doTaketheNoReq.getPayRecord(), doTaketheNoReq.getPayMoney());

            log.debug(" 2022 取号 HIS返回：" + doTaketheNoStr);
        } catch (Exception e) {
            log.debug(" 2022 取号 HIS返回有误：" + e.getMessage());
            log.error("",e);
            return errMsgReturn(doTaketheNoStr);
        }

        DoTaketheNoRes doTaketheNoRes = JSON.parseObject(doTaketheNoStr, DoTaketheNoRes.class);
        DoTaketheNoResDatas doTaketheNoResDatas = new DoTaketheNoResDatas();
        doTaketheNoResDatas.setOperCode(HisConvertConst.Operation.DO_TAKE_THE_NO);
        doTaketheNoResDatas.setMsg(doTaketheNoRes.getResultMsg());
        doTaketheNoResDatas.setHosId(doTaketheNoReqDatas.getHosId());
        //适配出参
        if (HisConvertConst.HisCode.SUCCESS_CODE.equals(doTaketheNoRes.getResultCode())) {
            doTaketheNoResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        } else {
            doTaketheNoResDatas.setSuccess(HisConvertConst.Operation.OPERATION_FAIL);
        }
        return new Gson().toJson(doTaketheNoResDatas);
    }

}