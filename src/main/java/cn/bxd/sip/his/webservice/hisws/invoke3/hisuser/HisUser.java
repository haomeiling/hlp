
package cn.bxd.sip.his.webservice.hisws.invoke3.hisuser;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "HisUser", targetNamespace = "http://tempuri.org/", wsdlLocation = "file:/E:/project/sip/src/main/resources/schema/1238_HisUser.wsdl")
public class HisUser
    extends Service
{

    private final static URL HISUSER_WSDL_LOCATION;
    private final static WebServiceException HISUSER_EXCEPTION;
    private final static QName HISUSER_QNAME = new QName("http://tempuri.org/", "HisUser");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/E:/project/sip/src/main/resources/schema/1238_HisUser.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HISUSER_WSDL_LOCATION = url;
        HISUSER_EXCEPTION = e;
    }

    public HisUser() {
        super(__getWsdlLocation(), HISUSER_QNAME);
    }

    public HisUser(WebServiceFeature... features) {
        super(__getWsdlLocation(), HISUSER_QNAME, features);
    }

    public HisUser(URL wsdlLocation) {
        super(wsdlLocation, HISUSER_QNAME);
    }

    public HisUser(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HISUSER_QNAME, features);
    }

    public HisUser(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HisUser(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns HisUserSoap
     */
    @WebEndpoint(name = "HisUserSoap")
    public HisUserSoap getHisUserSoap() {
        return super.getPort(new QName("http://tempuri.org/", "HisUserSoap"), HisUserSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HisUserSoap
     */
    @WebEndpoint(name = "HisUserSoap")
    public HisUserSoap getHisUserSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "HisUserSoap"), HisUserSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HISUSER_EXCEPTION!= null) {
            throw HISUSER_EXCEPTION;
        }
        return HISUSER_WSDL_LOCATION;
    }

}
