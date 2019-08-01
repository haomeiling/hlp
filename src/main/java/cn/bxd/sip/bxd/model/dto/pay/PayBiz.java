package cn.bxd.sip.bxd.model.dto.pay;

import java.math.BigDecimal;

/**
 * Description: 支付传入参数
 * Package: com.bxd.medicalinsurance.model.dto
 *
 * @author Leeves
 * @version 1.0.0  2018-05-17
 */


/**
 * http://localhost:8080/reservation/payMysql/h5.do?
 * orderId=3967
 * &peerOrderNo=3270841_0
 * &providerId=1
 * &amount=0.01
 * &hospitalId=7962
 * &orderTypeId=2
 * &isArrivalOrder=0
 * &patientName=%E6%9D%A8%E7%8E%89%E6%B8%85
 * &empi=000000002200
 */


public class PayBiz {
    private Long orderId;//订单ID
    private String peerOrderNo;//his订单号
    private Short providerId;//支付渠道
    private BigDecimal amount;//支付金额
    private Integer hospitalId;//医院编码
    private Short orderTypeId;//订单类型
    private String patientName;//患者
    private Long payPattern;//支付方式  11自费，12社保，20混合
    private Short isArrivalOrder;//是否是签到订单
    private String empi;
    private Long payType;
    private String deptName;
    private String doctorName;
    private String totalMoney;
    private int orderDay;
    private String socialNo;
    private String recordId;
    private String cashMoney;
    private String payMoney;
    private String hosId;
    private String overMoney;
    private String idNumber;
    private Long medicareType;
    private int orderSourceId;

    @Override
    public String toString() {
        return "PayBiz{" +
                "orderId=" + orderId +
                ", peerOrderNo='" + peerOrderNo + '\'' +
                ", providerId=" + providerId +
                ", amount=" + amount +
                ", hospitalId=" + hospitalId +
                ", orderTypeId=" + orderTypeId +
                ", patientName='" + patientName + '\'' +
                ", payPattern=" + payPattern +
                ", isArrivalOrder=" + isArrivalOrder +
                ", empi='" + empi + '\'' +
                ", payType=" + payType +
                ", deptName='" + deptName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", orderDay=" + orderDay +
                ", socialNo='" + socialNo + '\'' +
                ", recordId='" + recordId + '\'' +
                ", cashMoney='" + cashMoney + '\'' +
                ", payMoney='" + payMoney + '\'' +
                ", hosId='" + hosId + '\'' +
                ", overMoney='" + overMoney + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", medicareType=" + medicareType +
                ", orderSourceId=" + orderSourceId +
                '}';
    }

    public int getOrderSourceId() {
        return orderSourceId;
    }

    public void setOrderSourceId(int orderSourceId) {
        this.orderSourceId = orderSourceId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setOrderDay(Integer orderDay) {
        this.orderDay = orderDay;
    }

    public Long getMedicareType() {
        return medicareType;
    }

    public void setMedicareType(Long medicareType) {
        this.medicareType = medicareType;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(int orderDay) {
        this.orderDay = orderDay;
    }

    public String getSocialNo() {
        return socialNo;
    }

    public void setSocialNo(String socialNo) {
        this.socialNo = socialNo;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(String cashMoney) {
        this.cashMoney = cashMoney;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getOverMoney() {
        return overMoney;
    }

    public void setOverMoney(String overMoney) {
        this.overMoney = overMoney;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getPayType() {
        return payType;
    }

    public void setPayType(Long payType) {
        this.payType = payType;
    }

    public Long getPayPattern() {
        return payPattern;
    }

    public void setPayPattern(Long payPattern) {
        this.payPattern = payPattern;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPeerOrderNo() {
        return peerOrderNo;
    }

    public void setPeerOrderNo(String peerOrderNo) {
        this.peerOrderNo = peerOrderNo;
    }

    public Short getProviderId() {
        return providerId;
    }

    public void setProviderId(Short providerId) {
        this.providerId = providerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Short getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(Short orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getEmpi() {
        return empi;
    }

    public void setEmpi(String empi) {
        this.empi = empi;
    }

    public Short getIsArrivalOrder() {
        return isArrivalOrder;
    }

    public void setIsArrivalOrder(Short isArrivalOrder) {
        this.isArrivalOrder = isArrivalOrder;
    }

}
