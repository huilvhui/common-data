package com.xier.dynamic.form;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstrctLinkHandler<T> implements LinkHandler {
	
	protected List<T> selects;

	
	public AbstrctLinkHandler(List<T> selects) {
		this.selects = selects;
    }

	public List<T> getSelects() {
		return selects;
	}
	
	public void setSelects(List<T> selects) {
		this.selects = selects;
	}
    /**
     * 用于实现对每个需要联动的option的实现逻辑，封装cell
     * @author lvhui5 2017年11月8日 下午2:22:06
     * @param select 每个option对应的entiy
     * @return
     */
	public abstract Option setOptionsLinkCell(T select);
	
	
    public void setOptionsLink(List<Option> options) {
		if(options == null)
			options = new ArrayList<Option>();
		for(int i = 0; i < selects.size(); i++){
			T select = selects.get(i);
			options.add(setOptionsLinkCell(select));	
		}
		//处理下拉联动
	    
    }
}
