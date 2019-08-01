package cn.bxd.sip.bxd.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * ����:T_RHIP_ORDER_STATUS���ʵ����
 * @version
 * @author:  Administrator
 * @����ʱ��: 2018-07-26
 */
@Data
public class OrderStatus {
    private Long orderId;

    private Long statusId;
    
    private Short orderStatus;

    private Date orderStatusTime;
    
    private String orderStatusDesc;

    private String marks;

}