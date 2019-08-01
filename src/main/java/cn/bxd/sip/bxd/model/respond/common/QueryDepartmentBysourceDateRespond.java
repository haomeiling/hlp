package cn.bxd.sip.bxd.model.respond.common;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author : cRyann
 * @create 2018-09-06
 **/
@Data
public class QueryDepartmentBysourceDateRespond {
    ArrayList<HisDepartment> hisDepartment = new ArrayList<>();
}
