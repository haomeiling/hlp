package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Lisheng
 * @version 1.0.0  2018-09-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetHospitalizationRecordsResDatas extends BaseResDates {

//    private String msg;
    private GetHospitalizationRecordsItemResDatas data;

}