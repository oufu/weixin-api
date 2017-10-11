package com.sanyka.weixin.api.mp.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sanyka.weixin.api.mp.message.domain.ArticleDo;
import com.sanyka.weixin.api.mp.message.domain.ImageDo;
import com.sanyka.weixin.api.mp.message.domain.MusicDo;
import com.sanyka.weixin.api.mp.message.service.ImageMessage;
import com.sanyka.weixin.api.mp.message.service.MusicMessage;
import com.sanyka.weixin.api.mp.message.service.NewsMessage;
import com.sanyka.weixin.api.mp.message.service.TextMessage;

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
	public ReturnMessage textTypeMsg(Map<String, String> params) {
		String content = params.get("Content");
		TextMessage text = new TextMessage();
		if ("1".equals(content)) {
			// 图文
			NewsMessage newMsg = new NewsMessage();
			List<ArticleDo> list = new ArrayList<ArticleDo>();
			ArticleDo article = new ArticleDo();
			// 标题
			article.setTitle("图文测试");
			// 图片显示地址
			article.setPicUrl("http://avatar.csdn.net/7/8/0/1_oufua.jpg");
			// 跳转地址
			article.setUrl("http://blog.csdn.net/oufua/article/details/48198709");
			// 描述
			article.setDescription("还不知道说点会话上比较好等下我想好了再说吧。");
			list.add(article);
			newMsg.setArticles(list);
			newMsg.setArticleCount(list.size());
			return newMsg;
		} else if ("2".equals(content)) {
			// 图片
			ImageMessage imageBase = new ImageMessage();
			ImageDo images = new ImageDo();
			images.setMediaId("C23ppbEcKmJKrLDOQ5CXVh45_Hz8c-1r-THtaD12Y7Uwn69TmcCSht5dsjfv6XmF");
			imageBase.setImage(images);
			return imageBase;
		} else if ("3".equals(content)) {
			// 音乐
			MusicDo nusic = new MusicDo();
			nusic.setDescription("");
			nusic.setThumbMediaId("dakJp55sNNO6GhN4aAD70fS13ApC2CM2SqpOqHOQ5KObjXQq1rzYq4KmFTPyJ50_");
			nusic.setHQMusicUrl("http://wxtest.tunnel.mobi/wx/resource/12438064524840064.mp3");
			nusic.setTitle("初初那年");
			nusic.setMusicUrl("http://wxtest.tunnel.mobi/wx/resource/12438064524840064.mp3");
			MusicMessage imageBase = new MusicMessage();
			imageBase.setMusic(nusic);
			return imageBase;
		} else if ("4".equals(content)) {
			// 视频
		} else if ("5".equals(content)) {
			// 语音
		} else if ("?".equals(content) || "？".equals(content)) {
			text.setContent("欢迎你关注：1、查看天气  2、查看本人信息");
		} else {
			text.setContent("你发的信息是：" + content);
		}
		return text;
	}

	/**
	 * 图片类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage imageTypeMsg(Map<String, String> msg) {
		// 图片
		ImageMessage imageBase = new ImageMessage();
		ImageDo images = new ImageDo();
		images.setMediaId(msg.get("MediaId"));
		imageBase.setImage(images);
		return imageBase;
	}

	/**
	 * 语音类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage voiceTypeMsg(Map<String, String> msg) {
		TextMessage text = new TextMessage();
		text.setContent(msg.get("MediaId"));
		return text;
	}

	/**
	 * 视频类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage videoTypeMsg(Map<String, String> msg) {
		TextMessage text = new TextMessage();
		text.setContent(msg.get("MediaId"));
		return text;
	}

	/**
	 * 小视频类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage shortVideoTypeMsg(Map<String, String> msg) {
		TextMessage text = new TextMessage();
		text.setContent(msg.get("MediaId"));
		return text;
	}

	/**
	 * 地理位置类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage locationTypeMsg(Map<String, String> params) {
		TextMessage text = new TextMessage();
		text.setContent("位置：" + params.get("Label") + "x坐标信息"
				+ params.get("Location_X") + " y坐标信息"
				+ params.get("Location_Y") + " 精度:" + params.get("Scale"));
		return text;
	}

	/**
	 * 链接类型的消息处理
	 * 
	 * @param msg
	 *            接受消息对象
	 * @return 输出消息对象
	 */
	public ReturnMessage linkTypeMsg(Map<String, String> msg) {
		TextMessage text = new TextMessage();
		text.setContent("<a href =\"" + msg.get("Url") + "\" >"
				+ msg.get("Title") + "> </a>");
		return text;
	}
}
