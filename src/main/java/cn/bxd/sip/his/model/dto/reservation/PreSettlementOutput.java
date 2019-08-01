package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/7/23
 * @description:
 */
@Data
public class PreSettlementOutput {

    private String operCode;
    private String success;
    private String hosId;
    private String msg;
    private String orderId;
    private String recordId;
    private String medicareType;
    private String totalMoney;
    private String payMoney;
    private String overMoney;
    private String cashMoney;
}
