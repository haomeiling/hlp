package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author:tangliang
 * @date:2018/7/30
 * @description:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BODY")
public class GetSocialPolicyOutputList {
    @XmlElementWrapper(name = "ListItems")
    @XmlElements(value = {@XmlElement(name = "Item", type = GetSocialPolicyOutputItem.class)})
    private List<GetSocialPolicyOutputItem> Item;
}
