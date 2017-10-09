package com.sanyka.weixin.utils.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；<br/>
 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
 * 
 * @author OF
 * @date 2017年3月16日
 */
public class AESBase64 {

	/** 获取key的方式 */
	private static final String KEYAES = "AES";
	/** 获取实例模式 */
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	/** 编码格式 */
	private static final String CHARSET = "UTF-8";
	/** 默认向量 */
	private static String ivParameter = "8888888888888888";

	private Key key;
	/**
	 * AES CBC模式使用的Initialization Vector
	 */
	private IvParameterSpec iv;
	/**
	 * Cipher 物件
	 */
	private Cipher cipher;

	public AESBase64(String key) {

		this(key, ivParameter, CHARSET);
	}

	public AESBase64(String key, final String iv) {

		this(key, iv, CHARSET);
	}

	public AESBase64(String key, final String iv, final String charset) {

		if (key.length() != 16) {
			throw new RuntimeException("the key's length is not 16 byte");
		}
		try {
			this.key = new SecretKeySpec(key.getBytes("utf-8"), KEYAES);
			if (iv != null) {
				this.iv = new IvParameterSpec(iv.getBytes());
			} else {
				this.iv = new IvParameterSpec(ivParameter.getBytes());
			}
			this.cipher = Cipher.getInstance(TRANSFORMATION);
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 加密
	 * 
	 * @param encData
	 * 
	 * @return 加密后的密文
	 */
	public String encrypt(String encData) {
		try {
			// 初始化
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] encrypted = cipher.doFinal(encData.getBytes(CHARSET));
			return Base64.encode(encrypted);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            密文
	 * @return 解密明文
	 */
	public String decrypt(String data) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			// 先转换成base64
			byte[] en = Base64.Base64Decode(data.getBytes(CHARSET));
			byte[] original = cipher.doFinal(en);
			return new String(original, CHARSET);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 16进制转字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String encodeBytes(byte[] bytes) {
		StringBuffer strBuf = new StringBuffer();

		for (int i = 0; i < bytes.length; i++) {
			strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
			strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
		}
		return strBuf.toString();
	}

	public static void main(String arge[]) {
		try {
			// 需要加密的字串
			String cSrc = "123123";
			// 加密
			AESBase64 aes = new AESBase64("7856412346543216");
			String enString = aes.encrypt(cSrc);
			System.out.println("1:" + enString);
			// 解密
			String DeString = aes.decrypt(enString);
			System.out.println("2：" + DeString);
		} catch (Exception e) {
			e.getMessage();
		}
	}

}