package com.sanyka.weixin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.sanyka.weixin.utils.JsonUtil;
import com.sanyka.weixin.utils.file.FileUtil;

/**
 * 读配置文件
 * 
 * @author OF
 * @date 2017年10月17日
 */
public class RedMenu {

	public synchronized static String readJsonFile(String fileName) {
		File f = FileUtil.getFile(fileName, null);
		String jsonStr = FileUtil.readFile(f.getPath());
		return jsonStr;
	}

	/**
	 * 读出菜单 json
	 * 
	 * @param path
	 * @return
	 */
	public static String ReadFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		StringBuffer laststr = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr.append(tempString.trim());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr.toString();
	}
}
