package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@Data
public class QueryPatientInHosInfo {
    private String patientName;
    private String patientSex;
    private String cardNo;
    private String inHosNo;
    private String inHosSerialNo;
    private String inHosDate;
    private String leaveHosDate;        //出院时间 lisheng 2019/7/18
    private String inHosState;
    private String bedNo;
    private String departmentorganId;
    private String departmentName;
    private String medicalTypeCode;
    private String medicalTypeName;
    private String overMoney;
    private String payMoney;
    private String cashMoney;
    private String depositMoney;
    private String totalMoney;
    private String extend;
}