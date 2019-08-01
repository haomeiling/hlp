package cn.bxd.sip.his.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Description: WebService配置类
 * Package: com.bxd.sip.reservation.config
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * 配置ws路径
     */
    @Bean
    public ServletRegistrationBean appointmentDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    /**
     * ws命名空间和wsdl名
     */
    @Bean(name = "appointmentService")
    public MyWsdl11Definition mMyWsdl11Definition(XsdSchema hisConvertTestSchema) {
        MyWsdl11Definition wsdl11Definition = new MyWsdl11Definition();
        wsdl11Definition.setPortTypeName("ArrangeService");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://webservice.sip.bxd.cn/his");
        wsdl11Definition.setSchema(hisConvertTestSchema);
        return wsdl11Definition;
    }

    /**
     * xsd格式校验生成
     */
    @Bean
    public XsdSchema hisConvertTestSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/appointmentService.xsd"));
    }

}