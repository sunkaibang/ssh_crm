package crm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.VisitDao;
import crm.domain.PageBean;
import crm.domain.Visit;

@Transactional
public class VisitService {
	
	private VisitDao visitDao;
	private Integer pageCount = 3;
	
	public VisitDao getVisitDao() {
		return visitDao;
	}

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public void addVisit(Visit visit) {
		visitDao.addVisit(visit);
		
	}
	
	// 带分页的查询拜访信息
	public PageBean<Visit> findAllVisitByPage(PageBean<Visit> pageBean) {
		pageBean.setPageCount(pageCount);
		if (pageBean.getCurrentPage() == null) {
			pageBean.setCurrentPage(1);
		}
		Integer totalCount = visitDao.findVisitSize();
		pageBean.setTotalCount(totalCount);
		List<Visit> visitList = visitDao.findVisitByPage(pageBean.getBegin(), pageBean.getPageCount());
		pageBean.setList(visitList);
		return pageBean;
	}

	public void deleteVisit(Visit visit) {
		visitDao.deleteVisit(visit);
		
	}

	public Visit findVisitById(Integer vid) {
		return visitDao.findVisitById(vid);
	}

	public void modifyVisit(Visit visit) {
		visitDao.modifyVisit(visit);
	}

	public PageBean<Visit> findAllVisitByCondition(PageBean<Visit> pageBean, Visit visit) {
		pageBean.setPageCount(pageCount);
		if (pageBean.getCurrentPage() == null) {
			pageBean.setCurrentPage(1);
		}
		Integer totalCount = visitDao.findVisitSizeByCondition(visit);
		pageBean.setTotalCount(totalCount);
		List<Visit> visitList = visitDao.findVisitByPageAndCondition(visit, pageBean.getBegin(), pageBean.getPageCount());
		pageBean.setList(visitList);
		return pageBean;
	}
	
	
}
