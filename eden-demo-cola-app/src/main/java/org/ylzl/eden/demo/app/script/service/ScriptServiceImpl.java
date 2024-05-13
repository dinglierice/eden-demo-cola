package org.ylzl.eden.demo.app.script.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ylzl.eden.cola.dto.PageResponse;
import org.ylzl.eden.cola.dto.Response;
import org.ylzl.eden.cola.dto.SingleResponse;
import org.ylzl.eden.demo.app.script.service.executor.command.ScriptModifyCmdExe;
import org.ylzl.eden.demo.app.script.service.executor.query.ScriptByIdQueryExe;
import org.ylzl.eden.demo.client.script.api.ScriptService;
import org.ylzl.eden.demo.client.script.dto.ScriptDTO;
import org.ylzl.eden.demo.client.script.dto.command.ScriptAddCmd;
import org.ylzl.eden.demo.client.script.dto.command.ScriptModifyCmd;
import org.ylzl.eden.demo.client.script.dto.command.ScriptRemoveCmd;
import org.ylzl.eden.demo.client.script.dto.query.ScriptByIdQry;
import org.ylzl.eden.demo.client.script.dto.query.ScriptListByPageQry;

/**
 * @classname: ScriptServiceImpl
 * @description: 脚本服务实现
 * @date: 2024/5/10 9:03
 * @author: hollis
 */
@Slf4j
@RequiredArgsConstructor
@Service("script_service")
public class ScriptServiceImpl implements ScriptService {
	private final ScriptByIdQueryExe scriptByIdQueryExe;
	private final ScriptModifyCmdExe scriptModifyCmdExe;

	@Override
	public Response addScript(ScriptAddCmd scriptAddCmd) {
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Response modifyScript(ScriptModifyCmd scriptModifyCmd) {
		return scriptModifyCmdExe.execute(scriptModifyCmd);
	}

	@Override
	public Response removeScript(ScriptRemoveCmd scriptRemoveCmd) {
		return null;
	}

	@Override
	public SingleResponse<ScriptDTO> getScriptById(ScriptByIdQry scriptByIdQry) {
		return scriptByIdQueryExe.execute(scriptByIdQry);
	}

	@Override
	public PageResponse<ScriptDTO> getScriptsByPate(ScriptListByPageQry scriptListByPageQry) {
		return null;
	}
}
