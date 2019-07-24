package com.xier.dynamic.data.constructor;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

import com.xier.dynamic.data.block.PersistBlock;



/**
 * 
 * <p>
 * 持久化构造
 * </p>
 * @author lvhui5 2017年7月21日 下午1:55:06
 * @version V1.0
 */
public abstract class PersistBodyConstruct implements PersistConstruct {

	protected static final String separator = ",";
	
	protected static final String left_bracket = "(";
	
	protected static final String right_bracket = ")";
	
	protected static final String quote = "'";
	

	public final void construct(PersistBlock persistBlock) {
		if (persistBlock == null) {
			return;
		}
		if (StringUtils.isBlank(persistBlock.getTableName())){
			return;
		}
		if (!innerConstruct(persistBlock)) {
			return;
		}
		install(persistBlock);
	}


	protected String contructCloumns(PersistBlock persistBlock){
		String str = innerContructCloumns(persistBlock);
		return removeLastChar(str);
	}
	
	protected abstract String innerContructCloumns(PersistBlock persistBlock);
	
	protected abstract boolean innerConstruct(PersistBlock persistBlock);
	
	
	protected abstract void install(PersistBlock persistBlock);
	
	
	protected String adaptString(String str,Object... params){
		return MessageFormat.format(str, params);
	}
	//remove last separator
	protected String removeLastChar(String str){
		return str.substring(0, str.length()-1);
	}
}
