package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/7/23
 * @description:
 */
@Data
public class PreSettlementInput {
    private Long operCode;
    private Long hosId;
    private String empi;
    private Integer clinicType;
    private Integer status;
    private String clinicNo;
    private String name;
    private String idNumber;
    private String socialNo;
    private Long orderId;

    private String timestypeNo;//时间段标识          lisheng 2019/2/21
    private String timestypeNoName;//时间段显示名称  lisheng 2019/2/21
    private Short periodNo;//上下午 1 上午  诊疗类型是挂号必填 lisheng 2019/2/21
    private String deptCode;//科室编码
    private String doctorCode;//医生编码
    private Integer orderDay;//取号日期
}
