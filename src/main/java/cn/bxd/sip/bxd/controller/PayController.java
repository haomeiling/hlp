package cn.bxd.sip.bxd.controller;

import cn.bxd.sip.bxd.dao.HospitalSiConfigMapper;
import cn.bxd.sip.bxd.dao.PayParmMapper;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.dto.pay.PayReceiveMsg;
import cn.bxd.sip.bxd.model.dto.pay.PayReceiveOrder;
import cn.bxd.sip.bxd.model.dto.pay.R;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.model.entity.SiMedicareRecordsWithBLOBs;
import cn.bxd.sip.bxd.pay.CancelOrderService;
import cn.bxd.sip.bxd.service.IOrderService;
import cn.bxd.sip.bxd.service.IOrderStatusService;
import cn.bxd.sip.bxd.service.ISiMedicareRecordsService;
import cn.bxd.sip.bxd.util.Signature;
import cn.bxd.sip.bxd.var.OrderStatus;
import cn.bxd.sip.bxd.var.PNotifyType;
import cn.bxd.sip.bxd.var.PayConst;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.si.service.SiService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:支付回调
 * User: HaoMeiLing
 * Date: 2018-09-18
 * Time: 13:47
 */

@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    private IOrderStatusService orderStatusService;
    @Resource
    private PayParmMapper payParmMapper;
    @Resource
    private IOrderService orderService;
    @Resource
    private SiService siService;
    @Resource
    private HospitalSiConfigMapper tRhipHospitalSiConfigMapper;

    @Autowired
    private ISiMedicareRecordsService tSiSer;

    @Resource
    private CancelOrderService cancelOrderService;


    /**
     * POST 支付完成后回调
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public void payReceive(HttpServletRequest req, HttpServletResponse res) {

        //判断：入参是否为空
        String jsonString = getReqInputString(req, res);
        log.info("----payReceive入参----：\n" + jsonString);
        if (jsonString == null) {
            printResponseContent(res, JSON.toJSONString(R.sysError("有没有参数")));
            return;
        }

        //根据支付类型，orderId查询ProviderAppkey
        PayReceiveMsg receiveMsg = JSON.parseObject(jsonString, PayReceiveMsg.class);
        if (receiveMsg == null && StringUtils.isBlank(receiveMsg.getProviderTypeCode())) {
            printResponseContent(res, JSON.toJSONString(R.sysError("没有ProviderAppKey！")));
            return;
        }

        //只处理支付成功中的数据，否则会造成数据错乱  20190423
        String notifyType = receiveMsg.getNotifyType();
        switch (notifyType) {
            case PNotifyType.NOTIFY_PAYING:
                printResponseContent(res, JSON.toJSONString(R.sysError("支付中通知,不处理")));
                return;
            case PNotifyType.NOTIFY_REFUND_SUCCESS:
                printResponseContent(res, JSON.toJSONString(R.sysError("退款通知,不处理")));
                return;
            case PNotifyType.NOTIFY_PAY_SUCCESS:
                break;
            default:
                printResponseContent(res, JSON.toJSONString(R.sysError("未知的通知类型")));
                break;
        }

        String providerTypeCode = receiveMsg.getProviderTypeCode();
        long orderId = Long.parseLong(receiveMsg.getRequestNo());
        Short providerId;
        switch (providerTypeCode) {
            case PayConst.ProviderTypeCode.PROVIDER_TYPE_WXPAY:
                providerId = PayConst.Trans.PROVIDER_WEI_XIN;
                break;
            case PayConst.ProviderTypeCode.PROVIDER_TYPE_ALI:
                providerId = PayConst.Trans.PROVIDER_ALIPAY;
                break;
            default:
                printResponseContent(res, JSON.toJSONString(R.sysError("未知支付渠道")));
                return;
        }

        Map<String, Object> param = new HashMap<>(16);
        param.put("orderId", orderId);
        param.put("providerId", providerId);
        PayReceiveOrder payReceiveOrder = payParmMapper.selectByOrderIdAndProviderId(param);

        log.info("----payReceiveOrder返回----：\n" + payReceiveOrder);

        if (payReceiveOrder == null && StringUtils.isBlank(payReceiveOrder.getProviderAppkey())) {
            printResponseContent(res, JSON.toJSONString(R.sysError("获取provider app key出错！")));
            return;
        }

        Order orderInfo = orderService.getOrder(orderId);
        log.info("----查询订单返回----:" +JSON.toJSONString(orderInfo));
        //判断：查询订单返回是否错误
        if (orderInfo == null) {
            printResponseContent(res, JSON.toJSONString(R.resultError("查询订单错误！")));
            return;
        }

        Short payPattern = orderInfo.getPayPattern();

        //判断：是否自费订单
        if (payPattern != ReservationVar.PayPattern.SELF_FEE) {
            //请求：社保结算请求
            SiMedicareRecordsWithBLOBs records = tSiSer.selectByPrimaryKey(orderInfo.getMedicareRecordId().intValue());

            HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(String.valueOf(orderInfo.getHospitalId()), String.valueOf(orderInfo.getMedicareType()));
            PayInfo payInfo = siService.payment(hospitalSiConfig, records.getRecordid(), records.getFeeids(), String.valueOf(orderInfo.getOrderId()), orderInfo.getPatientName(), orderInfo.getCertIdno(), orderInfo.getSocialNo(), records.getInputstr(), orderInfo.getOrderId());
            orderStatusService.insertOrderStatus(orderId, OrderStatus.PAY_SETTLE_FAILS, "社保结算成功");
            return;
        }

        //更新：订单信息
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCashMoney(receiveMsg.getAmount());
        order.setTransId(receiveMsg.getTransId());
        order.setStatus(ReservationVar.Order.ORDER_STATUS_CONFIRMED);
        order.setPayFlag(ReservationVar.Order.PAY_FLAG_HAS_PAYED);
        order.setPayType(providerId);
        order.setAppId(orderInfo.getAppId());
        order.setPayBody(receiveMsg.getTransCode());
        order.setPayPattern(payPattern);
        order.setTerminalCode(orderInfo.getTerminalCode()); // lisheng  2019/7/11

        if (payPattern.intValue() == ReservationVar.PayPattern.SELF_FEE) {
            order.setMedicarePayState(ReservationVar.MedicarePayState.NOT_PAY);
        } else {
            order.setMedicarePayState((payPattern.intValue() == ReservationVar.PayPattern.MIX_FEE ? ReservationVar.MedicarePayState.HAS_PAYED
                    : ReservationVar.MedicarePayState.NOT_PAY));
        }

        log.info("----payReceive发送更新订单----：" + JSON.toJSONString(order));
        int updateSIOrderRes = orderService.updateOrderWithMedicare(order);
        log.info("----payReceive发送更新订单返回----：" + updateSIOrderRes);

        //判断：更新订单返回是否错误
        if (updateSIOrderRes <= 0) {
            //保存订单状态记录表: 自费支付失败
            orderStatusService.insertOrderStatus(orderId, OrderStatus.PAY_SELF_FEE_FAILS, "payReceive发送更新订单返回有误：" + updateSIOrderRes);
            printResponseContent(res, JSON.toJSONString(R.resultError("payReceive发送更新订单返回有误")));
            return;
        }
        //保存订单状态记录表: 自费支付成功
        orderStatusService.insertOrderStatus(orderId, OrderStatus.PAY_SELF_FEE_SUCCESS, "掌上医院 自费支付成功");

        //支付成功通知
        //sendSuccessNotify(queryOrderInfo);

        printResponseContent(res, JSON.toJSONString(R.ok("支付完成后回调")));
    }

    /**
     * 订单取消
     *
     * @Description:
     * @date: 2019年2月13日 下午3:54:43
     */
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    public void cancelOrder(@RequestParam String hospitalId, @RequestParam String orderId, @RequestParam String cancelReason,
                            @RequestParam BigDecimal amount, @RequestParam String hiFeeNo, HttpServletRequest req, HttpServletResponse res) {
        Order order = orderService.getOrder(Long.valueOf(orderId));
        if (order == null) {
            printResponseContent(res, JSON.toJSONString(R.resultErr("订单不存在").put("orderId", orderId)));
            return;
        }
        if (new BigDecimal(order.getCashMoney()).compareTo(amount) != 0) {
            printResponseContent(res, JSON.toJSONString(R.resultErr("订单信息不匹配").put("orderId", orderId)));
            return;
        }
        if (StringUtils.isBlank(hiFeeNo)) {
            printResponseContent(res, JSON.toJSONString(R.resultErr("医院缴费编号为空").put("orderId", orderId)));
            return;
        }
        if (!order.getPeerOrderNo().contains(hiFeeNo)) {
            printResponseContent(res, JSON.toJSONString(R.resultErr("订单信息不匹配").put("orderId", orderId)));
            return;
        }
        if (order.getPayFlag() != ReservationVar.Order.PAY_FLAG_HAS_PAYED) {//  0 = 未支付，1 = 支付中，2 = 已支付
            printResponseContent(res, JSON.toJSONString(R.resultErr("订单未支付").put("orderId", orderId)));
            return;
        }
        String result = "";
        try {
            result = cancelOrderService.cancel(order, cancelReason, hospitalId, false);
        } catch (Exception e) {
            log.error("取消订单异常, orderId:" + orderId, e);
        }
        if ("OK".equals(result)) {
            printResponseContent(res, JSON.toJSONString(R.ok("订单取消成功").put("orderId", orderId)));
            return;
        }
        printResponseContent(res, JSON.toJSONString(R.resultErr(result).put("orderId", orderId)));
    }


    /**
     * 获取入参字符串
     */
    private String getReqInputString(HttpServletRequest req, HttpServletResponse res) {
        String jsonString;
        try {
            if (req.getInputStream() == null) {
                return "";
            }
            // 读取请求内容
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            jsonString = sb.toString();
        } catch (IOException e) {
            log.error("", e);
            printResponseContent(res, JSON.toJSONString(R.sysError("获取参数错误")));
            return null;
        }
        return jsonString;
    }

    protected void printResponseContent(HttpServletResponse response, String content) {
        PrintWriter out = null;
        try {
            response.setContentType("text/json;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            out = response.getWriter();
            out.write(content);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 返回签名校验
     */
    public boolean isResSignatureValid(Object o, String key) {
        try {
            if (Signature.isSignatureValid(key, o)) {
                return true;
            }
            log.info("----签名校验错误----：" + o);
        } catch (IllegalAccessException e) {
            log.error("", e);
            log.info("----签名校验发生异常----：" + e.getMessage());
        }
        return false;
    }

}
