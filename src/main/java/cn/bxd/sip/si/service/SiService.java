package cn.bxd.sip.si.service;

import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;

/**
 * @author：weishaoxiang @version：V1.0
 *                      <P/>
 * @类说明：社保结算
 */
public interface SiService {
	
	/**
	 * 读社保卡信息 2100
	 * @param hospitalSiConfig
	 *            医院开通社保接口配置
	 * @param cardInfo
	 *            社保卡信息串
	 * @return
	 */
	public PayInfo readCardInfo(HospitalSiConfig hospitalSiConfig, String cardInfo );
	
	/**
	 * 费用预结算 2420
	 * @param hospitalSiConfig
	 *            医院开通社保接口配置
	 * @param feeIds
	 *            诊疗编号(诊疗记录标志)
	 * @param name
	 *            姓名
	 * @param idNumber
	 *            身份证号
	 * @param socialNo
	 *            社保卡号
	 * @param hospitalInfo
	 *            医院串
	 * @param orderId
	 *            订单ID
	 * @param  medicareMess
	 *            社保返回卡串信息
	 * @return
	 */
	public PayInfo payBefore(HospitalSiConfig hospitalSiConfig, String feeIds, String name, String idNumber, String socialNo, String hospitalInfo, Long orderId, String medicareMess );
	
	/**
	 * 费用结算 2410
	 * @param hospitalSiConfig
	 *            医院开通社保接口配置
	 * @param recordId
	 *            结算记录ID
	 * @param feeIds
	 *            诊疗编号(诊疗记录标志)
	 * @param poNo
	 *            订单号
	 * @param name
	 *            姓名
	 * @param idNumber
	 *            身份证号
	 * @param socialNo
	 *            社保卡号
	 * @param inputData
	 *            结算交易串
	 * @param orderId
	 *            订单ID
	 * @return
	 */
	public PayInfo payment(HospitalSiConfig hospitalSiConfig, int recordId, String feeIds, String poNo, String name, String idNumber, String socialNo, String inputData, Long orderId );
	
	/**
	 * 费用结算撤销 2430
	 * @param hospitalSiConfig
	 *            医院开通社保接口配置
	 * @param recordId
	 *            结算记录ID
	 * @param feeIds
	 *            诊疗编号(诊疗记录标志)
	 * @param poNo
	 *            订单号
	 * @param name
	 *            姓名
	 * @param idNumber
	 *            身份证号
	 * @param socialNo
	 *            社保卡
	 * @return
	 */
	public PayInfo cancelPayment(HospitalSiConfig hospitalSiConfig, int recordId, String feeIds, String poNo, String name, String idNumber, String socialNo, Long orderId );
	
	/**
	 * 结算信息查询(发票补打)(1101)
	 * @param hospitalSiConfig
	 *            医院开通社保接口配置
	 * @param feeIds
	 *            诊疗编号(诊疗记录标志)
	 * @param poNo
	 *            订单号
	 * @return 社保结算数据（同费用结算）
	 */
	public PayInfo selectPayment(HospitalSiConfig hospitalSiConfig, String feeIds, String poNo );
}
