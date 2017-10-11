package com.sanyka.weixin.api.mp.message.service;

import com.sanyka.weixin.api.mp.message.MsgTypeEnum;
import com.sanyka.weixin.api.mp.message.ReturnMessage;
import com.sanyka.weixin.api.mp.message.domain.ImageDo;

public class ImageMessage extends ReturnMessage {
	private static final long serialVersionUID = 1L;
	public ImageDo Image;

	public ImageMessage() {
		this.setMsgType(MsgTypeEnum.Image.toString());
	}

	public ImageDo getImage() {
		return Image;
	}

	public void setImage(ImageDo image) {
		Image = image;
	}

}
