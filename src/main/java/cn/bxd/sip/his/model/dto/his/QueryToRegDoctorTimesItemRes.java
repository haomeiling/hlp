package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: com.bxd.sip.reservation.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-09
 */
@Data
public class QueryToRegDoctorTimesItemRes {
    /** 时间段标识 */
    private String timestypeNo;
    /** 时间段显示名称 */
    private String timestypeNoName;
    /** 医生编号 */
    private String organdoctorId;
    /** 科室编号 */
    private String departmentorganId;
    /** 号源日期 */
    private String sourceDate;
    /** 号源数 */
    private String sourceDateNum;
    /** 1上午，2下午 ，3晚上 */
    private byte sourceTimeType;
}