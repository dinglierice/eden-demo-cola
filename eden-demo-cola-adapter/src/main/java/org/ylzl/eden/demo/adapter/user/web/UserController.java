/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.demo.adapter.user.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ylzl.eden.cola.dto.PageResponse;
import org.ylzl.eden.cola.dto.Response;
import org.ylzl.eden.cola.dto.SingleResponse;
import org.ylzl.eden.demo.adapter.constant.API;
import org.ylzl.eden.demo.client.user.api.UserService;
import org.ylzl.eden.demo.client.user.dto.UserDTO;
import org.ylzl.eden.demo.client.user.dto.command.UserAddCmd;
import org.ylzl.eden.demo.client.user.dto.command.UserLoginCmd;
import org.ylzl.eden.demo.client.user.dto.command.UserModifyCmd;
import org.ylzl.eden.demo.client.user.dto.command.UserRemoveCmd;
import org.ylzl.eden.demo.client.user.dto.query.UserByIdQry;
import org.ylzl.eden.demo.client.user.dto.query.UserListByPageQry;
import org.ylzl.eden.demo.infrastructure.common.JWTUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 用户领域控制器
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@RequiredArgsConstructor
@Slf4j
@RequestMapping(API.WEB_API_PATH + "/users")
@RestController
@Api(value = "用户管理")
public class UserController {

	private final UserService userService;
	private final JWTUtils jwtUtils;

	/**
	 * 创建用户
	 *
	 * @param cmd
	 * @return
	 */
	@PostMapping("/create")
	@ApiOperation(value = "用户注册")
	public Response createUser(@Valid @RequestBody UserAddCmd cmd) {
		return userService.createUser(cmd);
	}

	/**
	 * 修改用户
	 *
	 * @param id
	 * @param cmd
	 * @return
	 */
	@PutMapping("/{id}")
	public Response modifyUser(@PathVariable Long id, @Valid @RequestBody UserModifyCmd cmd) {
		cmd.setId(id);
		return userService.modifyUser(cmd);
	}

	/**
	 * 删除用户
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public Response removeUserById(@PathVariable Long id) {
		return userService.removeUser(UserRemoveCmd.builder().id(id).build());
	}

	/**
	 * 根据主键获取用户信息
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public SingleResponse<UserDTO> getUserById(@PathVariable Long id) {
		return userService.getUserById(UserByIdQry.builder().id(id).build());
	}

	/**
	 * 根据分页获取用户列表
	 *
	 * @param query
	 * @return
	 */
	@GetMapping
	@ApiImplicitParam(name = "Authorization", value = "Authorization",required = true, dataType = "String",paramType="header")
	public PageResponse<UserDTO> listUserByPage(@Valid @ModelAttribute UserListByPageQry query) {
		return userService.listUserByPage(query);
	}

	@PostMapping("/login")
	@ApiOperation(value = "用户登录")
	public Response login(HttpServletResponse httpServletResponse, @RequestBody @Valid UserLoginCmd cmd) {
		Response response = userService.login(cmd);
		if (response.isSuccess()) {
			String jwtToken = jwtUtils.generateToken(cmd.getLogin());
			httpServletResponse.setHeader(jwtUtils.HEADER, jwtToken);
		}
		return response;
	}
}
