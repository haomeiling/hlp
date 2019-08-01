package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import java.util.List;

/**
 * @author:tangliang
 * @date:2018/7/28
 * @description:
 */
@Data
public class GetSocialPolicyOutput {
    private Long operCode;
    private Long success;

    private List<GetSocialPolicyOutputItem> data;
}
