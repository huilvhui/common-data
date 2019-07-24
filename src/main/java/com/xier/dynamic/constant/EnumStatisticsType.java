package com.xier.dynamic.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 统计的方式
 * </p>
 * @author lvhui5 2017年7月21日 下午3:24:20
 * @version V1.0
 */ 
public enum EnumStatisticsType {
	
	 	MAX("max", "最大值"), SUM("sum", "汇总"), MIN("min", "最小值");

	    private String                               value;

	    private String                               decription;

	    /**
	     * 全局索引池
	     */
	    private static Map<String, EnumStatisticsType> pool = new HashMap<String, EnumStatisticsType>();
	    static {
	        for (EnumStatisticsType et : EnumStatisticsType.values()) {
	            pool.put(et.value, et);
	        }
	    }

	    /**
	     * 根据内容索引
	     * @param value
	     * @return
	     */
	    public static EnumStatisticsType indexByValue(String value) {
	        return pool.get(value);
	    }

	    private EnumStatisticsType(String value, String decription) {
	        this.value = value;
	        this.decription = decription;
	    }

	    public String getValue() {
	        return value;
	    }

	    public String getDecription() {
	        return decription;
	    }
}
