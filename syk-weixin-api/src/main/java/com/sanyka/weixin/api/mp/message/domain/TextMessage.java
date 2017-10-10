package com.sanyka.weixin.api.mp.message.domain;

import com.sanyka.weixin.util.MessageUtil;

/**
 * 文本消息
 * 
 * @author OF
 * @date 2017-10-19
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public TextMessage(BaseMessage baseMessage) {
		this.setToUserName(baseMessage.getToUserName());
		this.setFromUserName(baseMessage.getFromUserName());
		this.setCreateTime(baseMessage.getCreateTime());
		this.setFuncFlag(baseMessage.getFuncFlag());
		this.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
	}

	public TextMessage() {
		super();
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
