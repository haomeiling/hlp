package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetBillListReqDatas extends BaseReqDates {

    private String empi;//患者主索引
    private int clinicType;//诊疗类型     1：门诊    2：住院  3：体检    4：其他
    private String clinicNo;//诊疗编号(诊疗记录标志)
    private Long startDate = 0L;//开始时间 20160817
    private Long endDate = 0L;//结束时间 20160827

    /*
     * billTypeId :费用单类型 。
     * 当clinic type 为1(门诊)billTypeId 还不分；
     * 当clinic type 是2(住院)的时候，区分 billTypeId(1: 住院费用清单，2：住院押金)
     * */
    private int billTypeId;

    private int status = 0;//单据状态  0 全部，1：未缴费 2：已缴费 3： 已作废

    private int options;//选项，0 按单据号， 1- 按日期汇总，2 安诊疗记录汇总
}