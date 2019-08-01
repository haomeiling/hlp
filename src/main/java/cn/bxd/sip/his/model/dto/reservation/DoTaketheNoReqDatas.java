package cn.bxd.sip.his.model.dto.reservation;

import cn.bxd.sip.his.model.dto.his.BaseData;
import lombok.Data;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-01-16
 * Time: 9:38
 */
@Data
public class DoTaketheNoReqDatas extends BaseData {
     private String hosId;//医院编号
     private String OrderNo;
     private String PeerOrderNo;//预约凭证号
     private String OrderDate;
     private String ClinicDate;//号源日期
     private String PatientName;//患者姓名
     private String QueueName;
     private String PeriodDesc;
     private String DisplayQueueNo;

     private String patientNo;//患者编号   lisheng 2019/7/25
}
