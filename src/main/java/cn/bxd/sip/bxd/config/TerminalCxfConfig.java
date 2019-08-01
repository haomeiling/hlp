package cn.bxd.sip.bxd.config;

import cn.bxd.sip.bxd.webservice.IRegWebService;
import cn.bxd.sip.bxd.webservice.interceptor.RequsetInterceptor;
import cn.bxd.sip.bxd.webservice.interceptor.ResponseInterceptor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * author : cRyann
 * @create 2018-08-29
 **/

@Configuration
public class TerminalCxfConfig {
    private static final String WS_PACKAGE="cn.bxd.sip.bxd.webservice.impl.";
    @Autowired
    private Bus bus;
    @Autowired
    private YmlTerminalConfig ymlTerminalConfig;

    @Autowired
    private IRegWebService reg;

   /* @Bean
    public ServletRegistrationBean dispatcherServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet());
        servletRegistrationBean.addUrlMappings("/services/*", "/Client/*");
        return servletRegistrationBean;
    }*/


    @Bean
    public Endpoint endpointCommon() throws Exception{
        String qualifierName=ymlTerminalConfig.getCommonQualifier();
        Object object=Class.forName(WS_PACKAGE+qualifierName);
        EndpointImpl endpoint = new EndpointImpl(bus, ((Class)object).newInstance());
        endpoint.getInInterceptors().add(new RequsetInterceptor(Phase.PRE_LOGICAL));
        endpoint.getOutInterceptors().add(new ResponseInterceptor(Phase.SEND));
        endpoint.publish("/Common");
        return endpoint;
    }

    @Bean
    public Endpoint endpointHisUser() throws Exception{
        String qualifierName=ymlTerminalConfig.getHisUserQualifier();
        Object object=Class.forName(WS_PACKAGE+qualifierName);
        EndpointImpl endpoint = new EndpointImpl(bus, ((Class) object).newInstance());
        endpoint.getInInterceptors().add(new RequsetInterceptor(Phase.PRE_LOGICAL));
        endpoint.getOutInterceptors().add(new ResponseInterceptor(Phase.SEND));
        endpoint.publish("/HisUser");
        return endpoint;
    }

    @Bean
    public Endpoint endpointInHos()throws Exception {
        String qualifierName=ymlTerminalConfig.getInHosQualifier();
        Object object=Class.forName(WS_PACKAGE+qualifierName);
        EndpointImpl endpoint = new EndpointImpl(bus, ((Class) object).newInstance());
        endpoint.getInInterceptors().add(new RequsetInterceptor(Phase.PRE_LOGICAL));
        endpoint.getOutInterceptors().add(new ResponseInterceptor(Phase.SEND));
        endpoint.publish("/InHos");
        return endpoint;
    }

    @Bean
    public Endpoint endpointPay() throws Exception{
        String qualifierName=ymlTerminalConfig.getPayQualifier();
        Object object=Class.forName(WS_PACKAGE+qualifierName);
        EndpointImpl endpoint = new EndpointImpl(bus, ((Class) object).newInstance());
        endpoint.getInInterceptors().add(new RequsetInterceptor(Phase.PRE_LOGICAL));
        endpoint.getOutInterceptors().add(new ResponseInterceptor(Phase.SEND));
        endpoint.publish("/Pay");
        return endpoint;
    }

    @Bean
    public Endpoint endpointReg()throws Exception {
        String qualifierName=ymlTerminalConfig.getRegQualifier();
        Object object=Class.forName(WS_PACKAGE+qualifierName);
        EndpointImpl endpoint = new EndpointImpl(bus, ((Class) object).newInstance());
        endpoint.getInInterceptors().add(new RequsetInterceptor(Phase.PRE_LOGICAL));
        endpoint.getOutInterceptors().add(new ResponseInterceptor(Phase.SEND));
        endpoint.publish("/Reg");
        return endpoint;
    }



}
