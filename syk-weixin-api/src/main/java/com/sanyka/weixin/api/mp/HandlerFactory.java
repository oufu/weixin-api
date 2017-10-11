package com.sanyka.weixin.api.mp;

import com.sanyka.weixin.api.mp.message.EventMessageHandler;
import com.sanyka.weixin.api.mp.message.MessageBase;
import com.sanyka.weixin.api.mp.message.NormalMessageHandler;

/**
 * 处理器工厂
 * 
 * @author OF
 * @date 2017年10月10日
 */

public class HandlerFactory {

	private static MessageBase messageHandler = null;
	private static String defaultHandler = AppConfig.WX_APP_HANDLER;
	public static MessageBase getMessageHandler() {
		if (messageHandler == null) {
			try {
				// 加载处理器
				Class<?> clazz = Class.forName(defaultHandler);
				try {
					messageHandler = (MessageBase) clazz.newInstance();
				} catch (Exception ex) {
					System.out.println("初始化 messageHandler 异常：");
					ex.printStackTrace();
				}
			} catch (ClassNotFoundException ex) {
				System.out.println("找不到: " + defaultHandler + " 类!");
				ex.printStackTrace();
			}
		}
		return messageHandler;
	}

	private static NormalMessageHandler normalMessageHandler = null;
	private static String defaultNormalHandler = AppConfig.WX_APP_MESSAGE_HANDLER_NORMAL;

	public static NormalMessageHandler getNormalMessageHandler() {
		if (normalMessageHandler == null) {
			try { // 加载处理器
				Class<?> clazz = Class.forName(defaultNormalHandler);
				try {
					normalMessageHandler = (NormalMessageHandler) clazz
							.newInstance();
				} catch (Exception ex) {
					System.out.println("初始化 NormalMessageHandler 异常：");
					ex.printStackTrace();
				}
			} catch (ClassNotFoundException ex) {
				System.out.println("找不到: " + defaultNormalHandler + " 类!");
				ex.printStackTrace();
			}
		}
		return normalMessageHandler;
	}

	 private static EventMessageHandler eventMessageHandler = null;
	private static String defaultEventHandler = AppConfig.WX_WX_APP_MESSAGE_HANDLER_EVENT;

	public static EventMessageHandler getEventMessageHandler() {
		if (eventMessageHandler == null) {
			try { // 获取 defaultEventHandler =
					// 加载处理器
				Class<?> clazz = Class.forName(defaultEventHandler);
				try {
					eventMessageHandler = (EventMessageHandler) clazz
							.newInstance();
				} catch (Exception ex) {
					System.out.println("初始化 EventMessageHandler 异常：");
					ex.printStackTrace();
				}
			} catch (ClassNotFoundException ex) {
				System.out.println("找不到: " + defaultEventHandler + " 类!");
				ex.printStackTrace();
			}
		}
		return eventMessageHandler;
	}

}
