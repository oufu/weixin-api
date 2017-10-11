package com.sanyka.weixin.api.mp.message;

/**
 * 事件类型
 * 
 * @author OF
 */
public enum EventTypeEnum {

	/**
	 * 订阅
	 */
	Subscribe("subscribe"),
	/**
	 * 取消订阅
	 */
	Unsubscribe("unsubscribe"),
	/**
	 * 已关注用户扫描带参数二维码
	 */
	Scan("scan"),
	/**
	 * 上报地理位置
	 */
	Location("location"),
	/**
	 * 点击自定义菜单
	 */
	Click("click"),
	/**
	 * 查看菜单
	 */
	View("view"),
	/**
	 * 扫码推事件
	 */
	Scancode_Push("scancode_push"),
	/**
	 * 扫码推事件
	 */
	Scancode_Waitmsg("scancode_waitmsg"),
	/**
	 * 弹出系统拍照发图的事件
	 */
	Pic_Sysphoto("pic_sysphoto"),
	/**
	 * 弹出拍照或者相册发图的事件
	 */
	Pic_Photo_OR_Album("pic_photo_or_album"),
	/**
	 * 弹出微信相册发图器的事件
	 */
	Pic_Weixin("pic_weixin"),
	/**
	 * 弹出地理位置选择器的事件
	 */
	Location_Select("location_select");

	private String value = "";

	EventTypeEnum(String value) {
		this.value = value;
	}

	/**
	 * @return the msgType
	 */
	@Override
	public String toString() {
		return value;
	}
}
