package org.ylzl.eden.demo.app.user.handler;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.ylzl.eden.cola.dto.Response;
import org.ylzl.eden.cola.dto.SingleResponse;
import org.ylzl.eden.demo.infrastructure.common.JWTUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @classname: JWTLogoutSuccessHandler
 * @description: 处理登出
 * @date: 2024/5/15 0:16
 * @author: hollis
 */
@RequiredArgsConstructor
@Component
public class JWTLogoutSuccessHandler implements LogoutSuccessHandler {
	private final JWTUtils jwtUtils;

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		if(Objects.isNull(authentication)) {
			new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
		}
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();

		httpServletResponse.setHeader(jwtUtils.HEADER, "");
		Response response = SingleResponse.buildSuccess();
		outputStream.write(JSONUtil.toJsonStr(response).getBytes(StandardCharsets.UTF_8));
		outputStream.flush();
		outputStream.close();
	}
}
