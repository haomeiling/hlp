package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPaymentRecordListRes extends BaseRes {
    private List<QueryPaymentRecordListItem> hiFee;
}