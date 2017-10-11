package com.sanyka.weixin.api.mp.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 定义请求 */
	private HttpServletRequest request;
	/** 定义响应 */
	private HttpServletResponse response;
	/** 定义本地系统需要的参数 */
	private Map<String, Object> localMap;
	/** 消息对象 */
	private Object messageObject;

	/**
	 * 添加本地参数
	 * 
	 * @param key
	 *            参数名
	 * @param value
	 *            参数值
	 */
	public void addLocalParam(String key, Object value) {
		if (localMap == null) {
			localMap = new HashMap<String, Object>();
		}
		localMap.put(key, value);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Object getMessageObject() {
		return messageObject;
	}

	public void setMessageObject(Object messageObject) {
		this.messageObject = messageObject;
	}

	/**
	 * 添加本地参数All
	 * 
	 * @param key
	 *            参数名
	 * @param value
	 *            参数值
	 */
	public void addLocalParamAll(Map<String, ?> map) {
		if (localMap == null) {
			localMap = new HashMap<String, Object>();
		}
		localMap.putAll(map);
	}

	/**
	 * 返回本地（local）参数
	 * 
	 * @param key
	 *            获取本地参数值的ID
	 * @return Object 存储的对象
	 */
	public Object getLocalParam(String key) {
		if (this.localMap == null) {
			return null;
		}
		Object value = localMap.get(key);

		return value;
	}

	public Map<String, Object> getLocalMap() {
		if (localMap == null) {
			localMap = new HashMap<String, Object>();
		}
		return localMap;
	}

	public void setLocalMap(Map<String, Object> localMap) {
		this.localMap = localMap;
	}

}
