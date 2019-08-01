package cn.bxd.sip.bxd.model.entity;

import lombok.Data;

/**
 * 医院开通社保接口配置
 * 
 * @author weishaoxiang
 * 
 */
@Data
public class HospitalSiConfig {

	public String hospitalId;// 医院id
	public String hospitalNo;// 医院编号
	public String medicareType;// 医疗类型 诊疗类型 1：门诊 2：住院 3：体检4：其他
	public String operatorNo;// 操作员编号
	public String dynamic;// 网卡地址
	public String cycleNo;// 业务周期号
	public String dllParm;// dll参数
	public String area;// 统筹区
	public String remark;// 备注说明
	public String status;// 有效标志， 0 无效 1 有效
	public String createdate;// 创建日期

	private String msgType; // 业务编号
	private String businessNo;// 交易流水号
	private String centralCode;// 中心编码 NEW
	public String inputData;// 入参
	public String onlineSign;// 联机标志 1

}
