package cn.bxd.sip.bxd.model.policy;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 社保政策查询(110110)
 * 
 */
@XStreamAlias("BODY")
@Data
public class PolicyListReqData {
	private String PolicyType;// 政策类型
	private String PolicyCode;// 政策唯一编码
	private String StartTime;// 开始时间
	private String EndTime;// 结束时间

}
