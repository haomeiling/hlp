package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.HospitalEmpMapper;
import cn.bxd.sip.bxd.model.entity.HospitalEMP;
import cn.bxd.sip.bxd.service.IHospitalEMPService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-04-28
 * Time: 8:47
 */
public class HospitalEMPService implements IHospitalEMPService {

    @Autowired
    HospitalEmpMapper hospitalEmpMapper;

    @Override
    public List<HospitalEMP> selectByHospitalId(Integer hospitalId, Integer deptId) {
        return hospitalEmpMapper.selectByHospitalId(hospitalId,deptId);
    }

    @Override
    public List<HospitalEMP> selectByEMP(HospitalEMP record) {
        return hospitalEmpMapper.selectByEMP(record);
    }

    @Override
    public int insertSelective(HospitalEMP record) {
        return hospitalEmpMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(HospitalEMP record) {
        return hospitalEmpMapper.updateByPrimaryKeySelective(record);
    }
}
