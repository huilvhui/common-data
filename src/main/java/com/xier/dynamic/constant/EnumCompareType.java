package com.xier.dynamic.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 比较枚举
 * </p>
 * @author lvhui5 2017年7月21日 下午4:24:54
 * @version V1.0
 */
public enum EnumCompareType {
	
	EQUALS("eq", "=", "等于"), LESS("lt", "<", "小于"), MORE("gt", ">", "大于"), MORE_EQUALS("gteq", ">=", "大于等于"), LESS_EQUALS(
	        "lteq", "<=", "小于等于"),NOT_EQUALS("noteq", "!=", "等于"),;
	
	private String name;
	private String value;
	private String decription;
	
	/**
	 * 全局索引池
	 */
	private static Map<String, EnumCompareType> pool = new HashMap<String, EnumCompareType>();
	static {
		for (EnumCompareType et : EnumCompareType.values()) {
			pool.put(et.name, et);
		}
	}
	
	/**
	 * 根据内容索引
	 * @param value
	 * @return
	 */
	public static EnumCompareType indexByValue(String name) {
		return pool.get(name);
	}
	
	private EnumCompareType(String name, String value, String decription) {
		this.name = name;
		this.value = value;
		this.decription = decription;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getDecription() {
		return decription;
	}
	
}
