package cn.bxd.sip.bxd.hispay.protocol.payscan;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 扫码支付请求入参
 *
 * @Author haomeiling
 * @Date 2017/2/28
 */
@Data
public class PayScanReqData {
    /*支付渠道*/
    private String providerCode;
    /*支付来源，如自助机、移动端、官微等对应的code。 add by haomeiling  20180228*/
    private String sourceCode;
    /*子商户应用ID*/
    private String subAppId = "";
    /*子商户ID*/
    private String subMchId = "";
    /* 交易金额*/
    private BigDecimal amount;
    /*申请识别号*/
    private String requestNo = "";
    /* 返回地址*/
    private String location = "";
    /* 商品描述*/
    private String body = "";
    /*令牌标识*/
    private String token = "";
    /*授权因子*/
    private String authCode = "";
    /*签名*/
    private String sign = "";
}
