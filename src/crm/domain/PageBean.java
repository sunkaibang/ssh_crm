package crm.domain;

import java.util.List;

public class PageBean<T> {
	
	private Integer totalCount;       	 // 总记录数
	private Integer currentPage;         // 当前页
	private Integer pageCount;  	 	 // 每页记录数
	private Integer totalPage;       	 // 总的页数，只提供get方法，通过totalCount / pageCount计算
	private Integer begin;			 	 // 开始位置，只提供get方法，使用(currentCount - 1) * pageCount计算
	private String url;
	private List<T> list;
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getTotalPage() {
		totalPage= totalCount / pageCount;
		if (totalCount % pageCount == 0) {
			return totalPage;
		} else {
			return totalPage + 1;
		}
	}
	public Integer getBegin() {
		begin = (currentPage - 1) * pageCount;
		return begin;
	}
	
}
