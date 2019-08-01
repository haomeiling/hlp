package cn.bxd.sip.bxd.model.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author：weishaoxiang @version：V1.0
 *                      <P/>
 * @类说明：医保个账授权
 */

@XStreamAlias("BODY")
@Data
public class FamilyMembers {

	private String id;
	private String createtime; // 创建日期
	private String is_main; // 是否主账号（0否1是）
	private String headNo; // 头像编号
	private String is_authorize; // 是否授权
	private String user_id; //

	private String AuthorizerName; // 授权人姓名
	private String AuthorizerIDNumber; // 授权人身份证号码
	private String AuthorizerSocialNo; // 授权人社保卡号
	private String UserName; // 使用人姓名
	private String UserIDNumber; // 使用人身份证号码
	private String UserSocialNo; // 使用人社保卡号
	private String mobile_no; // 手机号码
	private String IsInsurance; // 是否参加基本医疗保险
	private String InsuranceCode; // 参加基本医疗保险险种
	private String Relation; // 与授权人关系

}
