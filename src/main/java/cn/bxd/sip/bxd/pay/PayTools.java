package cn.bxd.sip.bxd.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bxd.sip.bxd.model.dto.pay.TokenReq;
import cn.bxd.sip.bxd.model.dto.pay.TokenRes;
import cn.bxd.sip.bxd.util.HttpUtils;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.util.Signature;
import cn.bxd.sip.bxd.var.ClientConst;
import cn.bxd.sip.bxd.var.SIVar;

/**
 * 
 * @Description:
 * @author: chenchuanchuan 
 * @date:   2018年12月3日 下午12:03:04
 */

public class PayTools {
	
    private static Logger log = LoggerFactory.getLogger(PayTools.class);
    
    /**

    /**
     * 获取token
     *
     * @return success/fail
     */
    public static TokenRes getToken(String account, String secret, String key) {
        TokenRes resBody = new TokenRes();
        try {
            //发送数据封装
            TokenReq tokenReq = new TokenReq(account, secret);
            //签名
            String sign = Signature.getSign(key, tokenReq);
            tokenReq.setSign(sign);
            //发送请求
            String inParam=JsonTools.obj2Json(tokenReq);
            log.info("-----getToken req----" + inParam);
            String outPut=HttpUtils.sendPostRequest(SIVar.WS.paygateUrl+ClientConst.ReqUrl.TOKEN_REQ_URL,inParam);
            log.info("-----getToken res----" + outPut);
            resBody=JsonTools.gJson2Bean(outPut,TokenRes.class);
        } catch (Exception e) {
            log.error("",e);
            log.info("-----getToken失败，签名失败-----" + e.getMessage());
            resBody.setResultMsg(e.getMessage());
            resBody.setResultCode(SIVar.ResultCode.RESULT_CODE_FALI);
            return resBody;
        }
        return resBody;
    }

}
