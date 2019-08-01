package cn.bxd.sip.bxd.model.policy;

import lombok.Data;

import java.util.List;

/**
 * 社保政策查询(110110)
 * 
 */
@Data
public class PolicyListResData {
	private List<Policy> ListItems;

}
