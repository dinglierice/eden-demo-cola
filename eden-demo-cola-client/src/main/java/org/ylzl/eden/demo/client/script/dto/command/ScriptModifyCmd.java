package org.ylzl.eden.demo.client.script.dto.command;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @classname: ScriptModifyCmd
 * @description: 修改脚本命令
 * @date: 2024/5/10 9:00
 * @author: hollis
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ScriptModifyCmd {
	@NotNull
	private int id;
	private String script;
	private Long authorId;
}
