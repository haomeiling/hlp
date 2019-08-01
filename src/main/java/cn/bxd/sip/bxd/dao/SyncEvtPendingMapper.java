package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.entity.SyncEvtPending;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SyncEvtPendingMapper {
    int deleteByPrimaryKey(Long syncSeqId);

    int insert(SyncEvtPending record);

    int insertSelective(SyncEvtPending record);

    SyncEvtPending selectByPrimaryKey(Long syncSeqId);

    int updateByPrimaryKeySelective(SyncEvtPending record);

    int updateByPrimaryKey(SyncEvtPending record);

    int updateFlagingByPrimaryKey(Long syncSeqId);

    Object getPending(Map map);
}