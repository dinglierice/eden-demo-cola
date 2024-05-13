package org.ylzl.eden.demo.app.script.service.executor.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.ylzl.eden.cola.dto.Response;
import org.ylzl.eden.demo.app.script.service.assembler.ScriptAssembler;
import org.ylzl.eden.demo.app.script.service.assembler.ScriptAssemblerUtil;
import org.ylzl.eden.demo.client.script.dto.command.ScriptModifyCmd;
import org.ylzl.eden.demo.domain.script.entity.Script;
import org.ylzl.eden.demo.domain.script.gateway.ScriptGateway;
import org.ylzl.eden.demo.infrastructure.script.database.ScriptMapper;

/**
 * @classname: ScriptModifyCmdExe
 * @description: 脚本修改执行器
 * @date: 2024/5/12 0:27
 * @author: hollis
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ScriptModifyCmdExe {
	private final ScriptGateway scriptGateway;
	private final ScriptAssembler scriptAssembler;
	public Response execute(ScriptModifyCmd command) {
		Script script = scriptAssembler.toEntity(command);
		scriptGateway.updateById(script);
		return Response.buildSuccess();
	}
}
