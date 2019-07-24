package com.xier.dynamic.data.constructor;

import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.xier.dynamic.constant.EnumCompareType;
import com.xier.dynamic.constant.EnumStatisticsType;
import com.xier.dynamic.data.block.PersistBlock;
import com.xier.dynamic.data.block.PersistBlock.Filter;

public class QueryPersistBodyConstruct extends PersistBodyConstruct {
	
	private static final String inner = "select {0} from {1} t  {2}  {3} ";
	
	private static final String groupby_prefix = "group by ";
	
	private static final String where_prefix = "where ";
	
	private static final String column_prefix = "t.";
	
	private static final String condition = " and ";
	
	private static final String count_sum_column = " count(*) ";
	
	private static final String as = " as ";
	
	public static final String count_sum = " countSum ";

	private static final String order_by = " order by ";
	
	private LimitType limitType = LimitType.NOT_LIMIT;
	

	
	public QueryPersistBodyConstruct(LimitType limit) {
		super();
		this.limitType = limit;
	}
	
	public static enum LimitType {
		
		/**
		 * 支持分页
		 */
		LIMIT,
		
		/**
		 * 不做分页
		 */
		NOT_LIMIT
	}
	
	@Override
	public boolean innerConstruct(PersistBlock persistBlock) {
		if (CollectionUtils.isEmpty(persistBlock.getGroupBy()) && CollectionUtils.isEmpty(persistBlock.getColumns()))
			return false;
		if (!CollectionUtils.isEmpty(persistBlock.getGroupBy()) && CollectionUtils.isEmpty(persistBlock.getCountColumns()))
			return false;
		return true;
	}
	
	@Override
	protected void install(PersistBlock persistBlock) {
		Object[] params = {contructCloumns(persistBlock), persistBlock.getTableName(),
		        contructWhere(persistBlock.getFilters()), contructGroupby(persistBlock.getGroupBy())};
		if (limitType == LimitType.NOT_LIMIT) {
			persistBlock.setExcuteSql(adaptString(inner, params) + constructOrderby(persistBlock));
		} else {
			persistBlock.setExcuteSql(adaptString(inner, params) + constructOrderby(persistBlock) + persistBlock.limitSuffix());
		}
		
	}
	
	@Override
	protected String innerContructCloumns(PersistBlock persistBlock) {
		StringBuilder sb = new StringBuilder();
		if (!CollectionUtils.isEmpty(persistBlock.getGroupBy())) { // group by
			if (persistBlock.ifCount())
				sb.append(count_sum_column).append(as).append(count_sum).append(separator);
			for (Entry<String, EnumStatisticsType> column : persistBlock.getCountColumns().entrySet()) {
				if (!persistBlock.getGroupBy().contains(column.getKey())) {
					sb.append(column.getValue().getValue()).append(left_bracket)
					        // 转化下空格
					        //.append(constructConvert(column.getKey(), persistBlock)).append(persistBlock.getNumConvertSuffix())
					        .append(column.getKey())
					        .append(right_bracket).append(as).append(column.getKey()).append(separator);
				}
			}
			for (String groupby : persistBlock.getGroupBy()) {
				sb.append(groupby).append(as).append(groupby).append(separator);
			}
		} else {
			for (String column : persistBlock.getColumns()) {
				sb.append(column_prefix).append(column).append(as).append(column).append(separator);
			}
		}
		
		return sb.toString();
	}
	
	private String contructGroupby(List<String> groupbyColumns) {
		if (CollectionUtils.isEmpty(groupbyColumns))
			return StringUtils.EMPTY;
		StringBuilder sb = new StringBuilder(groupby_prefix);
		for (String column : groupbyColumns) {
			sb.append(column_prefix).append(column).append(separator);
		}
		return removeLastChar(sb.toString());
	}
	
	/**
	 * @author lvhui5 2017年7月21日 下午4:06:55
	 * @param groupbyColumns
	 * @return
	 */
	private String contructWhere(List<Filter> filters) {
		if (CollectionUtils.isEmpty(filters))
			return StringUtils.EMPTY;
		StringBuilder sb = new StringBuilder(where_prefix);
		for (int i = 0; i < filters.size(); i++) {
			Filter filtrer = filters.get(i);
			if (EnumCompareType.indexByValue(filtrer.getType()) == null) {
				throw new IllegalArgumentException("unknow filter type   " + filtrer.getType());
			}
			sb.append(column_prefix).append(filtrer.getName())
			        .append(EnumCompareType.indexByValue(filtrer.getType()).getValue()).append(quote)
			        .append(filtrer.getValue()).append(quote);
			if (i != filters.size() - 1) {
				sb.append(condition);
			}
		}
		return sb.toString();
	}
	
	/*private String constructConvert(String column, PersistBlock persistBlock) {
		StringBuilder sb = new StringBuilder(persistBlock.getConvertNum());
		sb.append(left_bracket).append(column).append(separator).append(quote).append(fill_char).append(quote)
		        .append(separator).append(quote).append(zero).append(quote).append(right_bracket);
		return sb.toString();
	}*/
	
	private String constructOrderby(PersistBlock persistBlock) {
		if (StringUtils.isNotBlank(persistBlock.getOrderByClause())) {
			StringBuilder sb = new StringBuilder(order_by);
			if (!CollectionUtils.isEmpty(persistBlock.getGroupBy())) {
				sb.append(persistBlock.getOrderByClause());
			} else {
				String[] orders = persistBlock.getOrderByClause().split(separator);
				for(int i = 0 ;i< orders.length ; i++){
					sb.append(column_prefix).append(orders[i]);	
					if(i != orders.length-1)
						sb.append(separator);
				}
			}
			return sb.toString();
		}
		return StringUtils.EMPTY;
	}
}
