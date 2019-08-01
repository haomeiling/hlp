package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.ProceduresMapper;
import cn.bxd.sip.bxd.service.ISeqService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：表ID存储过程调用实现
 *
 * @author liangshangsong
 * <p/>
 * 2015年8月13日 下午2:21:30
 */
@Service
public class SeqService implements ISeqService {

    @Autowired
    private ProceduresMapper proMapper;

    @Override
    public Long getOrderId() {
        Long orderId = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", "T_RHIP_ORDER");
        map.put("countParm", "1");
        map.put("creator", null);
        proMapper.selectSequenceByTableName(map);
        if (StringUtils.isNotEmpty((String) map.get("retVal"))) {
            orderId = Long.valueOf((String) map.get("retVal"));
        }
        return orderId;
    }

    @Override
    public Integer getUserId() {
        Integer userId = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", "T_RHIP_USER");
        map.put("countParm", "1");
        map.put("creator", null);
        proMapper.selectSequenceByTableName(map);
        if (StringUtils.isNotEmpty((String) map.get("retVal"))) {
            userId = Integer.valueOf((String) map.get("retVal"));
        }
        return userId;
    }

    @Override
    public Long getTransId() {
        Long TransId = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", "T_RHIP_TRANS");
        map.put("countParm", "1");
        map.put("creator", null);
        proMapper.selectSequenceByTableName(map);
        if (StringUtils.isNotEmpty((String) map.get("retVal"))) {
            TransId = Long.valueOf((String) map.get("retVal"));
        }
        return TransId;
    }


    public long getSyncSeqId() {
        Long syncEvtId = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", "T_RHIP_SYNC_EVT");
        map.put("countParm", "1");
        map.put("creator", null);
        proMapper.selectSequenceByTableName(map);
        if (StringUtils.isNotEmpty((String) map.get("retVal"))) {
            syncEvtId = Long.valueOf((String) map.get("retVal"));
        }
        return syncEvtId;
    }

    @Override
    public long getStatusId() {
        Long syncEvtId = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", "T_RHIP_ORDER_STATUS");
        map.put("countParm", "1");
        map.put("creator", null);
        proMapper.selectSequenceByTableName(map);
        if (StringUtils.isNotEmpty((String) map.get("retVal"))) {
            syncEvtId = Long.valueOf((String) map.get("retVal"));
        }
        return syncEvtId;
    }

    /**
     * 获取患者主索引ID
     * @return
     */
    @Override
    public long getPatientEMPIId() {
        Long personId = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", "T_MPI_PATIENT");
        map.put("countParm", "1");
        map.put("creator", null);
        proMapper.selectSequenceByTableName(map);
        if (StringUtils.isNotEmpty((String) map.get("retVal"))) {
            personId = Long.valueOf((String) map.get("retVal"));
        }
        return personId;
    }

    /**
     * 获取主键ID
     *
     * @param tableName
     * @return
     */
    @Override
    public long getKeyId(String tableName) {
        Long syncEvtId = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", tableName);
        map.put("countParm", "1");
        map.put("creator", null);
        proMapper.selectSequenceByTableName(map);
        if (StringUtils.isNotEmpty((String) map.get("retVal"))) {
            syncEvtId = Long.valueOf((String) map.get("retVal"));
        }
        return syncEvtId;
    }

    /**
     * 社保的业务流水号
     *
     * @return 业务号码
     */
    @Override
    public long getBusinessNo() {
        Long syncEvtId = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", "SI_BUSINESS_NO");
        map.put("countParm", "1");
        map.put("creator", null);
        proMapper.selectSequenceByTableName(map);
        if (StringUtils.isNotEmpty((String) map.get("retVal"))) {
            syncEvtId = Long.valueOf((String) map.get("retVal"));
        }
        return syncEvtId;
    }

    @Override
    public int getMedicareRecordId() {
        int recordId = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", "t_si_medicare_records");
        map.put("countParm", "1");
        map.put("creator", null);
        proMapper.selectSequenceByTableName(map);
        if (StringUtils.isNotEmpty((String) map.get("retVal"))) {
            recordId = Integer.parseInt((String) map.get("retVal"));
        }
        return recordId;
    }
}
