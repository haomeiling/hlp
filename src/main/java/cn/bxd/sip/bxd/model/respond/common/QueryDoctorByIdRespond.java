package cn.bxd.sip.bxd.model.respond.common;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description:
 * @author: chenchuanchuan 
 * @date:   2019年1月2日 下午4:06:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDoctorByIdRespond extends BaseRespond {
	private String Count;
	private String ReturnQty;
    List<Drug> drug = new ArrayList<>();
}
