package com.sanyka.weixin.api.mp.message.service;

import com.sanyka.weixin.api.mp.message.MsgTypeEnum;
import com.sanyka.weixin.api.mp.message.ReturnMessage;

/**
 * 文本消息
 * 
 * @author OF
 * @date 2017-10-19
 */
public class TextMessage extends ReturnMessage {
	private static final long serialVersionUID = 1L;
	// 回复的消息内容
	private String Content;

	public TextMessage() {
		super();
		this.setMsgType(MsgTypeEnum.Text.toString());
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
