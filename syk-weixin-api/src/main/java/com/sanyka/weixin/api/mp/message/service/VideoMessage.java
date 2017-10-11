package com.sanyka.weixin.api.mp.message.service;

import com.sanyka.weixin.api.mp.message.ReturnMessage;
import com.sanyka.weixin.api.mp.message.domain.VideoDo;

/**
 * 视频消息
 * 
 * @author OF
 * @date 2017年10月11日
 */
public class VideoMessage extends ReturnMessage {
	private static final long serialVersionUID = 1L;
	// 视频
	private VideoDo Video;

	public VideoDo getVideo() {
		return Video;
	}

	public void setVideo(VideoDo video) {
		Video = video;
	}
}
