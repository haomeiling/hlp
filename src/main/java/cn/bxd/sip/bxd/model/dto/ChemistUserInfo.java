package cn.bxd.sip.bxd.model.dto;

import lombok.Data;

/**
 * @author：weishaoxiang @version：V1.0
 * @创建时间：2018年08月07日 下午17:25:30 <BR/>
 *                   <P/>
 * @类说明：药店购药-人员信息
 */
@Data
public class ChemistUserInfo{
	
	private String AppType;// 终端类型 （1微信，2支付宝，3 APP）
	private String OpenId;// openid 用户id
	private String UserId;// user_id 用户id
	private String UserNo;// user_no 个人编号
	private String Name;// patient_name 姓名
	private String IDNumber;// card_id 身份证号
	private String SocialNo;// visitcard_num 社保卡号
	private String MobileNo;// mobile_no 手机号码
	private String CardInfo;// cardInfo 卡信息串
	private String Area;// overall_area 统筹区
	public ChemistUserInfo(String appType, String openId, String userId, String userNo, String name, String iDNumber,
			String socialNo, String mobileNo, String cardInfo, String area) {
		this.AppType = appType;
		this.OpenId = openId;
		this.UserId = userId;
		this.UserNo = userNo;
		this.Name = name;
		this.IDNumber = iDNumber;
		this.SocialNo = socialNo;
		this.MobileNo = mobileNo;
		this.CardInfo = cardInfo;
		this.Area = area;
	}
	public ChemistUserInfo() {
		
	}
	
	public ChemistUserInfo(Object appType, Object openId, Object userId, Object userNo, Object name, Object iDNumber,
			Object socialNo, Object mobileNo, Object cardInfo, Object area) {
		this.AppType = (String)appType;
		this.OpenId = (String)openId;
		this.UserId = (String)userId;
		this.UserNo = (String)userNo;
		this.Name = (String)name;
		this.IDNumber = (String)iDNumber;
		this.SocialNo = (String)socialNo;
		this.MobileNo = (String)mobileNo;
		this.CardInfo = (String)cardInfo;
		this.Area = (String)area;
	}
	
	
}
