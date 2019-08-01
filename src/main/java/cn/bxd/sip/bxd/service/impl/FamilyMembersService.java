package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.FamilyMembersMapper;
import cn.bxd.sip.bxd.model.dto.FamilyInfo;
import cn.bxd.sip.bxd.model.dto.FamilyInfoReqData;
import cn.bxd.sip.bxd.model.entity.FamilyMembers;
import cn.bxd.sip.bxd.service.IFamilyMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: Package: cn.bxd.sip.bxd.service.impl
 * @author Leeves
 * @version 1.0.0 2018-07-25
 */
@Service
public class FamilyMembersService implements IFamilyMembersService {
	
	@Autowired
	FamilyMembersMapper familyMembersMapper;
	
	@Override
	public int saveFamilyMembers(FamilyMembers familyMembers){
		return familyMembersMapper.saveFamilyMembers( familyMembers );
	}
	
	@Override
	public String findUserId(String string){
		return familyMembersMapper.findUserId( string );
	}
	
	@Override
	public int deleteFamilyMembers(FamilyMembers familyMembers){
		return familyMembersMapper.deleteFamilyMembers( familyMembers );
	}
	
	@Override
	public List<FamilyInfo> findFamilyInfo(FamilyInfoReqData familyInfoReqData){
		return familyMembersMapper.findFamilyInfo( familyInfoReqData );
	}
}
