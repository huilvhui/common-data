package com.xier.controller;

import java.util.ArrayList;
import java.util.List;


public class ResultDTO {
	
	private String code;
	private Object data;
	private String msg;
	
	public ResultDTO(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ResultDTO(List<?> trees) {
		super();
		TreePageData data = new TreePageData();
		data.setPageNo(1);
		data.setPageSize(trees.size());
		data.setRows(trees);
		this.data = data;
	}
	
	public ResultDTO(BaseResp baseResp) {
		super();
		this.code = String.valueOf(baseResp.getErrorCode());
		this.msg = baseResp.getErrorInfo();
		this.data = baseResp.getData();
	}
	
	public ResultDTO() {
		super();
	}
	
	/**
	 * 创建一个新的实例ResultDTO.
	 * @param query
	 * @param page 是否分页
	 */
	/*public <T> ResultDTO(BaseCommonPageQuery<T> query) {
		super();
		this.code = query.getErrorCode();
		this.msg = query.getErrorInfo();
		Data<T> data = new Data<T>();
		data.setPageNo(query.getStart());
		data.setPageSize(query.getSize());
		data.setTotal(query.getTotalCount());
		data.setTotalPage(query.getTotalPage());
		data.setList(query.getItems() == null?new ArrayList<T>():query.getItems());
		this.data = data;
	}*/
	
	
	public <T> ResultDTO(BaseCommonPageQuery<T> query) {
		super();
		this.code = query.getErrorCode();
		this.msg = query.getErrorInfo();
		PageData<T> data = new PageData<T>();
		data.setPageNo(query.getPageNo());
		data.setPageSize(query.getPageSize());
		data.setTotal(query.getTotalCount());
		data.setTotalPage(query.getTotalPage());
		data.setList(query.getItems() == null?new ArrayList<T>():query.getItems());
		this.data = data;
	}
	
	
	public ResultDTO(ImportDownUnitInfoQuery query) {
		super();
		this.code = query.getErrorCode();
		this.msg = query.getErrorInfo();
		PageData<ImportDownUnitResult> data = new PageData<ImportDownUnitResult>();
		data.setPageNo(query.getPageNo());
		data.setPageSize(query.getPageSize());
		data.setTotal(query.getTotalCount());
		data.setTotalPage(query.getTotalPage());
		data.setList(query.getItems() == null?new ArrayList<ImportDownUnitResult>():query.getItems());
		data.setCascadeId(query.getCascadeId());
		data.setDownCascadeIndex(query.getDownCascadeIndex());
		this.data = data;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	

	
	
    public Object getData() {
    	return data;
    }

	
    public void setData(Object data) {
    	this.data = data;
    }

	public void setData(PageData<?> data) {
		this.data = data;
	}
	
	public class TreePageData {
		
		private List<?> rows;
		
		private Integer pageNo;
		
		private Integer pageSize;
		
		private Boolean firstPage = true;
		private Boolean lastPage = true;

        
        public Boolean getFirstPage() {
        	return firstPage;
        }


		
        public void setFirstPage(Boolean firstPage) {
        	this.firstPage = firstPage;
        }


		
        public Boolean getLastPage() {
        	return lastPage;
        }


		
        public void setLastPage(Boolean lastPage) {
        	this.lastPage = lastPage;
        }


		public List<?> getRows() {
        	return rows;
        }

		
        public void setRows(List<?> rows) {
        	this.rows = rows;
        }

		
        public Integer getPageNo() {
        	return pageNo;
        }

		
        public void setPageNo(Integer pageNo) {
        	this.pageNo = pageNo;
        }

		
        public Integer getPageSize() {
        	return pageSize;
        }

		
        public void setPageSize(Integer pageSize) {
        	this.pageSize = pageSize;
        }
		
	
		
	}
	
	public class PageData<T> {
		
		private int pageSize;
		private int pageNo;
		private int total;
		private int totalPage;
		
		private Long cascadeId;
		
		private String downCascadeIndex;
		
		private List<T> list;
		
		public int getPageSize() {
			return pageSize;
		}
		
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		
		public int getPageNo() {
			return pageNo;
		}
		
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		
		public int getTotal() {
			return total;
		}
		
		public void setTotal(int total) {
			this.total = total;
		}
		
		public int getTotalPage() {
			return totalPage;
		}
		
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		
		public List<T> getList() {
			return list;
		}
		
		public void setList(List<T> list) {
			this.list = list;
		}
		
		public String getDownCascadeIndex() {
			return downCascadeIndex;
		}

		public void setDownCascadeIndex(String downCascadeIndex) {
			this.downCascadeIndex = downCascadeIndex;
		}

		public Long getCascadeId() {
			return cascadeId;
		}

		public void setCascadeId(Long cascadeId) {
			this.cascadeId = cascadeId;
		}
		
	}
	
}
