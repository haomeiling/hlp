package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.policy.Policy;
import cn.bxd.sip.bxd.model.policy.PolicyDetailReqData;
import cn.bxd.sip.bxd.model.policy.PolicyDetailResData;
import cn.bxd.sip.bxd.model.policy.PolicyListReqData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author：weishaoxiang @version：V1.0
 * @创建时间：2018年5月11日 下午16:57:48 <BR/>
 *                  <P/>
 * @类说明：社保政策查询
 */
@Mapper
public interface PolicyMapper {

	/**
	 * 社保政策查询(110110)
	 * 
	 * @param policyListReqData
	 * @return
	 */
	List<Policy> findPolicyList(PolicyListReqData policyListReqData);

	/**
	 * 政策详细查询(110111)
	 * 
	 * @param policyDetailReqData
	 * @return
	 */
	PolicyDetailResData findPolicyDetail(PolicyDetailReqData policyDetailReqData);

}
