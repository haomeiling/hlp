package cn.bxd.sip.his.model.dto.his;

/**
 * @author：moqp
 * @Description：预约挂号通知类
 * @Date：2016/12/13 16:37.
 */
public class CancelNotifyObject {

    //预约时间
    private String orderDayStr;
    //预约医生
    private String doctorName;
    //患者名字
    private String patientName;
    //预订时间段
    private String orderPeriod;
    //医院名字
    private String hospitalName;
    //科室名字
    private String deptName;

    //公共参数类对象
    private MsgBaseData msgBaseData;

    public String getOrderDayStr() {
        return orderDayStr;
    }

    public void setOrderDayStr(String orderDayStr) {
        this.orderDayStr = orderDayStr;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getOrderPeriod() {
        return orderPeriod;
    }

    public void setOrderPeriod(String orderPeriod) {
        this.orderPeriod = orderPeriod;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public MsgBaseData getMsgBaseData() {
        return msgBaseData;
    }

    public void setMsgBaseData(MsgBaseData msgBaseData) {
        this.msgBaseData = msgBaseData;
    }
}
