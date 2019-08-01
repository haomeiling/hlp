package cn.bxd.sip.his.config;

import org.springframework.util.Assert;
import org.springframework.ws.wsdl.wsdl11.provider.DefaultMessagesProvider;
import org.w3c.dom.Element;

/**
 * Description: 自定义MessagesProvider
 * Package: com.leeve.wsnewdemo.config
 *
 * @author Leeves
 * @version 1.0.0  2018-07-11
 */
public class MySuffixBasedMessagesProvider extends DefaultMessagesProvider {

    public static final String DEFAULT_REQUEST_SUFFIX = "Request";

    public static final String DEFAULT_RESPONSE_SUFFIX = "Response";

    public static final String DEFAULT_FAULT_SUFFIX = "Fault";

    private String requestSuffix = DEFAULT_REQUEST_SUFFIX;

    private String responseSuffix = DEFAULT_RESPONSE_SUFFIX;

    private String faultSuffix = DEFAULT_FAULT_SUFFIX;

    private String customSuffixStr = "Service";


    public String getRequestSuffix() {
        return requestSuffix;
    }

    public void setRequestSuffix(String requestSuffix) {
        Assert.hasText(requestSuffix, "'requestSuffix' must not be empty");
        this.requestSuffix = requestSuffix;
    }

    public String getResponseSuffix() {
        return responseSuffix;
    }

    public void setResponseSuffix(String responseSuffix) {
        Assert.hasText(responseSuffix, "'responseSuffix' must not be empty");
        this.responseSuffix = responseSuffix;
    }

    public String getFaultSuffix() {
        return faultSuffix;
    }

    public void setFaultSuffix(String faultSuffix) {
        Assert.hasText(faultSuffix, "'faultSuffix' must not be empty");
        this.faultSuffix = faultSuffix;
    }

    public String getCustomSuffixStr() {
        return customSuffixStr;
    }

    public void setCustomSuffixStr(String customSuffixStr) {
        this.customSuffixStr = customSuffixStr;
    }

    @Override
    protected boolean isMessageElement(Element element) {
        if (super.isMessageElement(element)) {
            String elementName = getElementName(element);
            Assert.hasText(elementName, "Element has no name");
            return elementName.endsWith(getRequestSuffix()) || elementName.endsWith(getResponseSuffix()) ||
                    elementName.endsWith(getFaultSuffix()) || elementName.endsWith(getCustomSuffixStr());
        }
        else {
            return false;
        }
    }
}