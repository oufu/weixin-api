package com.sanyka.weixin.utils.security.passwdControls;
/*
 * 
 * Copyright (c) CFCA. All Rights Reserved.
 * 
 */

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Enumeration;

import javax.crypto.Cipher;

/**
 * PKCS12
 * 
 * Decrypt RSA cipher using pfx
 * 
 * @version:1.0
 * 
 * @author:OF
 */

public class PKCS12 {
	
	/**
	 * 
	 * @param pfxFileName pfx file full path
	 * @param pfxPassword pfx password
	 * @param encryptedDataBase64 RSA encrypted data(base64 encoding)
	 * @param bigEndian if RSA encrypted data is Big-Endian integer
	 * @return
	 */
	public static byte[] RSADecrypt(String pfxFileName, String pfxPassword, String encryptedDataBase64)
	{
		byte[] plainBinary = null;
		
		KeyStore keyStore = null;
		KeyFactory keyFactory = null;
		FileInputStream file_inputstream = null;
			
		try
        {
			file_inputstream = new FileInputStream(pfxFileName);
			keyStore = KeyStore.getInstance("PKCS12");
			
			keyStore.load(file_inputstream, pfxPassword.toCharArray());
			
			String alias = "";
			Enumeration aliases = keyStore.aliases();
			while (aliases.hasMoreElements()) 
			{
				alias = aliases.nextElement().toString();
			}
			
			Key key = keyStore.getKey(alias, pfxPassword.toCharArray());
			
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
			
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			byte[] encodedBytes = Base64.DecodeBase64(encryptedDataBase64);
			
			plainBinary = rsaCipher.doFinal(encodedBytes);
		}
		catch(Exception e)
		{
			System.err.println("RSADecrypt Exception:- " + e);
		}		
			
		return plainBinary;
	}

}
