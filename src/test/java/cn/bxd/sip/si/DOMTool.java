package cn.bxd.sip.si;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.NodeList;

public class DOMTool {
	
	public static Document loadDocumentFromStr(String xml){
		Document document=null;
		try {
			document=DocumentHelper.parseText(xml);
		} catch (Exception e) {
			System.out.println(e.getCause());
		}
		return document;
	}
	
	public static Element getHeaderOrBody(Document document,String singleNode){
		Element header=null;
		try {
			 Element root=document.getRootElement();
			 header=root.element(singleNode);
		} catch (Exception e) {
		}
		return header;
	}
	public static Element getElement(Element element,String singleNode){
		Element elementtemp=null;
		try {
			elementtemp=element.element(singleNode);
		} catch (Exception e) {
		}
		return elementtemp;
	}
	
	public static String getValue(String xml, String nodeName, String filterAttribute, 
			String valueAttribute, String getAttribute) {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			//通过节点名获取xml文件中所有该名节点列表
		try {
			 DocumentBuilder builder = builderFactory.newDocumentBuilder();
			 ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes());
			 org.w3c.dom.Document document = builder.parse(is);
			NodeList nodeList = document.getElementsByTagName(nodeName);
			//定义属性值
			String valueStr = null;
			
			//遍历节点列表，获取某个节点的某个属性的值
			for(int i=0; i<nodeList.getLength(); i++){
				if(nodeList.item(i).getAttributes().getNamedItem(filterAttribute).getNodeValue().equals(valueAttribute)){
					valueStr = nodeList.item(i).getAttributes().getNamedItem(getAttribute).getNodeValue();
					return valueStr;
				}
			}
		}catch (Exception e) {
			e.getCause();
		}
			return null;	
		}
}
