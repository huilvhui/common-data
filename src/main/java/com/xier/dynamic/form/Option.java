package com.xier.dynamic.form;

/**
 * <p>
 * 动态表单select的option单元
 * </p>
 * @author lvhui5 2017年11月1日 下午5:27:39
 * @version V1.0
 */
public class Option {
	
	private String value;
	
	private String label;
	
	public Option() {
		super();
	}
	
	public Option(String value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
}
