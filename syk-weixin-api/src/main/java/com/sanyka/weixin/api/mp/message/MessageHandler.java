package com.sanyka.weixin.api.mp.message;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanyka.weixin.api.mp.HandlerFactory;
import com.sanyka.weixin.util.MessageUtil;

/**
 * 接收消息处理
 * 
 * @author OF
 * @date 2017年10月10日
 */
public class MessageHandler extends MessageBase {
	private static final Logger log = LoggerFactory
			.getLogger(MessageHandler.class);

	@Override
	public String execute(HttpServletRequest request) {
		// XML 解析
		Map<String, String> params;
		try {
			params = MessageUtil.xmlToMap(request);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("xml 解析失败");
			return "";
		}
		// 消息类型
		String msgType = params.get("MsgType");
		NormalMessageHandler normalMessage = HandlerFactory
				.getNormalMessageHandler();
		// 文本消息处理
		String result = null;
		if (MsgTypeEnum.Text.toString().equals(msgType)) {
			result = normalMessage.textTypeMsg(params);
		} else if (MsgTypeEnum.Image.toString().equals(msgType)) {
			// 处理图片消息
			result = normalMessage.imageTypeMsg(params);
		} else if (MsgTypeEnum.Voice.toString().equals(msgType)) {
			// 处理语音消息
			result = normalMessage.voiceTypeMsg(params);
		} else if (MsgTypeEnum.Video.toString().equals(msgType)) {
			// 处理视频消息
			result = normalMessage.videoTypeMsg(params);
		} else if (MsgTypeEnum.ShortVideo.toString().equals(msgType)) {
			// 处理小视频消息
			result = normalMessage.shortVideoTypeMsg(params);
		} else if (MsgTypeEnum.Location.toString().equals(msgType)) {
			// 处理地理位置消息
			result = normalMessage.locationTypeMsg(params);
		} else if (MsgTypeEnum.Link.toString().equals(msgType)) {
			// 处理链接消息
			result = normalMessage.linkTypeMsg(params);
		} else if (MsgTypeEnum.Event.toString().equals(msgType)) {
			// 事件处理

		}
//		result = MsgUtil.objToXml(text);
		return result;
	}
}
