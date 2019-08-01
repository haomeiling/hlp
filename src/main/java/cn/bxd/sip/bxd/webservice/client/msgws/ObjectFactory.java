
package cn.bxd.sip.bxd.webservice.client.msgws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.bxd.sip.bxd.webservice.client.msgws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendMsg_QNAME = new QName("http://webservice.messagecenter.efc.com/", "sendMsg");
    private final static QName _SendMsgResponse_QNAME = new QName("http://webservice.messagecenter.efc.com/", "sendMsgResponse");
    private final static QName _UserMessageQueryResponse_QNAME = new QName("http://webservice.messagecenter.efc.com/", "userMessageQueryResponse");
    private final static QName _MessageUnreadCount_QNAME = new QName("http://webservice.messagecenter.efc.com/", "messageUnreadCount");
    private final static QName _SendSmsMsgResponse_QNAME = new QName("http://webservice.messagecenter.efc.com/", "sendSmsMsgResponse");
    private final static QName _SendSmsMsg_QNAME = new QName("http://webservice.messagecenter.efc.com/", "sendSmsMsg");
    private final static QName _UserMessageQuery_QNAME = new QName("http://webservice.messagecenter.efc.com/", "userMessageQuery");
    private final static QName _MessageException_QNAME = new QName("http://webservice.messagecenter.efc.com/", "MessageException");
    private final static QName _MessageUnreadCountResponse_QNAME = new QName("http://webservice.messagecenter.efc.com/", "messageUnreadCountResponse");
    private final static QName _MessageReadResponse_QNAME = new QName("http://webservice.messagecenter.efc.com/", "messageReadResponse");
    private final static QName _MessageRead_QNAME = new QName("http://webservice.messagecenter.efc.com/", "messageRead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.bxd.sip.bxd.webservice.client.msgws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserMessageQueryResponse }
     * 
     */
    public UserMessageQueryResponse createUserMessageQueryResponse() {
        return new UserMessageQueryResponse();
    }

    /**
     * Create an instance of {@link SendSmsMsgResponse }
     * 
     */
    public SendSmsMsgResponse createSendSmsMsgResponse() {
        return new SendSmsMsgResponse();
    }

    /**
     * Create an instance of {@link MessageUnreadCount }
     * 
     */
    public MessageUnreadCount createMessageUnreadCount() {
        return new MessageUnreadCount();
    }

    /**
     * Create an instance of {@link UserMessageQuery }
     * 
     */
    public UserMessageQuery createUserMessageQuery() {
        return new UserMessageQuery();
    }

    /**
     * Create an instance of {@link SendSmsMsg }
     * 
     */
    public SendSmsMsg createSendSmsMsg() {
        return new SendSmsMsg();
    }

    /**
     * Create an instance of {@link SendMsg }
     * 
     */
    public SendMsg createSendMsg() {
        return new SendMsg();
    }

    /**
     * Create an instance of {@link SendMsgResponse }
     * 
     */
    public SendMsgResponse createSendMsgResponse() {
        return new SendMsgResponse();
    }

    /**
     * Create an instance of {@link MessageReadResponse }
     * 
     */
    public MessageReadResponse createMessageReadResponse() {
        return new MessageReadResponse();
    }

    /**
     * Create an instance of {@link MessageRead }
     * 
     */
    public MessageRead createMessageRead() {
        return new MessageRead();
    }

    /**
     * Create an instance of {@link MessageException }
     * 
     */
    public MessageException createMessageException() {
        return new MessageException();
    }

    /**
     * Create an instance of {@link MessageUnreadCountResponse }
     * 
     */
    public MessageUnreadCountResponse createMessageUnreadCountResponse() {
        return new MessageUnreadCountResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMsg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "sendMsg")
    public JAXBElement<SendMsg> createSendMsg(SendMsg value) {
        return new JAXBElement<SendMsg>(_SendMsg_QNAME, SendMsg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMsgResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "sendMsgResponse")
    public JAXBElement<SendMsgResponse> createSendMsgResponse(SendMsgResponse value) {
        return new JAXBElement<SendMsgResponse>(_SendMsgResponse_QNAME, SendMsgResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserMessageQueryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "userMessageQueryResponse")
    public JAXBElement<UserMessageQueryResponse> createUserMessageQueryResponse(UserMessageQueryResponse value) {
        return new JAXBElement<UserMessageQueryResponse>(_UserMessageQueryResponse_QNAME, UserMessageQueryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageUnreadCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "messageUnreadCount")
    public JAXBElement<MessageUnreadCount> createMessageUnreadCount(MessageUnreadCount value) {
        return new JAXBElement<MessageUnreadCount>(_MessageUnreadCount_QNAME, MessageUnreadCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSmsMsgResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "sendSmsMsgResponse")
    public JAXBElement<SendSmsMsgResponse> createSendSmsMsgResponse(SendSmsMsgResponse value) {
        return new JAXBElement<SendSmsMsgResponse>(_SendSmsMsgResponse_QNAME, SendSmsMsgResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSmsMsg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "sendSmsMsg")
    public JAXBElement<SendSmsMsg> createSendSmsMsg(SendSmsMsg value) {
        return new JAXBElement<SendSmsMsg>(_SendSmsMsg_QNAME, SendSmsMsg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserMessageQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "userMessageQuery")
    public JAXBElement<UserMessageQuery> createUserMessageQuery(UserMessageQuery value) {
        return new JAXBElement<UserMessageQuery>(_UserMessageQuery_QNAME, UserMessageQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "MessageException")
    public JAXBElement<MessageException> createMessageException(MessageException value) {
        return new JAXBElement<MessageException>(_MessageException_QNAME, MessageException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageUnreadCountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "messageUnreadCountResponse")
    public JAXBElement<MessageUnreadCountResponse> createMessageUnreadCountResponse(MessageUnreadCountResponse value) {
        return new JAXBElement<MessageUnreadCountResponse>(_MessageUnreadCountResponse_QNAME, MessageUnreadCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageReadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "messageReadResponse")
    public JAXBElement<MessageReadResponse> createMessageReadResponse(MessageReadResponse value) {
        return new JAXBElement<MessageReadResponse>(_MessageReadResponse_QNAME, MessageReadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageRead }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.messagecenter.efc.com/", name = "messageRead")
    public JAXBElement<MessageRead> createMessageRead(MessageRead value) {
        return new JAXBElement<MessageRead>(_MessageRead_QNAME, MessageRead.class, null, value);
    }

}
