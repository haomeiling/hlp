package cn.bxd.sip.bxd.hispay.common;

import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.protocol.Header;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 18:34
 */
public class HeaderUtils {
    /**
     * 构建头部
     *
     * @param hospitalCode 医院编码
     * @param resultCode   结果编码
     * @return
     */
    public static Header buildHeader(String hospitalCode, String resultCode, int msgType,String oMsgID) {
        // 初始化数据
        DateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dateTime = new Date();

        // 开始封装
        Header header = new Header();
        header.setMsgType(String.valueOf(msgType));
        header.setMsgTime(df2.format(dateTime));
        header.setHospitalCode(hospitalCode);
        header.setOmsgID(oMsgID);
        header.setMsgID(msgType + String.valueOf(new Date().getTime()));

        header.setResultCode(resultCode);

        return header;

    }

    public static String buildXmlExceptionHeader(String hospitalId, int msgType) {
        // 初始化数据
        DateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dateTime = new Date();
        String msgTimeString = df2.format(dateTime);

        String headerString = "<HIESBMSG>" + "<HEADER>"
                + "<MsgType>" + msgType + "</MsgType>"
                + "<MsgNo>" + msgType + "1516355062510</MsgNo>"
                + "<MsgTime>" + msgTimeString + "</MsgTime>"
                + "<HospitalCode>" + hospitalId + "</HospitalCode>"
                + "<ResultCode>" + ResultCodeConstant.RESULTCODE_90101 + "</ResultCode>"
                + "</HEADER>" + "</HIESBMSG>";

        return headerString;

    }

    public static String buildXmlExceptionHeader(int hospitalId,
                                                 String sessionId, int msgType, String regNo, String resultCode) {
        // 初始化数据
        DateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dateTime = new Date();
        String msgTimeString = df2.format(dateTime);

        String headerString = "<HIESBMSG>" + "<HEADER>" + "<AppID>"
               + "<MsgType>" + msgType
                + "</MsgType>" + "<MsgNo>" + msgType + "1516355062510</MsgNo>"
                + "<MsgTime>" + msgTimeString + "</MsgTime>" + "<OrgID>"
                + hospitalId + "</OrgID>" + "<ResultCode>" + resultCode
                + "</ResultCode>" + "<SessionID>" + sessionId + "</SessionID>"
                + "<RefNo>" + regNo + "</RefNo>" + "</HEADER>" + "</HIESBMSG>";

        return headerString;

    }
}
