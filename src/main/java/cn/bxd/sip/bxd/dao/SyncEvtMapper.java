package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.dto.HSyncEvn;
import cn.bxd.sip.bxd.model.entity.SyncEvt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SyncEvtMapper {
    int deleteByPrimaryKey(Long syncSeqId);

    int insert(SyncEvt record);

    int insertSelective(SyncEvt record);

    SyncEvt selectByPrimaryKey(Long syncSeqId);

    int updateByPrimaryKeySelective(SyncEvt record);

    int updateByPrimaryKey(SyncEvt record);

    int updateStatusByPrimaryKey(SyncEvt record);

    List<HSyncEvn> getByListId(int[] syncSeqIdList);

    /**
     * 根据入参查询异步通知消息
     * @param record 入参
     * @return 出参列表
     */
    List<SyncEvt> selectByCondition(SyncEvt record);
}