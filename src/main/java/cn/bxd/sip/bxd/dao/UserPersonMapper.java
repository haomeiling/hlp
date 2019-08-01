package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.dto.user.UserPersonBiz;
import cn.bxd.sip.bxd.model.entity.UserPerson;
import cn.bxd.sip.bxd.model.entity.UserPersonKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Description：平台账户人员信息Mapper
 *
 * @author liangshangsong
 *         <p/>
 *         2015年7月27日 上午11:55:27
 */
@Mapper
public interface UserPersonMapper {

    /**
     * 根据Key获取人员详细信息
     *
     * @param record
     * @return
     */
    List<UserPerson> selectByConditions(UserPerson record);


    /**
     * 获取对应医院下的就诊人
     *
     * @param map
     * @return
     */
    List<UserPersonBiz> selectByMapConditions(Map<String, Object> map);

    /**
     * 删除
     *
     * @param key
     * @return
     */
    int deleteByPrimaryKey(UserPersonKey key);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(UserPerson record);

    /**
     * 根据Key获取人员详细信息
     *
     * @param key
     * @return
     */
    UserPerson selectByPrimaryKey(UserPersonKey key);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserPerson record);
}