package com.sanyka.weixin.api.mp.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sanyka.weixin.api.mp.message.domain.ArticleDo;
import com.sanyka.weixin.api.mp.message.domain.ImageDo;
import com.sanyka.weixin.api.mp.message.service.ImageMessage;
import com.sanyka.weixin.api.mp.message.service.NewsMessage;
import com.sanyka.weixin.api.mp.message.service.TextMessage;

/**
 * 图片消息处理
 * 
 * @author OF
 * @date 2017年10月10日
 */
public abstract class EventMessageBase extends MessageBase {

	@Override
	public String execute(HttpServletRequest request) {
		return null;
	}

	/**
	 * 点击事件
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage click(Map<String, String> params) {
		if ("11".equals(params.get("EventKey"))) {
			// 最新图文
			NewsMessage newMsg = new NewsMessage();
			List<ArticleDo> list = new ArrayList<ArticleDo>();
			ArticleDo article = new ArticleDo();
			// 标题
			article.setTitle("图文测试");
			// 图片显示地址
			article.setPicUrl("http://upload-images.jianshu.io/upload_images/2397007-235cc559d1bd9ea6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
			// 跳转地址
			article.setUrl("http://blog.csdn.net/oufua/article/details/48198709");
			// 描述
			article.setDescription("还不知道说点会话上比较好等下我想好了再说吧。");
			list.add(article);
			newMsg.setArticles(list);
			newMsg.setArticleCount(list.size());
			return newMsg;
		} else if ("12".equals(params.get("EventKey"))) {
			// 获取二维码
			// 图片
			ImageMessage imageBase = new ImageMessage();
			ImageDo images = new ImageDo();
			images.setMediaId("HBhGeOfj_h-hxwpF-rzAEH_dbosS2F46YwpLRo9jSZDEdBg_PGNzpw5wklBGdCY1");
			imageBase.setImage(images);
			return imageBase;
		} else if ("32".equals(params.get("EventKey"))) {
			// 我的相片
			ImageMessage imageBase = new ImageMessage();
			ImageDo images = new ImageDo();
			images.setMediaId("C23ppbEcKmJKrLDOQ5CXVh45_Hz8c-1r-THtaD12Y7Uwn69TmcCSht5dsjfv6XmF");
			imageBase.setImage(images);
			return imageBase;
		}

		return null;
	}

	/**
	 * 点击菜单跳转链接时的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage view(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 关注事件
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage subscribe(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 取消关注事件
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage unsubscribe(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用户未关注时，进行关注后的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage qrsceneSubscribe(Map<String, String> params) {
		TextMessage message = new TextMessage();
		message.setContent("用户未关注、你是：" + params.get("EventKey"));
		return message;
	}

	/**
	 * 用户已关注时的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage qrsceneScan(Map<String, String> params) {
		TextMessage message = new TextMessage();
		message.setContent("用户已关注 你是：" + params.get("EventKey"));
		return message;
	}

	/**
	 * 上报地理位置事件
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage location(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 扫码推事件的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage scancodePush(Map<String, String> params) {
		TextMessage message = new TextMessage();
		message.setContent("扫码推事件的事件推送");
		return message;
	}

	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage scancodeWaitmsg(Map<String, String> params) {
		TextMessage message = new TextMessage();
		message.setContent("扫码推事件且弹出“消息接收中”提示框的事件推送");
		return message;
	}

	/**
	 * 弹出系统拍照发图的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage picSysphoto(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 弹出拍照或者相册发图的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage picPhotoOrAlbum(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 弹出微信相册发图器的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage picWeixin(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 弹出地理位置选择器的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage locationSelect(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
