package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.entity.HospitalDept;

import java.util.List;

/**
 * 根据医院id查询科室
 * Description:
 * User: LiSheng
 * Date: 2019-04-27
 */
public interface IHospitalDeptService {

    List<HospitalDept> selectByHospitalId(Integer hospitalId);

    List<HospitalDept> selectByHospitalDept(HospitalDept record);

    int insertSelective(HospitalDept record);

    int updateByPrimaryKeySelective(HospitalDept record);


}
