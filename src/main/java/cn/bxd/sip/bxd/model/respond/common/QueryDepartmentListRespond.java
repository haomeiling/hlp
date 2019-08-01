package cn.bxd.sip.bxd.model.respond.common;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : cRyann
 * @create 2018-09-07
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryDepartmentListRespond extends BaseRespond {
    List<HisDepartment> hisDepartment = new ArrayList<>();
    List<HisDepartment> department = new ArrayList<>();
}
