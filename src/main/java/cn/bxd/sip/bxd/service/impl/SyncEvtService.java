package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.dao.SyncEvtMapper;
import cn.bxd.sip.bxd.dao.SyncEvtPendingMapper;
import cn.bxd.sip.bxd.model.dto.QueryOrderInfo;
import cn.bxd.sip.bxd.model.entity.SyncEvt;
import cn.bxd.sip.bxd.model.entity.SyncEvtPending;
import cn.bxd.sip.bxd.service.ISeqService;
import cn.bxd.sip.bxd.service.ISyncEvtService;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.bxd.var.SyncType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异步业务服务类
 *
 * @author HML
 * @Date 2016/2/1
 */
@Service
public class SyncEvtService implements ISyncEvtService {
    @Resource
    private SyncEvtMapper syncEvtDao;
    @Resource
    private SyncEvtPendingMapper syncEvtPendingDao;
    @Resource
    private ISeqService proceduresService;
    @Resource
    private OrderMapper orderMapper;

    /**
     * 新增通知:通知医院还号
     *
     * @param hosId    医院ID
     * @param queueId  队列编号
     * @param periodNo 时段序号
     * @param orderDay 就诊时间
     * @param regNo    号数
     * @return
     */
    @Override
    public void addNotifyBackNo(int hosId, String queueId, short periodNo, Integer orderDay, String regNo) {
        SyncEvt syncEvt = new SyncEvt();
        syncEvt.setHospitalId(hosId);
        syncEvt.setStatusCode(ReservationVar.SyncStatusCode.UNDO);

        long syncSeqId = proceduresService.getSyncSeqId();
        syncEvt.setSyncSeqId(syncSeqId);

        syncEvt.setSyncTypeId(SyncType.BACK_NO_NOTIFY);//退号通知类型
        syncEvt.setCreatedTime(new Date());
        syncEvt.setQueueId(queueId);
        syncEvt.setPeriodNo(periodNo);
        syncEvt.setQueueNo(regNo);
        syncEvt.setClinicDate(orderDay);

        syncEvtDao.insert(syncEvt);//增加还号业务


        SyncEvtPending syncEvtPending = new SyncEvtPending();
        syncEvtPending.setSyncSeqId(syncSeqId);
        syncEvtPendingDao.insert(syncEvtPending);//增加未完成业务记录
    }

    /**
     * 新增通知：取消订单
     *
     * @param orderId 订单编号
     */
    @Override
    public void addNotifyCancelOrder(long orderId) {
        SyncEvt syncEvt = new SyncEvt();
        syncEvt.setStatusCode(ReservationVar.SyncStatusCode.UNDO);

        QueryOrderInfo order = orderMapper.selectOrderByOrderId(orderId);
        syncEvt.setHospitalId(order.getHospitalId());

        long syncSeqId = proceduresService.getSyncSeqId();
        syncEvt.setSyncSeqId(syncSeqId);

        syncEvt.setSyncTypeId(SyncType.CANCEL_ORDER_NOTIFY);//取消订单
        syncEvt.setCreatedTime(new Date());
        syncEvt.setOrderId(orderId);
        syncEvtDao.insert(syncEvt);//增加还号业务


        SyncEvtPending syncEvtPending = new SyncEvtPending();
        syncEvtPending.setSyncSeqId(syncSeqId);
        syncEvtPendingDao.insert(syncEvtPending);//增加未完成业务记录
    }

    /**
     * 新增通知：支付成功
     *
     * @param orderId 订单编号
     * @param transId 交易流水号
     */
    @Override
    public void addNotifyPaySuccess(long orderId, long transId) {
        SyncEvt syncEvt = new SyncEvt();
        syncEvt.setStatusCode(ReservationVar.SyncStatusCode.UNDO);

        QueryOrderInfo order = orderMapper.selectOrderByOrderId(orderId);
        syncEvt.setHospitalId(order.getHospitalId());

        long syncSeqId = proceduresService.getSyncSeqId();
        syncEvt.setSyncSeqId(syncSeqId);

        syncEvt.setSyncTypeId(SyncType.PAY_SUCCESS_NOTIFY);//支付成功通知
        syncEvt.setCreatedTime(new Date());
        syncEvt.setOrderId(orderId);
        syncEvt.setTransId(transId);
        syncEvtDao.insert(syncEvt);//增加还号业务


        SyncEvtPending syncEvtPending = new SyncEvtPending();
        syncEvtPending.setSyncSeqId(syncSeqId);
        syncEvtPendingDao.insert(syncEvtPending);//增加未完成业务记录
    }

    /**
     * 新增通知：退款成功
     *
     * @param orderId 订单编号
     * @param transId 交易流水号
     */
    @Override
    public void addNotifyRefundSuccess(long orderId, long transId) {

        //首先判断是否有支付成功通知，如果有，再添加退款通知。目前由于超时退款的通知，也会通知到订单
        //添加时间20180309 自己发现的bug
        SyncEvt syncEvt0 = new SyncEvt();
        syncEvt0.setOrderId(orderId);
        syncEvt0.setSyncTypeId(SyncType.PAY_SUCCESS_NOTIFY);
        List<SyncEvt> syncEvtList = syncEvtDao.selectByCondition(syncEvt0);
        if (syncEvtList == null || syncEvtList.size() == 0) return;


        QueryOrderInfo order = orderMapper.selectOrderByOrderId(orderId);
        SyncEvt syncEvt = new SyncEvt();
        syncEvt.setStatusCode(ReservationVar.SyncStatusCode.UNDO);
        syncEvt.setHospitalId(order.getHospitalId());

        long syncSeqId = proceduresService.getSyncSeqId();
        syncEvt.setSyncSeqId(syncSeqId);

        syncEvt.setSyncTypeId(SyncType.REFUND_SUCCESS_NOTIFY);//退款通知
        syncEvt.setCreatedTime(new Date());
        syncEvt.setOrderId(orderId);
        syncEvt.setTransId(transId);//退款单号
        syncEvtDao.insert(syncEvt);//增加退款通知


        SyncEvtPending syncEvtPending = new SyncEvtPending();
        syncEvtPending.setSyncSeqId(syncSeqId);
        syncEvtPendingDao.insert(syncEvtPending);//增加未完成退款通知
    }

    /**
     * 新增通知：订单消息
     *
     * @param orderId 订单编号
     */
    @Override
    public void addNotifyOrderConfirm(long orderId) {
        SyncEvt syncEvt = new SyncEvt();
        syncEvt.setStatusCode(ReservationVar.SyncStatusCode.UNDO);

        QueryOrderInfo order = orderMapper.selectOrderByOrderId(orderId);
        syncEvt.setHospitalId(order.getHospitalId());

        long syncSeqId = proceduresService.getSyncSeqId();
        syncEvt.setSyncSeqId(syncSeqId);

        syncEvt.setSyncTypeId(SyncType.ORDER_CONFIRM_NOTIFY);//订单通知
        syncEvt.setCreatedTime(new Date());
        syncEvt.setOrderId(orderId);
        syncEvtDao.insert(syncEvt);//增加订单撞人


        SyncEvtPending syncEvtPending = new SyncEvtPending();
        syncEvtPending.setSyncSeqId(syncSeqId);
        syncEvtPendingDao.insert(syncEvtPending);//增加未完成业务记录
    }


    /**
     * 获取待处理异步消息ID字符串
     * 调用存储过程
     * @return 消息ID字符串 ，通过逗号分隔
     */
    @Override
    public String getEvtPendingIdStr() {
        String res = "";
        Map map = new HashMap<>();
        syncEvtPendingDao.getPending(map);
        if (map != null) {
            Object idStr = map.get("id_str");
            if (idStr != null) {
                res = idStr.toString();
            }
        }
        return res;
    }
}
