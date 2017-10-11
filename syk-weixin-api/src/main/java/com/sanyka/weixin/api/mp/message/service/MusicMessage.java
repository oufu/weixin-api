package com.sanyka.weixin.api.mp.message.service;

import com.sanyka.weixin.api.mp.message.MsgTypeEnum;
import com.sanyka.weixin.api.mp.message.ReturnMessage;
import com.sanyka.weixin.api.mp.message.domain.MusicDo;

/**
 * 音乐消息
 * 
 * @author OF
 * @date 2017-10-11
 */
public class MusicMessage extends ReturnMessage {
	private static final long serialVersionUID = 1L;
	// 音乐
	private MusicDo Music;

	public MusicMessage() {
		this.setMsgType(MsgTypeEnum.Music.toString());
	}

	public MusicDo getMusic() {
		return Music;
	}

	public void setMusic(MusicDo music) {
		Music = music;
	}
}
