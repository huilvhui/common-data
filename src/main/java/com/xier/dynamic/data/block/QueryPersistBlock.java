package com.xier.dynamic.data.block;

import java.util.List;
import java.util.Map;

import com.xier.dynamic.constant.EnumStatisticsType;


public class QueryPersistBlock extends PersistBlock{


	/**
	 * 
	 * 
	 * 创建一个新的实例QueryPersistBlock.
	 * @param tableName  表名
	 * @param columns    字段
	 * @param type        数据库类型
	 */
	public QueryPersistBlock(String tableName,List<String> columns,DataBaseType type) {
	    super(tableName, type);
		setColumns(columns);
    }

	/**
	 * 
	 * 创建一个新的实例QueryPersistBlock.
	 * @param tableName     表名
	 * @param countColumns   统计字段 类型
	 * @param groupBy        汇总字端
	 * @param countNum       是否统计总条数
	 * @param type           数据库类型
	 */
	public QueryPersistBlock(String tableName,Map<String, EnumStatisticsType> countColumns,List<String> groupBy,boolean countNum,DataBaseType type) {
	    super(tableName,type);
		setCountColumns(countColumns);
		setGroupBy(groupBy);
		setIfCount(countNum);
    }


}
