package cn.bxd.sip.bxd.model;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-18
 **/
@Data
public class SmsSend {
    String userName;
    String userPwd;
    String contactPhone;
    Object contentSms;
    String msgId;
    String templateCode;

}
