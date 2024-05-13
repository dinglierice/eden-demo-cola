package org.ylzl.eden.demo.domain.script.entity;

import lombok.*;
import org.ylzl.eden.cola.domain.Entity;

/**
 * @classname: Script
 * @description: 脚本实体
 * @date: 2024/5/11 0:02
 * @author: hollis
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
public class Script {
	private int id;
	private String script;
}
