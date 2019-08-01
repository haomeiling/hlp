package cn.bxd.sip.bxd.model.policy;

import lombok.Data;

/**
 * 政策详细查询(110111)
 * 
 * @Version 1.0
 * @Author haomeiling
 * @Date 2018-03-05
 */
@Data
public class PolicyDetailResData {

	private String PolicyCode;// 政策唯一编码
	private String PolicyType;// 政策类型
	private String PolicyTypeName;// 政策类型名称
	private String Summary;// 政策摘要
	private String InfoBlob;// 政策内容
	private String Publisher;// 发行商
	private String ReleaseTime;// 发布时间
	private String Title;// 政策标题
	private String ContentPath;// 政策内容文件的路径

}
