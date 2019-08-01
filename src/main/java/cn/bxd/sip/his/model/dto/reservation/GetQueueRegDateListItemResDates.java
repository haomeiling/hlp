package cn.bxd.sip.his.model.dto.reservation;

import java.util.List;

/**
 * Description: TODO
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@lombok.Data
public class GetQueueRegDateListItemResDates {
    private int dateInt;
    private int totalNumber;
    private int remainNumber;
    private String date;
    private String week;
    private List<GetQueueRegQueueListItemResDates> queueList;
}