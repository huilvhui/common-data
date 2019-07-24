package com.xier.dynamic.data.constructor;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.xier.dynamic.data.block.PersistBlock;

public class InsertPersistBodyConstruct extends PersistBodyConstruct {
	
	private static final String inner = "insert into {0}( {1} )values  {2}  ";
	

	private int max_size = 120;

	@Override
	protected boolean innerConstruct(PersistBlock persistBlock) {
		if (CollectionUtils.isEmpty(persistBlock.getInsertValues()) || CollectionUtils.isEmpty(persistBlock.getColumns()))
			return false;
		return true;
	}

	/**
	 * 
	 * 创建一个新的实例InsertPersistBodyConstruct.
	 * @param maxSize  添加数据字段的最大长度
	 */
	public InsertPersistBodyConstruct(int maxSize) {
	    super();
	    max_size = maxSize;
    }

	@Override
	protected void install(PersistBlock persistBlock) {
		Object[] params = {persistBlock.getTableName(), contructCloumns(persistBlock),
		        constructInsertValues(persistBlock.getColumns(), persistBlock.getInsertValues(),persistBlock.getDefaultValue())};
		persistBlock.setExcuteSql(adaptString(inner, params));
	}
	
	@Override
	protected String innerContructCloumns(PersistBlock persistBlock) {
		StringBuilder sb = new StringBuilder();
		for (String column : persistBlock.getColumns()) {
			sb.append(column).append(separator);
		}
		return sb.toString();
	}
	
	private String constructInsertValues(List<String> columns, List<Map<String, String>> insertValues, Map<String, String> defaultValue) {
		StringBuilder sb = new StringBuilder();
		for (Map<String, String> insertValue : insertValues) {
			sb.append(left_bracket).append(StringUtils.EMPTY);
			for (int i = 0; i < columns.size(); i++) {
				String column = columns.get(i);
				String value = insertValue.get(column)==null?StringUtils.EMPTY:String.valueOf(insertValue.get(column));
				if (StringUtils.isBlank(value))
					value = defaultValue.get(column);
				if(value == null)
					value = StringUtils.EMPTY;
					//throw new IllegalArgumentException("unknow default  value    " + column);
				sb.append(quote).append(insertValueSizeControl(String.valueOf(value))).append(quote);
				if (i != columns.size() - 1) {
					sb.append(separator);
				}
			}
			sb.append(right_bracket).append(separator);
		}
		return removeLastChar(sb.toString());
	}
	
	private String insertValueSizeControl(String input){
		if(input.length() > max_size)
			return input.substring(0,max_size-1);
		return input;
	}
	
}
