package org.ylzl.eden.demo.adapter.user.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ylzl.eden.cola.dto.Response;
import org.ylzl.eden.cola.dto.SingleResponse;
import org.ylzl.eden.demo.adapter.constant.API;
import org.ylzl.eden.demo.client.script.api.ScriptService;
import org.ylzl.eden.demo.client.script.dto.ScriptDTO;
import org.ylzl.eden.demo.client.script.dto.command.ScriptModifyCmd;
import org.ylzl.eden.demo.client.script.dto.query.ScriptByIdQry;
import org.ylzl.eden.demo.client.user.dto.UserDTO;

/**
 * @classname: AdminController
 * @description: 管理员路由器
 * @date: 2024/5/10 8:53
 * @author: hollis
 */
@RestController
@RequestMapping(API.SCRIPT_API_PATH + "/api")
// TODO 日志框架学习
// TODO db缓存扫描
@Slf4j
@RequiredArgsConstructor
@Api(value = "脚本管理")
public class ScriptController {
	private final ScriptService scriptService;

	@GetMapping("/hello/{name}")
	@ApiOperation(value = "接口响应测试")
	public SingleResponse<String> test(@PathVariable String name) {
		return SingleResponse.of("hello my friend " + name);
	}

	@GetMapping("/query/{id}")
	@ApiOperation(value = "根据id查询脚本")
	public SingleResponse<ScriptDTO> getUserById(@PathVariable int id) {
		return scriptService.getScriptById(ScriptByIdQry.builder().id(id).build());
	}

	@PutMapping("/modify/{id}")
	@ApiOperation(value = "根据id修改脚本")
	public Response modifyScriptById(@PathVariable int id, @RequestBody ScriptModifyCmd scriptModifyCmd) {
		scriptModifyCmd.setId(id);
		return scriptService.modifyScript(scriptModifyCmd);
	}
}
