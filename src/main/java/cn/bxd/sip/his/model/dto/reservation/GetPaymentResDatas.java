package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetPaymentResDatas extends BaseResDates {

    private String status;//支付状态 0表示成功， 1表示失败
    
    private Integer recordId;//医保流水号  社保支付记录ID
    
	public GetPaymentResDatas(String operCode, String success, String hosId, String status, Integer recordId) {
		super(operCode, success, hosId);
		this.status = status;
	}

	public GetPaymentResDatas(String operCode, String success, String hosId) {
		super(operCode, success, hosId);
	}
    
    

}