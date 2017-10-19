package com.sanyka.weixin.api.mp.api;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sanyka.weixin.util.OkHttp;
import com.sanyka.weixin.util.RedMenu;

/**
 * 菜单接口
 * 
 * @author OF
 * @date 2017年10月17日
 */
public class Menu {
	/**
	 * 根据文件读取菜单
	 * 
	 * @param path
	 *            路径
	 * @param accessToken
	 *            访问token
	 * @return 结果
	 */
	public static Map<String, Object> cretaMenuFile(String path,
			String accessToken) {
		String jsonMenu = RedMenu.readJsonFile(path);
		return cretaMenu(accessToken, jsonMenu);
	}

	/**
	 * 创建菜单
	 * 
	 * @param accessToken
	 *            访问token
	 * @param jsonMenu
	 *            菜单json
	 * @return 结果
	 */
	public static Map<String, Object> cretaMenu(String accessToken,
			String jsonMenu) {
		String menuCreateUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		String url = menuCreateUrl.replace("ACCESS_TOKEN", accessToken);
		String json = OkHttp.okHttpsPostJson(url, jsonMenu);
		JSONObject obj = JSON.parseObject(json);
		return obj;
	}
}
