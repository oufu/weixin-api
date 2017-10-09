package com.sanyka.weixin.utils;

import org.dom4j.Element;

/**
 * XML文件操作工具类
 */
public class XmlUtil
{
	/**
	 * 取得节点的字符串属性值，如果该属性不存在，则返回""
	 */
	public static String getAttributeString(Element el, String attributeName)
	{
		return getAttributeString(el, attributeName, "");
	}

	/**
	 * 取得节点的字符串属性值，如果该属性不存在或为空，则返回defaultValue值
	 */
	public static String getAttributeString(Element el, String attributeName, String defaultValue)
	{
		String value = Tools.trimString(el.attributeValue(attributeName));
		if (Tools.strIsEmpty(value))
		{
			value = defaultValue;
		}
		return value;
	}

	/**
	 * 取得节点的布尔属性值，如果该属性不存在或为空，则返回defaultValue值
	 */
	public static Boolean getAttributeBoolean(Element el, String attributeName, Boolean defaultValue)
	{
		String str = Tools.trimString(el.attributeValue(attributeName));
		Boolean value;
		if (Tools.strIsEmpty(str))
		{
			value = defaultValue;
		} else
		{
			value = Tools.str2Boolean(str);
		}
		return value;
	}

	/**
	 * 取得节点的整型属性值，如果该属性不存在或为空，则返回defaultValue值
	 */
	public static Integer getAttributeInt(Element el, String attributeName, Integer defaultValue)
	{
		String str = Tools.trimString(el.attributeValue(attributeName));
		Integer value;
		if (Tools.strIsEmpty(str))
		{
			value = defaultValue;
		} else
		{
			value = Tools.str2Int(str);
		}
		return value;
	}
	
	/***
	 * 如果element!=null时返回element.getTextTrim的值，如果element==null则返回""<br />
	 * 此方法不返回null
	 */
	public static String getElementTextTrim(Element element)
	{
		if (element == null)
			return "";
		else
			return element.getTextTrim();
	}
}
