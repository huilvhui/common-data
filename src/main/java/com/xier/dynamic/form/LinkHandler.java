package com.xier.dynamic.form;

import java.util.List;

/**
 * 联动处理接口
 * <p>
 * 主要实现：
 * 处理select
 * </p>
 * @author lvhui5 2017年11月7日 下午3:37:19
 * @version V1.0
 */
@FunctionalInterface
public interface LinkHandler {
	/**
	 * 配置下拉联动
	 * @author lvhui5 2017年11月7日 下午3:41:02
	 */
	public void  setOptionsLink(List<Option> options);
	
}
