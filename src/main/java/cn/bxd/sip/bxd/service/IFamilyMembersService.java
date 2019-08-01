package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.dto.FamilyInfo;
import cn.bxd.sip.bxd.model.dto.FamilyInfoReqData;
import cn.bxd.sip.bxd.model.entity.FamilyMembers;

import java.util.List;

/**
 * @author：weishaoxiang
 * @version：V1.0 <P/>
 * @类说明：医保个账授权
 */

public interface IFamilyMembersService {

	public int saveFamilyMembers(FamilyMembers familyMembers);

	public String findUserId(String string) ;

	public int deleteFamilyMembers(FamilyMembers familyMembers);

	public List<FamilyInfo> findFamilyInfo(FamilyInfoReqData familyInfoReqData) ;

}
