package com.sanyka.weixin.api.mp.api;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sanyka.weixin.util.OkHttp;

/**
 * 用户管理
 * 
 * @author OF
 * @date 2017年10月16日
 */
public class UserInfo {
	/**
	 * 获取用户基本信息
	 * 
	 * @param accessToken
	 *            访问token
	 * @param openid
	 *            用户唯一标识
	 * @return json
	 */
	public static Map<String, Object> getUserInfo(String accessToken,
			String openid) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?"
				+ "access_token=" + accessToken + "&openid=" + openid
				+ "&lang=zh_CN";
		String json = OkHttp.okHttpGet(url);
		JSONObject obj = JSON.parseObject(json);
		return obj;

	}
}
