package cn.bxd.sip.si.model.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @Version 1.0
 * @Author haomeiling
 * @Date 2018-03-02
 */
@Data
@XStreamAlias("SIMSG")
public class HeaderBody<T> {

    @XStreamAlias("HEADER")
    private Header header;

    @XStreamAlias("BODY")
    private T body;

    @XStreamAlias("DSIGN")
    private String dsign;

}
