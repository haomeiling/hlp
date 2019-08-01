package cn.bxd.sip.bxd.model.entity;

/**
 * 患者机构交叉索引表
 *
 * @author hml
 *         <p/>
 *         2015年1月19日
 */
public class PixKey {
    private Integer empiId;//患者empi

    private Integer hospitalId;//机构代号

    public Integer getEmpiId() {
        return empiId;
    }

    public void setEmpiId(Integer empiId) {
        this.empiId = empiId;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }


}