package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.entity.SiRealUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SiRealUserMapper {
    int deleteByPrimaryKey(Object userId);

    int insert(SiRealUser record);

    int insertSelective(SiRealUser record);

    SiRealUser selectByPrimaryKey(Object userId);

    int updateByPrimaryKeySelective(SiRealUser record);

    int updateByPrimaryKey(SiRealUser record);
    
	
	SiRealUser selectByConditions(SiRealUser realUser);
	
	/**
	 * 通过OpenId获取人员信息
	 * @param AppType
	 * @param OpenId
	 *            微信OpenId
	 * @return
	 */
	SiRealUser findRealUserByOpenId(String AppType,String OpenId);
}