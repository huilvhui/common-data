package com.hikvision.dynamic.constant;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 数据类型比较枚举
 * 数据模型定义动态属性的数据类型
 * </p>
 * @author lvhui5 2017年8月11日 下午2:07:33
 * @version V1.0
 */
public enum EnumDataType {
	
	BLOB("blob", "长字符型"," "),//,new ColumnType.Varchar(2048).notNull().defaultIs(CharacterConstants.NEW_COLUMN_SPACE)
	VARCHAR("varchar", "字符型"," "), //,new ColumnType.Varchar(128).notNull().defaultIs(CharacterConstants.NEW_COLUMN_SPACE)
	INT("int", "整型","0"), //,new ColumnType.Int(8).notNull().defaultIs(0)
	FLOAT("float", "浮点型","0");//,new ColumnType.Double(8, 2).notNull().defaultIs(0)

	private String name;
	private String value;
	private String defaultValue;
	/**
	 * 全局索引池
	 */
	private static Map<String, EnumDataType> pool = new HashMap<String, EnumDataType>();
	static {
		for (EnumDataType et : EnumDataType.values()) {
			pool.put(et.name, et);
		}
	}
	
	/**
	 * 根据内容索引
	 * @param value
	 * @return
	 */
	public static EnumDataType indexByValue(String name) {
		return pool.get(name);
	}
	
	private EnumDataType(String name, String value,String defaultValue) {
		this.name = name;
		this.value = value;
		this.defaultValue = defaultValue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}

	
    public String getDefaultValue() {
    	return defaultValue;
    }
    
    






	
}
