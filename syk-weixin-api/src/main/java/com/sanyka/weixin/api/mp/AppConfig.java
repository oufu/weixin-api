package com.sanyka.weixin.api.mp;

/**
 * 公共配置文件
 * 
 * @author OF
 * @date 2017年10月10日
 */
public class AppConfig {
	/** 应用唯一标识 */
	public static String WX_APP_ID = "wx11bc2e6b3b37df0b";
	/** 密钥 */
	public static String WX_APP_SECRET = "8590ab61ba00c33dd2105dcfa25fa1a9";
	/** 令牌 */
	public static String WX_TOKEN = "oufu";
	/** 访问token */
	public static String WX_ACCESS_TOKEN = "WX_ACCESS_TOKEN";
	/** 超时时间 */
	public static String WX_APP_TIMEOUT = "3000";
	/** 编码 */
	public static String WX_APP_CHARSET = "utf-8";
	/** 消息处理类 */
	public static String WX_APP_HANDLER = "com.sanyka.weixin.api.mp.message.MessageHandler";
	/** 普通消息处理类 */
	public static String WX_APP_MESSAGE_HANDLER_NORMAL = "com.sanyka.weixin.api.mp.message.NormalMessageHandler";
	/** 事件处理类 */
	public static String WX_WX_APP_MESSAGE_HANDLER_EVENT = "com.sanyka.weixin.api.mp.message.EventMessageHandler";

	/** 默认配置文件路径 */
	public static String WX_CONFIG_FILE = "weixin.properties";

	/**
	 * 配置文件初始化
	 */
	public static void init() {
		WX_APP_ID = WeixinProperties.getPropertyValue("wx_app_id");
		WX_APP_SECRET = WeixinProperties.getPropertyValue("wx_app_secret");
		WX_TOKEN = WeixinProperties.getPropertyValue("wx_app_token");
		WX_APP_TIMEOUT = WeixinProperties.getPropertyValue("wx_app_timeout");
		WX_APP_CHARSET = WeixinProperties.getPropertyValue("wx_app_charset");
		WX_APP_MESSAGE_HANDLER_NORMAL = WeixinProperties
				.getPropertyValue("wx_app_message_handler_normal");
		WX_WX_APP_MESSAGE_HANDLER_EVENT = WeixinProperties
				.getPropertyValue("wx_app_message_handler_event");
	}

	/**
	 * 手动初始化
	 * 
	 * @param config
	 */
	public static void configConstant() {

	}
}
