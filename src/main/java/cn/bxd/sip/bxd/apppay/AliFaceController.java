package cn.bxd.sip.bxd.apppay;

import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.model.respond.SmilepayInitializeResData;
import cn.bxd.sip.bxd.var.ClientConst;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.ZolozAuthenticationSmilepayInitializeResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-05-14
 * Time: 13:44
 */
@Slf4j
@RestController
@RequestMapping("/aliFace")
public class AliFaceController extends BaseController {
    protected static final String SUCEESS = "SUCCESS";
    protected static final String FAIL = "FAIL";
    protected static final String WAITING = "WAITING";

    //沙箱网关的url
    public final static String openapiUrl = "https://openapi.alipay.com/gateway.do";

    //刷脸支付相关
    static final String SMILEPAY_CODE_SUCCESS = "10000";


    /**
     * 获取机具数据
     *
     * @param hospitalId 医院编码
     * @param appId      应用ID
     * @param providerId 渠道标志，微信、支付宝
     * @return 机具信息
     */
    @ResponseBody
    @PostMapping("/getMockInfo")
    public Map getMockInfo(@RequestParam("hospitalId") String hospitalId,
                           @RequestParam("appId") String appId,
                           @RequestParam("providerId") String providerId) {
        PayParm payParm = payProxyService.getToken(Integer.parseInt(hospitalId), String.valueOf(providerId), appId, false);
        Map merchantInfo = new HashMap();
        //以下信息请根据真实情况填写
        //商户id
        merchantInfo.put("partnerId", payParm.getMchId());
        merchantInfo.put("merchantId", payParm.getMchId());
        //开放平台注册的appid
        merchantInfo.put("appId", payParm.getProviderAppid());
        //机具编号，便于关联商家管理的机具
        merchantInfo.put("deviceNum", "TEST_ZOLOZ_TEST");
        //真实店铺号
        merchantInfo.put("storeCode", "TEST");
        //口碑店铺号
        merchantInfo.put("alipayStoreCode", "TEST");
        //品牌，传入拼音或者英文，标示该商家
        merchantInfo.put("brandCode", "TEST");

        merchantInfo.put("areaCode", "TEST");
        merchantInfo.put("geo", "0.000000,0.000000");
        merchantInfo.put("wifiMac", "TEST");
        merchantInfo.put("wifiName", "TEST");
        merchantInfo.put("deviceMac", "TEST");

        return merchantInfo;
    }


    /**
     * @param hospitalId 医院编码
     * @param appId      应用ID
     * @param providerId 渠道标志，微信、支付宝
     * @param metaInfo   机具信息
     */
    @ResponseBody
    @PostMapping("/payInitialize")
    public ZolozAuthenticationSmilepayInitializeResponse payInitialize(@RequestParam("hospitalId") String hospitalId,
                                                                       @RequestParam("appId") String appId,
                                                                       @RequestParam("providerId") String providerId,
                                                                       @RequestParam("metaInfo") String metaInfo) {
        log.debug("payInitialize-------hospitalId:"+hospitalId+",appId:"+appId+",providerId:"+providerId+",metaInfo:"+metaInfo);
        ZolozAuthenticationSmilepayInitializeResponse payInitializeResponse;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("metaInfo", metaInfo);
            String payResult = outCall(hospitalId, Short.valueOf(providerId), map,ClientConst.ReqUrl.SMILEPAY_INITIALIZE);
            log.debug("payInitialize请求返回:"+payResult);
            SmilepayInitializeResData smilepayInitializeResData = new Gson().fromJson(payResult, SmilepayInitializeResData.class);
            if(SUCEESS.equals(smilepayInitializeResData.getReturnCode()) && SUCEESS.equals(smilepayInitializeResData.getResultCode())){
                payInitializeResponse = new Gson().fromJson(smilepayInitializeResData.getAliResult(), ZolozAuthenticationSmilepayInitializeResponse.class);
            }else {
                payInitializeResponse = new ZolozAuthenticationSmilepayInitializeResponse();
                payInitializeResponse.setCode(smilepayInitializeResData.getErrCode());
                payInitializeResponse.setMsg(smilepayInitializeResData.getResultCode() + ":" + smilepayInitializeResData.getReturnMsg()
                        + "," + smilepayInitializeResData.getResultCode() + ":" + smilepayInitializeResData.getResultMsg());
            }
            log.debug("payInitialize出参:"+payInitializeResponse);
            return payInitializeResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ZolozAuthenticationSmilepayInitializeResponse();
        }
    }


    /**
     * 刷脸支付
     *
     * @param hospitalId 医院编码
     * @param auth_code  支付授权码
     * @param amount     金额
     * @param scene      支付场景
     *                   条码支付，取值：bar_code
     *                   声波支付，取值：wave_code
     *                   刷脸支付,  取值:  security_code
     * @return
     */
    @ResponseBody
    @PostMapping("/aliFacePay")
    public AlipayTradePayResponse aliFacePay(@RequestParam("hospitalId") String hospitalId,
                                             @RequestParam("auth_code") String auth_code,
                                             @RequestParam("amount") String amount,
                                             @RequestParam("scene") String scene,
                                             @RequestParam("orderTypeId") String orderTypeId,
                                             @RequestParam("hisOrderId") String hisOrderId,
                                             @RequestParam("body") String body,
                                             @RequestParam("providerId") String providerId
    ) {
        try {
            //auth_code和scene填写需要注意
            Order order = saveOrder(hospitalId, orderTypeId, hisOrderId, amount);

            Map<String, Object> microMap = new HashMap<>();
            microMap.put("amount", amount);
            microMap.put("requestNo", order.getOrderId());
            microMap.put("body", body);
            microMap.put("microAuthCode", auth_code);
            microMap.put("scene", scene);

            String payResult = outCall(hospitalId, Short.valueOf(providerId), microMap,ClientConst.ReqUrl.PAY_MICRO_URL);

            return formatResult(payResult);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new AlipayTradePayResponse();
        }
    }


    private AlipayTradePayResponse formatResult(String result) {
        AlipayTradePayResponse alipayTradePayResponse = new AlipayTradePayResponse();
        PayMicroResData payMicroResData = new Gson().fromJson(result, PayMicroResData.class);
        if (SUCEESS.equals(payMicroResData.getReturnCode()) && SUCEESS.equals(payMicroResData.getResultCode())) {
            alipayTradePayResponse.setCode(SMILEPAY_CODE_SUCCESS);
            alipayTradePayResponse.setMsg("支付成功");
        } else {
            alipayTradePayResponse.setCode(payMicroResData.getErrCode());
            alipayTradePayResponse.setMsg(payMicroResData.getReturnCode() + ":" + payMicroResData.getReturnMsg() +
                    ";" + payMicroResData.getResultCode() + ":" + payMicroResData.getResultMsg());
        }

        return alipayTradePayResponse;
    }

}
