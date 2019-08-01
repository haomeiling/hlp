package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.entity.HospitalEMP;

import java.util.List;

/**
 * 根据医院id查询科室
 * Description:
 * User: LiSheng
 * Date: 2019-04-27
 */
public interface IHospitalEMPService {

    List<HospitalEMP> selectByHospitalId(Integer hospitalId, Integer deptId);

    List<HospitalEMP> selectByEMP(HospitalEMP record);

    int insertSelective(HospitalEMP record);

    int updateByPrimaryKeySelective(HospitalEMP record);


}
