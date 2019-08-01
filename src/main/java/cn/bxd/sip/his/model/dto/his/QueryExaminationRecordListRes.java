package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-12-14
 * Time: 17:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryExaminationRecordListRes extends BaseRes {
    private List<QueryExaminationRecordListHisReportInfo> hisReport;

}
