package org.ylzl.eden.demo.client.script.api;

import org.ylzl.eden.cola.dto.PageResponse;
import org.ylzl.eden.cola.dto.Response;
import org.ylzl.eden.cola.dto.SingleResponse;
import org.ylzl.eden.demo.client.script.dto.ScriptDTO;
import org.ylzl.eden.demo.client.script.dto.command.ScriptAddCmd;
import org.ylzl.eden.demo.client.script.dto.command.ScriptModifyCmd;
import org.ylzl.eden.demo.client.script.dto.command.ScriptRemoveCmd;
import org.ylzl.eden.demo.client.script.dto.query.ScriptByIdQry;
import org.ylzl.eden.demo.client.script.dto.query.ScriptListByPageQry;

/**
 * @classname: ScriptService
 * @description: 脚本服务
 * @date: 2024/5/10 8:56
 * @author: hollis
 */
public interface ScriptService {
	Response addScript(ScriptAddCmd scriptAddCmd);
	Response modifyScript(ScriptModifyCmd scriptModifyCmd);
	Response removeScript(ScriptRemoveCmd scriptRemoveCmd);
	SingleResponse<ScriptDTO> getScriptById(ScriptByIdQry scriptByIdQry);
	PageResponse<ScriptDTO> getScriptsByPate(ScriptListByPageQry scriptListByPageQry);
}
