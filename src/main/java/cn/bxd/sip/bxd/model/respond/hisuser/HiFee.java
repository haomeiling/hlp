package cn.bxd.sip.bxd.model.respond.hisuser;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author cRyann
 * @create 2018-09-12
 **/
@Data
public class HiFee {
    String reportNo;
    String patientNo;
    String patientName;
    Date reportDate;
    Date createTime;
    String contentpicsrc;
    String reportUrl;
    String checkName;
    String doctorName;
    String chekckdoctorName;
    Integer reportStatus;
    Integer isPrint;
    Integer reportType;
    Integer isPic;
    String reportItems;
    String extend;
}
