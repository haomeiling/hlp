package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.SiMedicareRecordsMapper;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.SiMedicareRecords;
import cn.bxd.sip.bxd.model.entity.SiMedicareRecordsWithBLOBs;
import cn.bxd.sip.bxd.service.ISeqService;
import cn.bxd.sip.bxd.service.ISiMedicareRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:tangliang
 * @date:2018/7/26
 * @description:
 */
@Service
public class SiMedicareRecordsService implements ISiMedicareRecordsService {

	@Autowired
	private SiMedicareRecordsMapper tSiMedicareRecordsMapper;
	@Autowired
	private ISeqService seqService;

	@Override
	public SiMedicareRecordsWithBLOBs selectByPrimaryKey(Integer recordid) {
		return tSiMedicareRecordsMapper.selectByPrimaryKey(recordid);
	}

	/**
	 * 查询是否已存在预结算记录
	 * 
	 * @param hospitalId // 医院ID
	 * @param feeIds     诊疗编号(诊疗记录标志)
	 * @return PayInfo
	 */
	@Override
	public SiMedicareRecords selectMedicareRecords(PayInfo payInfo) {
		return tSiMedicareRecordsMapper.selectMedicareRecords(payInfo);
	}

	public PayInfo selectMedicareRecordsCancelPayment(PayInfo payInfo) {
		return tSiMedicareRecordsMapper.selectMedicareRecordsCancelPayment(payInfo);
	}
	
	/**
	 * 医保预结算保存
	 * 
	 * @param payInfo 医保结算信息
	 * @return
	 */
	@Override
	public int saveMedicareRecords(PayInfo payInfo) {
		int medicareRecordId = seqService.getMedicareRecordId();
		payInfo.setRecordId(medicareRecordId);

		return tSiMedicareRecordsMapper.saveMedicareRecords(payInfo);
	}

	/**
	 * 医保结算成功，更新结算状态
	 * 
	 * @param payInfo 医保结算信息
	 * @return
	 */
	@Override
	public int updateMedicareRecords(PayInfo payInfo) {
		return tSiMedicareRecordsMapper.updateMedicareRecords(payInfo);
	}

	/**
	 * 查询单据号是否已使用
	 * 
	 * @param payInfo
	 * @return
	 */
	@Override
	public String selectSiPoNo(PayInfo payInfo) {
		return tSiMedicareRecordsMapper.selectSiPoNo(payInfo);
	}

}
