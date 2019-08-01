package cn.bxd.sip.bxd.model.policy;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 政策详细查询(110111)
 * 
 */
@XStreamAlias("BODY")
@Data
public class PolicyDetailReqData {
	private String PolicyCode;// 政策编码

}
