package cn.bxd.sip.bxd.model.dto;

import lombok.Data;

/**
 * @author：weishaoxiang @version：V1.0
 *                      <P/>
 * @类说明：医保结算信息
 */
@Data
public class PayInfo {

	private String hospitalId;// 医院ID
	private String hospitalno;// 医院编号
	private String name;// 姓名
	private String idNumber;// 身份证号
	private String socialNo;// 社保卡号
	private String userNo;// 个人编号
	private String area;// 统筹区号
	private String cardInfo;// 为社保卡信息（如读卡交易入参）
	private String hospitalInfo;// 医院串
	private String inputStr;// 预结算交易入参串
	private String outputStr;//交易出参串--（medicareMess	String	否	医保个人基本信息（医保读卡交易返回值。根据 medicareType判断返回值格式）
	private String medicareInfo;// 预结算医保返回出参串outputData

	public String medicareType;// 医疗类型 诊疗类型 1：门诊 2：住院 3：体检4：其他
	public String businessCode;// 业务编号
	private String feeIds;// 诊疗编号(诊疗记录标志)
	private String businessNo;// 交易流水号
	private String siFeeIds;// 社保_就诊流水号
	private String siPoNo;// 社保_单据号
	public String poNo;// 订单号
	private int recordId;// 社保支付记录ID

	private String operatorCode;// 经办人（操作员）
	private String totalMoney;// 医疗费总额1
	private String payMoney;// 本次帐户支付3
	private String overMoney;// 统筹支出4
	private String cashMoney;// 现金支付金额7
	private String isOverall;// 是否进行统筹（0否，1是）
	private String appCode;// 成功标志位 0：成功；1：失败
	private String detailMessage;// 提示信息
	public String poState;// 交易状态（0未成功 ,1成功）
	public String payDate;// 结算日期
	public String savePrescriptionsign;// 是否保存处方标志（0不保存，1保存）

}
