package org.ylzl.eden.demo.app.user.executor.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.ylzl.eden.cola.dto.Response;
import org.ylzl.eden.demo.client.user.dto.command.UserLoginCmd;

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
	public Response login(UserLoginCmd userLoginCmd) {
		return null;
	}
}
