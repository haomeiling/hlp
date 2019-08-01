package cn.bxd.sip.bxd.model.policy;

import lombok.Data;

/**
 * 社保政策查询
 * 
 */
@Data
public class Policy {

	private String PolicyCode;// 政策唯一编码
	private String PolicyType;// 政策类型
	private String PolicyTypeName;// 政策类型名称
	private String Year;// 政策年份
	private String Title;// 政策标题
	private String SubTitle;// 政策副标题
	private String Summary;// 政策摘要
	private String Publisher;// 发行商
	private String ReleaseTime;// 发布时间
	private String Remark;// 备注说明
	private String ContentPath;// 政策内容文件的路径

}
