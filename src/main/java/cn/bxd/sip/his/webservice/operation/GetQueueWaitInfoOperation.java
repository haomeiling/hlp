package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.reservation.GetQueueWaitInfoReqDates;
import cn.bxd.sip.his.model.dto.reservation.GetQueueWaitInfoRes;
import cn.bxd.sip.his.model.dto.reservation.GetQueueWaitInfoResDates;
import cn.bxd.sip.his.model.dto.reservation.getQueueWait.res.QueueItem;
import cn.bxd.sip.his.model.dto.reservation.getQueueWait.res.QueueNoItem;
import cn.bxd.sip.his.model.dto.reservation.getQueueWait.res.QueueWaitResData;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 获取现场排队信息 4001
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Lisheng
 * @version 1.0.0  2018-11-1
 */
@Slf4j
@Component
public class GetQueueWaitInfoOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        GetQueueWaitInfoReqDates getQueueWaitInfoReqDates = JSON.parseObject(reqMsg, GetQueueWaitInfoReqDates.class);
        //封装：向HIS发送请求
        String getQueueWaitInfoStr = "";
        try {
            //ws客户端：1.6 住院信息
            Object hosWsClient5 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_IN_HOS, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();
            getQueueWaitInfoStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.GET_QUEUE_WAIT_INFO, sysUserName, sysKey,
                    "", getQueueWaitInfoReqDates.getEmpi(), "", "");

            log.debug(" 4001 排取人数查询 HIS返回：" + getQueueWaitInfoStr);
        } catch (Exception e) {
            log.debug(" 4001 排取人数查询 HIS返回有误：" + e.getMessage());
            log.error("",e);
            return errMsgReturn(getQueueWaitInfoStr);
        }

        GetQueueWaitInfoResDates getQueueWaitInfoResDates = JSON.parseObject(getQueueWaitInfoStr, GetQueueWaitInfoResDates.class);

        if (getQueueWaitInfoResDates.getWaitNumList() == null) {
            return errMsgReturn(getQueueWaitInfoResDates.getResultMsg());
        }
        QueueWaitResData queueWaitResData = new QueueWaitResData();
        //适配出参
        queueWaitResData.setMsg(getQueueWaitInfoResDates.getResultMsg());
        queueWaitResData.setSuccess((short) 1);
        queueWaitResData.setHosId(getQueueWaitInfoReqDates.getHosId());

        List<QueueItem> queueItemList = new ArrayList<>();
        for (GetQueueWaitInfoRes getQueueWaitInfoRes : getQueueWaitInfoResDates.getWaitNumList()) {
            QueueItem queueItem = new QueueItem();
            QueueNoItem queueNoItem = new QueueNoItem();
            List<QueueNoItem> queueNoItemList = new ArrayList<>();
            queueNoItem.setQueueNo(Long.parseLong(getQueueWaitInfoRes.getTakeNo()));//号码（叫号号码）-> 排队号
            queueNoItem.setDisplayQueueNo(getQueueWaitInfoRes.getTakeNo());//号码（叫号号码）-> 显示排队号
            queueNoItem.setWaitNum(getQueueWaitInfoRes.getWaitNum());//等待人数 -> 等待人数
            queueNoItemList.add(queueNoItem);
            queueItem.setItemList(queueNoItemList);
            queueItem.setQueueAddr(getQueueWaitInfoRes.getDepartmentAddress());//就诊地址 -> 候诊地址
            queueItem.setQueueName(getQueueWaitInfoRes.getDoctorName());//就诊医生 -> 队列名称
            queueItemList.add(queueItem);
        }
        queueWaitResData.setData(queueItemList);

        String res = new Gson().toJson(queueWaitResData);
        return res;
    }

}