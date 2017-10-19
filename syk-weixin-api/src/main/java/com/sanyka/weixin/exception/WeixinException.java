package com.sanyka.weixin.exception;

import java.util.Map;

public class WeixinException extends Exception {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;
	private String erroCode = null;
	private Map<String, Object> returndata;

	/**
	 * @param msg
	 */
	public WeixinException(String msg) {
		super(msg);
	}

	public WeixinException(String message, Map<String, Object> returndata) {
		super(message);
		this.returndata = returndata;
	}

	public WeixinException(String erroCode, String msg) {
		super(msg);
		this.erroCode = erroCode;
	}

	public String getErroCode() {
		return erroCode;
	}

	public Map<String, Object> getReturndata() {
		return returndata;
	}

}
