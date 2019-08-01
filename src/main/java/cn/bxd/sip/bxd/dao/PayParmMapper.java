package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.dto.pay.PayReceiveOrder;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.model.entity.PayParmKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PayParmMapper {
    PayParm selectByPrimaryKey(PayParmKey key);

    PayReceiveOrder selectByOrderIdAndProviderId(Map<String,Object> map);
}