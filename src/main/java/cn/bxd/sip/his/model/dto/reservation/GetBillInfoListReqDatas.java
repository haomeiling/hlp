package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetBillInfoListReqDatas extends BaseReqDates {
    /** 患者院内编码 */
    private String empi;
    /** 诊疗类型     1：门诊    2：住院  3：体检    4：其他 */
    private int clinicType;
    private String clinicNo;
    private String billNo;
    private String billTypeID;
    /** 开始时间 20160817 */
    private String startDate;
    /** 结束时间 20160827 */
    private String endDate;
    /** 状态  0：全部    1：进行中   2：已结束 */
    private Integer status;

    /** 0,非医保 1医保  （为0时， 返回值medicareInfo、 diagInfo可为空）*/
    private int medicareType;
    /** 姓名 当medicareType1时需要 */
    private String name;
    /** 身份证号 当medicareType1时需要 */
    private String idNumber;
    /** 社保卡号 当medicareType1时需要 */
    private String socialNo;
    /** 订单ID 当medicareType1时需要 */
    private Long orderId;

    private String  guidelinesInfo;//指引信息 lisheng 2019/1/28


    private String timestypeNo;//时间段标识          lisheng 2019/2/21
    private String timestypeNoName;//时间段显示名称  lisheng 2019/2/21
    private Short periodNo;//上下午 1 上午  诊疗类型是挂号必填 lisheng 2019/2/21
    private String deptCode;//科室编码
    private String doctorCode;//医生编码
    private Integer orderDay;//取号日期




}