package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.SiMedicareRecords;
import cn.bxd.sip.bxd.model.entity.SiMedicareRecordsWithBLOBs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SiMedicareRecordsMapper {
    SiMedicareRecordsWithBLOBs selectByPrimaryKey(Integer recordid);
	/**
	 * 查询是否已存在预结算记录
	 * 
	 * @param hospitalId
	 *            // 医院ID
	 * @param feeIds
	 *            诊疗编号(诊疗记录标志)
	 * @return PayInfo
	 */
    SiMedicareRecords selectMedicareRecords(PayInfo payInfo);
    
    PayInfo selectMedicareRecordsCancelPayment(PayInfo payInfo);

	/**
	 * 医保预结算保存
	 * 
	 * @param payInfo
	 *            医保结算信息
	 * @return
	 */
	int saveMedicareRecords(PayInfo payInfo);

	/**
	 * 医保结算成功，更新结算状态
	 * 
	 * @param payInfo
	 *            医保结算信息
	 * @return
	 */
	int updateMedicareRecords(PayInfo payInfo);

	/**
	 * 查询单据号是否已使用
	 * 
	 * @param payInfo
	 * @return
	 */
	String selectSiPoNo(PayInfo payInfo);
    
}