package cn.bxd.sip.bxd.hispay.common;

import cn.bxd.sip.bxd.hispay.constant.CharacterConstant;
import cn.bxd.sip.bxd.hispay.constant.CommonConstant;
import cn.bxd.sip.bxd.hispay.constant.ErrorConstant;
import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:18
 */
@Slf4j
public class GenerateXmlUtil {

    /**
     * 没有签名的xml生成
     *
     * @param map
     * @return
     * @throws Exception
     */
    public static String generateXmlWithoutSign(Map<String, Object> map) throws BusinessException {
        return getXmlBySax(map, false);
    }

    /**
     * 生成带有签名的xml生成
     *
     * @param map
     * @return
     * @throws Exception
     */
    public static String generateXml(Map<String, Object> map) throws BusinessException {
        return getXmlBySax(map, true);
    }

    /**
     * SAX生成Xml
     *
     * @param map
     * @return
     * @throws Exception
     * @throws BusinessException
     */
    public static String getXmlBySax(Map<String, Object> map, boolean isNeedSign) throws BusinessException {
        try {
            // 创建SAX转换工厂
            SAXTransformerFactory sff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            // 转换处理器，侦听 SAX ContentHandler
            // 解析事件，并将它们转换为结果树 Result
            TransformerHandler th = sff.newTransformerHandler();
            // 将源树转换为结果树
            Transformer transformer = th.getTransformer();
            // 设置字符编码
            transformer.setOutputProperty(OutputKeys.ENCODING, CharacterConstant.GBK);
            // 是否缩进
            transformer.setOutputProperty(OutputKeys.INDENT, CommonConstant.YES);

            StringWriter writer = new StringWriter();
            // 构建转换结果树所需的信息
            Result resultStr = new StreamResult(writer);
            // setResult() 必须在 startDocument() 之前调用
            th.setResult(resultStr);
            th.startDocument();

            // map转换xml
            mapToXml(th, map);

            // 接收文档的结尾的通知
            th.endDocument();

            // 返回
            StringBuffer buff = writer.getBuffer();
            String retXml = isNeedSign ? MsgDealDsign.DetachSign(buff.toString()) : buff.toString();
            return retXml;// 进行数字签名
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("生成xml失败", e);
            throw new BusinessException(ErrorConstant.ERROR_GENERATE_XML, ResultCodeConstant.RESULTCODE_90003);
        }
    }

    /**
     * map转换xml抽象方法
     *
     * @param th
     * @param map
     * @throws SAXException
     */
    private static void mapToXml(TransformerHandler th, Map<String, Object> map) throws SAXException {
        for (Entry<String, Object> tmp : map.entrySet()) {
            String key = tmp.getKey();
            Object obj = tmp.getValue();

            // 行结点属性
            AttributesImpl attr = new AttributesImpl();
            // 节点开始
            if (!(obj instanceof List)) {
                th.startElement("", "", key, attr);
            }

            // 判断是什么类型
            if (obj instanceof List) {
                List<Map<String, Object>> list = (List) obj;
                for (Map<String, Object> tmplist : list) {
                    th.startElement("", "", key, attr);
                    mapToXml(th, tmplist);
                    th.endElement("", "", key);
                }
            } else if (obj instanceof Map) {
                mapToXml(th, (Map) obj);
            } else {
                String value = obj == null ? "" : obj.toString();
                th.characters(value.toCharArray(), 0, value.length());
            }

            // 节点结束
            if (!(obj instanceof List)) {
                th.endElement("", "", key);
            }
        }
    }
}
