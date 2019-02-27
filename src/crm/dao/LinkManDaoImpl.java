package crm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import crm.domain.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	@Override
	public void addLinkMan(LinkMan linkMan) {
		getHibernateTemplate().save(linkMan);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkMan> findAllLinkMan() {
		List<LinkMan> linkManList = (List<LinkMan>) getHibernateTemplate().find("from LinkMan", null);
		return linkManList;
	}

	@Override
	public Integer findLinkManCountByCondition(LinkMan linkMan) {
//		DetachedCriteria criteria = getDetachCriteria(linkMan);
//		criteria.setProjection(Projections.rowCount());
//		@SuppressWarnings("unchecked")
//		List<LinkMan> object = (List<LinkMan>) getHibernateTemplate().findByCriteria(criteria);
//		Object obj = object.get(0);
//		Long lobg = (Long) obj;
//		return lobg.intValue();
		
		StringBuilder whereSql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		getWhereSql(linkMan, whereSql, param);
		
		String sql = "select count(*) from LinkMan a, Customer b where a.customer.cid=b.cid" + whereSql.toString();
		List<Object> objectList =  (List<Object>) getHibernateTemplate().find(sql , param.toArray());
		Long lobg =  (Long) objectList.get(0);
		return lobg.intValue();
	}

	@Override
	public List<LinkMan> findLinkManByCoudition(LinkMan linkMan, Integer begin,
			Integer pageCount) {
//		@SuppressWarnings("unchecked")
//		List<LinkMan> linkManList = (List<LinkMan>) getHibernateTemplate()
//				.findByCriteria(getDetachCriteria(linkMan), begin, pageCount);
//		return linkManList;
		
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		StringBuilder whereSql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		getWhereSql(linkMan, whereSql, param);
		
		String sql = "select a from LinkMan a, Customer b where a.customer.cid=b.cid" + whereSql.toString();
		Query query = session.createQuery(sql);
		for (int i = 0; i < param.size(); i++) {
			query.setParameter(i, param.get(0));
		}
		query.setFirstResult(begin);
		query.setMaxResults(pageCount);
		
		List<LinkMan> linkManList = (List<LinkMan>) query.list();
		return linkManList;
	}
	
	private void getWhereSql(LinkMan linkMan, StringBuilder str, List<Object> param) {
		
		if (linkMan.getLinkName() != null && ! linkMan.getLinkName().isEmpty()) {
			str.append(" and a.linkName like ?");
			param.add("%" + linkMan.getLinkName() + "%");
		}
		if (linkMan.getLinkGender() != null && ! linkMan.getLinkGender().isEmpty()) {
			str.append(" and a.linkGender=?");
			param.add(linkMan.getLinkGender());
		}
		if (linkMan.getLinkPhone() != null && ! linkMan.getLinkPhone().isEmpty()) {
			str.append(" and a.linkPhone like ?");
			param.add("%" + linkMan.getLinkPhone() + "%");
		}
		if (linkMan.getLinkMobile() != null && ! linkMan.getLinkMobile().isEmpty()) {
			str.append(" and a.linkMobile like ?");
			param.add("%" + linkMan.getLinkMobile() + "%");
		}
		if (linkMan.getCustomer() != null && linkMan.getCustomer().getCustName() != null && ! linkMan.getCustomer().getCustName().isEmpty()) {
			str.append(" and b.custName like ?");
			param.add("%" + linkMan.getCustomer().getCustName() + "%");
		}
	}
	
	private DetachedCriteria getDetachCriteria(LinkMan linkMan) {
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		if (linkMan.getLinkName() != null && ! linkMan.getLinkName().isEmpty()) {
			criteria.add(Restrictions.like("linkName", "%" + linkMan.getLinkName() + "%"));
		}
		if (linkMan.getLinkGender() != null && ! linkMan.getLinkGender().isEmpty()) {
			criteria.add(Restrictions.like("linkGender", "%" + linkMan.getLinkGender() + "%"));
		}
		if (linkMan.getLinkPhone() != null && ! linkMan.getLinkPhone().isEmpty()) {
			criteria.add(Restrictions.like("linkPhone", "%" + linkMan.getLinkPhone() + "%"));
		}
		if (linkMan.getLinkMobile() != null && ! linkMan.getLinkMobile().isEmpty()) {
			criteria.add(Restrictions.like("linkMobile", "%" + linkMan.getLinkMobile() + "%"));
		}
		if (linkMan.getCustomer() != null && linkMan.getCustomer().getCustName() != null && ! linkMan.getCustomer().getCustName().isEmpty()) {
			criteria.add(Restrictions.like("customer.cid", "%" + 1 + "%"));
			System.out.println(linkMan.getCustomer().getCustName());
		}
		return criteria;
	}

	@Override
	public LinkMan findLinkManById(Integer linkid) {
		return getHibernateTemplate().load(LinkMan.class, linkid);
	}

	@Override
	public void modifyLinkMan(LinkMan linkMan) {
		getHibernateTemplate().update(linkMan);
	}

	@Override
	public void deleteLinkMan(LinkMan linkMan) {
		getHibernateTemplate().delete(linkMan);
		
	}
}
