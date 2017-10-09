/**
 * 
 */
package com.sanyka.weixin.utils.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author OF
 * 
 */
public class ColumnCheckUtil {
	public static Matcher matcher;
	// 数字字符
	public static final String WORDNUMBER = "[a-zA-Z0-9—_-]*";
	// 邮箱
	// public static final String EMAILADDRESS =
	// "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	public static final String EMAILADDRESS = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

	public static final String CARD = "\\d{13,32}";

	public static final String BUSILICENSE = "[a-zA-Z0-9\\s—_-]*";

	public static final String APP_CERT_CODE = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])(\\d{4}||\\d{3}[X]{1})$";
	// 15位
	public static final String APP_CERT_CODE_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	// 18位
	public static final String APP_CERT_CODE_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$";

	public static final String PHONE = "\\d{11}";
	public static final String CVV2 = "\\d{3}";

	public static void main(String[] args) {
		matcher = Pattern.compile(APP_CERT_CODE).matcher("372526198105031439");
		System.out.println(matcher.matches());
	}

	public static boolean checkEmail(String email) {
		return check(EMAILADDRESS, email);
	}

	public static boolean checkWordNumber(String str) {
		return check(WORDNUMBER, str);
	}

	public static boolean checkCard(String str) {
		return check(CARD, str);
	}

	public static boolean checkBusiLicense(String str) {
		return check(BUSILICENSE, str);
	}

	public static boolean checkAppCertCode(String str, int flag) {
		if (flag == 15) {
			return check(APP_CERT_CODE_15, str);
		} else if (flag == 18) {
			return check(APP_CERT_CODE_18, str);
		}
		return true;
	}

	public static boolean checkPhone(String str) {
		return check(PHONE, str);
	}

	public static boolean check(String rex, String str) {
		matcher = Pattern.compile(rex).matcher(str);
		return matcher.matches();
	}
}
