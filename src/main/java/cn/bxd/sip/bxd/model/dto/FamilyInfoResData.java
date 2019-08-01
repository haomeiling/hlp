package cn.bxd.sip.bxd.model.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

@XStreamAlias("ListItems")
@Data
public class FamilyInfoResData {

	private List<FamilyInfo> ListItems;

}
