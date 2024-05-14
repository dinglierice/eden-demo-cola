package org.ylzl.eden.demo.app.user.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @classname: UserDetailService
 * @description: 获取用户信息服务
 * @date: 2024/5/14 13:21
 * @author: hollis
 */
public class UserDetailService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return null;
	}

	/**
	 * 返回用户所拥有的权限
	 * TODO 待实现
	 * @param userId
	 * @return
	 */
	public List<GrantedAuthority> getUserAuthority(Long userId) {
		return null;
	}
}
