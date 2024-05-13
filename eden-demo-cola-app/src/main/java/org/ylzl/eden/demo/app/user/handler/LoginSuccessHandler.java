package org.ylzl.eden.demo.app.user.handler;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.ylzl.eden.cola.dto.SingleResponse;
import org.ylzl.eden.demo.infrastructure.common.JWTUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @classname: LoginSuccessHandler
 * @description: 用户登录事件处理器
 * @date: 2024/5/14 0:31
 * @author: hollis
 */
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private final JWTUtils jwtUtils;

	/**
	 * 用户处理用户登录成功
	 * 登录成功 -> 返回token
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();

		String jwt = jwtUtils.generateToken(authentication.getName());
		httpServletResponse.setHeader(jwtUtils.getHeader(), jwt);

		SingleResponse<String> result = SingleResponse.of("login success");
		outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
		outputStream.flush();
		outputStream.close();
	}
}
