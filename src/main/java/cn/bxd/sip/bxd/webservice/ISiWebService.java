package cn.bxd.sip.bxd.webservice;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ISiWebService {
    @WebMethod
    String RecvMsg(String inMsgStr);
}