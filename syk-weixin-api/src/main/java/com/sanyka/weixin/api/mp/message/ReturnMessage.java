package com.sanyka.weixin.api.mp.message;

/**
 * 消息返回基类
 * 
 * @author OF
 * @date 2017年10月11日
 */
public abstract class ReturnMessage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 接收方帐号（收到的OpenID）
	 */
	private String ToUserName;
	/**
	 * 开发者微信号
	 */
	private String FromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private Long CreateTime;
	/**
	 * 消息类型（text/music/news）
	 */
	private String MsgType;

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	/**
	 * 获取 接收方帐号（收到的OpenID）
	 * 
	 * @return 接收方帐号（收到的OpenID）
	 */
	public String getToUserName() {
		return ToUserName;
	}

	/**
	 * 设置 接收方帐号（收到的OpenID）
	 * 
	 * @return 接收方帐号（收到的OpenID）
	 */
	public String getFromUserName() {
		return FromUserName;
	}

	/**
	 * 获取 消息创建时间 （整型）
	 * 
	 * @return 消息创建时间 （整型）
	 */
	public Long getCreateTime() {
		return CreateTime;
	}

	public void setToUserName(String ToUserName) {
		this.ToUserName = ToUserName;
	}

	public void setFromUserName(String FromUserName) {
		this.FromUserName = FromUserName;
	}

	public void setCreateTime(Long CreateTime) {
		this.CreateTime = CreateTime;
	}
}
