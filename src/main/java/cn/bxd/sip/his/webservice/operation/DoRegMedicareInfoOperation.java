package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.reservation.*;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description: 获取挂号医保信息 2031
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Lisheng
 * @version 1.0.0  2019-02-15
 */
@Slf4j
@Component
public class DoRegMedicareInfoOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        DoRegMedicareInfoReqDatas doRegMedicareInfoReqDatas = JSON.parseObject(reqMsg, DoRegMedicareInfoReqDatas.class);
        DoRegMedicareInfoReq doRegMedicareInfoReq = new DoRegMedicareInfoReq();
        doRegMedicareInfoReq.setOrgandoctorId(doRegMedicareInfoReqDatas.getOrgandoctorId());
        doRegMedicareInfoReq.setDepartmentorganId(doRegMedicareInfoReqDatas.getDepartmentorganId());
        doRegMedicareInfoReq.setPatientNo(doRegMedicareInfoReqDatas.getPatientNo());
        doRegMedicareInfoReq.setSocialsecurityNO(doRegMedicareInfoReqDatas.getSocialsecurityNO());
        doRegMedicareInfoReq.setSourceDate(doRegMedicareInfoReqDatas.getSourceDate());
        doRegMedicareInfoReq.setTimestypeNo(doRegMedicareInfoReqDatas.getTimestypeNo());
        doRegMedicareInfoReq.setSourceTimeType(doRegMedicareInfoReqDatas.getSourceTimeType());
        doRegMedicareInfoReq.setMedicareMess(doRegMedicareInfoReqDatas.getMedicareMess());
        doRegMedicareInfoReq.setTerminalCode(doRegMedicareInfoReqDatas.getTerminalCode());
        doRegMedicareInfoReq.setMedicareType(doRegMedicareInfoReqDatas.getMedicareType());
        doRegMedicareInfoReq.setTerminalType(doRegMedicareInfoReqDatas.getTerminalType());
        //封装：向HIS发送请求
        String doregFeeInterfaceStr = "";
        try {
            //ws客户端：1.5 取号
            Object hosWsClient4 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();
            String doRegMedicareInfoStr = JSON.toJSONString(doRegMedicareInfoReq);
            log.debug("---his doTaketheNo入参 ---:" + doRegMedicareInfoStr);
            doregFeeInterfaceStr = HisWSClient.invoke(hosWsClient4, HisFunNameConst.DOREG_MEDICARE_INFO, sysUserName, sysKey, doRegMedicareInfoStr);

            log.debug(" 2031 获取挂号医保信息 HIS返回：" + doregFeeInterfaceStr);
        } catch (Exception e) {
            log.debug(" 2031 获取挂号医保信息 HIS返回有误：" + e.getMessage());
            log.error("",e);
            return errMsgReturn(doregFeeInterfaceStr);
        }

        DoRegMedicareInfoRes doRegMedicareInfoRes = JSON.parseObject(doregFeeInterfaceStr, DoRegMedicareInfoRes.class);
        DoRegMedicareInfoResDatas doRegMedicareInfoResDatas = new DoRegMedicareInfoResDatas();
        doRegMedicareInfoResDatas.setOperCode(HisConvertConst.Operation.DO_REG_MEDICARE_INFO);
        doRegMedicareInfoResDatas.setMsg(doRegMedicareInfoRes.getResultMsg());
        doRegMedicareInfoResDatas.setHosId(doRegMedicareInfoReqDatas.getHosId());
        doRegMedicareInfoResDatas.setMedicareInfo(doRegMedicareInfoRes.getMedicareInfo());
        //适配出参
        if (HisConvertConst.HisCode.SUCCESS_CODE.equals(doRegMedicareInfoRes.getResultCode())) {
            doRegMedicareInfoResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        } else {
            doRegMedicareInfoResDatas.setSuccess(HisConvertConst.Operation.OPERATION_FAIL);
        }
        return new Gson().toJson(doRegMedicareInfoResDatas);
    }

}