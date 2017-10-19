package com.sanyka.weixin.api.mp.message;

import java.util.Map;

/**
 * 普通消息处理
 * 
 * @author OF
 * @date 2017年10月10日
 */
public abstract class NormalMessageBase {

	/**
	 * 文字内容的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage textTypeMsg(Map<String, String> params) {
		return null;
	}

	/**
	 * 图片类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage imageTypeMsg(Map<String, String> msg) {
		return null;
	}

	/**
	 * 语音类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage voiceTypeMsg(Map<String, String> msg) {
		return null;
	}

	/**
	 * 视频类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage videoTypeMsg(Map<String, String> msg) {
		return null;
	}

	/**
	 * 小视频类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage shortVideoTypeMsg(Map<String, String> msg) {
		return null;
	}

	/**
	 * 地理位置类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage locationTypeMsg(Map<String, String> params) {
		return null;
	}

	/**
	 * 链接类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage linkTypeMsg(Map<String, String> msg) {
		return null;
	}
}
