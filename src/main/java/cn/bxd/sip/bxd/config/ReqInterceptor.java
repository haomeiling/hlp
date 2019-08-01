package cn.bxd.sip.bxd.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.service.invoker.MethodDispatcher;
import org.apache.cxf.service.model.BindingOperationInfo;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author : cRyann
 * @create 2018-09-27
 **/
@Slf4j
public class ReqInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    /**
     * Instantiates the interceptor to live in a specified phase. The
     * interceptor's id will be set to the name of the implementing class.
     */
    public ReqInterceptor(String phase) {
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
        List<Object> result = MessageContentsList.getContentsList(message);
        Exchange exchange = message.getExchange();
        BindingOperationInfo bop = exchange.getBindingOperationInfo();
        MethodDispatcher md = (MethodDispatcher) exchange.getService().get(MethodDispatcher.class.getName());
        Method method = md.getMethod(bop);
        log.info("HIS InterFace[{}] Req --> {}", method.getName(), JSON.toJSONString(result));
    }
}
