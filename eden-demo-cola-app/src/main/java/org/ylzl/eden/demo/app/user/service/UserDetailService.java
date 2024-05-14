package org.ylzl.eden.demo.app.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.ylzl.eden.demo.app.user.model.AccountUser;
import org.ylzl.eden.demo.domain.user.entity.User;
import org.ylzl.eden.demo.domain.user.gateway.UserGateway;

import java.util.List;

/**
 * @classname: UserDetailService
 * @description: 获取用户信息服务
 * @date: 2024/5/14 13:21
 * @author: hollis
 */
@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
	private final UserGateway userGateway;
	private final String AUTHORITY = "admin";

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userGateway.getUserByName(s);
		if (user == null) {
			throw new UsernameNotFoundException("用户名或密码错误");
		}
		return new AccountUser(user.getId(), user.getLogin(), user.getPassword(), getUserAuthority(user.getId()));
	}

	/**
	 * 返回用户所拥有的权限
	 * 目前阶段默认返回管理员权限
	 * TODO 待实现
	 * @param userId
	 * @return
	 */
	public List<GrantedAuthority> getUserAuthority(Long userId) {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(AUTHORITY);
	}
}
