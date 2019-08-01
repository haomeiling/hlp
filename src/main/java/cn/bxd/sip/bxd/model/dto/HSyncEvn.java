package cn.bxd.sip.bxd.model.dto;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.Order;
import lombok.Data;

import java.util.Date;

/**
 * @author:tangliang
 * @date:2018/7/12
 * @description:
 */
@Data
public class HSyncEvn {
    private Long syncSeqId;
    private Date createdTime;
    private Date firstProcTime;
    private Short procCount;
    private Date lastProcTime;
    private Object statusCode;
    private Object errorMessage;
    private Short syncTypeId;
    private Long orderId;
    private Long transId;


    private Short periodNo;


    private Integer queueNo;


    private Integer clinicDate;


    private Integer hospitalId;


    private Short rushRequired;


    private Object queueId;

    private String wsUrl;

    private Order order;

    private ConnectParm connectParm;

    private String contactPhone;    //联系电话 lisheng
    private String clinicCardNo;    //就诊卡号 lisheng

    @Override
    public String toString() {
        return "HSyncEvn{" +
                "syncSeqId=" + syncSeqId +
                ", createdTime=" + createdTime +
                ", firstProcTime=" + firstProcTime +
                ", procCount=" + procCount +
                ", lastProcTime=" + lastProcTime +
                ", statusCode=" + statusCode +
                ", errorMessage=" + errorMessage +
                ", syncTypeId=" + syncTypeId +
                ", orderId=" + orderId +
                ", transId=" + transId +
                ", periodNo=" + periodNo +
                ", queueNo=" + queueNo +
                ", clinicDate=" + clinicDate +
                ", hospitalId=" + hospitalId +
                ", rushRequired=" + rushRequired +
                ", queueId=" + queueId +
                ", wsUrl='" + wsUrl + '\'' +
                ", order=" + order +
                ", connectParm=" + connectParm +
                ", contactPhone='" + contactPhone + '\'' +
                ", clinicCardNo='" + clinicCardNo + '\'' +
                '}';
    }
}
