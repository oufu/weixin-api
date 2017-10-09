package com.sanyka.weixin.utils.transXml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 类名： TransXmlTools<br>
 * 功能：交易xml处理类<br>
 * 版本： 1.0<br>
 * 日期： 2016年1月8日<br>
 * 作者： OF<br>
 * 说明：<br>
 */
public class TransXmlTools {

	/**
	 * XmlToMap 转换
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> xml2Map(String xml) {
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(is);
			Element root = doc.getRootElement();
			obj.putAll(iterateElement(root));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("传入XML后转换MAP出现错误:");
		}
		return obj;
	}

	/**
	 * 迭代方法
	 * 
	 * @param element
	 * @return  Map 实例
	 */
	@SuppressWarnings("unchecked")
	private static Map iterateElement(Element element) {
		List jiedian = element.getChildren();
		Element et = null;
		Map<String, Object> obj = new HashMap<String, Object>();
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
				obj.put(et.getName(), et.getTextTrim());
			}
		}
		return obj;
	}
}
