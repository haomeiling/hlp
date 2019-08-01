package cn.bxd.sip.bxd.model.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@XStreamAlias("BODY")
@Data
public class FamilyInfoReqData {

	private String Name; // 姓名
	private String IDNumber; // 身份证号
	private String user_id;

}
