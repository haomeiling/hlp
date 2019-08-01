package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: TODO
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Data
public class GetClinicTypeInfoDataListItemResDates {
    private int clinicTypeId;
    private String queueTypeCode;
    private String queueTypeName;
    private byte specifiedDoctor;
    private byte individualPrice;
    private BigDecimal registrationPrice;
    private BigDecimal emergencyAddPrice;
}