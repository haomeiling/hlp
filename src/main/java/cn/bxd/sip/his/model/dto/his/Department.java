package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-04-27
 */
@Data
public class Department {
    private String departmentId;//	平台科室ID
    private String departmentorganId;//	科室编号
    private String name;//	科室名称
    private Integer numDoctors;//	医生数量
    private String ddesc;//	科室介绍
    private String departAddr;//	科室地址

}
