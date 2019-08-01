package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author:tangliang
 * @date:2018/7/28
 * @description:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class GetSocialPolicyOutputItem {
    private String PolicyCode;
    private String PolicyType;
    private String Year;
    private String Title;
    private String SubTitle;
    private String Summary;
    private String Publisher;
    private String ReleaseTime;
    private String Remark;

}
