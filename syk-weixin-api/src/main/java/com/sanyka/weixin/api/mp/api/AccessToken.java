package com.sanyka.weixin.api.mp.api;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sanyka.weixin.api.mp.AppConfig;
import com.sanyka.weixin.exception.WeixinException;
import com.sanyka.weixin.util.OkHttp;

/**
 * accessToken处理
 * 
 * @author OF
 * @date 2017年10月16日
 */
public class AccessToken {
	/**
	 * 获取普通accessToken<br>
	 * {"access_token":"ACCESS_TOKEN","expires_in":7200}
	 * 
	 * @return json
	 */
	public static Map<String, Object> getAccessToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&"
				+ "appid="
				+ AppConfig.WX_APP_ID
				+ "&secret="
				+ AppConfig.WX_APP_SECRET;
		String json = null;
		JSONObject jobjct = null;
		json = OkHttp.okHttpGet(url);
		jobjct = JSON.parseObject(json);
		return jobjct;
	}

	/**
	 * 网页授权获取accessToken
	 * 
	 * @param code
	 *            微信服务返回
	 * @return
	 */
	public static String getOauthToken(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=" + AppConfig.WX_APP_ID + "&secret="
				+ AppConfig.WX_APP_SECRET + "&code=" + code
				+ "&grant_type=authorization_code";
		String json = OkHttp.okHttpGet(url);
		return json;
	}

	/**
	 * 刷新页面访问 token
	 * 
	 * @param refreshToken
	 *            刷新token
	 * @return
	 */
	public static String getRefreshToken(String refreshToken) {
		String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?"
				+ "appid=" + AppConfig.WX_APP_ID + "&grant_type=refresh_token&"
				+ "refresh_token=" + refreshToken;
		String json = OkHttp.okHttpGet(url);
		return json;
	}

}
