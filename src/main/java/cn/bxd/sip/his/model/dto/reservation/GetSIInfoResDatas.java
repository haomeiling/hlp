package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetSIInfoResDatas extends BaseResDates {
//    private String msg;
    private String UserNo;
    private String Name;
    private String IDNumber;
    private String SocialNo;
    private String MobileNo;
    private String RealTime;
    private String CardInfo;
    private String Area;
}