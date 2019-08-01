package cn.bxd.sip.bxd.model.dto;

import lombok.Data;

@Data
public class FamilyInfo {

	private String UserName;// 使用人姓名
	private String UserIDNumber;// 使用人身份证号码
	private String UserSocialNo;// 使用人社保卡号
	private String IsInsurance;// 手机号码
	private String Relation;// 与授权人关系

}
