package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 判断用户是否已注册 4005
 * @author Administrator
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JudgeThirdUserReqDates extends BaseReqDates {
    private String openId;
    private String extUserTypeId;
    private String userId;
}