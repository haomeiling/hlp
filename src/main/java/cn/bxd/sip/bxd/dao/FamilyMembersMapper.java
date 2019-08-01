package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.dto.FamilyInfo;
import cn.bxd.sip.bxd.model.dto.FamilyInfoReqData;
import cn.bxd.sip.bxd.model.entity.FamilyMembers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author：weishaoxiang
 * @version：V1.0 <P/>
 * @类说明：医保个账授权
 */

@Mapper
public interface FamilyMembersMapper {

	int saveFamilyMembers(FamilyMembers familyMembers);

	String findUserId(String string);

	int deleteFamilyMembers(FamilyMembers familyMembers);

	List<FamilyInfo> findFamilyInfo(FamilyInfoReqData familyInfoReqData);

}
