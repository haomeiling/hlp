package cn.bxd.sip.his.model.dto.reservation;

import cn.bxd.sip.bxd.webservice.Header;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author:tangliang
 * @date:2018/7/30
 * @description:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SIMSG")
public class GetSocialPolicyOutputXml {

    @XmlElement(name = "HEADER")
    private Header header;

    @XmlElement(name = "BODY")
    private GetSocialPolicyOutputList body;

    @XmlElement(name = "DSIGN")
    private String dsign;
}
