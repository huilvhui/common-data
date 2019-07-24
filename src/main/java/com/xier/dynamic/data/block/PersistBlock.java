package com.xier.dynamic.data.block;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hikvision.dynamic.base.BasePageQuery;
import com.hikvision.dynamic.constant.EnumStatisticsType;
import com.hikvision.dynamic.data.constructor.PersistBodyConstruct;

/**
 * <p>
 * 执行数据库
 * </p>
 * @author lvhui5 2017年7月21日 下午1:59:14
 * @version V1.0
 */
public abstract class PersistBlock extends BasePageQuery<Map<String, String>> {
	
	/**
	 * excuteSql
	 */
	private String excuteSql;
	
	/**
	 * countColumns like : {deviceType:max ,deviceCode:sum}
	 */
	private Map<String, EnumStatisticsType> countColumns;
	
	private List<String> columns;
	/**
	 * table name
	 */
	private String tableName;
	
	/**
	 * insert values
	 */
	private List<Map<String, String>> insertValues;
	
	/**
	 * groupBy condition notice: order to put in columns
	 */
	private List<String> groupBy;
	
	private DataBaseType dataBaseType;
	
	private Map<String, String> defaultValue;

	/**
	 * 是否统计数据条数
	 */
	private boolean ifCount = false;
	
	public boolean ifCount() {
		return ifCount;
	}
    public void setIfCount(boolean ifCount) {
    	this.ifCount = ifCount;
    }

	protected PersistBlock(String tableName, DataBaseType type) {
		super();
		this.dataBaseType = type;
		this.tableName = tableName;
	}
	
	public Map<String, String> getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(Map<String, String> defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public List<String> getGroupBy() {
		return groupBy;
	}
	
	public void setGroupBy(List<String> groupBy) {
		this.groupBy = groupBy;
	}
	
	public List<Map<String, String>> getInsertValues() {
		return insertValues;
	}
	
	public void setInsertValues(List<Map<String, String>> insertValues) {
		this.insertValues = insertValues;
	}
	
	public String getExcuteSql() {
		return excuteSql;
	}
	
	public void setExcuteSql(String excuteSql) {
		this.excuteSql = excuteSql;
	}
	
	public List<String> getColumns() {
		return columns;
	}
	
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
    public Map<String, EnumStatisticsType> getCountColumns() {
    	return countColumns;
    }
	
    public void setCountColumns(Map<String, EnumStatisticsType> countColumns) {
    	this.countColumns = countColumns;
    }
	public PersistBlock build(PersistBodyConstruct persistBodyConstruct) {
		persistBodyConstruct.construct(this);
		return this;
	}
	
	public static final class Filter {
		
		public Filter(String name, String type, String value) {
			super();
			this.name = name;
			this.type = type;
			this.value = value;
		}
		public Filter() {
			super();
		}
		/**
		 * 比较字段名称
		 */
		private String name;
		/**
		 * 比较类型 需要转换
		 */
		private String type;
		/**
		 * 值
		 */
		private String value;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getType() {
			return type;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
		
	}
	
	public String getNumConvertSuffix() {
		return this.dataBaseType.getNumConvertSuffix();
	}
	
	public String getConvertNum() {
		return this.dataBaseType.getConvertNum();
	}
	
	public static enum DataBaseType {
		
		MYSQL("+0", "replace"),
		
		POSTGRESQL("::integer", "replace");
		
		// select * from common_data where i_id::integer<'10'
		private String numConvertSuffix;
		private String convertNum;
		
		private DataBaseType(String numConvertSuffix, String convertNum) {
			this.setNumConvertSuffix(numConvertSuffix);
			this.setConvertNum(convertNum);
		}
		
		public String getConvertNum() {
			return convertNum;
		}
		
		public void setConvertNum(String convertNum) {
			this.convertNum = convertNum;
		}
		
		public String getNumConvertSuffix() {
			return numConvertSuffix;
		}
		
		public void setNumConvertSuffix(String numConvertSuffix) {
			this.numConvertSuffix = numConvertSuffix;
		}
		
	}
	
	/**
	 * create limit
	 * @author lvhui5 2017年7月24日 下午3:48:17
	 * @return
	 */
	public String limitSuffix() {
		StringBuilder buffer = new StringBuilder();
		switch (dataBaseType) {
			case POSTGRESQL:
				buffer.append(" limit ").append(getPageSize()).append(" offset ")
				        .append((getCurrentPage() - 1) * getPageSize());
				break;
			case MYSQL:
				buffer.append(" limit ").append(getPageFirstItem()).append(",").append(getPageSize());
				break;
			default:
				buffer.append(StringUtils.EMPTY);
				break;
		}
		return buffer.toString();
	}
	
}
