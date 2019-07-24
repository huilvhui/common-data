package com.xier.dynamic.form;

import java.util.List;

/**
 * <p>
 * 普通下拉实现
 * </p>
 * @author lvhui5 2017年11月7日 下午3:43:46
 * @version V1.0
 */
public class SelectLinkHandler extends AbstrctLinkHandler<Option>{

	public SelectLinkHandler(List<Option> selects) {
	    super(selects);
    }

	@Override
    public Option setOptionsLinkCell(Option select) {
	    return select;
    }
	
}
