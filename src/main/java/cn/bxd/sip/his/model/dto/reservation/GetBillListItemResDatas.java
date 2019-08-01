package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-18
 */
@Data
public class GetBillListItemResDatas {
    private Long accDate;  //账务日期（YYYYMMDD）
    private String billNo;  //单据单号
    private Long billDate;  //单据日期（YYYYMMDD）
    private String billType;  //单据类型
    private String doctorName;  //开单医生
    private String deptName;  //科室名称
    private String bedNo;  //床位号
    private String clinicalNo;  //诊疗编号
    private Short clinicalStatus;  //诊疗状态   1：未结束   2：已结束
    private BigDecimal amount;  //费用金额
    private Double depositAmount;  //预交金额
    private String providerCode;  //交易渠道代码
    private int billStatus;  //单据状态   1：未缴费   2：已缴费   3：已作废
    private String medicareInfo;
    private String diagInfo;
    private String medicareType;
}