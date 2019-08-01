package cn.bxd.sip.bxd.util;

import cn.bxd.sip.bxd.webservice.client.msgws.MessageCenterWebserviceService;
import cn.bxd.sip.bxd.webservice.client.msgws.MessageCenterWebserviceService_Service;

/**
 * 请求消息中心
 * User: LiSheng
 * Date: 2018-11-12
 * Time: 15:15
 */
public class SendMsgUtils {

    /**
     * 发送消息
     */
    public static String sendMsg(String json){
        MessageCenterWebserviceService_Service instance = MessageCenterWebserviceService_Service.getInstance();
        MessageCenterWebserviceService messageCenterWebserviceService = instance.getMessageCenterWebserviceServicePort();
        return messageCenterWebserviceService.sendMsg(json);

    }

}
