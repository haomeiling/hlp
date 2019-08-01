package cn.bxd.sip.his.model.dto.reservation;

import cn.bxd.sip.his.model.dto.his.BaseData;

/**
 * Description:
 * User: LiSheng
 * Date: 2018-11-01
 * Time: 15:16
 */
public class GetQueueWaitInfoReqDates  extends BaseData {

   private Integer  hosId;//医院ID
    private String empi;//患者院内编码
   private String  clinicDate;//日期

    public String getEmpi() {
        return empi;
    }

    public void setEmpi(String empi) {
        this.empi = empi;
    }

    public Integer getHosId() {
        return hosId;
    }

    public void setHosId(Integer hosId) {
        this.hosId = hosId;
    }

    public String getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(String clinicDate) {
        this.clinicDate = clinicDate;
    }
}
