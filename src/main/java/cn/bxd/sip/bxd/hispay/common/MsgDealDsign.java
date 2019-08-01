package cn.bxd.sip.bxd.hispay.common;

import cn.bxd.sip.bxd.hispay.constant.ConfigConstant;
import cn.bxd.sip.bxd.hispay.constant.ErrorConstant;
import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Description:消息签名处理
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:28
 */
@Slf4j
public class MsgDealDsign {
    /**
     * 取得输入消息中的附加验证信息，并以字符串返回
     *
     * @param inContent
     * @return
     * @throws Exception
     */
    public static String getDetachedSign(String inContent) {
        int DsignStart = inContent.indexOf("<DSIGN>");
        int DsignEnd = inContent.lastIndexOf("</DSIGN>");
        if (DsignStart > 0) {
            // 取得数字签名的内容
            return inContent.substring(DsignStart + 7, DsignEnd);
        } else {
            return null;
        }
    }

    /**
     * 去掉输入消息中的附加验证信息，并以字符串返回去掉数字签名后的消息内容
     *
     * @param inContent
     * @return
     * @throws Exception
     */
    public static String removeDetachedSign(String inContent) {
        String strEnc = "";
        String tmp = inContent;
        int DsignStart = tmp.indexOf("<DSIGN>");
        int DsignEnd = tmp.lastIndexOf("</DSIGN>");
        if (DsignStart > 0) {
            // 取得数字签名的内容
            strEnc = inContent.substring(DsignStart, DsignEnd + 8);
        }
        return inContent.replace(strEnc, "");
    }

    /**
     * 给输入的消息做数字签名，并追加到原消息内
     *
     * @param inContent
     * @return
     * @throws BusinessException
     */
    public static String DetachSign(String inContent) throws BusinessException {
        if (ConfigConstant.Constant.WITHDSIGN) {
            StringBuffer buff = new StringBuffer();// 用来存储字符
            String addStart = null;
            String addEnd = null;
            String dsignTagS = "<DSIGN>";
            String dsignTagE = "</DSIGN>";
            if (inContent.indexOf(dsignTagS) > -1) {// 去掉原有的数字签名，重新添加新的签名
                int dsignTagStart = inContent.indexOf(dsignTagS);
                int dsignTagEnd = inContent.indexOf(dsignTagE) + dsignTagE.length();
                addStart = inContent.substring(0, dsignTagStart);
                addEnd = inContent.substring(dsignTagEnd, inContent.length());
                buff.append(addStart).append(addEnd);
                inContent = buff.toString();
                buff = new StringBuffer();
            } else {
                int HiEsbMsgEnd = inContent.lastIndexOf("</HIESBMSG>");
                addStart = inContent.substring(0, HiEsbMsgEnd);
                addEnd = inContent.substring(HiEsbMsgEnd, inContent.length());
            }

            // 制作不带原文的数字签名
            String strEnc = "";
            try {
                RSA rsa = RSA.getInstance();
                strEnc = rsa.sign(inContent);
            } catch (UnsupportedEncodingException e) {
                log.error("数字签名失败，不支持的字符集", e);
                throw new BusinessException(ErrorConstant.ERROR_ADD_SIGN);
            } catch (Exception e) {
                log.error("数字签名时出现错误，信息如下", e);
                throw new BusinessException(ErrorConstant.ERROR_ADD_SIGN);
            }
            // LOG.debug("====================dSign
            // source===================================");
            // LOG.debug(inContent);
            // LOG.debug("====================dSign
            // result===================================");
            // LOG.debug(strEnc);
            // LOG.debug("===================================================================");
            buff.append(addStart).append("<DSIGN>").append(strEnc).append("</DSIGN>").append(addEnd);
            return buff.toString();
        } else {
            return inContent;
        }
    }

    /**
     * 验证签名
     *
     * @param inSign
     * @param inContent
     * @return
     * @throws BusinessException
     */
    public static boolean verify(String inSign, String inContent) throws BusinessException {
        boolean isSuccess = false;
        if (ConfigConstant.Constant.WITHDSIGN && StringUtils.isNotEmpty(inContent)) {
            try {
                RSA rsa = RSA.getInstance();
                isSuccess = rsa.verify(inContent, inSign);
            } catch (UnsupportedEncodingException e) {
                log.error("验证签名失败，不支持的字符集", e);
                throw new BusinessException(ErrorConstant.ERROR_VERIFY_SIGN);
            } catch (Exception e) {
                log.error("验证数字签名时出现其他错误", e);
                throw new BusinessException(ErrorConstant.ERROR_VERIFY_SIGN);
            }
        } else {
            isSuccess = true;
        }
        return isSuccess;
    }
}
