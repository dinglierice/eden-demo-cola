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

package org.ylzl.eden.demo.infrastructure.user.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.ylzl.eden.demo.domain.user.entity.User;
import org.ylzl.eden.demo.domain.user.gateway.UserGateway;
import org.ylzl.eden.demo.infrastructure.common.PasswordUtils;
import org.ylzl.eden.demo.infrastructure.user.database.convertor.UserConvertor;
import org.ylzl.eden.demo.infrastructure.user.database.UserMapper;
import org.ylzl.eden.demo.infrastructure.user.database.dataobject.UserDO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 用户领域防腐层实现
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.13
 */
@RequiredArgsConstructor
@Slf4j
@Repository
public class UserGatewayImpl implements UserGateway {

	private final UserMapper userMapper;

	private final UserConvertor userConvertor;

	/**
	 * 新增用户
	 *
	 * @param user
	 */
	@Override
	public void save(User user) {
		UserDO userDO = userConvertor.toDataObject(user);
		userDO.setLangKey("CN");
		String salt = RandomStringUtils.randomAlphabetic(24);
		userDO.setSalt(salt);
		userDO.setPasswordHash(PasswordUtils.createHashedPassword(user.getPassword(), salt));
		userMapper.insert(userDO);
	}

	/**
	 * 修改用户
	 * 重新生成盐值并加密
	 * @param user
	 */
	@Override
	public void updateById(User user) {
		UserDO userDO = userConvertor.toDataObject(user);
		String salt = RandomStringUtils.randomAlphabetic(24);
		userDO.setSalt(salt);
		if (StringUtils.isBlank(user.getPassword())) {
			throw new RuntimeException("未传入新密码,无法更新信息");
		}
		userDO.setPasswordHash(PasswordUtils.createHashedPassword(user.getPassword(), salt));
		userMapper.updateById(userDO);
	}

	/**
	 * 删除用户
	 *
	 * @param user
	 */
	@Override
	public void deleteById(User user) {
		userMapper.deleteById(user.getId());
	}

	@Override
	public User getUserByName(String name) {
		UserDO userDO = userMapper.selectByUsername(name);
		return userConvertor.toEntity(userDO);
	}

	@Override
	public boolean verifyUserLogin(User user) {
		UserDO userDO = userMapper.selectByUsername(user.getLogin());
		if (null == userDO) {
			throw new RuntimeException("用户不存在");
		}
		boolean verifyResult = PasswordUtils.verifyLoginPassword(user.getLogin(), userDO.getSalt(), userDO.getPasswordHash());
		if (verifyResult) {
			return true;
		}
		return false;
	}
}
