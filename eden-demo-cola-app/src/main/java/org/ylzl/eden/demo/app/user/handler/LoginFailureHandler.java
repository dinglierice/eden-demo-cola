package org.ylzl.eden.demo.app.user.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.ylzl.eden.cola.dto.SingleResponse;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @classname: LoginFailureHandler
 * @description: 处理用户登录失败
 * @date: 2024/5/14 0:44
 * @author: hollis
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	/**
	 * 处理用户登录失败
	 * 仅返回登录失败的报文
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param e AuthenticationException 表示用户登录失败中捕获的错误
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		SingleResponse<String> response = SingleResponse.of("login fail");
		response.setErrCode("400");
		response.setErrMessage("用户登录失败");
		response.setSuccess(false);

		outputStream.write(JSONUtil.toJsonStr(response).getBytes(StandardCharsets.UTF_8));
		outputStream.flush();
		outputStream.close();
	}
}
