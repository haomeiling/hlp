package cn.bxd.sip.bxd.model.dto.pay;


/**
 * Description: 退费返回
 * Package: com.bxd.medicalinsurance.model.pay
 *
 * @author Leeves
 * @version 1.0.0  2018-05-23
 */
public class RefundRes {
    /** 返回状态码 */
    private String returnCode;

    /** 返回信息 */
    private String returnMsg;

    /** 处理结果 */
    private String resultCode;

    /** 结果描述 */
    private String resultMsg;

    /** 错误编码 */
    private String errCode;

    /** 申请识别号, */
    private String requestNo;

    /** 签名 */
    private String sign;
}
