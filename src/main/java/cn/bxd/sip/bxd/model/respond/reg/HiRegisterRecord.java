package cn.bxd.sip.bxd.model.respond.reg;

import lombok.Data;

import java.util.Date;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@Data
public class HiRegisterRecord {
    String sourceMark;
    String organdoctorId;
    String organdoctorName;
    String departmentorganId;
    String departmentName;
    String sourceDate;
    Short sourceTimeType;
    float consultationFee;
    String timestypeNo;
    String timestypeNoName;
    String visitAddress;
    String takeAddress;
    String orderNo;

}
