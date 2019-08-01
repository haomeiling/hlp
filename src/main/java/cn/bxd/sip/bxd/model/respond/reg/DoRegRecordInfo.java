package cn.bxd.sip.bxd.model.respond.reg;

import lombok.Data;

import java.util.Date;

/**
 * @author : cRyann
 * @create 2018-09-07
 **/
@Data
public class DoRegRecordInfo {
    String sourceMark = "";
    String organdoctorId = "";
    String organdoctorName = "";
    String departmentorganId = "";
    String departmentName = "";
    String visitAddress = "";
    String takeAddress = "";
    Date sourceDate;
    String sourceTimeType = "";
    String timestypeNo = "";
    String timestypeNoName = "";
    float consultationFee = 0L;

}
