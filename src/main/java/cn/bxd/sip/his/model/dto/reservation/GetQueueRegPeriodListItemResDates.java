package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * Description: TODO
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Data
public class GetQueueRegPeriodListItemResDates {
    private String periodStart;
    private int periodNo;
    private String periodName;
    private int periodEndInt;
    private String periodEnd;
    private int periodStartInt;
    private int totalNumber;
    private int remainNumber;
    private String timesTypeNo;
    private String timesTypeNoName; //Lisheng 2018/9/28
}