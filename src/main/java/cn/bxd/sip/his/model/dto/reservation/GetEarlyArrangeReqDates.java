package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Description: 查询最早排班日期 操作码2020
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetEarlyArrangeReqDates extends BaseReqDates{
    private Date startDate;
    private int queueTypeID;
    private int deptTypeId;
    private String deptCode;
    private int dateCount;
}