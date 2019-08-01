package cn.bxd.sip.bxd.hispay.protocol.payscan;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 扫码支付请求出参
 *
 * @Author haomeiling
 * @Date 2017/3/2
 */
@Data
public class PayScanResData {
    /*返回状态码*/
    private String returnCode;
    /* 返回信息*/
    private String returnMsg;
    /*处理结果*/
    private String resultCode;
    /* 结果描述*/
    private String resultMsg;
    /*错误编码*/
    private String errCode;
    /* 申请识别码*/
    private String requestNo;
    /*交易流水号*/
    private Long transId;
    /*交易金额*/
    private BigDecimal amount;
    /*二维码地址*/
    private String codeURL;
    /* 签名*/
    private String sign;
}
