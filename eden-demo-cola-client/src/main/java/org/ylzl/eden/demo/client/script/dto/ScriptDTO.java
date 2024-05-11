package org.ylzl.eden.demo.client.script.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @classname: ScriptDTO
 * @description: 脚本数据传输模型
 * @date: 2024/5/10 23:58
 * @author: hollis
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ScriptDTO implements Serializable {
	private int id;
	private String script;
	private Long authorId;
}
