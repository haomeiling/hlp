package cn.bxd.sip.bxd.webservice.client;

import cn.bxd.sip.bxd.var.SIVar;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: WS客户端
 * Package: com.bxd.medicalinsurance.ws
 *
 * @author Leeves
 * @date 2018-03-07
 */

public class WSClient {

    private static Logger log = LoggerFactory.getLogger(WSClient.class);

    private static WSClient wsClient;
    private Client client;

    public static synchronized WSClient getInstance() {
        if (wsClient == null) {
            wsClient = new WSClient();
        }
        return wsClient;
    }

    private WSClient() {
        init();
    }

    private void init(){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        client=dcf.createClient(SIVar.WS.ADDRESS);
        client.getOutInterceptors().add(new LoginInterceptor(SIVar.WS.USERNAME, SIVar.WS.PASSWORD));
    }


    public String sendWS(String inMsgStr) {
        String outMsgStr = "";
        Object[] objects = new Object[0];
        try {
            objects = client.invoke("RecvMsg", inMsgStr);
            outMsgStr = objects[0].toString();
        } catch (Exception e) {
            log.error("",e);
        }
        return outMsgStr;
    }


}
