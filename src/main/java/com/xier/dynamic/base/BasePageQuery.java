package com.xier.dynamic.base;



/**
 * <p>
 * baseQuery 基础分页查询类
 * </p>
 * @author lvhui5 2017年5月16日 下午5:20:05
 * @version V1.0
 */

public class BasePageQuery<T>  extends BaseQuery<T> {
	
	/*Logger LOG = LoggerFactory.getLogger(BasePageQuery.class);*/
	
	private static final int defaultPageSize = 10;// 每页默认显示10条
	private static final int defaultFristPage = 1;// 默认页码
	private static final Long defaultTotalCount = 0L;// 默认总记录数

	private Integer pageSize;// 当前每页大小
	private Long totalCount;// 当前总记录数

	private Integer currentPage;// 当前页码
	
	@SuppressWarnings("unused")
	private Integer pageFirstItem;// 分页查询起始值（row >= pageFirstItem）
	@SuppressWarnings("unused")
	private Integer pageLastItem;// 分页查询结束值（row <= pageLastItem）
	
	private String orderByClause;
	
	public Integer getPageFirstItem() {
		int cPage = this.getCurrentPage().intValue();
		if (cPage == 1) {
			return 0;
		} else {
			cPage--;
			int pgSize = this.getPageSize().intValue();
			return pgSize * cPage;
		}
	}
	
	protected Integer getDefaultPageSize() {
		return defaultPageSize;
	}
	
	public Integer getCurrentPage() {
		if (currentPage == null) {
			return defaultFristPage;
		} else {
			return currentPage;
		}
	}
	
	public void setCurrentPage(Integer cPage) {
		currentPage = cPage;
	}
	
	public Integer getPageSize() {
		if (pageSize == null) {
			return this.getDefaultPageSize();
		} else {
			return pageSize;
		}
	}
	
	public void setPageSize(Integer pSize) {
		pageSize = pSize;
	}
	
	public Long getTotalCount() {
		if (totalCount == null) {
			return defaultTotalCount;
		} else {
			return totalCount;
		}
	}
	
	public void setTotalCount(Long totalCount) {
		if (totalCount == null) {
			totalCount = 0L;
		}
		this.totalCount = totalCount;
		int current = this.getCurrentPage().intValue();
		int lastPage = this.getTotalPage();
		if (current > lastPage) {
			this.setCurrentPage(lastPage);
		}
	}
	
	public int getTotalPage() {
		int pgSize = this.getPageSize().intValue();
		int totalCount = this.getTotalCount().intValue();
		int result = totalCount / pgSize;
		if (totalCount % pgSize != 0) {
			result++;
		}
		return result;
	}
	
	public String getOrderByClause() {
		return orderByClause;
	}
	
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	
}
