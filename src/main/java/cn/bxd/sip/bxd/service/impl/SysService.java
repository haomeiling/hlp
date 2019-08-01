package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.SysMapper;
import cn.bxd.sip.bxd.service.ISysService;
import cn.bxd.sip.bxd.var.SIVar;
import cn.bxd.sip.bxd.webservice.Header;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：weishaoxiang
 * @version：V1.0 <P/>
 * @类说明：访问权限控制
 */

@Service
@Slf4j
public class SysService implements ISysService {

	@Autowired
	SysMapper sysMapper;

	/**
	 * 校验权限
	 * 
	 * @param clientIP
	 *            客户端IP
	 * @param header
	 *            报文头
	 * @return
	 */
	public boolean checkPermission(String clientIP, Header header) {
		boolean sign = true;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appID", header.getAppID());
		param.put("Msgtype", header.getMsgType());
		param.put("clientIP", clientIP);
		int i = sysMapper.checkIP(param);
		if (i <= 0) {
			header.setResultCode(SIVar.ResultCode.RESULT_CODE_90105);
			header.setResultMsg("此IP没有访问权限！");
			sign = false;
			return sign;
		}

		int j = sysMapper.checkBusiness(param);
		if (j <= 0) {
			header.setResultCode(SIVar.ResultCode.RESULT_CODE_90105);
			header.setResultMsg("没有此交易权限！");
			sign = false;
			return sign;
		}

		return sign;
	}

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
	public int saveBusinessLog(String clientIP, Header header, String InMsgStr, String OutMsgStr) {
		int i = 0;

		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("clientIP", clientIP);
			param.put("Version", header.getVersion());// 报文规范版本号 如：V1.0
			param.put("MsgType", header.getMsgType());// 消息种类
			param.put("MsgID", header.getMsgID());// 消息唯一标识
			param.put("AppID", header.getAppID());// 应用ID 8位数字，由综合外联平台统一分配。
			param.put("MsgTime", header.getMsgTime());// 消息产生的时间：YYYYMMDDHHMISS
			param.put("ResultCode", header.getResultCode());// 应答消息的结果编码，参见结果编码表
			param.put("ResultMsg", header.getResultMsg());// 结果消息
			param.put("InMsgStr", InMsgStr.getBytes("GBK"));// 结果消息
			param.put("OutMsgStr", OutMsgStr.getBytes("GBK"));// 结果消息

			i = sysMapper.saveBusinessLog(param);
		} catch (Exception e) {
			log.error("",e);
		}
		return i;
	}

}
