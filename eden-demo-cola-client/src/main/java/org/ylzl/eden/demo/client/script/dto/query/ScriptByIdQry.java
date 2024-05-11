package org.ylzl.eden.demo.client.script.dto.query;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @classname: ScriptById
 * @description: 根据id查询用户
 * @date: 2024/5/10 9:01
 * @author: hollis
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ScriptByIdQry {
	@NotNull(message = "用户ID 不能为空")
	private int id;
}
