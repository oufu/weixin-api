package com.sanyka.weixin.utils.transXml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sanyka.weixin.exception.ErrorException;

import freemarker.template.TemplateException;

/**
 * 类名： transUtil<br>
 * 功能：交易工具类<br>
 * 版本： 1.0<br>
 * 日期： 2016年1月8日<br>
 * 作者： OF<br>
 * 版权：开科维识<br>
 * 说明：交易请求数据进行xml打包，xml解析。<br>
 */

public class TransUtil {
	/**
	 * 数据打包
	 * 
	 * @param pathName
	 * @param data
	 * @return
	 * @throws ErrorException
	 */
	public static String pack(String pathName, Map data) throws ErrorException {
		String result = null;

		if (pathName.isEmpty()) {
			throw new ErrorException("模板名不能为空！");
		}
		if (data.isEmpty()) {
			throw new ErrorException("打包数据不能为空！");
		}
		try {

			result = TempleteManager.process(pathName, data);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorException("数据打包异常！");
		}

		return result;
	}

	/**
	 * 解包
	 * 
	 * @param xml
	 *            xml数据
	 * @return 返回 map
	 * @throws ErrorException
	 */
	public static Map unPack(String xml) throws ErrorException {
		Map result = null;
		if (xml.isEmpty()) {
			throw new ErrorException("xml值不能为空！");
		}
		result = TransXmlTools.xml2Map(xml);
		return result;
	}

	public static void main(String[] args) throws IOException,
			TemplateException, ErrorException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trans_no", "T100000000011");
		map.put("trans_name", "支付交易");
		map.put("amt", "1000");
		// 打包
		String result = pack("100010", map);
		System.out.println("打包后的数据：" + result);
		// 解包
		Map source = unPack(result);
		System.out.println("解包后的数据 " + source);

	}
}
