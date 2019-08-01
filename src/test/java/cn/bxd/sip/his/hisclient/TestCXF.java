package cn.bxd.sip.his.hisclient;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.*;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-10-02
 * Time: 11:28
 */
public class TestCXF {
    private static final String SERVICE="file:/E:/project/sip/src/main/resources/schema/Service.wsdl";
    private static Client noInstance ;//延迟加载

    @Test
    public  void testCXF()  throws Exception {
        syncNoInit();
        Object[] objects = noInstance.invoke("queryPatientInfoByIdNO", " "," ","452528198009278777");
        System.out.println(objects[0].toString());

    }


    public static Client getClient() {
        syncNoInit();
        return noInstance;
    }

    private static synchronized void syncNoInit() {
        if (noInstance == null) {
            //CXF CLIENT
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            noInstance = dcf.createClient(SERVICE);
            /**
             * 增加以下代码解决异常问题
             * org.apache.cxf.interceptor.Fault: Could not send Message.
             at org.apache.cxf.interceptor.MessageSenderInterceptor$MessageSenderEndingInterceptor.handleMessage(MessageSenderInterceptor.java:64)
             */
            HTTPConduit http = (HTTPConduit) noInstance.getConduit();
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
            httpClientPolicy.setConnectionTimeout(30000);// 连接超时(毫秒)
            httpClientPolicy.setAllowChunking(false);// 取消块编码
            httpClientPolicy.setReceiveTimeout(180000);// 响应超时(毫秒)
            http.setClient(httpClientPolicy);
        }
    }

    private final static QName SERVICE_QNAME = new QName("http://tempuri.org/", "Service");

    public static void main(String[] args) throws Exception {
        // 创建动态客户端
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(SERVICE, SERVICE_QNAME);
        ClientImpl clientImpl = (ClientImpl) client;
        Endpoint endpoint = clientImpl.getEndpoint();
        List<ServiceInfo> serviceInfos = endpoint.getService().getServiceInfos();
        ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
        QName bindingName = new QName("http://tempuri.org/",
                "ServiceSoap");
        BindingInfo binding = serviceInfo.getBinding(bindingName);
        QName opName = new QName("http://tempuri.org/", "Service");
        BindingOperationInfo boi = binding.getOperation(opName);
        BindingMessageInfo inputMessageInfo = null;
        if (!boi.isUnwrapped()) {
            //OrderProcess uses document literal wrapped style.
            inputMessageInfo = boi.getWrappedOperation().getInput();
        } else {
            inputMessageInfo = boi.getUnwrappedOperation().getInput();
        }
        List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
        // only one part.
        MessagePartInfo partInfo = parts.get(0);
        Class<?> partClass = partInfo.getTypeClass();
        System.out.println(partClass.getCanonicalName()); // GetAgentDetails
        Object inputObject = partClass.newInstance();
        // 取得字段的set方法并赋值
//        PropertyDescriptor partPropertyDescriptor = new PropertyDescriptor("age", partClass);
//        Method userNoSetter = partPropertyDescriptor.getWriteMethod();
//        userNoSetter.invoke(inputObject,"20");
//
        // 取得字段的set方法并赋值
        PropertyDescriptor partPropertyDescriptor2 = new PropertyDescriptor("name", partClass);
        Method productCodeSetter = partPropertyDescriptor2.getWriteMethod();
        productCodeSetter.invoke(inputObject, " ", " ", "452528198009278777");
        Object[] result = client.invoke(opName, inputObject);
        System.out.println(result[0]);
    }


}
