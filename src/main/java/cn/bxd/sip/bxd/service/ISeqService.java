package cn.bxd.sip.bxd.service;

/**
 * Description：表ID存储过程调用
 *
 * @author liangshangsong
 * <p/>
 * 2015年8月13日 下午2:57:00
 */
public interface ISeqService {
    /**
     * 获取订单下一主键
     *
     * @return
     */
    Long getOrderId();

    /**
     * 获取平台账户信息表下一主键
     *
     * @return
     */
    Integer getUserId();

    /**
     * 获取支付交易记录下一主键
     *
     * @return
     */
    Long getTransId();

    /**
     * 获取异步事物处理主键
     *
     * @return
     */
    long getSyncSeqId();

    /**
     * 获取订单状态表ID
     *
     * @return
     */
    long getStatusId();

    /**
     * 获取主键ID
     *
     * @return
     */
    long getKeyId(String tableName);

    /**
     * 获取业务流水号ID
     */
    long getBusinessNo();

    /**
     * 获取社保ID
     */

    int getMedicareRecordId();

    /**
     * 获取患者主索引ID
     *
     * @return
     */
    long getPatientEMPIId();
}
