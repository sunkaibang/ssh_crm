package crm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import crm.domain.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	@Override
	public void addVisit(Visit visit) {
		getHibernateTemplate().save(visit);
	}
	
	// 查询所有记录的大小
	@Override
	public Integer findVisitSize() {
		List<Object> object = (List<Object>) getHibernateTemplate().find("select count(*) from Visit", null);
		Object obj = object.get(0);
		Long lobj = (Long) obj;
		return lobj.intValue();
	}
	
	// 带分页的查询拜访列表
	@Override
	public List<Visit> findVisitByPage(Integer begin, Integer pageCount) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		List<Visit> visitList = (List<Visit>) getHibernateTemplate().findByCriteria(criteria, begin, pageCount);
		return visitList;
	}

	@Override
	public void deleteVisit(Visit visit) {
		getHibernateTemplate().delete(visit);
	}

	@Override
	public Visit findVisitById(Integer vid) {
		return getHibernateTemplate().load(Visit.class, vid);
	}

	@Override
	public void modifyVisit(Visit visit) {
		getHibernateTemplate().update(visit);
	}

	@Override
	public Integer findVisitSizeByCondition(Visit visit) {
		
		StringBuilder whereSql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		getWhereSql(visit, whereSql, param);
		
		String sql = "select count(*) from Visit v, Customer c, Manager m where v.customer.cid=c.cid and v.manager.id=m.id" + whereSql.toString();
		List<Object> objectList = (List<Object>) getHibernateTemplate().find(sql, param.toArray());
		Long size = (Long) objectList.get(0);
		return size.intValue();
	}

	@Override
	public List<Visit> findVisitByPageAndCondition(Visit visit, int begin, int pageCount) {
		StringBuilder whereSql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		getWhereSql(visit, whereSql, param);
		
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "select v from Visit v, Customer c, Manager m where v.customer.cid=c.cid and v.manager.id=m.id" + whereSql.toString();
		Query query = session.createQuery(sql);
		for (int i = 0; i < param.size(); i++) {
			query.setParameter(i, param.get(i));
		}
		query.setFirstResult(begin);
		query.setMaxResults(pageCount);
		List<Visit> visitList = query.list();
		return visitList;
	}
	
	private void getWhereSql(Visit visit, StringBuilder str, List<Object> param) {
		if (visit.getCustomer() != null && visit.getCustomer().getCustName() != null && ! visit.getCustomer().getCustName().isEmpty()) {
			str.append(" and c.custName like ?");
			param.add("%" + visit.getCustomer().getCustName() + "%");
		}
		if (visit.getManager() != null && visit.getManager().getUsername() != null && ! visit.getManager().getUsername().isEmpty()) {
			str.append(" and m.username like ?");
			param.add("%" + visit.getManager().getUsername() + "%");
		}
		if (visit.getVaddress() != null && ! visit.getVaddress().isEmpty()) {
			str.append(" and v.vaddress like ?");
			param.add("%" + visit.getVaddress() + "%");
		}
		if (visit.getVcontent() != null && ! visit.getVcontent().isEmpty()) {
			str.append(" and v.vcontent like ?");
			param.add("%" + visit.getVcontent() + "%");
		}
	}
}
