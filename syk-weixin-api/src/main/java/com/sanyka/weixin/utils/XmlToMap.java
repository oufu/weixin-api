package com.sanyka.weixin.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlToMap {
	private static final Log log = LogFactory.getLog(XmlToMap.class);

	@SuppressWarnings("unchecked")
	/**
	 * XmlToMap 转换
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Map xml2Map(String xml) throws Exception {
		Map obj = new HashMap();

		try {
			InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(is);
			Element root = doc.getRootElement();
			obj.putAll(iterateElement(root));
			// String object = JsonUtil.objectToJson(iterateElement(root));
			return obj;
		} catch (Exception e) {
			log.error("传入XML后转换MAP出现错误=====>>", e);
			throw e;
		}
	}

	/**
	 * 一个迭代方法
	 * 
	 * @param element
	 *            : org.jdom.Element
	 * @return java.util.Map 实例
	 */
	@SuppressWarnings("unchecked")
	private static Map iterateElement(Element element) {
		List jiedian = element.getChildren();
		Element et = null;
		Map obj = new HashMap();
		List list = null;
		for (int i = 0; i < jiedian.size(); i++) {
			list = new LinkedList();
			et = (Element) jiedian.get(i);
			if (et.getTextTrim().equals("")) {
				if (et.getChildren().size() == 0)
					continue;
				if (obj.containsKey(et.getName())) {
					list = (List) obj.get(et.getName());
				}
				list.add(iterateElement(et));
				obj.put(et.getName(), list);
			} else {
				if (obj.containsKey(et.getName())) {
					list = (List) obj.get(et.getName());
				}
				// list.add(et.getTextTrim());
				obj.put(et.getName(), et.getTextTrim());
			}
		}
		return obj;
	}

	/**
	 * XML tO Map
	 * 
	 * @param xmlString
	 *            xml 流
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Map<String, Object> getMapFromXML(String xmlString) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
			javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory
					.newInstance();
			javax.xml.parsers.DocumentBuilder builder = factory
					.newDocumentBuilder();
			java.io.InputStream is = Util.getStringStream(xmlString);
			org.w3c.dom.Document document = builder.parse(is);
			// 获取到document里面的全部结点
			NodeList allNodes = document.getFirstChild().getChildNodes();
			Node node;
			int i = 0;
			while (i < allNodes.getLength()) {
				node = allNodes.item(i);
				if (node instanceof Element) {
					map.put(node.getNodeName(), node.getTextContent());
				}
				i++;
			}

		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

}
