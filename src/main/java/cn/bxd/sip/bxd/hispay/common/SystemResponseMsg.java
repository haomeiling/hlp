package cn.bxd.sip.bxd.hispay.common;

import cn.bxd.sip.bxd.hispay.constant.ConfigConstant;
import cn.bxd.sip.bxd.hispay.constant.OperationConst;
import cn.bxd.sip.bxd.hispay.constant.SessionConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:17
 */
@Slf4j
public class SystemResponseMsg {

    /**
     * 系统错误应答929999,惠民平台专用
     *
     * @param refNo
     * @param msg
     * @return
     */
    public static String msg929999(String refNo, String msg, String refId, String resultCode) {
        try {
            Map<String, Object> rootMap = new LinkedHashMap<>();
            Map<String, Object> hiEsbMap = new LinkedHashMap<>();
            hiEsbMap.put("HEADER", getHeadMap(OperationConst.OPR_CODE_OF_SYS_ERROR_RES, refNo, refId, resultCode));
            hiEsbMap.put("BODY", getBodyMap(msg, resultCode));
            rootMap.put("HIESBMSG", hiEsbMap);
            return GenerateXmlUtil.generateXml(rootMap);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("系统错误应答生成Xml出错（929999）:" + e.getMessage());
            }
            return msg;
        }
    }

    /**
     * 通用消息应答929901,11,12等,惠民平台专用
     *
     * @param refNo
     * @param msg
     * @return
     */

    public static String msg9299xx(int msgType, String refNo, String refId, String msg, String resultCode) {
        try {
            Map<String, Object> rootMap = new LinkedHashMap<>();
            Map<String, Object> hiEsbMap = new LinkedHashMap<>();
            hiEsbMap.put("HEADER", getHeadMap(msgType, refNo, refId, resultCode));
            hiEsbMap.put("BODY", getBodyMap(refNo, refId, msg, resultCode));
            rootMap.put("HIESBMSG", hiEsbMap);
            return GenerateXmlUtil.generateXml(rootMap);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("通用消息应答生成Xml出错（" + msgType + "）:" + e.getMessage());
            }
            return msg;
        }
    }

    /**
     * 通用消息应答，构造简单的头部的系统应答消息
     *
     * @param refNo
     * @param msg
     * @return
     */

    public static String msgSimpleRes(int msgType, String msg, String resultCode) {
        try {
            Map<String, Object> rootMap = new LinkedHashMap<>();
            Map<String, Object> hiEsbMap = new LinkedHashMap<>();
            hiEsbMap.put("HEADER", getHeadMap(msgType, resultCode));
            hiEsbMap.put("BODY", getBodyMap(msg, resultCode));
            rootMap.put("HIESBMSG", hiEsbMap);
            return GenerateXmlUtil.generateXml(rootMap);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("通用消息应答生成Xml出错（" + msgType + "）:" + e.getMessage());
            }
            return msg;
        }
    }

    /**
     * 构造headMap，包含头部请求消息的全部属性
     *
     * @param msgType
     * @param refNo
     * @return
     */
    public static Map<String, Object> getHeadMap(int msgType) {
        Map<String, Object> headMap = new LinkedHashMap<>();
        headMap.put("AppID", "HURP-GX");
        headMap.put("AppVersion", "1.0");
        headMap.put("MsgType", msgType);
        headMap.put("MsgID", "");
        headMap.put("MsgNo", getMsgNo(msgType));
        headMap.put("RefNo", null);
        headMap.put("MsgTime", getTimeEndSs());
        headMap.put("OrgID", ConfigConstant.Constant.ORG_ID);
        headMap.put("SessionID", SessionConstant.sessionId);
        return headMap;
    }

    /**
     * 构造headMap，包含响应时消息头部的全部属性
     *
     * @param msgType
     * @param refNo
     * @return
     */
    public static Map<String, Object> getHeadMap(int msgType, String refNo, String refId, String resultCode) {
        Map<String, Object> headMap = new LinkedHashMap<>();
        headMap.put("AppID", "HURP-GX");
        headMap.put("AppVersion", "1.0");
        headMap.put("MsgType", msgType);
        headMap.put("MsgID", refId);
        headMap.put("MsgNo", getMsgNo(msgType));
        headMap.put("RefNo", refNo);
        headMap.put("MsgTime", getTimeEndSs());
        headMap.put("OrgID", ConfigConstant.Constant.ORG_ID);
        headMap.put("SessionID", SessionConstant.sessionId);
        headMap.put("ResultCode", resultCode);
        return headMap;
    }

    /**
     * 构造headMap，包含响应时消息头部的全部属性(排队叫号消息用)
     *
     * @param msgType
     * @param refNo
     * @return
     */
    public static Map<String, Object> getHeadMapForMakeQueue(int msgType, String refNo, String resultCode) {
        Map<String, Object> headMap = new LinkedHashMap<>();
        headMap.put("MsgType", msgType);
        headMap.put("MsgID", getMsgNo(msgType));
        headMap.put("OMsgID", refNo);
        headMap.put("MsgTime", DateFormatUtils.format(Calendar.getInstance(), "yyyyMMddHHmmss"));
        headMap.put("ReplyTo", "");
        headMap.put("ResultCode", resultCode);
        return headMap;
    }

    /**
     * 构造headMap，仅包含MsgType等简单熟悉
     *
     * @param msgType
     * @param refNo
     * @return
     */
    public static Map<String, Object> getHeadMap(int msgType, String resultCode) {
        Map<String, Object> headMap = new LinkedHashMap<>();
        headMap.put("MsgType", msgType);
        headMap.put("ResultCode", resultCode);
        return headMap;
    }

    /**
     * 构造bodyMap，含原消息号，消息ID
     *
     * @param refNo
     * @param refId
     * @param msg
     * @param resultCode
     * @return
     */
    private static Map<String, Object> getBodyMap(String refNo, String refId, String msg, String resultCode) {
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("ResultCode", resultCode);
        bodyMap.put("ResultMsg", msg);
        bodyMap.put("OriginMsgNo", refNo);
        bodyMap.put("OriginMsgID", refId);
        return bodyMap;
    }

    /**
     * 构造bodyMap，不包含原消息号、消息ID
     *
     * @param refNo
     * @param refId
     * @param msg
     * @param resultCode
     * @return
     */
    private static Map<String, Object> getBodyMap(String msg, String resultCode) {
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("ResultCode", resultCode);
        bodyMap.put("ResultMsg", msg);
        return bodyMap;
    }

    /**
     * 获取消息唯一编码
     *
     * @return
     */
    private static String getMsgNo(int MsgType) {
        return MsgType + getTimeEndSs() + getRandomNum();
    }

    /**
     * 获取时间到秒s
     *
     * @return
     */
    public static String getTimeEndSs() {
        Calendar cal = Calendar.getInstance();
        String time = DateFormatUtils.format(cal, "yyyyMMddHHmmss");
        return time;
    }

    /**
     * 获取时间到天d
     *
     * @return
     */
    public static String getTimeEndDd() {
        Calendar cal = Calendar.getInstance();
        String time = DateFormatUtils.format(cal, "yyyyMMdd");
        return time;
    }

    /**
     * 获取10000-99999随机数
     *
     * @return
     */
    public static int getRandomNum() {
        Random rand = new Random();
        int randNum = rand.nextInt(9999) + 10000;
        return randNum;
    }
}
