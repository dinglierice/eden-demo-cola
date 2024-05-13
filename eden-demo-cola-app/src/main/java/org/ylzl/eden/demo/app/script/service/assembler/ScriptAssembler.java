package org.ylzl.eden.demo.app.script.service.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.ylzl.eden.demo.client.script.dto.command.ScriptModifyCmd;
import org.ylzl.eden.demo.domain.script.entity.Script;

/**
 * @classname: ScriptAssembler
 * @description: 脚本转换器
 * @date: 2024/5/12 15:07
 * @author: hollis
 */
@Mapper(componentModel = "spring",
	nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScriptAssembler {
	Script toEntity(ScriptModifyCmd scriptModifyCmd);
}
