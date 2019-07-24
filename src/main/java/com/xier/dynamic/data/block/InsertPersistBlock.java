package com.xier.dynamic.data.block;

import java.util.List;
import java.util.Map;



public class InsertPersistBlock extends PersistBlock{


	/**
	 * 
	 * 创建一个新的实例InsertPersistBlock.
	 * @param tableName  表名
	 * @param defaultValue 字段默认值
	 * @param columns     字段
	 * @param insertValues   插入值
	 * @param type         数据库类型
	 */
	public InsertPersistBlock(String tableName,Map<String,String> defaultValue,List<String> columns,List<Map<String,String>> insertValues, DataBaseType type) {
		super(tableName,type);
		setInsertValues(insertValues);
		setColumns(columns);
		setDefaultValue(defaultValue);
    }

}
