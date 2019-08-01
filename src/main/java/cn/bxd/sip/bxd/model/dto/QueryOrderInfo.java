package cn.bxd.sip.bxd.model.dto;

import cn.bxd.sip.bxd.model.entity.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description：订单记录扩展类
 *
 * @author liangshangsong
 *         <p/>
 *         2015年8月6日 上午9:22:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryOrderInfo extends Order {

    private String hospitalName;//医院名称
    private String hospitalAddress;//医院地址
    private BigDecimal longitude;//经度
    private BigDecimal latitude;//纬度
    private String contactPersonPhone;//医院联系电话
    private String contactPhone;//联系人电话
    private Date orderDate;//预定日期，orderDay的日期格式，方便前端显示，直接封装
    private String empLogoUrl;//医生头像
    private String qualificationName;//职称名称，如主任医师等
    private String clinicCardNo = "";//就诊卡号
    private String hmpi = "";//医院主索引号



}
