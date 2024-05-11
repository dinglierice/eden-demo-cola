package org.ylzl.eden.demo.app.script.service.executor.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.ylzl.eden.cola.dto.SingleResponse;
import org.ylzl.eden.demo.app.script.service.assembler.ScriptAssemblerUtil;
import org.ylzl.eden.demo.client.script.dto.ScriptDTO;
import org.ylzl.eden.demo.client.script.dto.query.ScriptByIdQry;
import org.ylzl.eden.demo.client.user.dto.UserDTO;
import org.ylzl.eden.demo.client.user.dto.query.UserByIdQry;
import org.ylzl.eden.demo.infrastructure.script.database.ScriptMapper;
import org.ylzl.eden.demo.infrastructure.script.database.dataobject.ScriptDO;

/**
 * @classname: ScriptByIdQuery
 * @description: 查询单个脚本
 * @date: 2024/5/10 23:31
 * @author: hollis
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class ScriptByIdQueryExe {
	private final ScriptMapper scriptMapper;

	/**
	 * TODO 不太明白这里用注入的意义是什么
	 * @param query
	 * @return
	 */
//	private final ScriptAssembler scriptAssembler;
	public SingleResponse<ScriptDTO> execute(ScriptByIdQry query) {
		ScriptDO scriptDO = scriptMapper.selectById(query.getId());
		return SingleResponse.of(ScriptAssemblerUtil.toDTO(scriptDO));
	}
}
