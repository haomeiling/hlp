package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description: 查询患者住院信息
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Lisheng
 * @version 1.0.0  2018-09-026
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryHospitalizationRecordsRes extends BaseRes {

    private List<QueryPatientInHosInfo> inHosList;
    private List<QueryPatientInHosInfo> inHosInfo;
}