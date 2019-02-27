package crm.dao;

import java.util.List;

import crm.domain.LinkMan;

public interface LinkManDao {

	void addLinkMan(LinkMan linkMan);

	List<LinkMan> findAllLinkMan();

	Integer findLinkManCountByCondition(LinkMan linkMan);

	List<LinkMan> findLinkManByCoudition(LinkMan linkMan, Integer begin,
			Integer pageCount);

	LinkMan findLinkManById(Integer linkid);

	void modifyLinkMan(LinkMan linkMan);

	void deleteLinkMan(LinkMan linkMan);

}
