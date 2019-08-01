package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetPatientInHosReqDatas extends BaseReqDates {

    private String empi;
    private String clinicType;
    private String startDate;
    private String endDate;
    private String status;
    private String clinicalNo;//Lisheng 2018/9/27  住院号
    private String socialsecurityNO;//Lisheng 2018/9/28

    private String visitCardNo;//诊疗卡号   haomeiling 2019/02/13

    private String cardNo;//身份证号  Lisheng 2019/6/19


}