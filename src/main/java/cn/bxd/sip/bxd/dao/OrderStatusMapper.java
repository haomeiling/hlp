package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.entity.OrderStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderStatusMapper {
    int deleteByPrimaryKey(OrderStatus key);

    int insert(OrderStatus record);

    int insertSelective(OrderStatus record);

    OrderStatus selectByPrimaryKey(OrderStatus key);

    int updateByPrimaryKeySelective(OrderStatus record);

    int updateByPrimaryKey(OrderStatus record);
}