package cn.bxd.sip.his.model.dto.reservation;

import cn.bxd.sip.his.comm.HisConvertConst;
import lombok.Data;

/**
 * Description:
 * Package: com.bxd.sip.reservation.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Data
public class BaseErrResDates {
    private String code;
    private String success;
    private String msg;

    public BaseErrResDates() {
    }

    public BaseErrResDates(String msg) {
        code = "";
        success = HisConvertConst.Operation.OPERATION_FAIL;
        this.msg = msg;
    }

    public BaseErrResDates(String code, String msg) {
        this.code = code;
        success = HisConvertConst.Operation.OPERATION_FAIL;
        this.msg = msg;
    }
}