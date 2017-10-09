package com.sanyka.weixin.utils.security;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class KDES {

	/* 几种运算法则 */
	public static int	_DES		= 1;
	public static int	_DESede		= 2;
	public static int	_Blowfish	= 3;

	// 加密对象
	private Cipher		p_Cipher;

	// 加密key
	private SecretKey	p_Key;

	// 运算法则
	private String		p_Algorithm;

	private void selectAlgorithm(int al) {
		switch (al) {
		default:
		case 1:
			this.p_Algorithm = "DES";
			break;
		case 2:
			this.p_Algorithm = "DESede";
			break;
		case 3:
			this.p_Algorithm = "Blowfish";
			break;
		}
	}

	/**
	 * 初始化加密的运算法则和对象
	 *
	 * @param algorithm
	 * @throws Exception
	 */
	public KDES(int algorithm) throws Exception {
		this.selectAlgorithm(algorithm);
		// Security.addProvider(new com.sun.crypto.provider.SunJCE());
		this.p_Cipher = Cipher.getInstance(this.p_Algorithm);
	}

	/**
	 * 初始化加密的运算法则和对象
	 *
	 * @param algorithm
	 * @throws Exception
	 */
	public KDES() throws Exception {

		this.selectAlgorithm(1);
		// Security.addProvider(new com.sun.crypto.provider.SunJCE());
		this.p_Cipher = Cipher.getInstance(this.p_Algorithm);
		// this.p_Cipher = Cipher.getInstance("DES/ECB/NoPadding","SunJCE");

	}

	/**
	 * 设置加密key
	 *
	 * @param key
	 */
	public void setKey(String key) {
		if (key == null || key.equals("")) {
			key = "00000000";
		}
		int n = key.length() % 8;
		if (n != 0) {
			for (int i = 8; i > n; i--) {
				key = key + "0";
			}
		}
		this.p_Key = new SecretKeySpec(key.getBytes(), this.p_Algorithm);
	}

	private SecretKey checkKey() {
		try {
			if (this.p_Key == null) {
				KeyGenerator keygen = KeyGenerator
						.getInstance(this.p_Algorithm);
				this.p_Key = keygen.generateKey();
			}
		} catch (Exception ex) {
		}
		return this.p_Key;
	}

	/**
	 * 对数据进行加密
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String encode(String data) throws Exception {
		this.p_Cipher.init(Cipher.ENCRYPT_MODE, this.checkKey());
		return new String(byte2hex(this.p_Cipher.doFinal(data.getBytes("GBK"))));
	}

	/**
	 * 对数据解密
	 *
	 * @param encdata
	 * @param enckey
	 * @return
	 * @throws Exception
	 */
	public String decode(String encdata, String enckey) throws Exception {
		this.setKey(enckey);
		this.p_Cipher.init(Cipher.DECRYPT_MODE, this.p_Key);
		return new String(this.p_Cipher.doFinal(hex2byte(encdata)), "GBK");
	}

	/**
	 * 将字节数组转换为hex串
	 *
	 * @param b
	 * @return
	 */
	public String byte2hex(byte[] b) {
		return String.valueOf(Hex.encodeHex(b));

	}

	/**
	 * 将hex串转换为字节数组
	 *
	 * @param hex
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws DecoderException
	 */
	public byte[] hex2byte(String hex) throws IOException, DecoderException {
		return Hex.decodeHex(hex.toCharArray());
	}

	public static void main(String[] args) throws Exception {
		KDES des = new KDES();
		des.setKey("12345678");
		System.out.println(des.encode("123123"));
		String nn = "RWo5l4tXQQM=";
		System.out.println(des.decode("d3ffa190cc70806b","12345678"));
	}
}
