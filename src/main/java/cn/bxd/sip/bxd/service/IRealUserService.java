package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.dto.ChemistUserInfo;
import cn.bxd.sip.bxd.model.entity.RealUser;
import cn.bxd.sip.bxd.model.entity.SiRealUser;

/**
 * @author：weishaoxiang @version：V1.0
 *                      <P/>
 * @类说明：查询认证信息
 */
public interface IRealUserService {
	
	public SiRealUser findRealUser(SiRealUser realUser);
	
	public SiRealUser findRealUserByUserId(String userId);
	
	public SiRealUser findRealUserByOpenId(String AppType,String OpenId);
}
