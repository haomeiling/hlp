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
public class JudgeUserReqDates extends BaseReqDates {
    private String userCode;
    private String loginMobile;
    private String loginEmail;
}