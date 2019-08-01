package cn.bxd.sip.his.model.dto.reservation;

import cn.bxd.sip.his.model.dto.his.BaseData;
import lombok.Data;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-01-16
 * Time: 9:38
 */
@Data
public class DoTaketheNoReq extends BaseData {
    private String sourceMark;//号源编号
    private String patientNo;//患者编号
    private String sourceDate;//号源日期
    private String departmentorganId;//科室编号
    private String payType;//1：银联，2：支付宝，3：现场支付，4：医保账户，5：微信，6：建行，7：中行，8：招行
    private String payRecord;//	支付交易流水号
    private String payMoney;//	实际支付金额



}
