package com.sanyka.weixin.utils.security;

/**	　HMAC的原理和应用
 hmac的原理
 计算HMAC需要一个散列函数hash（可以是md5或者sha-1）和一个密钥key。用L表示hash函数输出字符串长（md5是16），用B表示数据块的长度（md5和sha-1的分割数据块长都是64）。密钥key的长度可以小于等于数据块长B，如果大于数据块长度，可以使用hash函数对key进行转换，结果就是一个L长的key。
 然后创建两个B长的不同字符串：
 innerpad = 长度为B的 0×36
 outterpad = 长度为B的 0×5C
 计算输入字符串str的HMAC：
 hash(key ^ outterpad, hash(key ^ innerpad, str))
 hmac的应用
 hmac主要应用在身份验证中，它的使用方法是这样的：
 1. 客户端发出登录请求（假设是浏览器的GET请求）
 2. 服务器返回一个随机值，并在会话中记录这个随机值
 3. 客户端将该随机值作为密钥，用户密码进行hmac运算，然后提交给服务器
 4. 服务器读取用户数据库中的用户密码和步骤2中发送的随机值做与客户端一样的hmac运算，然后与用户发送的结果比较，如果结果一致则验证用户合法
 在这个过程中，可能遭到安全攻击的是服务器发送的随机值和用户发送的hmac结果，而对于截获了这两个值的黑客而言这两个值是没有意义的，绝无获取用户密码的可能性，随机值的引入使hmac只在当前会话中有效，大大增强了安全性和实用性。大多数的语言都实现了hmac算法，比如php的mhash、python的hmac.py、java的MessageDigest类，在web验证中使用hmac也是可行的，用js进行md5运算的速度也是比较快的。
 ---------------------
 实例：*/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HMacMD5 {
	/**
	 * 获取md5串
	 *
	 * @param str
	 *            需要加密串
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] md5(byte[] str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str);
		return md.digest();
	}

	/**
	 * 获取byte[] 格式签名
	 *
	 * @param key
	 *            签名key
	 * @param data
	 *            签名内容
	 * @return 签名结果-16进制内容(二进制byte[]转换成16进制)
	 * @throws NoSuchAlgorithmException
	 */
	public static String getHmacMd5Bytes(byte[] key, byte[] data)
			throws NoSuchAlgorithmException {

		int length = 64;
		byte[] ipad = new byte[length];
		byte[] opad = new byte[length];
		for (int i = 0; i < 64; i++) {
			ipad[i] = 0x36;
			opad[i] = 0x5C;
		}
		byte[] actualKey = key; // Actual key.
		byte[] keyArr = new byte[length]; // Key bytes of 64 bytes length

		if (key.length > length) {
			actualKey = md5(key);
		}
		for (int i = 0; i < actualKey.length; i++) {
			keyArr[i] = actualKey[i];
		}

		if (actualKey.length < length) {
			for (int i = actualKey.length; i < keyArr.length; i++)
				keyArr[i] = 0x00;
		}

		byte[] kIpadXorResult = new byte[length];
		for (int i = 0; i < length; i++) {
			kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
		}

		byte[] firstAppendResult = new byte[kIpadXorResult.length + data.length];
		for (int i = 0; i < kIpadXorResult.length; i++) {
			firstAppendResult[i] = kIpadXorResult[i];
		}
		for (int i = 0; i < data.length; i++) {
			firstAppendResult[i + keyArr.length] = data[i];
		}

		byte[] firstHashResult = md5(firstAppendResult);

		byte[] kOpadXorResult = new byte[length];
		for (int i = 0; i < length; i++) {
			kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
		}

		byte[] secondAppendResult = new byte[kOpadXorResult.length
				+ firstHashResult.length];
		for (int i = 0; i < kOpadXorResult.length; i++) {
			secondAppendResult[i] = kOpadXorResult[i];
		}
		for (int i = 0; i < firstHashResult.length; i++) {
			secondAppendResult[i + keyArr.length] = firstHashResult[i];
		}

		byte[] hmacMd5Bytes = md5(secondAppendResult);

		return byte2HexStr(hmacMd5Bytes);

	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf
	 * @return
	 */
	public static String byte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

}
