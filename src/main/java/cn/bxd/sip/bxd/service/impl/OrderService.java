package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.model.dto.OrderDBResInfo;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.service.IOrderService;
import cn.bxd.sip.bxd.util.MapUtils;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.bxd.var.TerminalVar;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-09-18
 * Time: 16:32
 */
@Service
public class OrderService implements IOrderService {
    private static final Logger logger = Logger.getLogger(OrderService.class.getName());

    @Resource
    private OrderMapper orderDao;
    @Resource
    private SyncEvtService syncEvtService;

    @Override
    public Order getOrder(Long orderId) {
        return orderDao.selectByPrimaryKey(orderId);
    }


    /**
     * 支付完成后对订单的业务处理
     *
     * @param orderId 订单编码
     * @param transId 交易编码
     * @param result  处理结果 SUCCESS/FALI
     * @return 处理结果
     * @Author 郝美玲
     * @Date 20161212
     */
    @Override
    public OrderDBResInfo orderPayResult(Long orderId, Long transId, Short transTypeId, String result) {
        //封装入参
        Map<String, Object> inParam = new HashMap<>();
        inParam.put("orderId", orderId);
        inParam.put("transId", transId);
        inParam.put("result", result);
        inParam.put("transTypeId", transTypeId);

        //处理支付结果
        orderDao.orderPayResult(inParam);

        //封装出参
        OrderDBResInfo baseResData = new OrderDBResInfo();
        MapUtils.transMap2Bean(inParam, baseResData);

        return baseResData;
    }

    @Override
    public OrderDBResInfo orderPayPre(Long orderId, Long transId) {
        //封装入参
        Map<String, Object> inParam = new HashMap<>();
        inParam.put("orderId", orderId);
        inParam.put("transId", transId);

        //处理支付结果`
        orderDao.orderPayPre(inParam);

        //封装出参
        OrderDBResInfo baseResData = new OrderDBResInfo();
        MapUtils.transMap2Bean(inParam, baseResData);


        return baseResData;
    }
    
    public int updateByPrimaryKey(Order order) {
    	return orderDao.updateByPrimaryKey(order);
    }


    @Override
    @Transactional
    public int updateOrderWithMedicare(Order order){
        
    	logger.info("更新的订单：" + JSON.toJSONString(order));
    	order.setPeerOrderNo(null);
        int sus = orderDao.updateByPrimaryKey(order);
        logger.info("更新的订单sus:" + sus);
        logger.info("order.getAppId():" + order.getAppId());
    	if(ReservationVar.AppID.APPID_ZDIV10.equals(order.getAppId()) ||
                ReservationVar.AppID.APPID_YZDIV10.equals(order.getAppId()) ||
                TerminalVar.TerminalCode.PALM_TERMINAL_CODE_HIS_MICRO.equals(order.getTerminalCode())) {
    		logger.info("终端订单不进行异步落地：" + JSON.toJSONString(order));
    		return 1;
    	}
        //支付成功
        if (sus > 0) {
            Order newOrder = orderDao.selectByPrimaryKey(order.getOrderId());
            Short payType = newOrder.getPayPattern();
            Short payFlag = order.getPayFlag();
            Short medicarePayState = order.getMedicarePayState();
            if (payType != null && payFlag != null) {
                if (order.getOrderId() == null) {
                    logger.info("OrderId null");
                }
                if (order.getTransId() == null) {
                    logger.info("TransId null");
                }
                if (payFlag == null) {
                    logger.info("payFlag null");
                }
                if (medicarePayState == null) {
                    logger.info("medicarePayState null");
                }
                if (payType == ReservationVar.PayPattern.SELF_FEE) {//自费
                    if (payFlag == ReservationVar.Order.PAY_FLAG_HAS_PAYED) {
                        syncEvtService.addNotifyPaySuccess(order.getOrderId(), order.getTransId());
                    }
                } else if (payType == ReservationVar.PayPattern.SOSIAL_FEE) {//社保
                    if (medicarePayState == ReservationVar.MedicarePayState.HAS_PAYED) {
                        syncEvtService.addNotifyPaySuccess(order.getOrderId(), order.getTransId());
                    }
                } else if (payType == ReservationVar.PayPattern.MIX_FEE) {//混合
                    if (payFlag == ReservationVar.Order.PAY_FLAG_HAS_PAYED && medicarePayState == ReservationVar.MedicarePayState.HAS_PAYED) {
                        syncEvtService.addNotifyPaySuccess(order.getOrderId(), order.getTransId());
                    }
                }
            }
        }

        return sus;
    }
}
