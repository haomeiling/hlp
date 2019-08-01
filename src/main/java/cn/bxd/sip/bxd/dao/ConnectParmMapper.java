package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.entity.ConnectParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConnectParmMapper {
    int deleteByPrimaryKey(Integer hospitalId);

    int insert(ConnectParm record);

    int insertSelective(ConnectParm record);

    ConnectParm selectByPrimaryKey(Integer hospitalId);

    int updateByPrimaryKeySelective(ConnectParm record);

    int updateByPrimaryKey(ConnectParm record);

    List<ConnectParm> selectAllConnectParm();
}