package cn.bxd.sip.his.model.dto.reservation;


import cn.bxd.sip.his.model.dto.his.BaseData;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author：haomeiling
 * @Description：诊疗记录
 * @Date：2018/09/23 9:57.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetClinicRecordResData extends BaseData {
    private Short success;
    private String msg;
    private Integer hosId;

    private String idNumber;//身份证

    private GetClinicRecordRes data;//协议对象类


    public Short getSuccess() {
        return success;
    }

    public void setSuccess(Short success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getHosId() {
        return hosId;
    }

    public void setHosId(Integer hosId) {
        this.hosId = hosId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public GetClinicRecordRes getData() {
        return data;
    }

    public void setData(GetClinicRecordRes data) {
        this.data = data;
    }
}
