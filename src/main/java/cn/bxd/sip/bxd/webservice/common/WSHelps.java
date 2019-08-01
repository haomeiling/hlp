package cn.bxd.sip.bxd.webservice.common;

import cn.bxd.sip.bxd.var.ReservationVar;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-12-25
 * Time: 18:10
 */
@Component
public class WSHelps {
    /**
     * 终端性别转换
     *
     * @param genderCode 性别编码
     * @return 2 男  3女 9 未知性别
     */
    public static short getGender(String genderCode) {
        short genderId;
        switch (genderCode) {
            case ReservationVar.Gender.MAN_CH:
                genderId = ReservationVar.Gender.MAN;
                break;
            case ReservationVar.Gender.WOMAN_CH:
                genderId = ReservationVar.Gender.WOMAN;
                break;
            default:
                genderId = ReservationVar.Gender.UNKNOW;
        }

        return genderId;
    }

}
