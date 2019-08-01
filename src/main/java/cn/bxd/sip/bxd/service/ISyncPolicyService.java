package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.policy.Policy;
import cn.bxd.sip.bxd.model.policy.PolicyDetailReqData;
import cn.bxd.sip.bxd.model.policy.PolicyDetailResData;
import cn.bxd.sip.bxd.model.policy.PolicyListReqData;

import java.util.List;

/**
 * @author：weishaoxiang @version：V1.0
 * @创建时间：2018年8月10日 下午10:49:30 <BR/>
 *                  <P/>
 * @类说明：社保政策
 */
public interface ISyncPolicyService {

	/**
	 * 查询政策信息
	 * 
	 * @param policyListReqData
	 * @return
	 */
	public List<Policy> findPolicyList(PolicyListReqData policyListReqData);

	/**
	 * 查询政策明细
	 * 
	 * @param policyDetailReqData
	 * @return
	 */
	public PolicyDetailResData findPolicyDetail(PolicyDetailReqData policyDetailReqData);

}
