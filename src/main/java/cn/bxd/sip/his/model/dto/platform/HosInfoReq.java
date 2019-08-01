package cn.bxd.sip.his.model.dto.platform;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.platform
 *
 * @author Leeves
 * @version 1.0.0  2018-08-23
 */
@Data
public class HosInfoReq {

    @Min(value = 0, message = "医院ID有误")
    int hosId;

}