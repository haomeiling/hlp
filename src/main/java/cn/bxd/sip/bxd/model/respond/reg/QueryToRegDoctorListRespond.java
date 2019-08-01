package cn.bxd.sip.bxd.model.respond.reg;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : haomeiling
 * @create 2018-12-20
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryToRegDoctorListRespond extends BaseRespond {
    List<RegDoctor> regDoctor;
}
