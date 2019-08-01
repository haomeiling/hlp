package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/7/23
 * @description:
 */
@Data
public class DoSettlementOutput {
    private String operCode;
    private String success;
    private String hosId;
    private String msg;
    private String orderId;
    private String recordId;
    private String totalMoney;
    private String payMoney;
    private String overMoney;
    private String cashMoney;
    private String isOverAll;
    private String socialSecurityNo;
    private String medicareType;
    private String cardInfo;
    private String medicareMess;
    private String mPoPayTime;
    private String mPoPayState;

    //跟海慈联调过程中，海慈方需要该字段落地HIS  add  by  haomeiling  20181206
    private String medicareReturn;//社保结算返回，HIS需要该字段落地
    private String  userNo;//社保用户编码
}
