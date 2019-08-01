package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-16
 */
@Data
public class DoPayMedicareDataMapReq {
    private String cashMoney;
    private String medicareMoney;
    private String socialsecurityNO;
    private String medicareRecord;
    private String overMoney;
    private String medicareReturn;
    private String userNo;
    private String medicareType;
}