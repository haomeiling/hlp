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
public class JudgeUserResDates extends BaseResDates {
	
	private Boolean isRegister;
	
	private String userId;
	
	public JudgeUserResDates(Boolean isRegister, String operCode, String success, String hosId, String userId) {
		super(operCode, success, hosId);
		this.isRegister = isRegister;
		this.userId = userId;
	}
}