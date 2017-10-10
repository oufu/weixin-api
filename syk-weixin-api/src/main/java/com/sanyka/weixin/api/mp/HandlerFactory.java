package com.sanyka.weixin.api.mp;

import com.sanyka.weixin.api.mp.message.MessageBase;
import com.sanyka.weixin.api.mp.message.NormalMessageHandler;

/**
 * 输入消息处理器工具类
 * 
 * @author OF
 * @date 2017年10月10日
 */

public class HandlerFactory {

	private static MessageBase messageHandler = null;
	private static String defaultHandler = "com.sanyka.weixin.api.mp.message.MessageHandler";

	public static MessageBase getMessageHandler() {
		if (messageHandler == null) {
			try {
				// 加载处理器
				Class<?> clazz = Class.forName(defaultHandler);
				try {
					messageHandler = (MessageBase) clazz.newInstance();
				} catch (Exception ex) {
					System.out.println("初始化 IMessageHandler 异常：");
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
	private static String defaultNormalHandler = "com.sanyka.weixin.api.mp.message.NormalMessageHandler";

	public static NormalMessageHandler getNormalMessageHandler() {
		if (normalMessageHandler == null) {
			try { // 获取 defaultNormalHandler =
//				Configuration.getProperty("weixin4j.message.handler.normal",
//						defaultNormalHandler);
//				if (Configuration.isDebug()) {
//					System.out
//							.println("微信接受消息处理Hanler:" + defaultNormalHandler);
//				} // 加载处理器
				Class<?> clazz = Class.forName(defaultNormalHandler);
				try {
					normalMessageHandler = (NormalMessageHandler) clazz
							.newInstance();
				} catch (Exception ex) {
					System.out.println("初始化 INormalMessageHandler 异常：");
					ex.printStackTrace();
				}
			} catch (ClassNotFoundException ex) {
				System.out.println("找不到: " + defaultNormalHandler + " 类!");
				ex.printStackTrace();
			}
		}
		return normalMessageHandler;
	}

//	private static IEventMessageHandler eventMessageHandler = null;
	private static String defaultEventHandler = "org.weixin4j.spi.DefaultEventMessageHandler";

	/*
	 * public static IEventMessageHandler getEventMessageHandler() { if
	 * (eventMessageHandler == null) { try { //获取 defaultEventHandler =
	 * Configuration.getProperty("weixin4j.message.handler.event",
	 * defaultEventHandler); if (Configuration.isDebug()) {
	 * System.out.println("微信接受消息处理Hanler:" + defaultEventHandler); } // 加载处理器
	 * Class<?> clazz = Class.forName(defaultEventHandler); try {
	 * eventMessageHandler = (IEventMessageHandler) clazz.newInstance(); } catch
	 * (Exception ex) { System.out.println("初始化 IEventMessageHandler 异常：");
	 * ex.printStackTrace(); } } catch (ClassNotFoundException ex) {
	 * System.out.println("找不到: " + defaultEventHandler + " 类!");
	 * ex.printStackTrace(); } } return eventMessageHandler; }
	 */
}
