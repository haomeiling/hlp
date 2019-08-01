package cn.bxd.sip.bxd.model.respond.inhos;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-26
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class LeaveHosPayRespond extends BaseRespond {
    String clearingNO;
    String patientName;
    Integer patientSex;
    String inHosDate;
    Integer inHosState;
    String bedNo;
    String departmentorganId;
    String departmentName;
    String reminder;
}
