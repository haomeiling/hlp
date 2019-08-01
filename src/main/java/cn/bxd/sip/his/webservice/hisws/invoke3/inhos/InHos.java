
package cn.bxd.sip.his.webservice.hisws.invoke3.inhos;

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
@WebServiceClient(name = "InHos", targetNamespace = "http://tempuri.org/", wsdlLocation = "file:/E:/project/sip/src/main/resources/schema/1238_InHos.wsdl")
public class InHos
    extends Service
{

    private final static URL INHOS_WSDL_LOCATION;
    private final static WebServiceException INHOS_EXCEPTION;
    private final static QName INHOS_QNAME = new QName("http://tempuri.org/", "InHos");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/E:/project/sip/src/main/resources/schema/1238_InHos.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        INHOS_WSDL_LOCATION = url;
        INHOS_EXCEPTION = e;
    }

    public InHos() {
        super(__getWsdlLocation(), INHOS_QNAME);
    }

    public InHos(WebServiceFeature... features) {
        super(__getWsdlLocation(), INHOS_QNAME, features);
    }

    public InHos(URL wsdlLocation) {
        super(wsdlLocation, INHOS_QNAME);
    }

    public InHos(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INHOS_QNAME, features);
    }

    public InHos(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InHos(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns InHosSoap
     */
    @WebEndpoint(name = "InHosSoap")
    public InHosSoap getInHosSoap() {
        return super.getPort(new QName("http://tempuri.org/", "InHosSoap"), InHosSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns InHosSoap
     */
    @WebEndpoint(name = "InHosSoap")
    public InHosSoap getInHosSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "InHosSoap"), InHosSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (INHOS_EXCEPTION!= null) {
            throw INHOS_EXCEPTION;
        }
        return INHOS_WSDL_LOCATION;
    }

}
