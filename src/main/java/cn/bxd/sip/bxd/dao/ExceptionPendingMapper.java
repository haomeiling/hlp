package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.entity.ExceptionPending;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ExceptionPendingMapper {
    int deleteByPrimaryKey(Long excSeqId);

    int insert(ExceptionPending record);

    int insertSelective(ExceptionPending record);
}