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
public class JudgeThirdUserResDates extends BaseResDates {
	
	private Boolean isFirstLogin;
	
	public JudgeThirdUserResDates(Boolean isFirstLogin, String operCode, String success, String hosId) {
		super(operCode, success, hosId);
		this.isFirstLogin = isFirstLogin;
	}
}