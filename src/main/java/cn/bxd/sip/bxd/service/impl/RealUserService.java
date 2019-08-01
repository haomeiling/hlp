package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.SiRealUserMapper;
import cn.bxd.sip.bxd.model.dto.ChemistUserInfo;
import cn.bxd.sip.bxd.model.entity.RealUser;
import cn.bxd.sip.bxd.model.entity.SiRealUser;
import cn.bxd.sip.bxd.service.IRealUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: Package: cn.bxd.sip.bxd.service.impl
 * @author Leeves
 * @version 1.0.0 2018-07-25
 */
@Service
public class RealUserService implements IRealUserService {
	
	@Autowired
	private SiRealUserMapper realUserMapper;// MySql数据源
	
	@Override
	public SiRealUser findRealUser(SiRealUser siRealUser){
		return realUserMapper.selectByConditions(siRealUser);
	}
	
	@Override
	public SiRealUser findRealUserByUserId(String userId){
		return realUserMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public SiRealUser findRealUserByOpenId(String AppType,String OpenId){
		return realUserMapper.findRealUserByOpenId(AppType, OpenId );
	}
}
