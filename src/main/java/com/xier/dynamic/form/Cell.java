package com.xier.dynamic.form;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Cell implements FrontCell{

	
	private String key;
	
	private String label;
	
	private String type;
	
	private String vtype;
	
	private Object value;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getVtype() {
		return vtype;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Cell() {
	}
	
	public Cell(String key, String label, String type, String vtype) {
		this.key = key;
		this.label = label;
		this.type = type;
		this.vtype = vtype;
	}
	
	public Cell(String key, String label, String type, String vtype, Object value) {
		this.key = key;
		this.label = label;
		this.type = type;
		this.vtype = vtype;
		this.value = value;
	}
	
	@Override
	public void setOtherAttr(LinkHandler linkHander) {
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}



}
