package com.sanyka.weixin.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			return byte2hex(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String SHA(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA");
			digest.update(decript.getBytes());
			return  byte2hex(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String byte2hex(byte[] messageDigest) {
		// Create Hex String
		StringBuffer hexString = new StringBuffer();
		// 字节数组转换为 十六进制 数
		for (int i = 0; i < messageDigest.length; i++) {
			String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
		}
		return hexString.toString();
	}
	public static void main(String[] args) {
		System.out.println(SHA1("123123"));
		try {
			System.out.println(HMacMD5.getHmacMd5Bytes("1".getBytes(),"123123".getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
}
