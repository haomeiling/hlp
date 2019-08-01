package cn.bxd.sip.bxd.model.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author：weishaoxiang
 * @version：V1.0 <P/>
 * @类说明：查询认证信息（710104）
 */
@Data
@XStreamAlias("BODY")
public class RealUser {

	private String UserNo;// 个人编号
	private String Name;// 姓名
	private String IDNumber;// 身份证号
	private String SocialNo;// 社保卡号
	private String MobileNo;// 手机号码
	private String RealTime;// 认证时间
	private String CardInfo;// 卡信息串
	private String Area;// 统筹区
	private String UserId;// 用户id
	
	public RealUser(String userNo, String name, String iDNumber, String socialNo, String mobileNo, String realTime,
			String cardInfo, String area, String userId) {
		UserNo = userNo;
		Name = name;
		IDNumber = iDNumber;
		SocialNo = socialNo;
		MobileNo = mobileNo;
		RealTime = realTime;
		CardInfo = cardInfo;
		Area = area;
		UserId = userId;
	}
	
	public RealUser(Object userNo, Object name, Object iDNumber, Object socialNo, Object mobileNo, Object realTime,
			Object cardInfo, Object area, Object userId) {
		UserNo = (String)userNo;
		Name = (String)name;
		IDNumber = (String)iDNumber;
		SocialNo = (String)socialNo;
		MobileNo = (String)mobileNo;
		RealTime = (String)realTime;
		CardInfo = (String)cardInfo;
		Area = (String)area;
		UserId = (String)userId;
	}
	public RealUser() {
	}
	
	

}
