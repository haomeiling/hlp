package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.entity.HospitalEMP;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 医生
 * @author: haomeiling
 * @date: 2018年12月26日 上午10:05:57
 */
@Mapper
public interface HospitalEmpMapper {

    /**
     * 通过医院ID获取医生列表
     *
     * @param hospitalId 医院ID
     * @return 医生列表
     * @author haomeiling
     * @date 2018-12-26
     */
    List<HospitalEMP> selectByHospitalId(@Param("hospitalId") Integer hospitalId, @Param("deptId")Integer deptId);

    List<HospitalEMP> selectByEMP(HospitalEMP record);

    int insertSelective(HospitalEMP record);

    int updateByPrimaryKeySelective(HospitalEMP record);

}