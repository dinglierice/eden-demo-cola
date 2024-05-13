package org.ylzl.eden.demo.app.user.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.ylzl.eden.demo.infrastructure.common.JWTUtils;

import javax.annotation.Resource;

/**
 * @classname: JwtAuthenticationFilter
 * 父类BasicAuthenticationFilter用于普通http请求进行身份认证
 * 父类持有AuthenticationManager
 * UsernamePasswordAuthenticationToken 认证成功后的信息
 * UsernamePasswordAuthenticationToken 需要交给Context持有 以在其他地方调用
 * @description: token过滤器
 * @date: 2024/5/14 0:50
 * @author: hollis
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	@Resource
	JWTUtils jwtUtils;

	@Resource
	UserDetailsService userDetailsService;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
		super(authenticationManager, authenticationEntryPoint);
	}
}
