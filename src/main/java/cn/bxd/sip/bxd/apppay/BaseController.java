package cn.bxd.sip.bxd.apppay;

import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.pay.PayProxyService;
import cn.bxd.sip.bxd.service.impl.SeqService;
import cn.bxd.sip.bxd.util.HttpUtils;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.util.Signature;
import cn.bxd.sip.bxd.util.WXPayUtils;
import cn.bxd.sip.bxd.var.PayConst;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.bxd.var.SIVar;
import cn.bxd.sip.his.comm.HisConvertConst;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-05-16
 * Time: 13:51
 */
@Slf4j
@Controller
public class BaseController {
    @Resource
    OrderMapper orderMapper;
    @Resource
    SeqService seqService;
    @Resource
    PayProxyService payProxyService;


    /**
     * @param amount      金额
     * @param hospitalId  医院ID
     * @param orderTypeId 订单类型
     * @param hisOrderId  医院单号
     * @return
     * @throws Exception
     */
    protected Order saveOrder(String hospitalId, String orderTypeId, String hisOrderId, String amount) throws Exception {
        Order order = new Order();
        Long orderNo = seqService.getOrderId();
        order.setOrderId(orderNo);
        order.setHospitalId(Integer.valueOf(hospitalId));
        order.setPeerOrderNo(hisOrderId);
        order.setCashMoney(amount);
        order.setAmount(new BigDecimal(amount));
        order.setAppId(ReservationVar.AppID.APPID_PALMHV10);
        order.setOrderTypeId(Short.valueOf(orderTypeId));

        //设置支付类型
        order.setPayType(PayConst.Trans.PROVIDER_ALIPAY);

        //设置终端号
        Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
        ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
        order.setTerminalCode(connectParm.getPalmTerminalCode());

        //设置支付类型
        order.setPayPattern(Short.valueOf(ReservationVar.PayPattern.SELF_FEE + ""));


        if (orderMapper.insertSelective(order) <= 0) {
            throw new Exception("数据新增异常");
        }

        return order;
    }


    protected String outCall(String hospitalCode, Short payType, Map<String, Object> map,String reqUrl) throws Exception {

        //发送请求
        String output = outCall2Paygate(hospitalCode, payType, map, false,reqUrl);
        //解析
        JSONObject obj = JSONObject.parseObject(output);
        String errorCode = obj.getString("errCode");
        //若token失效，则重试一次
        if (StringUtils.isNotBlank(errorCode) && (PayConst.ErrorCode.TOKEN_INVALID.equals(errorCode)
                || PayConst.ErrorCode.TOKEN_IS_EMPTY.equals(errorCode))) {
            output = outCall2Paygate(hospitalCode, payType, map, true,reqUrl);
            if (StringUtils.isBlank(output)) {
                throw new BusinessException("服务异常", ResultCodeConstant.RESULTCODE_90003);
            }
        }
        return output;
    }

    private String outCall2Paygate(String hospitalCode, Short payType, Map<String, Object> map, boolean reload,String reqUrl) throws Exception {
        PayParm payParm = payProxyService.getToken(Integer.parseInt(hospitalCode), String.valueOf(payType),
                ReservationVar.AppID.APPID_PALMHV10, reload);
        if (payParm == null) {
            throw new Exception("配置异常");
        }

        map.put("sourceCode", payParm.getProviderPaySourceCode());
        map.put("providerCode", payParm.getProviderPayCode());

        map.put("token", payParm.getToken());

        String authCode = WXPayUtils.getAuthCode(payParm.getProviderPayAccount(), payParm.getSessionKey());
        map.put("authCode", authCode);

        String sign = Signature.getSign(payParm.getProviderPayKey(), map);
        map.put("sign", sign);

        String input = JsonTools.obj2Json(map);
        log.info("------人脸支付入参--------" + input);
        String output = HttpUtils.sendPostRequest(SIVar.WS.paygateUrl + reqUrl, input);
        log.info("------人脸支付出参-------" + output);
        if (StringUtils.isBlank(output)) {
            throw new Exception("服务异常");
        }

        Map outObj = (Map) JSON.parse(output);
        String outSign = Signature.getSign(payParm.getProviderPayKey(), outObj);

        if (!outSign.equals(outObj.get("sign"))) {
            throw new Exception("验签失败");
        }
        return output;
    }
}
