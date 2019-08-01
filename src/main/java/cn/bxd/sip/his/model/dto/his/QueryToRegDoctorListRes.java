package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description: 查询号源
 * Package: com.bxd.sip.reservation.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryToRegDoctorListRes extends BaseRes {
    private List<QueryToRegDoctorListItemRes> regDoctor;
}