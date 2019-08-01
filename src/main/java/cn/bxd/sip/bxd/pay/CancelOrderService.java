package cn.bxd.sip.bxd.pay;

import java.util.HashMap;
import java.util.Map;

import cn.bxd.sip.bxd.model.dto.PayCancelResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.model.dto.PayCancelReqData;
import cn.bxd.sip.bxd.model.dto.PayScanResData;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.util.HttpUtils;
import cn.bxd.sip.bxd.util.Signature;
import cn.bxd.sip.bxd.util.WXPayUtils;
import cn.bxd.sip.bxd.var.ClientConst;
import cn.bxd.sip.bxd.var.PayConst;
import cn.bxd.sip.bxd.var.SIVar;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CancelOrderService {
     
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private PayProxyService payProxyService;
	
	public String cancel(Order order, String reason, String hospitalId, boolean isReloadToken) throws Exception{
		
        PayParm payParm = payProxyService.getToken(Integer.valueOf(hospitalId), String.valueOf(order.getPayType()), order.getAppId(), isReloadToken);
        if (payParm == null) {
            return "配置异常";
        }
        
        //封装数据
        PayCancelReqData reqData = new PayCancelReqData();
        reqData.setRequestNo(String.valueOf(order.getOrderId()));
        reqData.setReason(reason);
        reqData.setToken(payParm.getToken());

        String authCode = WXPayUtils.getAuthCode(payParm.getProviderPayAccount(), payParm.getSessionKey());
        reqData.setAuthCode(authCode);
        //签名
        String sign = Signature.getSign(payParm.getProviderPayKey(), reqData);
        reqData.setSign(sign);
        log.info("取消订单请求参数:"+JSON.toJSONString(reqData));
        //发送
        String outData = HttpUtils.sendPostRequest(SIVar.WS.paygateUrl + ClientConst.ReqUrl.PAY_CANCEL_URL, JSON.toJSONString(reqData));
        log.info("取消订单响应参数:"+outData);
		return doCancel(order, hospitalId, reason, outData, payParm.getProviderPayKey());
	}
	
	public String doCancel(Order order, String hospitalId, String reason, String outData, String providerPayKey) throws Exception {
		//连接超时处理
        if (outData == null || outData.isEmpty()) {
            log.info("取消订单响应内容为空，返回为空：orderId--" + order.getOrderId());
            return "取消异常";
        }

        PayCancelResData resData = JSON.parseObject(outData, PayCancelResData.class);

        //返回token无效或为空，则重新获取token，再次发送支付请求
        boolean tokenIsInvalid = PayConst.ErrorCode.TOKEN_INVALID.equals(resData.getErrCode()) || PayConst.ErrorCode.TOKEN_IS_EMPTY.equals(resData.getErrCode());
        if (tokenIsInvalid) {
             return cancel(order, reason, hospitalId, true);
        }

        //返回参数进行签名校验
        if (!payProxyService.isResSignatureValid(resData, providerPayKey, resData.getSign())) {
            log.info("取消订单 验签出错：orderId--" + order.getOrderId());
            return "验签失败";
        }

        if (PayConst.PayCode.FAIL.equals(resData.getReturnCode()) || PayConst.PayCode.FAIL.equals(resData.getResultCode())) {
            log.info("----取消订单失败，失败原因----：" + resData.getResultMsg());
            return "取消失败，"+resData.getResultMsg()+resData.getErrCode();
        }
        //更新状态
        Map<String, Object> inParam = new HashMap<>();
        inParam.put("orderId", order.getOrderId());
        inParam.put("canceledReason", reason);
        orderMapper.orderCancel(inParam);
        return "OK";
	}
	
}
