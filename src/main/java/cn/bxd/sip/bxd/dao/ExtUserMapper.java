package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.entity.ExtUser;
import cn.bxd.sip.bxd.model.entity.ExtUserKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ExtUserMapper {
    /**
     * 通过查询条件搜索外部账号
     *
     * @param record
     * @return
     */
    List<ExtUser> getExtUserListByCondition(ExtUser record);

    int deleteByPrimaryKey(ExtUserKey key);

    int insert(ExtUser record);

    int insertSelective(ExtUser record);

    ExtUser selectByPrimaryKey(ExtUserKey key);

    int updateByPrimaryKeySelective(ExtUser record);

    int updateByPrimaryKey(ExtUser record);
}