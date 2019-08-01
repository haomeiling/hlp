package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description: 查询医生号源时间段
 * Package: com.bxd.sip.reservation.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryToRegDoctorTimesRes extends BaseRes {
    private List<QueryToRegDoctorTimesItemRes> regDoctorTimes;
}