package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/7/19
 * @description:
 */
@Data
public class Payment {
    private String payType;
    private String payRecord;
    private String totalMoney;
    private String hasMPay;
}
