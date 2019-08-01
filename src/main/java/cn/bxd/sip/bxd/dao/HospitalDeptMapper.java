package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.entity.HospitalDept;
import cn.bxd.sip.bxd.model.entity.HospitalDeptKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @author: chenchuanchuan
 * @date: 2018年11月29日 上午10:05:57
 */
@Mapper
public interface HospitalDeptMapper {


    /**
     * 通过主键获取科室详细信息
     *
     * @param key
     * @return
     */
    HospitalDept selectByPrimaryKey(HospitalDeptKey key);

    /**
     * 通过医院ID获取科室列表
     *
     * @param hospitalId 医院ID
     * @return 科室列表
     * @author haomeiling
     * @date 2018-12-26
     */
    List<HospitalDept> selectByHospitalId(Integer hospitalId);

    /**
     * 选择字段查询
     *
     * @param record
     * @return 科室列表
     * @author lisheng
     * @date 2019-4-27
     */
    List<HospitalDept> selectByHospitalDept(HospitalDept record);

    /**
     * 选择字段新增
     * @param record
     * @return
     * @author lisheng
     * @date 2019-4-27
     */
    int insertSelective(HospitalDept record);

    /**
     * 选着字段修改
     * @param record
     * @return
     * @author lisheng
     * @date 2019-4-27
     */
    int updateByPrimaryKeySelective(HospitalDept record);

}