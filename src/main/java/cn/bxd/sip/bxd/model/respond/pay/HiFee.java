package cn.bxd.sip.bxd.model.respond.pay;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@Data
public class HiFee {
    String hiFeeNo;
    String patientNo;
    String patientName;
    String organdoctorId;
    String doctorName;
    String organName;
    String serialNumber;
    String visitDate;
    String settleType;
    Double settleAmount;
    Double patientAmount;
    Integer status;
    Integer isPrint;
    String createTime;
    String takeNo;
    Integer medicareType;
    Float favorablePrice;
    String settleTime;
    String accountName; 
    List<HiFeeItem> hiFeeItem;
}
