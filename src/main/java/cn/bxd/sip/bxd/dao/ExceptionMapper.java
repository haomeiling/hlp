package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.entity.Exception;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ExceptionMapper {
    int deleteByPrimaryKey(Long excSeqId);

    int insert(Exception record);

    int insertSelective(Exception record);

    Exception selectByPrimaryKey(Long excSeqId);

    int updateByPrimaryKeySelective(Exception record);

    int updateByPrimaryKey(Exception record);
}