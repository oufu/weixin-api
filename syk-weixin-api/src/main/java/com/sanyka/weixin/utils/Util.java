package com.sanyka.weixin.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

public class Util {
	private static Logger log = LoggerFactory.getLogger(Util.class);

	public static byte[] readInput(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
		return out.toByteArray();
	}

	public static String inputStreamToString(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	public static InputStream getStringStream(String sInputString) {
		ByteArrayInputStream tInputStringStream = null;
		if (sInputString != null && !sInputString.trim().equals("")) {
			tInputStringStream = new ByteArrayInputStream(
					sInputString.getBytes());
		}
		return tInputStringStream;
	}

	public static Object getObjectFromXML(String xml, Class tClass) {
		// 将从API返回的XML数据映射到Java对象
		XStream xStreamForResponseData = new XStream();
		xStreamForResponseData.alias("xml", tClass);
		xStreamForResponseData.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
		return xStreamForResponseData.fromXML(xml);
	}

	public static String getStringFromMap(Map<String, Object> map, String key,
			String defaultValue) {
		if (key == "" || key == null) {
			return defaultValue;
		}
		String result = (String) map.get(key);
		if (result == null) {
			return defaultValue;
		} else {
			return result;
		}
	}

	public static int getIntFromMap(Map<String, Object> map, String key) {
		if (key == "" || key == null) {
			return 0;
		}
		if (map.get(key) == null) {
			return 0;
		}
		return Integer.parseInt((String) map.get(key));
	}

	@SuppressWarnings("rawtypes")
	public static String getURLParam(Map map) {
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		for (Object key : map.keySet()) {
			sb.append(key).append("=").append(map.get(key)).append("&");
		}
		sb.deleteCharAt(sb.lastIndexOf("&"));
		return sb.toString();
	}

	public static void main(String[] args) {
		String s = "#0001*1250/4096-250";
		// -1 表示没有，0表示存
		System.out.println(s.indexOf("#"));
		if (s.indexOf("#") >= 0) {
			String res = s.substring(s.indexOf("#") + 1,s.indexOf("#") + 5);
			System.out.println(res);
			String reding = "1329";
			s = s.replace("#" + res, reding);
			System.out.println(s);
			StringCaculate caculate = new StringCaculate();
			System.out.println(caculate.parse(s).setScale(2,BigDecimal.ROUND_UP));
		} else {
			System.out.println(s.indexOf("#"));
		}
		
		StringCaculate caculate = new StringCaculate();
		System.out.println(caculate.parse("1820*1250/4096-250").setScale(2,BigDecimal.ROUND_UP));
	}
}
