package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.dto.QueryOrderInfo;
import cn.bxd.sip.bxd.model.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {


    /**
     * 根据id获取一条order记录（所有字段）
     *
     * @param orderId
     * @return 订单新
     */
    Order selectByPrimaryKey(Long orderId);

    /**
     * 删除订单
     *
     * @param orderId 订单编码
     */
    void deleteByPrimaryKey(Long orderId);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insertSelective(Order record);

    /**
     * 更新订单相应值
     * 本接口代码优化时新增，通过接口一次性更新字段，减少数据库操作次数
     *
     * @param inParam 入参
     */
    int updateByPrimaryKey(Map<String, Object> inParam);

    /**
     * 更新订单相应值
     * 本接口代码优化时新增，通过接口一次性更新字段，减少数据库操作次数
     *
     * @param order 入参
     */
    int updateByPrimaryKey(Order order);


    void orderCancel(Map<String, Object> inParm);


    QueryOrderInfo selectOrderByOrderId(Long orderId);

    /**
     * 支付前对订单的业务处理，防止退号线程将支付中的订单退号
     *
     * @param inParm 订单编码
     * @return 处理结果
     * @Author 郝美玲
     * @Date 20161212
     */
    void orderPayPre(Map<String, Object> inParm);

    /**
     * 支付完成后对订单的业务处理
     *
     * @param inParm 订单编码 处理结果
     * @return 处理结果
     * @Author 郝美玲
     * @Date 20161212
     */
    void orderPayResult(Map<String, Object> inParm);

    /**
     * 查询患者的预约订单
     */
    List<Order> selectRegOrderListByPatient(@Param("hospitalId") Integer hospitalId, @Param("patientId")String patientId);
    
    /**
     * 按ID集合查询
     * @Description: 
     * @date:   2019年2月26日 上午10:37:03
     */
    List<Order> queryOrdersByIds(@Param("orderIds")List<Long> orderIds);
}