package com.sanyka.weixin.api.mp.message;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sanyka.weixin.api.mp.message.domain.BaseMessage;
import com.sanyka.weixin.api.mp.message.domain.TextMessage;

/**
 * 普通消息处理
 * 
 * @author OF
 * @date 2017年10月10日
 */
public class NormalMessageHandler extends MessageBase {

	public String execute(HttpServletRequest request) {
		return null;
	}

	/**
	 * 文字内容的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public String textTypeMsg(Map<String, String> params) {
		// 发送方帐号（open_id）
		String fromUserName = params.get("FromUserName");
		// 公众帐号
		String toUserName = params.get("ToUserName");
		// 消息类型
		String msgType = params.get("MsgType");
		// 内容
		String content = params.get("Content");
		String result = "";
		BaseMessage baseMsg = new BaseMessage();
		baseMsg.setCreateTime(new Date().getTime());
		baseMsg.setFromUserName(toUserName);
		baseMsg.setToUserName(fromUserName);

		TextMessage text = new TextMessage(baseMsg);
		if ("1".equals(content)) {
			text.setContent("我不告诉你！");
		} else if ("?".equals(content) || "？".equals(content)) {
			text.setContent("欢迎你关注：1、查看天气  2、查看本人信息");
		} else {
			text.setContent("你发的信息是：" + content);
		}
		// result = MessageUtil.objToXml(text);
		return returnMsg(text);
	}

	/**
	 * 图片类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public String imageTypeMsg(Map<String, String> msg) {
		return null;
	}

	/**
	 * 语音类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public String voiceTypeMsg(Map<String, String> msg) {
		return null;
	}

	/**
	 * 视频类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public String videoTypeMsg(Map<String, String> msg) {
		return null;
	}

	/**
	 * 小视频类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public String shortVideoTypeMsg(Map<String, String> msg) {
		return null;
	}

	/**
	 * 地理位置类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public String locationTypeMsg(Map<String, String> msg) {
		return null;
	}

	/**
	 * 链接类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public String linkTypeMsg(Map<String, String> msg) {
		return null;
	}
}
