package com.xier.dynamic.form;

import java.util.ArrayList;
import java.util.List;

import com.hikvision.dynamic.constant.EnumInputType;

/**
 * <p>
 * 动态表单联动cell单元
 * </p>
 * @author lvhui5 2017年11月1日 下午5:25:40
 * @version V1.0
 */
public class LinkageCell extends Cell{
	/**
	 * 
	 * 创建一个新的实例LinkageCell.
	 * @param key         元素的key
	 * @param label       展示名称
	 * @param vtype       vtype  校验属性
	 * @param linkHander  联动逻辑
	 */
	public LinkageCell(String key, String label, String vtype, LinkHandler linkHander) {
	    super(key, label, EnumInputType.ENUM.getCode(), vtype);
	    setOtherAttr(linkHander);
    }
	/**
	 * 
	 * 创建一个新的实例LinkageCell.
	 * @param key         元素的key
	 * @param label       展示名称
	 * @param vtype       vtype  校验属性
	 * @param value       值
	 * @param linkHander  联动逻辑
	 */
	public LinkageCell(String key, String label, String vtype, String value,LinkHandler linkHander) {
	    super(key, label, EnumInputType.ENUM.getCode(), vtype,value);
	    setOtherAttr(linkHander);
    }
	/**
	 * 普通enum类型cell
	 * 创建一个新的实例LinkageCell.
	 * @param key
	 * @param label
	 * @param type
	 * @param vtype
	 * @param options
	 */
	public LinkageCell(String key, String label, String vtype,List<Option> options) {
	    super(key, label, EnumInputType.ENUM.getCode(), vtype);
	    this.options = options;
    }
	
	/**
	 * 
	 * 创建一个新的实例LinkageCell.
	 * @param key 页面key
	 * @param label 页面label
	 * @param vtype
	 * @param options 下拉options
	 * @param value   值
	 */
	public LinkageCell(String key, String label, String vtype,List<Option> options,Object value) {
	    super(key, label, EnumInputType.ENUM.getCode(), vtype,value);
	    this.options = options;
    }
	public LinkageCell(){
		super();
	}
	
	private List<Option> options = new ArrayList<Option>();
	
	public List<Option> getOptions() {
		return options;
	}
	
	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
    public void setOtherAttr(LinkHandler linkHander) {
		linkHander.setOptionsLink(this.options);
	    
    }
}
