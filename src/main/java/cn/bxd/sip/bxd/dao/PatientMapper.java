package cn.bxd.sip.bxd.dao;


import cn.bxd.sip.bxd.model.entity.Patient;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatientMapper {
    int deleteByPrimaryKey(Integer empiId);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Integer empiId);

    /**
     * 根据条件查询主索引记录
     *
     * @param patient 查询条件
     * @return 主索引列表
     */
    List<Patient> selectByConditions(Patient patient);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);

    /**
     * 通过医院ID获取就诊人列表
     *
     * @param hosId
     * @return
     */
    List<Patient> getPatientListByHosId(int hosId);
}