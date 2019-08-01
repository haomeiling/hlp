package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description: 查询号源 queryToRegDoctorList
 * Package: com.bxd.sip.reservation.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Data
public class QueryToRegDoctorListReq {
    /** 用户名 */
    private String synUserName;
    /** 效验码 */
    private String synKey;
    /** 起始时间YYYY-MM-dd */
    private String startDate;
    /** 结束时间 */
    private String endDate;
}