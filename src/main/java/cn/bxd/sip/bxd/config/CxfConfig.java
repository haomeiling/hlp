package cn.bxd.sip.bxd.config;

import cn.bxd.sip.bxd.hispay.HisPayWebService;
import cn.bxd.sip.bxd.webservice.ISiWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @Version 1.0
 * @Author haomeiling
 * @Date 2018-03-02
 */
@Configuration
public class CxfConfig {

	@Autowired
	private Bus bus;

	@Autowired
    ISiWebService siWebService;

	@Autowired
	HisPayWebService hisPayWebService;

	/** JAX-WS **/
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, siWebService);
		endpoint.publish("/sip");
		return endpoint;
	}


	@Bean
	public Endpoint endpointHisPayWebService(){
		EndpointImpl endpoint=new EndpointImpl(bus,hisPayWebService);
		endpoint.publish("/hisPayWebservice");
		return endpoint;
	}

}
