package cn.bxd.sip.his.config;

import org.springframework.ws.wsdl.wsdl11.provider.AbstractPortTypesProvider;

import javax.wsdl.Message;

/**
 * Description: 自定义PortTypesProvider
 * Package: com.leeve.wsnewdemo.config
 *
 * @author Leeves
 * @version 1.0.0  2018-07-10
 */
public class MySuffixBasedPortTypesProvider extends AbstractPortTypesProvider {

    public static final String DEFAULT_REQUEST_SUFFIX = "Service";

    public static final String DEFAULT_RESPONSE_SUFFIX = "Response";

    public static final String DEFAULT_FAULT_SUFFIX = "Fault";

    private String requestSuffix = DEFAULT_REQUEST_SUFFIX;

    private String responseSuffix = DEFAULT_RESPONSE_SUFFIX;

    private String faultSuffix = DEFAULT_FAULT_SUFFIX;

    private String customSuffixStr = "Service";

    @Override
    protected String getOperationName(Message message) {
        String messageName = getMessageName(message);
        if (messageName != null) {
            if (messageName.endsWith(getCustomSuffixStr())) {
                return messageName;
            } else if (messageName.endsWith(getRequestSuffix())) {
                return messageName.substring(0, messageName.length() - getRequestSuffix().length());
            } else if (messageName.endsWith(getResponseSuffix())) {
                return messageName.substring(0, messageName.length() - getResponseSuffix().length());
            } else if (messageName.endsWith(getFaultSuffix())) {
                return messageName.substring(0, messageName.length() - getFaultSuffix().length());
            }
        }
        return null;
    }


    @Override
    protected boolean isInputMessage(Message message) {
        String messageName = getMessageName(message);
        return messageName != null && messageName.endsWith(getRequestSuffix());
    }

    @Override
    protected boolean isOutputMessage(Message message) {
        String messageName = getMessageName(message);
        return messageName != null && messageName.endsWith(getResponseSuffix());
    }

    @Override
    protected boolean isFaultMessage(Message message) {
        String messageName = getMessageName(message);
        return messageName != null && messageName.endsWith(getFaultSuffix());
    }

    private String getMessageName(Message message) {
        return message.getQName().getLocalPart();
    }

    public String getRequestSuffix() {
        return requestSuffix;
    }

    public void setRequestSuffix(String requestSuffix) {
        this.requestSuffix = requestSuffix;
    }

    public String getResponseSuffix() {
        return responseSuffix;
    }

    public void setResponseSuffix(String responseSuffix) {
        this.responseSuffix = responseSuffix;
    }

    public String getFaultSuffix() {
        return faultSuffix;
    }

    public void setFaultSuffix(String faultSuffix) {
        this.faultSuffix = faultSuffix;
    }

    public String getCustomSuffixStr() {
        return customSuffixStr;
    }

    public void setCustomSuffixStr(String customSuffixStr) {
        this.customSuffixStr = customSuffixStr;
    }
}