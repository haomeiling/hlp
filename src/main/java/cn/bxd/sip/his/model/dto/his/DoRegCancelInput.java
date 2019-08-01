package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/8/16
 * @description:
 */
@Data
public class DoRegCancelInput {
    public String synUserName;
    public String synKey;
    public String sourceMark;
    public String patientNo;
    public String sourceDate;
    public String departmentorganId;
    public String extend;
}
