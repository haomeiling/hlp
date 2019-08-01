package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description: 根据医生ID查询号源
 * Package: com.bxd.sip.reservation.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-10
 */
@Data
public class QueryToRegDoctorListByDoctorIdReq {

    private String synUserName;
    private String synKey;
    private String startDate;
    private String endDate;
    private String organdoctorId;

}