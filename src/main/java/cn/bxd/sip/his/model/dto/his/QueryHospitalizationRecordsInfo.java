package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Lisheng
 * @version 1.0.0  2018-09-26
 */
@Data
public class QueryHospitalizationRecordsInfo {
    private String patientName;
    private String patientSex;
    private String cardNo;
    private String inHosNo;
    private String inHosSerialNo;
    private String inHosDate;
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