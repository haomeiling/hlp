package cn.bxd.sip.bxd.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Description：存储过程调用
 *
 * @author liangshangsong
 *         <p/>
 *         2015年8月13日 下午2:10:17
 */
@Mapper
public interface ProceduresMapper {
    /**
     * 查询sequence
     *
     * @param map
     * @return
     */
    Object selectSequenceByTableName(Map map);

}
