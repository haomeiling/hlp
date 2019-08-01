package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.entity.SiUser;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
@Mapper
public interface SiUserMapper {
    int deleteByPrimaryKey(BigDecimal userId);

    int insert(SiUser record);

    int insertSelective(SiUser record);

    SiUser selectByPrimaryKey(BigDecimal userId);

    int updateByPrimaryKeySelective(SiUser record);

    int updateByPrimaryKey(SiUser record);
}