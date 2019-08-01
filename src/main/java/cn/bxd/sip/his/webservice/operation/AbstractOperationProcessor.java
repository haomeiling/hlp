package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.BaseErrResDates;
import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Description:
 * Package: com.bxd.sip.reservation.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-09
 */
public abstract class AbstractOperationProcessor implements OperationProcessor {

    protected String errMsgReturn(String errMsg) {

        return JSON.toJSONString(new BaseErrResDates(errMsg));
    }

    protected String noDataMsgReturn(String code, String noDataMsg) {
        BaseErrResDates baseErrResDates = new BaseErrResDates();
        baseErrResDates.setCode(code);
        baseErrResDates.setMsg(noDataMsg);
        baseErrResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        return JSON.toJSONString(baseErrResDates);
    }

    @Override
    public String getDataFromHis(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        return sendToHisOperation(reqMsg, tRhipConnectParm);
    }

    /**
     * 具体发送到his方法
     */
    protected abstract String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception;

    /**
     * 根据key获取wsClient
     */
    Object getHosWsClient(String clientKey, ConnectParm tRhipConnectParm) {
        Map<String, Object> hosWsClientMap = tRhipConnectParm.getHosWsClientMap();
        return hosWsClientMap.get(clientKey);
    }

}