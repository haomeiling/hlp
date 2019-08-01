package cn.bxd.sip.bxd.model.respond.reg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author : cRyann
 * @create 2018-09-25
 **/
@Data
public class PoState {
    String state = "";
    String departmentNum = "";
    String visitAddress = "";
    String takeAddress = "";
    String reservation = "";
    String sourceDate;
    @JsonIgnore
    Date sourceDateDb;
    String sourceTimeType = "";
    String timestypeNo = "";
    String timestypeNoName = "";
    @JsonIgnore
    String patientNo = "";
    String patientName;
    String departmentName;
    String doctorName;
    String regType;
}
