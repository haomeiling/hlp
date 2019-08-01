package cn.bxd.sip.bxd.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Description: TODO
 * Package: com.bxd.utils.sign
 *
 * @author Leeves
 * @date 2018-01-12
 */

public class WXPayUtils {


    /**
     * 获取授权因子
     * @return
     * @throws Exception
     */
    public static String getAuthCode(String account,String session) throws Exception {
        //获取授权因子
        Map<String, String> authMap = new HashMap<>(16);
        authMap.put("accountNo", account);
        String authData = JSON.toJSONString(authMap);
        return DESCode.encrypt(authData, session);
    }

    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

}
