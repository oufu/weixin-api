package com.sanyka.weixin.api.mp.message.service;

import com.sanyka.weixin.api.mp.message.ReturnMessage;
import com.sanyka.weixin.api.mp.message.domain.VoiceDo;

/**
 * 语音消息
 * 
 * @author OF
 * @date 2017年10月11日
 */
public class VoiceMessage extends ReturnMessage {

	private static final long serialVersionUID = 1L;
	// 语音
	private VoiceDo Voice;

	public VoiceDo getVoice() {
		return Voice;
	}

	public void setVoice(VoiceDo voice) {
		Voice = voice;
	}
}
