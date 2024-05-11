package org.ylzl.eden.demo.domain.script.gateway;

import org.ylzl.eden.demo.domain.script.entity.Script;
import org.ylzl.eden.demo.domain.user.entity.User;

/**
 * @classname: ScriptGateway
 * @description: 脚本网关
 * @date: 2024/5/12 0:37
 * @author: hollis
 */
public interface ScriptGateway {
	void updateById(Script user);
}
