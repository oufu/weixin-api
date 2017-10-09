package com.sanyka.weixin.utils.security.passwdControls;
/*
 * 
 * Copyright (c) CFCA. All Rights Reserved.
 * 
 */

import sun.misc.BASE64Decoder;

/**
 * Base64
 * 
 * Decode Base64 String
 * 
 * @version:1.0
 * 
 * @author:Feng Lin
 */
public class Base64 {
	
	public static byte[] DecodeBase64(String base64String)
	{
		BASE64Decoder decoder = new BASE64Decoder(); 
		byte[] encodedBytes = null;
		
		try
		{
			encodedBytes = decoder.decodeBuffer(base64String);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return encodedBytes;
	}
}
