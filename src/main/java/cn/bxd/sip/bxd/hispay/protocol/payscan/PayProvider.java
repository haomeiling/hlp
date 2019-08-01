package cn.bxd.sip.bxd.hispay.protocol.payscan;

import lombok.Data;

/*
 * 支付渠道实体类
 */
@Data
public class PayProvider {

    private String ProviderID; // 渠道ID

    private String ProviderName; // 渠道名称

    private String PaymentStr; // 扫描支付串

}