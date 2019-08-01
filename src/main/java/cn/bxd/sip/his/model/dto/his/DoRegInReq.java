package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-08-03
 */
@Data
public class DoRegInReq {
    private String sourceMark;
    private String patientNo;
    private String sourceDate;
    private String departmentorganId;
    private String terminalCode;
    private String extend;
    private String isDopay;
    private String paymentData;
}