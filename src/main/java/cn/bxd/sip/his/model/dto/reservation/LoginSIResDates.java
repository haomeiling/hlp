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
public class LoginSIResDates extends BaseResDates {
    private String userId;
    private String msg;

	public LoginSIResDates(String operCode, String success, String hosId, String userId, String msg) {
		super(operCode, success, hosId);
		this.userId = userId;
		this.msg = msg;
	}

}