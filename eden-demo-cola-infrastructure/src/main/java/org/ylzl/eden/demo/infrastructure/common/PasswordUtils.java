package org.ylzl.eden.demo.infrastructure.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @classname: PasswordUtils
 * @description: 用户密码相关工具类
 * @date: 2024/5/18 16:37
 * @author: hollis
 */
@Slf4j
public class PasswordUtils {
	public static String createHashedPassword(String password, String salt)  {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA");
			String confusePassword = password + salt;
			byte[] bb = sha.digest(confusePassword.getBytes(StandardCharsets.UTF_8));
			return new BigInteger(1, bb).toString(16);
		} catch (Exception e) {
			log.error("用户加密失败, 请检查密码输入 : password {}", password);
			throw new RuntimeException("用户加密失败");
		}
	}


	public static boolean verifyLoginPassword(String inputPassword, String salt, String dbPassword) {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA");
			String confusePassword = inputPassword + salt;
			byte[] bb = sha.digest(confusePassword.getBytes(StandardCharsets.UTF_8));
			String result = new BigInteger(1, bb).toString(16);
			return StringUtils.equals(result, dbPassword);
		} catch (Exception e) {
			log.error("用户密码验证失败 : password {}", inputPassword);
			throw new RuntimeException("用户密码验证失败");
		}
	}
}
