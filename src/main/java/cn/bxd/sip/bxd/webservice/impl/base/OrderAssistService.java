package cn.bxd.sip.bxd.webservice.impl.base;

import cn.bxd.sip.bxd.common.TimeUtils;
import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.model.respond.reg.HiRegisterRecord;
import cn.bxd.sip.bxd.model.respond.reg.QueryRegBypatientNoRespond;
import cn.bxd.sip.bxd.service.impl.SeqService;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.bxd.var.TerminalVar;
import cn.bxd.sip.bxd.webservice.common.WSHelps;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-12-25
 * Time: 20:17
 */
@Service
public abstract class OrderAssistService {
    @Resource
    public OrderMapper orderMapper;
    @Resource
    private SeqService seqService;

    /**
     *
     *
     * @param orderTypeId
     * @param doRegIn 终端传入的字符串，字段保持一致
     */
    /**
     * 终端订单保存通用方法
     *
     * @param orderTypeId 订单类型  1 预约  7挂号
     * @param status      状态
     * @param hospitalId  医院ID
     * @param sourceMark  号源编号
     * @param takeNo      就诊编码
     * @param doRegIn     终端传入参数

     */
    public Long insertOrder(short orderTypeId, short status, String hospitalId, String sourceMark, String takeNo, String terminalCode, String doRegIn) {
        JSONObject jsonObject = JSONObject.parseObject(doRegIn);
        Long orderId = seqService.getOrderId();
        Order order = new Order();
        order.setOrderId(orderId);//主键订单ID
        order.setHospitalId(Integer.parseInt(hospitalId));//医院ID
        order.setCertIdno(jsonObject.getString("cardNo"));//身份证号码
        order.setPatientNo(jsonObject.getString("patientNo"));//患者院内编码
        order.setOrderTypeId(orderTypeId);//挂号订单或者预约订单
        order.setPeerOrderNo(sourceMark);//HIS唯一编码
        order.setAppId(jsonObject.getString("appId"));//20190222 
        order.setTerminalCode(terminalCode);
        //设置排队号
        String queueNo = " ".equals(takeNo) ? "" : takeNo;
        order.setQueueNo(queueNo);//排队号
        order.setStatus(status);//已确认订单
        order.setAppId(ReservationVar.AppID.APPID_ZDIV10);//应用ID
        order.setOrderSourceId(ReservationVar.Order.SOURCE_ZD);//终端来源
        order.setLastUpdatedTime(new Date());

        //时段
        order.setOrderPeriod(jsonObject.getString("timestypeNoName"));//时段
        order.setDisplayQueueNo(jsonObject.getString("timestypeNo"));//时间段标识
        order.setPeriodNo(jsonObject.getShort("sourceTimeType"));//1,上午，2，下午 3，晚上

        //患者姓名
        order.setPatientName(jsonObject.getString("patientName"));

        //性别
        String genderCode = jsonObject.getString("gender");
        short genderId = WSHelps.getGender(genderCode);
        order.setGenderId(genderId);

        //金额
        String payAmount = jsonObject.getString("payAmount");
        order.setAmount(new BigDecimal(payAmount));

        //预约日期
        String sourceDate = jsonObject.getString("sourceDate");
        String orderDay = sourceDate.replaceAll("-", "");
        order.setOrderDay(Integer.parseInt(orderDay));

        //新增平台医生ID
        order.setDoctorCode(jsonObject.getString("doctorId"));
        order.setDoctorName(jsonObject.getString("doctorName"));

        //队列ID设置为医生ID
        order.setQueueId(jsonObject.getString("organdoctorId"));

        //新增科室ID
        order.setDeptCode(jsonObject.getString("departmentorganId"));
        order.setDeptName(jsonObject.getString("deptName"));

        //处理用户问题
        Object userObject=jsonObject.getString("userNo");
        if(userObject!=null&&!userObject.equals("")){
            order.setUserId(Integer.parseInt(userObject.toString()));
        }
        //新增订单
        orderMapper.insertSelective(order);

        return orderId;
    }


    /**
     * 取消订单
     *
     * @param orderId      订单ID
     * @param cancelReason 取消原因
     */
    public void cancelOrder(Long orderId, String cancelReason) {
        //检测取消是否成功，若成功，则平台需要取消
        Map<String, Object> inParam = new HashMap<>();
        inParam.put("orderId", orderId);
        inParam.put("canceledReason", cancelReason);
        orderMapper.orderCancel(inParam);
    }


    /**
     * 更新订单
     *
     * @param orderId 订单号
     * @param takeNo  取号号码
     */
    public void updateOrder(Long orderId, String takeNo) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setQueueNo(takeNo);
        order.setLastUpdatedTime(new Date());
        order.setStatus(ReservationVar.Order.ORDER_STATUS_CONFIRMED);
        orderMapper.updateByPrimaryKey(order);
    }

    /**
     * sourceMark	String	否	号源编号
     * organdoctorId	String	否	医生编号
     * organdoctorName	String	否	医生姓名
     * departmentorganId	String	否	科室编号
     * departmentName	String	否	科室名称
     * sourceDate	Date	否	号源日期
     * sourceTimeType	Integer	否	1,上午，2，下午 3，晚上
     * consultationFee	float	否	挂号费
     * timestypeNo	String	否	时间段标识
     * timestypeNoName	String	否	时间段显示名称
     * visitAddress	String	否	就诊地址
     * takeAddress	String	否	取号地址
     *
     * @param hosId
     * @param patientNo
     */
    public QueryRegBypatientNoRespond findOrderByPatient(Integer hosId, String patientNo) {
        QueryRegBypatientNoRespond respond = new QueryRegBypatientNoRespond();

        List<HiRegisterRecord> hiRegisterRecordList = new ArrayList<>();
        List<Order> orderList = orderMapper.selectRegOrderListByPatient(hosId, patientNo);
        for (Order order : orderList) {
            HiRegisterRecord hiRegisterRecord = new HiRegisterRecord();
            hiRegisterRecord.setSourceMark(order.getPeerOrderNo());
            hiRegisterRecord.setOrgandoctorId(order.getDoctorCode());
            hiRegisterRecord.setOrgandoctorName(order.getDoctorName());
            hiRegisterRecord.setDepartmentorganId(order.getHisDeptCode());
            hiRegisterRecord.setDepartmentName(order.getDeptName());
            hiRegisterRecord.setSourceDate(TimeUtils.transDateInt2Str(order.getOrderDay()));
            hiRegisterRecord.setSourceTimeType(order.getPeriodNo());

            //金额
            BigDecimal amount = order.getAmount();
            if (amount == null) {
                amount = new BigDecimal(0.00);
            }
            hiRegisterRecord.setConsultationFee(amount.floatValue());
            hiRegisterRecord.setTimestypeNo(order.getDisplayQueueNo());
            hiRegisterRecord.setTimestypeNoName(order.getOrderPeriod());
            hiRegisterRecord.setVisitAddress(order.getQueueAddr());
            hiRegisterRecord.setTakeAddress(order.getQueueAddr());
            hiRegisterRecord.setOrderNo(String.valueOf(order.getOrderId()));
            hiRegisterRecordList.add(hiRegisterRecord);
        }

        respond.setHiRegisterRecordList(hiRegisterRecordList);
        respond.setResultCode(TerminalVar.SUCCESS_CODE);
        respond.setResultMsg(TerminalVar.STATUS_SUCCESS);

        return respond;

    }

}
