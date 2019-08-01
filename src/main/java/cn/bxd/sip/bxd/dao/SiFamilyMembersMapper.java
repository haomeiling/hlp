package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.entity.SiFamilyMembers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SiFamilyMembersMapper {
    int insert(SiFamilyMembers record);

    int insertSelective(SiFamilyMembers record);
}