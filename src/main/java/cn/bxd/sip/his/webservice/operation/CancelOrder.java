package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.dao.HospitalSiConfigMapper;
import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.dto.PayScanResData;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.service.IClientService;
import cn.bxd.sip.bxd.service.IMessageService;
import cn.bxd.sip.bxd.var.AppVar;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.CancelOrderInput;
import cn.bxd.sip.his.model.dto.reservation.CancelOrderOutput;
import cn.bxd.sip.si.service.SiService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 取消订单
 *
 * @author:tangliang
 * @date:2018/8/8
 * @description:
 */
@Component
public class CancelOrder extends AbstractOperationProcessor {

    @Autowired
    private SiService hisSer;

    @Autowired
    private IClientService clientServiceSer;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private HospitalSiConfigMapper tRhipHospitalSiConfigMapper;

    @Autowired
    private IMessageService messageService;


    @Override
    public String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        CancelOrderOutput output = new CancelOrderOutput();
        CancelOrderInput input = JSON.parseObject(reqMsg, CancelOrderInput.class);
        output.setOperCode(Integer.parseInt(HisConvertConst.Operation.CANCEL_ORDER));
        String check = checkParam(input);
        if (check == null || check.equals("")) {
            Order order = orderMapper.selectByPrimaryKey(Long.parseLong(input.orderNo));
            if (order != null) {
                Short pattern = order.getPayPattern();
                if (pattern != null) {
                    String msg;
                    if (pattern == 11) {//支付方式  11自费，12社保，20混合
                        msg = doCancelSelfFee(order, input);
                    } else if (pattern == 12) {
                        msg = doCancelSocial(order);
                    } else {
                        msg = doCancelSelfFee(order, input);
                        msg += doCancelSocial(order);
                    }

                    if (msg == null || msg.equals("")) {
                        /*output.setSuccess(1);
                        output.setMsg("退款操作成功！");*/
                    } else {
                        output.setSuccess(0);
                        output.setMsg("退款操作失败！" + msg);
                        String res = JSON.toJSONString(output);
                        return res;
                    }
                }

                //更新状态
                Map<String, Object> inParam = new HashMap<>();
                inParam.put("orderId", order.getOrderId());
                inParam.put("canceledReason", input.getCancelReason());
                orderMapper.orderCancel(inParam);

                //发送取消消息通知
                //绑号失败的时候，不发送通知
                //判断是否有订单院内编码或者订单确认发送标志位是否已发送
                String peerOrderNo = order.getPeerOrderNo();
                Short sentFlag = order.getSentFlag();
                boolean hasSend = peerOrderNo != null && !peerOrderNo.equals("") && sentFlag != null && sentFlag == ReservationVar.Is.TRUE;
                if (hasSend) {
                    messageService.sendCancelNotify(input.getHosId(), order.getUserId(), order.getOrderId());
                }
                output.setSuccess(1);
                output.setMsg("取消操作成功！");
            } else {
                output.setSuccess(0);
                output.setMsg("获取不到订单数据！");
                System.out.println("获取不到订单数据！");
            }
        } else {
            output.setSuccess(0);
            output.setMsg(check);
            System.out.println(check);
        }

        String res = JSON.toJSONString(output);
        return res;
    }

    public String doCancelSelfFee(Order order, CancelOrderInput input) throws IOException, IllegalAccessException {
        String res = "";
        if (order.getPayFlag() != null && order.getPayFlag() == 2) {//自费支付状态  0 = 未支付，1 = 支付中，2 = 已支付
            PayScanResData cancelRes = clientServiceSer.cancel(order.getOrderId().toString(), input.getCancelReason());
            res = cancelRes.getResultCode().equals("SUCCESS") ? "" : cancelRes.getResultMsg();
        } else {
            res = "订单未支付！";
        }
        return res;
    }

    public String doCancelSocial(Order order) {
        String res = "";
        if (order.getMedicarePayState() == 1) {//医保支付状态：0,未支付，1，支付成功，2失败
            HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(order.getHospitalId().toString(), order.getMedicareType().toString());
            PayInfo info = hisSer.cancelPayment(hospitalSiConfig, Integer.parseInt(order.getMedicareRecordId().toString()), order.getPatientNo(),
                    order.getOrderId().toString(), order.getPatientName(), order.getCertIdno(), order.getSocialNo(), order.getOrderId());
            res = info.getAppCode().equals("0") ? "" : info.getDetailMessage();
        } else {
            res = "社保未支付或支付失败！";
        }
        return res;
    }

    private String checkParam(CancelOrderInput input) {
        String res = "";
        if (input == null) {
            res = "入参格式错误！";
        }
        if (input.getCancelReason() == null) {
            res = "入参取消原因不能为空！";
        }
        if (input.getHosId() == null) {
            res = "入参医院Id不能为空！";
        }
        //已经进入到方法，不用检测操作码  haomeiling 20190415
       /* if(input.getOperCode() == null){
            res = "入参操作码不能为空！";
        }*/
        if (input.getOrderNo() == null) {
            res = "入参订单号不能为空！";
        }
        return res;
    }
}
