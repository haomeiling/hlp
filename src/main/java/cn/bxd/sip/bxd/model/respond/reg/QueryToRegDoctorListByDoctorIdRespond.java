package cn.bxd.sip.bxd.model.respond.reg;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : cRyann
 * @create 2018-09-17
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryToRegDoctorListByDoctorIdRespond extends BaseRespond {
    List<RegDoctor> regDoctor;
}
