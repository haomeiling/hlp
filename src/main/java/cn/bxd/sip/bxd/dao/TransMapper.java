package cn.bxd.sip.bxd.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.bxd.sip.bxd.model.entity.Trans;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TransMapper {

	 int insert(Trans trans);

	int deleteByPrimaryKey(@Param("transId") Long transId);
	
}
