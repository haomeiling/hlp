package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description: 查询医生号源时间段 queryToRegDoctorTimes
 * Package: com.bxd.sip.reservation.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-09
 */
@Data
public class QueryToRegDoctorTimesReq {
    /** 用户名 */
    private String synUserName;
    /** 效验码 */
    private String synKey;
    /** 号源日期YYYY-MM-dd */
    private String sourceDate;
    /** 医生编号 */
    private String organdoctorId;
    /** 科室编号 */
    private String departmentorganId;
    /** 1上午，2下午 ，3晚上，其他全天 */
    private byte sourceTimeType;
}