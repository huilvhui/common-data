package com.xier.dynamic.base;

import java.util.List;
import java.util.Map;

import com.hikvision.dynamic.data.block.PersistBlock.Filter;

public class BaseQuery<T> extends BaseResp {
	
	/**
	 * 设备编号
	 */
	private String deviceCode;
	/**
	 * 设备类型
	 */
	private String deviceType;
	/**
	 * 通道编号
	 */
	private String channelCode;
	
	/**
	 * 通道类型
	 */
	private String channelType;
	
	/**
	 * 模型类型
	 */
	private String moduleType;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 动态属性Map
	 */
	private Map<String, String> dynamicaMap;
	
	/**
	 * 检索条件 conditions like: {"name:deviceType,type:<,value:1}
	 */
	protected List<Filter> filters;
	
	/**
	 * 返回的结果集
	 */
	private List<T> items;
	/**
	 * 动态列
	 */
	private Map<String, String> columnsType;
	

	
    public Map<String, String> getColumnsType() {
    	return columnsType;
    }

	
    public void setColumnsType(Map<String, String> columnsType) {
    	this.columnsType = columnsType;
    }

	public List<T> getItems() {
		return items;
	}
	
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	public List<Filter> getFilters() {
		return filters;
	}
	
	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getDeviceCode() {
		return deviceCode;
	}
	
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	public String getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getChannelCode() {
		return channelCode;
	}
	
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	
	public String getChannelType() {
		return channelType;
	}
	
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	
	public String getModuleType() {
		return moduleType;
	}
	
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	public Map<String, String> getDynamicaMap() {
		return dynamicaMap;
	}
	
	public void setDynamicaMap(Map<String, String> dynamicaMap) {
		this.dynamicaMap = dynamicaMap;
	}
}
