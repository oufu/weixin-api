package com.sanyka.weixin.api.mp.message;

import java.util.Date;
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
		log.debug("params :[{}] ", params.toString());
		// 消息类型
		String msgType = params.get("MsgType");
		NormalMessageBase normalMessage = HandlerFactory
				.getNormalMessageHandler();
		// 文本消息处理
		ReturnMessage result = null;
		if (MsgTypeEnum.Text.toString().equalsIgnoreCase(msgType)) {
			result = normalMessage.textTypeMsg(params);
		} else if (MsgTypeEnum.Image.toString().equalsIgnoreCase(msgType)) {
			// 处理图片消息
			result = normalMessage.imageTypeMsg(params);
		} else if (MsgTypeEnum.Voice.toString().equalsIgnoreCase(msgType)) {
			// 处理语音消息
			result = normalMessage.voiceTypeMsg(params);
		} else if (MsgTypeEnum.Video.toString().equalsIgnoreCase(msgType)) {
			// 处理视频消息
			result = normalMessage.videoTypeMsg(params);
		} else if (MsgTypeEnum.ShortVideo.toString().equalsIgnoreCase(msgType)) {
			// 处理小视频消息
			result = normalMessage.shortVideoTypeMsg(params);
		} else if (MsgTypeEnum.Location.toString().equalsIgnoreCase(msgType)) {
			// 处理地理位置消息
			result = normalMessage.locationTypeMsg(params);
		} else if (MsgTypeEnum.Link.toString().equalsIgnoreCase(msgType)) {
			// 处理链接消息
			result = normalMessage.linkTypeMsg(params);
		} else if (MsgTypeEnum.Event.toString().equalsIgnoreCase(msgType)) {
			// 获取事件类型
			String event = params.get("Event");
			// 获取消息处理工具类
			EventMessageBase eventMsgHandler = HandlerFactory
					.getEventMessageHandler();
			// 自定义菜单事件
			if (EventTypeEnum.Click.toString().equalsIgnoreCase(event)) {
				// 点击菜单拉取消息时的事件推送
				result = eventMsgHandler.click(params);
			} else if (EventTypeEnum.View.toString().equalsIgnoreCase(event)) {
				// 点击菜单跳转链接时的事件推送
				result = eventMsgHandler.view(params);
			} else if (EventTypeEnum.Subscribe.toString().equalsIgnoreCase(
					event)) {
				// 关注事件
				// 获取事件KEY值，判断是否关注
				String eventKey = params.get("EventKey");
				if (eventKey.startsWith("qrscene_")) {
					// 用户未关注时，进行关注后的事件推送
					params.put("EventKey", eventKey.replace("qrscene_", ""));
					result = eventMsgHandler.qrsceneSubscribe(params);
				} else {
					result = eventMsgHandler.subscribe(params);
				}
			} else if (EventTypeEnum.Unsubscribe.toString().equalsIgnoreCase(
					event)) {
				// 取消关注事件
				result = eventMsgHandler.unsubscribe(params);
			} else if (EventTypeEnum.Scan.toString().equalsIgnoreCase(event)) {
				// 扫描带参数二维码事件
				// 获取事件KEY值，判断是否关注
				String eventKey = params.get("EventKey");
				if (eventKey.startsWith("qrscene_")) {
					// 用户未关注时，进行关注后的事件推送
					result = eventMsgHandler.qrsceneSubscribe(params);
				} else {
					// 用户已关注时的事件推送
					result = eventMsgHandler.qrsceneScan(params);
				}
			} else if (EventTypeEnum.Location.toString()
					.equalsIgnoreCase(event)) {
				// 上报地理位置事件
				result = eventMsgHandler.location(params);
			} else if (EventTypeEnum.Scancode_Push.toString().equalsIgnoreCase(
					event)) {
				// 扫码推事件的事件推送
				result = eventMsgHandler.scancodePush(params);
			} else if (EventTypeEnum.Scancode_Waitmsg.toString()
					.equalsIgnoreCase(event)) {
				// 扫码推事件且弹出“消息接收中”提示框的事件推送
				result = eventMsgHandler.scancodeWaitmsg(params);
			} else if (EventTypeEnum.Pic_Sysphoto.toString().equalsIgnoreCase(
					event)) {
				// 弹出系统拍照发图的事件推送
				result = eventMsgHandler.picSysphoto(params);
			} else if (EventTypeEnum.Pic_Photo_OR_Album.toString()
					.equalsIgnoreCase(event)) {
				// 弹出拍照或者相册发图的事件推送
				result = eventMsgHandler.picPhotoOrAlbum(params);
			} else if (EventTypeEnum.Pic_Weixin.toString().equalsIgnoreCase(
					event)) {
				// 弹出微信相册发图器的事件推送
				result = eventMsgHandler.picWeixin(params);
			} else if (EventTypeEnum.Location_Select.toString()
					.equalsIgnoreCase(event)) {
				// 弹出地理位置选择器的事件推送
				result = eventMsgHandler.locationSelect(params);
			}
		}
		String res = null;
		if (result != null) {
			// 设置返回数据
			setOutputMsgInfo(result, params);
			// 打包
			if (MsgTypeEnum.News.toString().equalsIgnoreCase(
					result.getMsgType())) {
				res = MessageUtil.newsMessageToXml(result);
			} else {
				res = MessageUtil.objToXml(result);
			}
		} else {
			res = "";
		}
		log.debug("result :[{}] ", res);
		return res;
	}

	/**
	 * 设置返回数据
	 * 
	 * @param result
	 * @param params
	 */
	private static void setOutputMsgInfo(ReturnMessage result,
			Map<String, String> params) {
		// 发送方帐号（open_id）
		String fromUserName = params.get("FromUserName");
		// 公众帐号
		String toUserName = params.get("ToUserName");
		// 设置发送信息
		result.setCreateTime(new Date().getTime());
		result.setToUserName(fromUserName);
		result.setFromUserName(toUserName);
	}

}
