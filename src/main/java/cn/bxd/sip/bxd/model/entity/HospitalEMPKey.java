package cn.bxd.sip.bxd.model.entity;

/**
 * Description:机构人员主键
 *
 * @author laingshs
 *         <p/>
 *         2015年7月10日 上午10:05:06
 */
public class HospitalEMPKey {
    private Integer hospitalId;//医院代号

    private Integer empNo;//人员序号

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    /**
     * 带参数构造函数
     *
     * @param hospitalId
     * @param empNo
     */
    public HospitalEMPKey(Integer hospitalId, Integer empNo) {
        this.hospitalId = hospitalId;
        this.empNo = empNo;
    }

    /**
     * 默认构造函数
     */
    public HospitalEMPKey() {
        // TODO Auto-generated constructor stub
    }
}