package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.HospitalDeptMapper;
import cn.bxd.sip.bxd.model.entity.HospitalDept;
import cn.bxd.sip.bxd.service.IHospitalDeptService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Description:
 * User: LiSheng
 * Date: 2019-04-27
 */
public class HospitalDeptService implements IHospitalDeptService {

    @Autowired
    HospitalDeptMapper hospitalDeptMapper;

    @Override
    public List<HospitalDept> selectByHospitalId(Integer hospitalId) {
        return hospitalDeptMapper.selectByHospitalId(hospitalId);
    }

    @Override
    public List<HospitalDept> selectByHospitalDept(HospitalDept record) {
        return  hospitalDeptMapper.selectByHospitalDept( record);
    }

    @Override
    public int insertSelective(HospitalDept record) {
        return hospitalDeptMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(HospitalDept record) {
        return hospitalDeptMapper.updateByPrimaryKeySelective(record);
    }
}
