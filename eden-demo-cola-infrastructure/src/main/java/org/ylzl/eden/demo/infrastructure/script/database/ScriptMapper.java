package org.ylzl.eden.demo.infrastructure.script.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.calcite.adapter.java.Map;
import org.apache.ibatis.annotations.Mapper;
import org.ylzl.eden.demo.infrastructure.script.database.dataobject.ScriptDO;

/**
 * @classname: ScriptMapper
 * @description: 脚本DAO
 * @date: 2024/5/10 23:44
 * @author: hollis
 */
@Mapper
public interface ScriptMapper extends BaseMapper<ScriptDO> {
}
