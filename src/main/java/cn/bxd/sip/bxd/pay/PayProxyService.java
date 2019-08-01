package cn.bxd.sip.bxd.pay;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.bxd.sip.bxd.dao.PayParmMapper;
import cn.bxd.sip.bxd.model.dto.PayScanReqData;
import cn.bxd.sip.bxd.model.dto.QueryPayStatusReqData;
import cn.bxd.sip.bxd.model.dto.pay.TokenRes;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.util.Signature;
import cn.bxd.sip.bxd.util.WXPayUtils;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.bxd.var.SIVar;


@Service
public class PayProxyService {
	
	private static final Logger log=Logger.getLogger(PayProxyService.class.getName());
	
	public static Map<String,PayParm> TOKEN_MAP = new HashMap<>();
	
	
	public static final String separator = "|";
	
	@Autowired
	private PayParmMapper payParmMapper;
	
	
	
	public PayParm getToken(Integer hospitalId, String providerId, String appId, boolean isReload) {
		providerId = ReservationVar.PayType.convert(providerId); 
		String key = StringUtils.join(Arrays.asList(hospitalId, providerId, appId), separator);
		PayParm payParm = TOKEN_MAP.get(key);
		if(isReload) {
			payParm = null;
		}
		if(payParm != null) {
			return payParm;
		}
		payParm = payParmMapper.selectByPrimaryKey(new PayParm(appId, Short.valueOf(providerId), hospitalId));
		if(payParm == null) {
			payParm = payParmMapper.selectByPrimaryKey(new PayParm(ReservationVar.AppID.APPID_ZDIV10, 
					Short.valueOf(providerId), hospitalId));
			if(payParm == null) {
				payParm = payParmMapper.selectByPrimaryKey(new PayParm(ReservationVar.AppID.APPID_PALMHV10, 
						Short.valueOf(providerId), hospitalId));
			}
		}
		if(payParm == null) {
			return null;
		}
		log.info("支付配置信息:" + JsonTools.obj2Json(payParm));
		TokenRes token = PayTools.getToken(payParm.getProviderPayAccount(), payParm.getProviderPaySecret(), payParm.getProviderPayKey());
		if (SIVar.ResultCode.RESULT_CODE_SUCCESS.equals(token.getResultCode())) {
			payParm.setToken(token.getToken());
            payParm.setSessionKey(token.getSessionKey());
            TOKEN_MAP.put(key, payParm);
            return payParm;
        } else {
            log.info("====pay再次请求，获取token失败====：" + key + "\n==返回==:" + token);
            return null;
        }
	}
	
    /**
     * 支付请求参数，签名封装
     *
     * @return H5PayReq
     * @throws BizException 获取异常信息
     */
    public PayScanReqData signPayParam(BigDecimal amount, String requestNo, String location,
    		PayParm payParm) {
        PayScanReqData req= new PayScanReqData(payParm.getProviderPayCode(), payParm.getProviderPaySourceCode(), amount, requestNo,
        		location, "扫码支付", payParm.getToken());
        String key = getKey(payParm.getHospitalId(), String.valueOf(payParm.getProviderId()), 
        		String.valueOf(payParm.getAppId()));
        String token = TOKEN_MAP.get(key).getToken();
        req.setToken(token);
        //获取授权因子
        try {
            String authCode = WXPayUtils.getAuthCode(payParm.getProviderPayAccount(), payParm.getSessionKey());
            req.setAuthCode(authCode);
            String sign = Signature.getSign(payParm.getProviderPayKey(), req);
            req.setSign(sign);
        } catch (Exception e) {
            log.error("",e);
        }
        log.info("签名--------" + req);
        log.info("签名--------" + payParm);
        return req;
    }
    
    public QueryPayStatusReqData queryPayStatus(String requestNo,
    		PayParm payParm) {
    	QueryPayStatusReqData req= new QueryPayStatusReqData(requestNo, payParm.getToken());
        String key = getKey(payParm.getHospitalId(), String.valueOf(payParm.getProviderId()), 
        		String.valueOf(payParm.getAppId()));
        String token = TOKEN_MAP.get(key).getToken();
        req.setToken(token);
        //获取授权因子
        try {
            String authCode = WXPayUtils.getAuthCode(payParm.getProviderPayAccount(), payParm.getSessionKey());
            req.setAuthCode(authCode);
            String sign = Signature.getSign(payParm.getProviderPayKey(), req);
            req.setSign(sign);
        } catch (Exception e) {
            log.error("",e);
        }
        log.info("签名--------" + req);
        log.info("签名--------" + payParm);
        return req;
    }
	
    /**
     * 返回签名校验
     */
    public boolean isResSignatureValid(Object o, String key, String oriSign) {
        try {
        	String sign = Signature.getSign(key, o);
            if (oriSign.equals(sign)) {
                return true;
            }
            log.info("----签名校验错误----：" + o);
        } catch (IllegalAccessException e) {
            log.error("",e);
            log.info("----签名校验发生异常----：" + e.getMessage());
        }
        return false;
    }

    public String getKey(Integer hospitalId, String providerId, String appId) {
        return StringUtils.join(Arrays.asList(hospitalId, providerId, appId), separator);
    }

}
