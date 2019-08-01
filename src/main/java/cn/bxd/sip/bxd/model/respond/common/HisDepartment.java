package cn.bxd.sip.bxd.model.respond.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : cRyann
 * @create 2018-09-13
 **/
@Data
public class HisDepartment implements Serializable {
    String departmentId;
    String organId;
    String departmentorganId;
    String name;
    String ddesc;
    String departAddr;
    String numDoctors;
    boolean numDoctorsSpecified;
}
