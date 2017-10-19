package com.sanyka.weixin.api.mp.api;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sanyka.weixin.api.mp.AppConfig;
import com.sanyka.weixin.util.OkHttp;

/**
 * 微信网页授权
 * 
 * @author OF
 * @date 2017年10月16日
 */
public class Oauth {
	public static String url = "https://api.weixin.qq.com/sns/userinfo?access_token=";

	/**
	 * <per> {"expire_seconds": 604800, "action_name": "QR_STR_SCENE", //
	 * "action_info": {"scene": {"scene_str": "test"}}} </per>
	 * 
	 * @param accessToken
	 *            访问 Token
	 * @param openid
	 *            用户唯一标识
	 * @return json
	 */
	public static Map<String, Object> doGet(String accessToken, String openid) {
		String user_url = "url" + accessToken + "&openid=" + openid
				+ "&lang=zh_CN";
		String json = OkHttp.okHttpGet(user_url);
		JSONObject obj = JSON.parseObject(json);
		return obj;
	}

	/**
	 * 获取页面授权用户信息
	 * 
	 * @param accessToken
	 *            访问 Token
	 * @param openid
	 *            用户唯一标识
	 * @return json
	 */
	public static Map<String, Object> getUserInfo(String accessToken,
			String openid) {
		String user_url = "https://api.weixin.qq.com/sns/userinfo?"
				+ "access_token=" + accessToken + "&openid=" + openid
				+ "&lang=zh_CN";
		String json = OkHttp.okHttpGet(user_url);
		JSONObject obj = JSON.parseObject(json);
		return obj;
	}

	/**
	 * 认证URL地址
	 * 
	 * @param resultUrl
	 *            回调地址
	 * @param redirectUri
	 *            微信回调地址
	 * @return url
	 */
	public static String oathUrl(String resultUrl, String redirectUri) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ AppConfig.WX_APP_ID
				+ "&redirect_uri="
				+ redirectUri
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo"
				+ "&state="
				+ resultUrl + "#wechat_redirect";
		return url;
	}
}
