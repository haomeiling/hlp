package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * 退款入参
 * @Description:
 * @author: chenchuanchuan 
 * @date:   2018年10月27日 上午11:28:42
 */
@Data
public class RefundSocialInput {
    public Integer operCode;
    public Long hosId;
    private String recordId;//社保缴费记录号
    private String patientNo;//患者编号
    private String transId;//交易id
    private String patientName;//患者姓名
    private String idNumber;//患者身份证号
    private String socialNo;//患者社保号
    public String cancelReason;
}
