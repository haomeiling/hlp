package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.model.dto.AppTokenAndKey;
import cn.bxd.sip.bxd.model.dto.PayCancelReqData;
import cn.bxd.sip.bxd.model.dto.PayScanResData;
import cn.bxd.sip.bxd.service.IClientService;
import cn.bxd.sip.bxd.util.DESCode;
import cn.bxd.sip.bxd.util.HttpUtils;
import cn.bxd.sip.bxd.util.Signature;
import cn.bxd.sip.bxd.var.ClientConst;
import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:tangliang
 * @date:2018/8/8
 * @description:
 */
@Service
@Slf4j
public class ClientService implements IClientService {

    /**
     * 取消订单
     * @throws IllegalAccessException
     * @throws IOException
     */
    public PayScanResData cancel(String requestNo,String reason) throws IOException, IllegalAccessException {
        AppTokenAndKey token = new AppTokenAndKey();
        token = getToken();

        PayScanResData resData = new PayScanResData();

        if(token.getAppToken()!= null && token.getSessionKey() !=null){
            //封装数据
            PayCancelReqData reqData = new PayCancelReqData();
            reqData.setRequestNo(requestNo);
            reqData.setReason(reason);
            reqData.setToken(token.getAppToken());

            //获取授权因子
            Map<String, String> authMap = new HashMap<String, String>();
            authMap.put("accountNo", ClientConst.ACCOUNT_NO);
            String authData = JSON.toJSONString(authMap);

            try {
                String mi = DESCode.encrypt(authData, token.getSessionKey());
                reqData.setAuthCode(mi);
            } catch (Exception e) {
                log.error("",e);
            }

            //签名
            String sign = Signature.getSign(ClientConst.PUBLIC_KEY, reqData);
            reqData.setSign(sign);

            //发送
            String outDate = HttpUtils.sendPostRequest(ClientConst.ReqUrl.PAY_CANCEL_URL, JSON.toJSONString(reqData));
            resData = JSON.parseObject(outDate, PayScanResData.class);

            //返回验签
            System.out.println("服务器响应的sign:"+resData.getSign());
            String afterSign = Signature.getSign(ClientConst.PUBLIC_KEY, resData);
            System.out.println("验签的sign:"+afterSign);
        }
        return resData;
    }

    /**
     * 获取凭证
     * @throws IOException
     */
    public AppTokenAndKey getToken() throws IOException{
        AppTokenAndKey res = new AppTokenAndKey();
        //封装数据，其他客户端参照可用对象封装数据
        Map<String, Object> reqData = new HashMap<String, Object>();
        reqData.put("accountNo", ClientConst.ACCOUNT_NO);
        reqData.put("accountSecret", ClientConst.ACCOUNT_SECRET);

        //加签
        String sign = Signature.getSign(ClientConst.PUBLIC_KEY, reqData);
        reqData.put("sign", sign);
        //发送通知
        String outDate = HttpUtils.sendPostRequest(ClientConst.ReqUrl.TOKEN_REQ_URL, JSON.toJSONString(reqData));
        Map<String, Object> resData = JSON.parseObject(outDate, new TypeReference<Map<String, Object>>() {});
        //返回验签

        String afterSign = Signature.getSign(ClientConst.PUBLIC_KEY, resData);
        String appToken = String.valueOf(resData.get("token"));
        String sessionKey = String.valueOf(resData.get("sessionKey"));

        System.out.println("服务器响应的sign:"+resData.get("sign"));
        System.out.println("验签的sign:"+afterSign);
        System.out.println("获取到的token:"+appToken);
        System.out.println("获取到的sessionKey:"+sessionKey);

        res.setAppToken(appToken);
        res.setSessionKey(sessionKey);
        return  res;
    }
}
