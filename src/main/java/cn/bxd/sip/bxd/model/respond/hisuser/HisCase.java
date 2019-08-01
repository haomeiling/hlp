package cn.bxd.sip.bxd.model.respond.hisuser;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@Data
public class HisCase {
    String historyCaseNo;
    String patientNo;
    String patientName;
    String organdoctorId;
    String doctorName;
    String organName;
    String visitingTime;
    String hospitalName;
    String departmentName;
    String sex;
    String age;
    String resultTest;
    String opinionSecond;
    String adviceProcess;
    String resultCode;
    String resultMsg;
    String extend;

}
