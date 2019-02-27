package crm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.LinkManDao;
import crm.domain.LinkMan;
import crm.domain.PageBean;

@Transactional
public class LinkManService {
	private LinkManDao linkManDao;
	private Integer pageCount = 3;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	// 添加联系人
	public void addLinkMan(LinkMan linkMan) {
		linkManDao.addLinkMan(linkMan);
	}
	
	// 查询所有联系人
	public List<LinkMan> findAllLinkMan() {
		return linkManDao.findAllLinkMan();
	}
	
	// 条件查询
	public PageBean<LinkMan> findAllLinkManByCondition(
			PageBean<LinkMan> pageBean, LinkMan linkMan) {
		pageBean.setPageCount(pageCount);
		if (pageBean.getCurrentPage() == null ) {
			pageBean.setCurrentPage(1);
		}
		Integer totalCount = linkManDao.findLinkManCountByCondition(linkMan);
		pageBean.setTotalCount(totalCount);
		List<LinkMan> list = linkManDao.findLinkManByCoudition(linkMan, pageBean.getBegin(), pageBean.getPageCount());
		pageBean.setList(list);
		return pageBean;
	}
	
	
	public LinkMan findLinkManById(Integer linkid) {
		return linkManDao.findLinkManById(linkid);
	}

	public void modifyLinkMan(LinkMan linkMan) {
		linkManDao.modifyLinkMan(linkMan);
	}

	public void deleteLinkMan(LinkMan linkMan) {
		LinkMan newLinkMan = linkManDao.findLinkManById(linkMan.getLinkid());
		if (newLinkMan != null) {
			linkManDao.deleteLinkMan(newLinkMan);
		}
	}
	
}
