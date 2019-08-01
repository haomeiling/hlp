package cn.bxd.sip.his.model.dto.reservation;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: TODO Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0 2018-07-06
 */
@Getter
@Setter
public class BaseResDates {
	private String operCode;
	private String success;
	private String hosId;
	private String msg;
	
	public BaseResDates(String operCode, String success, String hosId) {
		this.operCode = operCode;
		this.success = success;
		this.hosId = hosId;
	}

	public BaseResDates() {
		super();
	}
	
	

	
}