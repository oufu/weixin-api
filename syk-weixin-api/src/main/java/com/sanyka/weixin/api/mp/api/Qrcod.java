package com.sanyka.weixin.api.mp.api;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sanyka.weixin.util.OkHttp;

/**
 * 带参数二维码生成
 * <p>
 * {"expire_seconds": 604800, "action_name": "QR_STR_SCENE", "action_info":
 * {"scene": {"scene_str": "test"}}}
 * </p>
 * 
 * @author OF
 * @date 2017年10月16日
 */
public class Qrcod {
	public static String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?"
			+ "access_token=";

	/**
	 * 生成带参数的二维码<br>
	 * 
	 * @param accessToken
	 *            访问 Token
	 * @param expire
	 *            过期时间单位：秒
	 * @param actionName
	 *            QR_STR_SCENE 临时/永久二维码
	 * @param sceneStr
	 *            自定义数据
	 * @return json
	 */
	public static Map<String, Object> creatQrcod(String accessToken,
			String expire, String actionName, String scenestr) {
		JSONObject sceneStr = new JSONObject();
		sceneStr.put("scene_str", scenestr);
		JSONObject scene = new JSONObject();
		scene.put("scene", sceneStr);
		JSONObject data = new JSONObject();
		data.put("expire_seconds", expire);
		data.put("action_name", actionName);
		data.put("action_info", scene);
		String json = OkHttp.okHttpsPostJson(url + accessToken,
				data.toJSONString());
		JSONObject obj = JSON.parseObject(json);
		return obj;
	}

	/**
	 * 永久二维码
	 * 
	 * @param accessToken
	 *            访问 Token
	 * @param expire
	 *            过期时间单位：秒
	 * @param actionName
	 *            QR_STR_SCENE 临时/永久二维码
	 * @param sceneStr
	 *            自定义数据
	 * @return
	 */
	public static Map<String, Object> limitQrcod(String accessToken,
			String expire, String sceneStr) {
		return creatQrcod(accessToken, expire, "QR_LIMIT_STR_SCENE", sceneStr);

	}

	/**
	 * 临时二维码
	 * 
	 * @param accessToken
	 *            访问 Token
	 * @param expire
	 *            过期时间单位：秒
	 * @param actionName
	 *            QR_STR_SCENE 临时/永久二维码
	 * @param sceneStr
	 *            自定义数据
	 * @return
	 */
	public static Map<String, Object> strQrcod(String accessToken,
			String expire, String sceneStr) {
		return creatQrcod(accessToken, expire, "QR_STR_SCENE", sceneStr);

	}

	private static String showQrcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

	/**
	 * 通过ticket换取二维码地址
	 * 
	 * @param ticket
	 *            换取二维码参数
	 * @return String url
	 */
	public static String getShowQrcodeUrl(String ticket) {
		return showQrcodeUrl + ticket;
	}
}
