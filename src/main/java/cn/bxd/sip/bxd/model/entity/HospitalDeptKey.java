package cn.bxd.sip.bxd.model.entity;

/**
 * 
 * @Description:
 * @author: chenchuanchuan 
 * @date:   2018年11月29日 上午9:59:39
 */
public class HospitalDeptKey {
    private Integer hospitalId;//机构代号

    private Integer deptNo;//科室代号

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * 带参数的构造函数
     *
     * @param hospitalId
     * @param deptNo
     */
    public HospitalDeptKey(Integer hospitalId, Integer deptNo) {
        this.hospitalId = hospitalId;
        this.deptNo = deptNo;
    }

    /**
     * 默认构造函数
     */
    public HospitalDeptKey() {
        // TODO Auto-generated constructor stub
    }
}