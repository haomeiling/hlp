package cn.bxd.sip.bxd.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author：weishaoxiang
 * @version：V1.0
 * @类说明：访问权限控制
 */
@Mapper
public interface SysMapper {

	int checkIP(Map<String, Object> param);

	int checkBusiness(Map<String, Object> param);

	int saveBusinessLog(Map<String, Object> param);
}
