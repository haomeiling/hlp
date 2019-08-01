package cn.bxd.sip.his.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import lombok.extern.slf4j.Slf4j;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * Description: Jaxb xml和java bean互转
 * Package: com.bxd.utils
 *
 * @author Leeves
 * @date 2018-01-18
 */
@Slf4j
public class JaxbUtil {
    /**
     * JavaBean转换成xml
     * @param obj
     * @param encoding
     * @return
     */
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            log.error("",e);
        }

        return result;
    }

    /**
     * xml转换成JavaBean
     * @param xml
     * @param c
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) throws JAXBException {
        T t = null;
//        try {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        t = (T) unmarshaller.unmarshal(new StringReader(xml));
//        } catch (Exception e) {
//        	LOG.error("从His端，xml转换成JavaBean失败", e);
//			throw new BusinessException("调用His Webservice异常，从His端，xml转换成JavaBean失败");
//        }
        return t;
    }
}
