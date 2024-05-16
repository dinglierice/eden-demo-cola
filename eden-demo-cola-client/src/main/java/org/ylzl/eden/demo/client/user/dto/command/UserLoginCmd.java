package org.ylzl.eden.demo.client.user.dto.command;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @classname: UserLoginCmd
 * @description: 用户登录命令
 * @date: 2024/5/16 23:42
 * @author: hollis
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class UserLoginCmd {
	@NotBlank(message = "账号不能为空")
	private String login;

	@NotBlank(message = "密码不能为空")
	private String password;
}
