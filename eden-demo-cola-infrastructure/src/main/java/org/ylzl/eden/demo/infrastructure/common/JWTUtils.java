package org.ylzl.eden.demo.infrastructure.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * @classname: JWTUtils
 * @description: Jwt工具类
 * @date: 2024/5/14 0:19
 * @author: hollis
 */
@Component
@Data
public class JWTUtils {
	private final Long expire = 604800L ;
	private final String secret = "abcdefghabcdefghabcdefghabcdefgh";
	private final String header = "Authorization";

	/**
	 * 生成token方法
	 * @param username
	 * @return
	 */
	public String generateToken(String username) {
		Date nowDate = new Date();
		Date expireDate = new Date(nowDate.getTime() + expire * 1000);
		return Jwts.builder()
			.setHeaderParam("typ", "JWT")
			.setSubject(username)
			.setIssuedAt(nowDate)
			.setExpiration(expireDate)
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}

	/**
	 * 解析token
	 * @param token
	 * @return
	 */
	public Claims getClaimsByToken(String token) {
		return Jwts.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token)
			.getBody();
	}

	/**
	 * 判断token是否已经过期
	 * @param claims
	 * @return
	 */
	public boolean isTokenExpired(Claims claims) {
		return claims.getExpiration().before(new Date());
	}
}
