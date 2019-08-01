package cn.bxd.sip.bxd.model.respond.reg;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-17
 **/
@Data
public class RegDoctor {
    String sourceId;
    String doctorId;
    String departmentId;
    String sourceDate;
    Float consultationFee;
    Integer morningNum;
    Integer afternoonNum;
    Integer eveningNum;
    Double afternoonFee;
    Double eveningFee;
    String organdoctorId;
    String departmentorganId;
}
