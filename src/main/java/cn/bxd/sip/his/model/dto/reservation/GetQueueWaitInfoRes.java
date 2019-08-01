package cn.bxd.sip.his.model.dto.reservation;

/**
 * Description:
 * User: LiSheng
 * Date: 2018-11-01
 * Time: 15:16
 */
public class GetQueueWaitInfoRes {

    private String departmentNum;//就诊科室
    private String takeNo;//号码（叫号号码）
    private String waitNum;//等待人数
    private String departmentAddress;//就诊地址
    private String doctorName;//就诊医生

    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum;
    }

    public String getTakeNo() {
        return takeNo;
    }

    public void setTakeNo(String takeNo) {
        this.takeNo = takeNo;
    }

    public String getWaitNum() {
        return waitNum;
    }

    public void setWaitNum(String waitNum) {
        this.waitNum = waitNum;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
