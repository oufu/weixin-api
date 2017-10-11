package com.sanyka.weixin.api.mp;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
/**
 * 配置文件工具类
 * @author OF
 * @date 2017年10月11日
 */
public class WeixinProperties {
	private static Properties props = new Properties();
	public static void init () {
		InputStream is = null;
		try {
			is = WeixinProperties.class.getClassLoader().getResourceAsStream(
					AppConfig.WX_CONFIG_FILE);
			props.load(is);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static String getPropertyValue(String key) {
		String reuslt = null;
		try {
			reuslt = new String(props.getProperty(key).getBytes("ISO-8859-1"),
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reuslt;
	}
}
