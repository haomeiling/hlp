package cn.bxd.sip.si.service;

import cn.bxd.sip.bxd.model.dto.ChemistPayInfo;
import cn.bxd.sip.bxd.model.dto.ChemistRecipeInfo;
import cn.bxd.sip.bxd.model.dto.ChemistUserInfo;

import java.util.List;

/**
 * @author：weishaoxiang @version：V1.0
 * @创建时间：2018年08月07日 下午17:25:30 <BR/>
 *                   <P/>
 * @类说明：药店购药
 */
public interface ChemistShopService{
	
	/**
	 * 药店-获取人员信息（310201）
	 */
	public ChemistUserInfo selectUser(String AppType,String OpenID);
	
	/**
	 * 药店-处方明细查询（310202）
	 */
	public List<ChemistRecipeInfo> selectRecipe(String ChemistShopNo,String MedicareType,String BusinessNo,String PayDate);
	
	/**
	 * 药店-预结算（310203）
	 */
	public ChemistPayInfo payBefore(ChemistPayInfo chemistPayInfo);
	
	/**
	 * 药店-结算（310204）
	 */
	public ChemistPayInfo payment(ChemistPayInfo chemistPayInfo);
	
	/**
	 * 药店-结算撤销（310205）
	 */
	public ChemistPayInfo cancelPayment(ChemistPayInfo chemistPayInfo);
	
	/**
	 * 药店-结算查询（310206）
	 */
	public ChemistPayInfo selectPayment(ChemistPayInfo chemistPayInfo);
	
	/**
	 * 药店-付款通知（310207）
	 */
	public ChemistPayInfo PaymentAdvice(ChemistPayInfo chemistPayInfo);
}
