package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/8/8
 * @description:
 */
@Data
public class CancelOrderOutput {
    public int operCode;
    /*
    * 成功标志位1：成功；0：失败
    */
    public int success;

    public String msg ;

	public CancelOrderOutput(int operCode, int success, String msg) {
		this.operCode = operCode;
		this.success = success;
		this.msg = msg;
	}

	public CancelOrderOutput() {
	}
    
	
     
}
