package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 查询患者住院信息
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPatientInHosRes extends BaseRes {

    private QueryPatientInHosInfo inHosInfo;
}