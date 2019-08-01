package cn.bxd.sip.bxd.model.entity;

/**
 * 患者机构交叉索引表
 *
 * @author hml
 *         <p/>
 *         2015年1月19日
 */
public class Pix extends PixKey {
    private Object clinicCardNo = "";//就诊卡号

    private Object hmpi;//医院主索引号

    private String patientNo = "";//患者编号  add--by--lkw 2016/7/8

    public Object getClinicCardNo() {
        return clinicCardNo;
    }

    public void setClinicCardNo(Object clinicCardNo) {
        this.clinicCardNo = clinicCardNo;
    }

    public Object getHmpi() {
        return hmpi;
    }

    public void setHmpi(Object hmpi) {
        this.hmpi = hmpi;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }


}