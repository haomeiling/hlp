package cn.bxd.sip.bxd.webservice.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.service.invoker.MethodDispatcher;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author : cRyann
 * @create 2018-09-27
 **/
@Slf4j
public class RequsetInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    /**
     * Instantiates the interceptor to live in a specified phase. The
     * interceptor's id will be set to the name of the implementing class.
     */
    public RequsetInterceptor(String phase) {
        super(phase);
    }

    /**
     * Intercepts a msgws.
     * Interceptors should NOT invoke handleMessage or handleFault
     * on the next interceptor - the interceptor chain will
     * take care of this.
     *
     * @param message
     */
    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        String remteAddr = "";
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        if (request != null) {
            remteAddr = request.getRemoteAddr();
        }
        Object result = MessageContentsList.getContentsList(message).get(0);
        message.put("paramsMap", result);
        Exchange exchange = message.getExchange();
        BindingOperationInfo bop = exchange.getBindingOperationInfo();
        MethodDispatcher md = (MethodDispatcher) exchange.getService().get(MethodDispatcher.class.getName());
        Method method = md.getMethod(bop);
        log.info(" [{}] be Invoked From[{}] ---> {}", method.getName(), remteAddr, JSON.toJSONString(result));
    }
}
