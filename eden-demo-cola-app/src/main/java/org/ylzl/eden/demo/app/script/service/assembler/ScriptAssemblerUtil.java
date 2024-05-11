package org.ylzl.eden.demo.app.script.service.assembler;

import org.ylzl.eden.demo.client.script.dto.ScriptDTO;
import org.ylzl.eden.demo.client.script.dto.command.ScriptAddCmd;
import org.ylzl.eden.demo.client.script.dto.command.ScriptModifyCmd;
import org.ylzl.eden.demo.client.script.dto.command.ScriptRemoveCmd;
import org.ylzl.eden.demo.domain.script.entity.Script;
import org.ylzl.eden.demo.infrastructure.script.database.dataobject.ScriptDO;

import java.util.List;

/**
 * @classname: ScriptAssembler
 * @description: 脚本实体转换类
 * @date: 2024/5/11 0:32
 * @author: hollis
 */
public class ScriptAssemblerUtil {
	/**
	 * DO 转 VO
	 *
	 * @param dataObject
	 * @return
	 */
	public static ScriptDTO toDTO(ScriptDO dataObject) {
		return ScriptDTO.builder()
			.id(dataObject.getId())
			.script(dataObject.getScript())
			.authorId(dataObject.getAuthorId())
			.build();
	};

	public static Script toDO(ScriptModifyCmd dataObject) {
		return Script.builder().id(dataObject.getId()).script(dataObject.getScript()).build();
	}
}
