package cn.bxd.sip.bxd.model.dto.pay;


/**
 * Description: 退费请求
 * Package: com.bxd.medicalinsurance.model.pay
 *
 * @author Leeves
 * @version 1.0.0  2018-05-23
 */
public class RefundReq {

    /** 申请识别号, */
    private String requestNo;

    /** 原因 */
    private String reason;

    /** 令牌标识 */
    private String token;

    /** 签名 */
    private String sign;

    /** 授权因子 */
    private String authCode;

}
