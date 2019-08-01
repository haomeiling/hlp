package cn.bxd.sip.his.hisclient;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface AppService {

    @WebMethod
    String RecvMsg(String inMsgStr);
}