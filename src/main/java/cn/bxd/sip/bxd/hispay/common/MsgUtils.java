package cn.bxd.sip.bxd.hispay.common;

import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.exception.SysErrException;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:12
 */
@Slf4j
public class MsgUtils {
    // 获取日志输出对象

    /**
     * 获取请求响应标志位
     *
     * @param inMsgStr
     * @return
     * @throws SysErrException
     */
    public static int getMsgResReqFlag(String inMsgStr) throws SysErrException {
        int msgType = getMsgType(inMsgStr);
        return Integer.parseInt(String.valueOf(msgType).substring(1, 2));
    }

    /**
     * 获取消息代码MsgType
     *
     * @param inMsgStr
     * @return
     * @throws SysErrException
     */
    public static int getMsgType(String inMsgStr) throws SysErrException {
        try {
            String tag = "<MsgType>";
            int start = inMsgStr.indexOf("<MsgType>");
            int end = inMsgStr.indexOf("</MsgType>");
            return Integer.parseInt(inMsgStr.substring(start + tag.length(), end));
        } catch (Exception ex) {
            log.error("消息报文类型错误:\n" + inMsgStr);
            throw new SysErrException("找不到消息的报文类型(MsgType).", ResultCodeConstant.RESULTCODE_90001);
        }
    }

    /**
     * 获取消息ID
     *
     * @param inMsgStr
     * @return
     * @throws SysErrException
     */
    public static String getMsgID(String inMsgStr) throws SysErrException {
        try {
            String tag = "<MsgID>";
            int start = inMsgStr.indexOf("<MsgID>");
            int end = inMsgStr.indexOf("</MsgID>");
            return inMsgStr.substring(start + tag.length(), end);
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("消息报文类型错误:\n" + inMsgStr);
            }
            throw new SysErrException("找不到消息的报文类型(MsgID).", ResultCodeConstant.RESULTCODE_90001);
        }
    }

    /**
     * 获取消息NO
     *
     * @param inMsgStr
     * @return
     * @throws SysErrException
     */
    public static String getMsgNo(String inMsgStr) throws SysErrException {
        try {
            String tag = "<MsgNo>";
            int start = inMsgStr.indexOf("<MsgNo>");
            int end = inMsgStr.indexOf("</MsgNo>");
            return inMsgStr.substring(start + tag.length(), end);
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("未定义的报文类型:\n" + inMsgStr);
            }
            throw new SysErrException("找不到消息的报文类型(MsgNo).", ResultCodeConstant.RESULTCODE_90001);
        }
    }

    /**
     * 获取数字签名DSIGN
     *
     * @param inMsgStr
     * @return signature
     * @throws SysErrException
     */
    public static String getDsign(String inMsgStr) throws SysErrException {
        try {
            String tag = "<DSIGN>";
            int start = inMsgStr.indexOf("<DSIGN>");
            int end = inMsgStr.indexOf("</DSIGN>");
            return inMsgStr.substring(start + tag.length(), end);
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("未知的报文签名:\n" + inMsgStr);
            }
            throw new SysErrException("获取报文签名失败.", ResultCodeConstant.RESULTCODE_90001);
        }
    }

    /**
     * 获取消息参考NO
     *
     * @param inMsgStr
     * @return
     * @throws SysErrException
     */
    public static String getRefNo(String inMsgStr) throws SysErrException {
        try {
            String tag = "<RefNo>";
            int start = inMsgStr.indexOf("<RefNo>");
            int end = inMsgStr.indexOf("</RefNo>");
            return inMsgStr.substring(start + tag.length(), end);
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("未定义报文类型:\n" + inMsgStr);
            }
            throw new SysErrException("找不到消息的报文类型(RefNo).", ResultCodeConstant.RESULTCODE_90001);
        }
    }


    /**
     * 获取医院Code
     *
     * @param inMsgStr
     * @return
     * @throws SysErrException
     */
    public static String getHospitalCode(String inMsgStr) throws SysErrException {
        try {
            String tag = "<HospitalCode>";
            int start = inMsgStr.indexOf("<HospitalCode>");
            int end = inMsgStr.indexOf("</HospitalCode>");
            return inMsgStr.substring(start + tag.length(), end);
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("未定义医院编码:\n" + inMsgStr);
            }
            throw new SysErrException("找不到消息的医院编码(HospitalCode).", ResultCodeConstant.RESULTCODE_90001);
        }
    }

    /**
     * 获取BODY代码
     *
     * @param inMsgStr
     * @return
     * @throws SysErrException
     *
     * @author Leeves
     *
     * @Date 2017.10.13
     *
     */
    public static String getBody(String inMsgStr) throws SysErrException {
        try {
            String tag = "<BODY>";
            int start = inMsgStr.indexOf("<BODY>");
            int end = inMsgStr.indexOf("</BODY>");

            return inMsgStr.substring(start, end + tag.length() + 1);
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("消息报文类型错误:\n" + inMsgStr);
            }
            throw new SysErrException("找不到消息的报文类型(BODY).", ResultCodeConstant.RESULTCODE_90001);
        }
    }

    /**
     * 获取消息代码ResultCode
     *
     * @param inMsgStr
     * @return
     * @throws SysErrException
     */
    public static String getResultCode(String inMsgStr) throws SysErrException {
        try {
            String tag = "<ResultCode>";
            int start = inMsgStr.indexOf("<ResultCode>");
            int end = inMsgStr.indexOf("</ResultCode>");
            return inMsgStr.substring(start + tag.length(), end);
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("消息报文类型错误:\n" + inMsgStr);
            }
            throw new SysErrException("找不到消息的报文类型(ResultCode).", ResultCodeConstant.RESULTCODE_90001);
        }
    }
}
