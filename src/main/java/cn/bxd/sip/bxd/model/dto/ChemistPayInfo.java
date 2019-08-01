package cn.bxd.sip.bxd.model.dto;

import lombok.Data;

/**
 * @author：weishaoxiang @version：V1.0
 * @创建时间：2018年08月07日 下午17:25:30 <BR/>
 *                   <P/>
 * @类说明：药店购药-结算信息
 */
@Data
public class ChemistPayInfo {
	
	private String BusinessNo;// 交易流水号
	private String SiPoNo;// 单据号
	private String MedicareType;// 医疗类别
	private String PayDate;// 交易日期
	private String ChemistShopNo;// 药店编号
	private String ChemistShopName;// 药店名称
	private String Name;// 姓名
	private String IDNumber;// 身份证号
	private String SocialNo;// 社保卡号
	private String UserNo;// 个人编号
	private String Area;// 统筹区号
	private String BeforeMoney;// 本次结算前个人账户余额
	private String AfterMoney;// 本次结算后个人账户余额
	private String TotalMoney;// 费用总金额
	private String PayMoney;// 本次帐户支付
	private String OverMoney;// 统筹支出
	private String CashMoney;// 自费金额
	private String FeesProjectGrade;// 收费项目等级
	private String SavePrescriptionSign;// 是否保存处方标志
	private String Operator;// 经办人
	private String Status;// 状态标志
}
