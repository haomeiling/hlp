package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.OrderStatusMapper;
import cn.bxd.sip.bxd.model.entity.OrderStatus;
import cn.bxd.sip.bxd.service.IOrderStatusService;
import cn.bxd.sip.bxd.service.ISeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author:tangliang
 * @date:2018/8/3
 * @description:
 */
@Service
public class OrderStatusService implements IOrderStatusService {

    @Autowired
    private ISeqService seqService;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    public int insertOrderStatus(Long orderId, cn.bxd.sip.bxd.var.OrderStatus orderStatus, String mark){
        OrderStatus model = new OrderStatus();
        Long newId = seqService.getStatusId();
        model.setStatusId(newId);
        model.setOrderStatus((short)orderStatus.getId());
        model.setOrderId(orderId);
        model.setMarks(mark);
        model.setOrderStatusTime(new Date());
        model.setOrderStatusDesc(orderStatus.getName());
        int res = orderStatusMapper.insert(model);
        return res;
    }

}
