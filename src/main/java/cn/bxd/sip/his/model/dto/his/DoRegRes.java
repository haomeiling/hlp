package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 预约挂号
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DoRegRes extends BaseRes {

    private String sourceMark;
    private String departmentNum;
    private String takeNo;
    private String medicalCode;
}