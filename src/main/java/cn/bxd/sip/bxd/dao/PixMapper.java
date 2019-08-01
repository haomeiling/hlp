package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.entity.Pix;
import cn.bxd.sip.bxd.model.entity.PixKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PixMapper {

    /**
     * 有条件获取交叉主索引记录
     *
     * @param record
     * @return
     */
    List<Pix> selectPixByConditions(Pix record);


    int deleteByPrimaryKey(PixKey key);

    int insert(Pix record);

    int insertSelective(Pix record);

    Pix selectByPrimaryKey(PixKey key);

    int updateByPrimaryKeySelective(Pix record);

    int updateByPrimaryKey(Pix record);
}