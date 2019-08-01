package cn.bxd.sip.si.model.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Version 1.0
 * @Author haomeiling
 * @Date 2018-03-02
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HEADER")
public class Header {

    /** 消息种类 */
    @XStreamAlias("MsgType")
    private String MsgType;

    /** 规范版本号 */
    @XStreamAlias("Version")
    private String Version;

    /** 应用ID */
    @XStreamAlias("AppID")
    private String AppID;

    /** 消息唯一标识 */
    @XStreamAlias("MsgID")
    private String MsgID;

    /** 消息产生的时间：YYYYMMDDHHMISS */
    @XStreamAlias("MsgTime")
    private String MsgTime;

    /** 应答消息的结果编码，参见结果编码表 */
    @XStreamAlias("ResultCode")
    private String ResultCode;

    /** 结果消息 */
    @XStreamAlias("ResultMsg")
    private String ResultMsg;

}
