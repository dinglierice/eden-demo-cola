package org.ylzl.eden.demo.infrastructure.script.database.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @classname: ScriptDO
 * @description: 脚本数据库模型
 * @date: 2024/5/10 23:52
 * @author: hollis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@ToString
@TableName("script")
public class ScriptDO implements Serializable {
	@TableField("id")
	private int id;
	@TableField("script")
	private String script;
	@TableField("author_id")
	private Long authorId;
	@TableField("gmt_create")
	private Date gmtCreate;
	@TableField(value = "gmt_update")
	private Date gmtUpdate;
}
