package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description: 根据医生ID查询号源
 * Package: com.bxd.sip.reservation.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryToRegDoctorListByDoctorIdRes extends BaseRes {
    private List<QueryToRegDoctorListItemRes> regDoctor;
}