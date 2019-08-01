package cn.bxd.sip.bxd.model.dto;

import lombok.Data;

/**
 * @author：weishaoxiang @version：V1.0
 * @创建时间：2018年08月07日 下午17:25:30 <BR/>
 *                   <P/>
 * @类说明：药店购药-处方信息
 */
@Data
public class ChemistRecipeInfo {
	
	private String BusinessNo;// 交易流水号
	private String MedicareType;// 医疗类别
	private String Status;// 状态标志
	private String FeesProjectType;// 收费项目种类
	private String FeesType;// 费用类别
	private String RecipeNo;// 处方号
	private String RecipeDate;// 处方日期
	private String FeesProjectISN;// 医院收费项目内码
	private String FeesProjectCentralCode;// 收费项目中心编码
	private String FeesProjectName;// 医院收费项目名称
	private String Price;// 单价
	private String Amount;// 数量
	private String Drug;// 剂型
	private String Standard;// 规格
	private String Dosage;// 每次用量
	private String Frequency;// 使用频次
	private String RecipeDoctorName;// 处方医师姓名
	private String RecipeDoctorNo;// 处方医师编号
	private String Usage;// 用法
	private String Unit;// 单位
	private String OfficesName;// 科室名称
	private String LimitDays;// 限定天数
	private String CompoundSign;// 草药单复方标志
	private String Operator;// 经办人
	private String DoseUnit;// 药品剂量单位
}
