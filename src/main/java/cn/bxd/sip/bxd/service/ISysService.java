package cn.bxd.sip.bxd.service;


import cn.bxd.sip.bxd.webservice.Header;

/**
 * @author：weishaoxiang
 * @version：V1.0 <P/>
 * @类说明：访问权限控制
 */

public interface ISysService {

	/**
	 * 校验权限
	 * 
	 * @param clientIP
	 *            客户端IP
	 * @param header
	 *            报文头
	 * @return
	 */
	boolean checkPermission(String clientIP, Header header);

	/**
	 * 保存交易日志
	 * 
	 * @param clientIP
	 *            客户端IP
	 * @param header
	 *            报文头
	 * @param inMsgStr
	 *            请求报文
	 * @param outMsgStr
	 *            返回报文
	 * @return
	 * @throws Exception
	 */
	int saveBusinessLog(String clientIP, Header header, String InMsgStr, String OutMsgStr);

}
