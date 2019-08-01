package cn.bxd.sip.si.utils;

import cn.bxd.sip.si.model.dto.Header;
import cn.bxd.sip.si.model.dto.HeaderBody;
import lombok.extern.slf4j.Slf4j;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import static cn.bxd.sip.si.utils.MD5Util.signatureXmlStr;

/**
 * Description: XStreamAlias xml和java bean互转
 * Package: com.bxd.utils
 *
 * @author Leeves
 * @date 2018-03-07
 */
@Component
@Slf4j
public class XStreamUtil {


    /**
     * 根据类型，生成xml string
     *
     * @param msgType 消息类型
     */
    public static <T> String generateXML(String msgType, T t) {
        Header header = new Header();
        header.setMsgType(msgType);
        header.setMsgID(SystemResponseMsg.getMsgNo(msgType));
        header.setMsgTime(SystemResponseMsg.getTimeEndSs());
        HeaderBody<T> headerBody = new HeaderBody<>();
        headerBody.setHeader(header);
        headerBody.setBody(t);
        return signatureXmlStr(beanToXml(headerBody));
    }

    /**
     * 根据类型，生成xml string
     *
     * @param msgType 消息类型
     */
    public <T> String generatePayXML(String msgType, T t) {
        Header header = new Header();
        header.setMsgType(msgType);
        header.setMsgID(SystemResponseMsg.getMsgNo(msgType));
//        header.setMsgID(SystemResponseMsg.getMsgID(mSiOrderService.getMsgID()));
        header.setMsgTime(SystemResponseMsg.getTimeEndSs());
        HeaderBody<T> headerBody = new HeaderBody<>();
        headerBody.setHeader(header);
        headerBody.setBody(t);
        return signatureXmlStr(beanToXml(headerBody));
    }


    /**
     * 根据类型，生成xml string
     *
     * @param msgType 消息类型
     */
    public static <T> String generateTestPayXML(String msgType, T t) {
        Header header = new Header();
        header.setMsgType(msgType);
        header.setMsgID(SystemResponseMsg.getTestMsgNo());
        header.setMsgTime(SystemResponseMsg.getTimeEndSs());
        header.setVersion("v1.01");
        header.setAppID("45090001");
        HeaderBody<T> headerBody = new HeaderBody<>();
        headerBody.setHeader(header);
        headerBody.setBody(t);
        return signatureXmlStr(beanToXml(headerBody));
    }

    public static String beanToXml(Object obj) {
        XStream xStream = new XStream();
        //xstream使用注解转换
        xStream.processAnnotations(obj.getClass());
        xStream.aliasSystemAttribute(null, "class");
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" + xStream.toXML(obj);
    }

    @SuppressWarnings("unchecked")
    public static <T> T xmlToObject(String xml, Class<T> cls) {
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{cls});
        //xstream使用注解转换
        xstream.processAnnotations(cls);
        return (T) xstream.fromXML(xml);
    }

    /**
     * @param
     * @author cRyann
     * @Description
     **/
    public static String xmlToJson(String xml){
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            //设置缩进
            String jsonPrettyPrintString = xmlJSONObj.toString(4);
            //输出格式化后的json
            return jsonPrettyPrintString;
        } catch (JSONException e) {
            log.error("",e);
            return null;
        }
    }
}
