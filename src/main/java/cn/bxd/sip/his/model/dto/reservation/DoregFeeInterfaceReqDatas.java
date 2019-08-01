package cn.bxd.sip.his.model.dto.reservation;

import cn.bxd.sip.his.model.dto.his.BaseData;
import lombok.Data;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-02-24
 * Time: 9:38
 */
@Data
public class DoregFeeInterfaceReqDatas extends BaseData {
    private String hosId;//医院编号
    private String organdoctorId;//	医生编号
    private String departmentorganId;//	科室编号
    private String patientNo;//	患者编号
    private String sourceDate;//	取号日期（号源日期）
    private String timestypeNo;//	时间段标识 0：表示无时间段
    private String sourceTimeType;//	1：上午，2：下午， 3：晚上
    private String RegType;//	挂号类型 1：挂号， 2：取号
    private String queueId;//队列ID

}
