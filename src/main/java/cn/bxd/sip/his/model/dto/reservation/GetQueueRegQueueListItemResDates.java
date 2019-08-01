package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import java.util.List;

/**
 * Description: TODO
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Data
public class GetQueueRegQueueListItemResDates {
    private String sectionId;
    private String queueId;
    private String queueName;
    private String sectionName;
    private String registerPrice;
    private String emergencyAddPrice;
    private List<GetQueueRegPeriodListItemResDates> periodList;
}