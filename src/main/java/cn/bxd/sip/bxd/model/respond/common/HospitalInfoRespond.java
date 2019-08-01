package cn.bxd.sip.bxd.model.respond.common;

import java.util.List;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;

/**
 * 
 * @Description:
 * @author: chenchuanchuan 
 * @date:   2019年1月2日 下午4:14:45
 */
@Data
public class HospitalInfoRespond extends BaseRespond{

	private String hospitalInfo;
	private String hospitalUrl;
}
