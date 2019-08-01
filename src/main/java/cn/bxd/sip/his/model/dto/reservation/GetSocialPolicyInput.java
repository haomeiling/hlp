package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/7/28
 * @description:
 */
@Data
public class GetSocialPolicyInput {
    private String PolicyType;
    private String StartTime;
    private String EndTime;
    private String PolicyCode;
}
