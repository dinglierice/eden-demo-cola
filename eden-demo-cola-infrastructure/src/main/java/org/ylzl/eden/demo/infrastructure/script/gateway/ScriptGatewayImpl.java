package org.ylzl.eden.demo.infrastructure.script.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.ylzl.eden.demo.domain.script.entity.Script;
import org.ylzl.eden.demo.domain.script.gateway.ScriptGateway;
import org.ylzl.eden.demo.domain.user.entity.User;
import org.ylzl.eden.demo.infrastructure.script.database.ScriptMapper;
import org.ylzl.eden.demo.infrastructure.script.database.convertor.ScriptConvertor;

/**
 * @classname: ScriptGatewayImpl
 * @description: 脚本网关实现
 * @date: 2024/5/12 0:38
 * @author: hollis
 */
@RequiredArgsConstructor
@Slf4j
@Repository
public class ScriptGatewayImpl implements ScriptGateway {
	private final ScriptMapper scriptMapper;
	private final ScriptConvertor scriptConvertor;
	@Override
	public void updateById(Script script) {
		scriptMapper.updateById(scriptConvertor.toDataObject(script));
	}
}
