package cn.bxd.sip.bxd.hispay.protocol;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @ClassName: Header
 *
 * @Description: 头部对象
 *
 * @author Leeves
 *
 * @date 2017年10月13日
 */
@XStreamAlias("HEADER")
@Data
public class Header {
	private String MsgType;// 消息种类 3

	private String MsgID;// 消息内部标识4

	private String OmsgID;// 原消息标识

	private String MsgTime;// 消息时间7

	private String ReplyTo="";// 消息应答队列

	private String ResultCode;// 结果编码10

	private String hospitalCode;//医院编码
}
