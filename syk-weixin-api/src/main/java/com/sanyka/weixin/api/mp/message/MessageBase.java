package com.sanyka.weixin.api.mp.message;

import javax.servlet.http.HttpServletRequest;

import com.sanyka.weixin.util.MessageUtil;

public abstract class MessageBase {
	/**
	 * 业务处理方法
	 * 
	 * @param request
	 * @return
	 */
	public abstract String execute(HttpServletRequest request);
	/**
	 * 返回方法
	 * @param obj
	 * @return
	 */
	public String returnMsg(Object obj) {
		return MessageUtil.objToXml(obj);
	}
}
