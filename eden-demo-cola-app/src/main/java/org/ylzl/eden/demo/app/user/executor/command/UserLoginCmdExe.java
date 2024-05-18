package org.ylzl.eden.demo.app.user.executor.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.ylzl.eden.cola.dto.Response;
import org.ylzl.eden.demo.app.user.assembler.UserAssembler;
import org.ylzl.eden.demo.client.user.dto.command.UserLoginCmd;
import org.ylzl.eden.demo.domain.user.entity.User;
import org.ylzl.eden.demo.domain.user.gateway.UserGateway;
import org.ylzl.eden.demo.infrastructure.common.PasswordUtils;

import java.util.Objects;

/**
 * @classname: UserLoginCmdExe
 * @description: 用户登录执行器
 * @date: 2024/5/16 23:45
 * @author: hollis
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class UserLoginCmdExe {
	private final UserGateway userGateway;
	private final UserAssembler userAssembler;

	/**
	 * 处理用户登录
	 * @param userLoginCmd
	 * @return
	 */
	public Response login(UserLoginCmd userLoginCmd) {
		boolean result = userGateway.verifyUserLogin(userAssembler.toEntity(userLoginCmd));
		if (result) {
			return Response.buildSuccess();
		}
		Response response = new Response();
		response.setSuccess(false);
		response.setErrCode("403");
		response.setErrMessage("登录校验失败");
		return response;
	}
}
