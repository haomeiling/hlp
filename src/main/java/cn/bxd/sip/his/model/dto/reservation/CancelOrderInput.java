package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/8/8
 * @description:
 */
@Data
public class CancelOrderInput {
    public Integer operCode;
    public Integer hosId;
    public String orderNo;
    public String cancelReason;
}
