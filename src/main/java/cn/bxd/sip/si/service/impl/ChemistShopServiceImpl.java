package cn.bxd.sip.si.service.impl;

import cn.bxd.sip.bxd.model.dto.ChemistPayInfo;
import cn.bxd.sip.bxd.model.dto.ChemistRecipeInfo;
import cn.bxd.sip.bxd.model.dto.ChemistUserInfo;
import cn.bxd.sip.bxd.model.entity.SiRealUser;
import cn.bxd.sip.bxd.service.IRealUserService;
import cn.bxd.sip.bxd.util.Signature;
import cn.bxd.sip.si.service.ChemistShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：weishaoxiang @version：V1.0
 * @创建时间：2018年08月07日 下午17:25:30 <BR/>
 *                   <P/>
 * @类说明：药店购药
 */
@Service
@Slf4j
public class ChemistShopServiceImpl implements ChemistShopService{
	
	@Value("${si.url}")
	private String url;
	@Autowired
    IRealUserService realUserService;
	
	@Override
	public ChemistUserInfo selectUser(String AppType,String OpenId){
		SiRealUser siRealUser = realUserService.findRealUserByOpenId(AppType, OpenId);
		if(siRealUser == null) {
			return null;
		}
		return new ChemistUserInfo(AppType, OpenId, siRealUser.getUserId(), siRealUser.getUserNo(), siRealUser.getPatientName()
				, siRealUser.getCardId(), siRealUser.getVisitcardNum(), siRealUser.getMobileNo(), siRealUser.getCardinfo(),
				siRealUser.getOverallArea());
	}
	
	@Override
	public List<ChemistRecipeInfo> selectRecipe(String ChemistShopNo,String MedicareType,String BusinessNo,String PayDate){
		
		
		
		return null;
	}
	
	@Override
	public ChemistPayInfo payBefore(ChemistPayInfo chemistPayInfo){
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ChemistPayInfo payment(ChemistPayInfo chemistPayInfo){
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ChemistPayInfo cancelPayment(ChemistPayInfo chemistPayInfo){
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ChemistPayInfo selectPayment(ChemistPayInfo chemistPayInfo){
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ChemistPayInfo PaymentAdvice(ChemistPayInfo chemistPayInfo){
		// TODO Auto-generated method stub
		return null;
	}
}
