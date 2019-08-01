package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登陆社保系统
 * @author Administrator
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginSIReqDates extends BaseReqDates {
    private String userCode;
    private String password;
    private Integer extUserTypeId;//外部账户类别代号
    private String openId;
}