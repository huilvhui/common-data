package com.xier.dynamic.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 页面元素类型转换
 * </p>
 * @author lvhui5 2017年10月25日 下午2:04:33
 * @version V1.0
 */
public enum EnumInputType {
	
	BOOL("bool", "switch"),
	
	STRING("string", "input"),
	
	NUMBER("number", "input"),
	
	IP("ip", "input"),
	
	PASSWORD("password", "input"),
	
	ARRAY("array", "array"),
	
	ENUM("enum", "enum"),
	
	HASH("hash", "hash");
	
	private String code;
	private String value;
	
	private EnumInputType(String code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getValue() {
		return value;
	}
	/**
	 * 全局索引池
	 */
	private static Map<String, EnumInputType> pool = new HashMap<String, EnumInputType>();
	static {
		for (EnumInputType et : EnumInputType.values()) {
			pool.put(et.code, et);
		}
	}
	
	/**
	 * 根据内容索引
	 * @param value
	 * @return
	 */
	public static EnumInputType indexByValue(String key) {
		if(pool.get(key) == null){
			return STRING;
		}
		return pool.get(key);
	}
}
