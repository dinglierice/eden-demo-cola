package org.ylzl.eden.demo.app.user.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.ylzl.eden.cola.dto.SingleResponse;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import cn.hutool.json.JSONUtil;

/**
 * @classname: JwtAccessDeniedHandler
 * @description: jwt认证失败处理器
 * @date: 2024/5/14 23:29
 * @author: hollis
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		SingleResponse<String> response = SingleResponse.of("fail");
		response.setErrCode("403");
		response.setSuccess(false);

		outputStream.write(JSONUtil.toJsonStr(response).getBytes(StandardCharsets.UTF_8));
		outputStream.flush();
		outputStream.close();
	}
}
