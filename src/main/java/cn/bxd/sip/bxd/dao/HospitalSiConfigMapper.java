package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author：weishaoxiang
 * @version：V1.0
 * @创建时间：2018年7月23日 下午16:01:30 <BR/>
 *                  <P/>
 * @类说明：医院社保接口配置
 */
@Mapper
public interface HospitalSiConfigMapper {

	/**
	 * 查询医院社保接口配置表
	 * 
	 * @param hospitalId
	 *            医院id
	 * @param medicareType
	 *            医疗类型 诊疗类型 1：门诊 2：住院 3：体检4：其他
	 * @return
	 */
	HospitalSiConfig selectTRhipHospitalSiConfig(@Param("hospitalId")String hospitalId, @Param(value = "medicareType")String medicareType);

}
