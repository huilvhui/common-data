package com.xier.dynamic.form;

import java.util.List;

/**
 * <p>
 * 动态表单select的option单元
 * </p>
 * @author lvhui5 2017年11月1日 下午5:27:39
 * @version V1.0
 */
public class LinkageOption extends Option {
	
	/**
	 * 创建一个普通的下拉LinkageOption.
	 * @param key
	 * @param label
	 */
	public LinkageOption(String key, String label) {
	    super(key, label);
    }


	/**
	 * 存在多个属性联动
	 */
	private List<Cell> cell;

	
    public List<Cell> getCell() {
    	return cell;
    }

	
    public void setCell(List<Cell> cell) {
    	this.cell = cell;
    }
	

	
}
