package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.PolicyMapper;
import cn.bxd.sip.bxd.model.policy.Policy;
import cn.bxd.sip.bxd.model.policy.PolicyDetailReqData;
import cn.bxd.sip.bxd.model.policy.PolicyDetailResData;
import cn.bxd.sip.bxd.model.policy.PolicyListReqData;
import cn.bxd.sip.bxd.service.ISyncPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：weishaoxiang @version：V1.0
 * @创建时间：2018年8月10日 下午10:49:30 <BR/>
 *                  <P/>
 * @类说明：社保政策
 */
@Service
public class SyncPolicyService implements ISyncPolicyService {

	@Autowired
	PolicyMapper policyMapper;// 本地数据库

	@Override
	public List<Policy> findPolicyList(PolicyListReqData policyListReqData) {
		return policyMapper.findPolicyList(policyListReqData);
	}

	@Override
	public PolicyDetailResData findPolicyDetail(PolicyDetailReqData policyDetailReqData){
		return policyMapper.findPolicyDetail( policyDetailReqData );
	}
	
}
