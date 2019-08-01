package cn.bxd.sip.bxd.hispay.factory.impl;

import cn.bxd.sip.bxd.hispay.common.XsdSchemaValidator;
import cn.bxd.sip.bxd.hispay.constant.CharacterConstant;
import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.exception.SystemException;
import cn.bxd.sip.bxd.hispay.factory.IOperation;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
/**
 * Description:消息处理之解析Xml
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:53
 */
@Slf4j
public abstract class BaseXmlParse extends DefaultHandler implements IOperation {

    /*** 当前节点 */
    protected String currentTag = null;

    /*** 当前map */
    protected Map<String, Object> currentMap = null;

    /*** 保存的堆栈 */
    protected Stack<Map<String, Object>> stack = new Stack<>();

    /*** 解析生成的Map */
    protected Map<String, Object> map = null;

    /** 用于保留原请求消息参考 */
    protected String refNo = null;

    /** 存储节点信息 */
    protected StringBuffer buffSax;

    /** 需要创建Map的标签 */
    protected Map<String, String> needMapTags = new HashMap<>();

    /**
     * 解析xml入口
     *
     * @param inMsgStr
     * @throws SystemException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    protected void parse(String inMsgStr) throws SystemException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new ByteArrayInputStream(inMsgStr.getBytes(CharacterConstant.GBK)), this);
        } catch (Exception e) {
                if (log.isErrorEnabled())
                log.error(e.getMessage(), e);
            throw new SystemException("XML解析失败:" + e.getMessage(), ResultCodeConstant.RESULTCODE_90003);
        }
    }

    /**
     * 验证xml合法性
     *
     * @param inMsgStr
     * @return
     */
    protected void validateXml(String inMsgStr) throws SystemException {

        String schameFileName = getSchameFileName();
        String thisClassPath = this.getClass().getResource("").getPath().substring(1);
        String filePath = File.separator + thisClassPath.substring(0, thisClassPath.lastIndexOf("classes")) + "classes";
        String fileName = filePath + File.separator + schameFileName;
        XsdSchemaValidator validator = new XsdSchemaValidator();
        if (!validator.validateXML(fileName, inMsgStr)) {
            if (log.isErrorEnabled())
                log.error("XML validate failed:" + validator.getErrorMsg());
            throw new SystemException("XML validate failed:" + validator.getErrorMsg(),
                    ResultCodeConstant.RESULTCODE_90003);
        }
    }

    /**
     * 初始化创建map标签
     */
    protected void initNeedMapTags() {
        needMapTags.put("HIESBMSG", "HIESBMSG");
        needMapTags.put("HEADER", "HEADER");
        needMapTags.put("BODY", "BODY");
    }

    /**
     * 文档开始
     */
    @Override
    public void startDocument() throws SAXException {
        initNeedMapTags();
        map = new HashMap<String, Object>();
        stack.push(map);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 遇到父节点需要创建Map对象，保存子节点内容
        if (needMapTags.containsKey(qName)) {
            currentMap = new HashMap<String, Object>();
            stack.push(currentMap);
        } else {
            buffSax = new StringBuffer();// 创建buffer对象存储节点数据
        }
        currentTag = qName;// 将正在解析的节点名称赋给preTag
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (needMapTags.containsKey(qName)) {
            Map tmpMap = stack.pop();
            currentMap = stack.peek();
            if (currentMap.containsKey(qName)) {
                Object obj = currentMap.get(qName);
                List<Map<String, Object>> list;
                if (obj instanceof Map) {
                    list = new ArrayList<>();
                    list.add((Map) obj);
                    list.add(tmpMap);
                } else {
                    list = (List) obj;
                    list.add(tmpMap);
                }
                currentMap.put(qName, list);
            } else {
                currentMap.put(qName, tmpMap);
            }
        } else {
            currentMap.put(currentTag, buffSax.toString());
        }

        /**
         * 当解析结束时置为空。这里很重要，例如，当图中画3的位置结束后，会调用这个方法
         * ，如果这里不把preTag置为null，根据startElement(....)方法，preTag的值还是book，当文档顺序读到图
         * 中标记4的位置时，会执行characters(char[] ch, int start, int
         * length)这个方法，而characters(....)方
         * 法判断preTag!=null，会执行if判断的代码，这样就会把空值赋值给book，这不是我们想要的。
         */
        currentTag = null;
    }

    /**
     * 这里是一行一行的获取
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTag != null && !needMapTags.containsKey(currentTag)) {// 是父节点的不写入内容
            String content = new String(ch, start, length);
            buffSax.append(content);// 对于多行的需要缓存起来
        }
    }

    /**
     * 获取校验xml的xsd文件
     *
     * @return
     */
    protected abstract String getSchameFileName();
}
