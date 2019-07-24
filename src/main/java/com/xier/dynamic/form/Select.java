package com.xier.dynamic.form;

import java.util.List;

/**
 * <p>
 * 用于封装前台的select
 * </p>
 * @author lvhui5 2017年10月27日 上午11:15:38
 * @version V1.0
 */
public class Select {
	
	private List<Option> options;
	
	private String type;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	
    public List<Option> getOptions() {
    	return options;
    }

	
    public void setOptions(List<Option> options) {
    	this.options = options;
    }

	
}
