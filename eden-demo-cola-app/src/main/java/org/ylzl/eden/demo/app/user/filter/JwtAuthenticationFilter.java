package org.ylzl.eden.demo.app.user.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.ylzl.eden.demo.app.user.service.UserDetailService;
import org.ylzl.eden.demo.domain.user.entity.User;
import org.ylzl.eden.demo.domain.user.gateway.UserGateway;
import org.ylzl.eden.demo.infrastructure.common.JWTUtils;
import org.ylzl.eden.demo.infrastructure.user.database.UserMapper;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

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
	UserDetailService userDetailService;

	@Resource
	UserGateway userGateway;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
		super(authenticationManager, authenticationEntryPoint);
	}

	/**
	 * 验证jwtToken
	 * 如果正确增加一个认证token于上下文中
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = request.getHeader(jwtUtils.HEADER);

		if (StringUtils.isBlank(token)) {
			chain.doFilter(request, response);
			return;
		}

		Claims claims = jwtUtils.getClaimsByToken(token);
		if (Objects.isNull(claims)) {
			throw new JwtException("token 解析异常");
		}
		if (jwtUtils.isTokenExpired(claims)) {
			throw new JwtException("token 已过期");
		}

		String username  = claims.getSubject();
		User user = userGateway.getUserByName(username);

		// 构建UsernamePasswordAuthenticationToken,这里密码为null，是因为提供了正确的JWT,实现自动登录
		UsernamePasswordAuthenticationToken tokenPass = new UsernamePasswordAuthenticationToken(username, null, userDetailService.getUserAuthority(user.getId()));
		SecurityContextHolder.getContext().setAuthentication(tokenPass);

		chain.doFilter(request, response);
	}
}
