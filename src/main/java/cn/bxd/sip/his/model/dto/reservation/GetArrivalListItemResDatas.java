package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@Data
public class GetArrivalListItemResDatas {

    /** 平台唯一订单号 */
    private Long orderId;
    /** 医院订单编号 */
    private String peerOrderNo;
    /** 就诊日期 */
    private Integer clinicDate;
    /** 订单来源 */
    private Integer orderSourceId;
    /** 订单类型 */
    private Integer orderTypeId;
    /** 支付状态 */
    private Integer paidFlag;
    /** 签到状态 */
    private Integer arrivalFlag;

    /** 科室门类编码 */
    private String subjectCode;
    /** 科室门类名称 */
    private String subjectName;
    /** 订单金额 */
    private Double amount;
    /** 医生编码 */
    private String doctorCode;
    /** 医生名称 */
    private String doctorName;
    /** 队列ID */
    private String queueId;
    /** 队列名称 */
    private String queueName;
    /** 时段ID(时段序号) */
    private Integer periodId;
    /** 时段名称(就诊时段时间描述) */
    private String periodName;
    /** 排队号 */
    private Integer queueNo;
    /** 显示排队号 */
    private String displayQueueNo;

    /** 候诊地址 */
    private String queueAddr;

    //目前只有业务接收参数使用
    /** 医院编码 */
    private Integer hosId;
    /** 患者姓名 */
    private String patientName;

}