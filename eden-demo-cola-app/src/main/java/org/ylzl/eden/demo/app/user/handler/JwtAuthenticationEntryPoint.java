package org.ylzl.eden.demo.app.user.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.ylzl.eden.cola.dto.SingleResponse;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @classname: JwtAuthenticationEntryPoint
 * @description: JWT认证失败处理器
 * @date: 2024/5/14 22:59
 * @author: hollis
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		SingleResponse<String> singleResponse = SingleResponse.of("请先登录");
		singleResponse.setSuccess(false);
		singleResponse.setErrMessage("请先登录");
		singleResponse.setErrCode("401");
		outputStream.write(JSONUtil.toJsonStr(singleResponse).getBytes(StandardCharsets.UTF_8));
		// flush的意义:强制输出缓冲区的数据，避免丢失
		outputStream.flush();
		outputStream.close();
	}
}
