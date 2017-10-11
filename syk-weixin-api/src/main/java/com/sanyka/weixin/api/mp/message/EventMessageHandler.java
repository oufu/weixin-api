package com.sanyka.weixin.api.mp.message;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 图片消息处理
 * 
 * @author OF
 * @date 2017年10月10日
 */
public class EventMessageHandler extends MessageBase {

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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用户已关注时的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage qrsceneScan(Map<String, String> params) {
		return null;
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
		return null;
	}

	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件推送
	 * 
	 * @param params
	 * @return
	 */
	public ReturnMessage scancodeWaitmsg(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null; 
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
