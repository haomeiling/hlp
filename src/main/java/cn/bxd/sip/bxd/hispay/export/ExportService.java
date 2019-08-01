package cn.bxd.sip.bxd.hispay.export;

import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import cn.bxd.sip.bxd.hispay.exception.SysErrException;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.pay.PayProxyService;
import cn.bxd.sip.bxd.util.HttpUtils;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.util.Signature;
import cn.bxd.sip.bxd.util.WXPayUtils;
import cn.bxd.sip.bxd.var.ClientConst;
import cn.bxd.sip.bxd.var.PayConst;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.bxd.var.SIVar;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;


/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 16:30
 */
@Slf4j
@Service
public class ExportService implements IExportService {
    private static final String PROVIDER_CODE = "providerCode";
    private static final String SOURCE_CODE = "sourceCode";
    @Autowired
    PayProxyService payProxyService;

    /**
     * @param hospitalCode 医院编码
     * @param methodName   方法名称
     * @param payType      支付类型
     * @param map          入参
     * @return
     * @throws BusinessException
     * @throws SysErrException
     * @throws SQLException
     */
    @Override
    public String export(String hospitalCode, String methodName, Short payType, Map<String, Object> map) throws BusinessException {

        long start = System.currentTimeMillis(), // 获取消息处理的开始时间
                end = 0;// 消息处理结束时间

        // 调用服务，包含容错、恢复机制处理
        String inMsgStr = "";
        try {
            // 调用
            inMsgStr = outCall(hospitalCode, methodName, payType, map);
        } catch (Exception e) {// 如果调用异常，抛出异常提醒
            // 记录异常
            log.error(e.getMessage(), e);
            throw new BusinessException("请求支付服务出现异常---" + e.getMessage(), ResultCodeConstant.RESULTCODE_90003);
        }
        // 输出用时较久的记录
        end = System.currentTimeMillis();// 获取消息处理的结束时间
        if ((end - start) > 300 && log.isInfoEnabled()) {
            log.info("\n time used：" + (end - start) + "ms\n超时入参：\n" + inMsgStr);
        }

        return inMsgStr;
    }

    private String outCall(String hospitalCode, String methodName, Short payType, Map<String, Object> map) throws Exception {
        //发送请求
        String output = outCall2Paygate(hospitalCode, methodName, payType, map, false);
        //解析
        JSONObject obj = JSONObject.parseObject(output);
        String errorCode = obj.getString("errCode");
        //若token失效，则重试一次
        if (StringUtils.isNotBlank(errorCode) && (PayConst.ErrorCode.TOKEN_INVALID.equals(errorCode)
                || PayConst.ErrorCode.TOKEN_IS_EMPTY.equals(errorCode))) {
            output = outCall2Paygate(hospitalCode, methodName, payType, map, true);
            if (StringUtils.isBlank(output)) {
                throw new BusinessException("服务异常", ResultCodeConstant.RESULTCODE_90003);
            }
        }
        return output;
    }

    private String outCall2Paygate(String hospitalCode, String methodName, Short payType, Map<String, Object> map, boolean reload) throws Exception {
        PayParm payParm = payProxyService.getToken(Integer.parseInt(hospitalCode), String.valueOf(payType),
                ReservationVar.AppID.APPID_PALMHV10, reload);
        if (payParm == null) {
            throw new BusinessException("配置异常", ResultCodeConstant.RESULTCODE_90003);
        }

        /**
         * 检测是否存在这两个，如果存在，则重新赋值
         * microMap.put("providerCode", "");
         * microMap.put("sourceCode", "");
         */
        Object providerCode = map.get(PROVIDER_CODE);
        if (providerCode != null) {
            map.put(PROVIDER_CODE, payParm.getProviderPayCode());
        }
        Object sourceCode = map.get(SOURCE_CODE);
        if (sourceCode != null) {
            map.put(SOURCE_CODE, payParm.getProviderPaySourceCode());
        }

        map.put("token", payParm.getToken());

        String authCode = WXPayUtils.getAuthCode(payParm.getProviderPayAccount(), payParm.getSessionKey());
        map.put("authCode", authCode);

        String sign = Signature.getSign(payParm.getProviderPayKey(), map);
        map.put("sign", sign);

        String input = JsonTools.obj2Json(map);
        log.info("------支付(查询)入参--------" + input);
        String output = HttpUtils.sendPostRequest(SIVar.WS.paygateUrl + methodName, input);
        log.info("------支付(查询)出参-------" + output);
        if (StringUtils.isBlank(output)) {
            throw new BusinessException("服务异常", ResultCodeConstant.RESULTCODE_90003);
        }

        //目前查询存在验签问题,该方法暂时不验签 haomeiling 2019-04-29
        boolean isQuery = methodName.contains(ClientConst.ReqUrl.PAY_QUERY_TRANS_URL);
        if (!isQuery) {
            Map outObj = (Map) JSON.parse(output);
            String outSign = Signature.getSign(payParm.getProviderPayKey(), outObj);
            if (!outSign.equals(outObj.get("sign"))) {
                throw new BusinessException("验签失败", ResultCodeConstant.RESULTCODE_90003);
            }
        }

        return output;
    }

}
