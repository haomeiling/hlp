package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-19
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDoctorByIdRes extends BaseRes {
    Doctor doctor;
}
