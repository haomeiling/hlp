package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/7/23
 * @description:
 */
@Data
public class DoSettlementInput {
    private Long operCode;
    private Long hosId;
    private Long orderId;
    private Long recordId;
    private String name;
    private String socialNo;
    private String medicareType;
    private String totalMoney;
    private String payMoney;
    private String overMoney;
    private String cashMoney;
    private String idNumber;
}
