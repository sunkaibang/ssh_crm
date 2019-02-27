package crm.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import crm.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public void addCustomer(Customer customer) {
		getHibernateTemplate().save(customer);

	}

	@Override
	public List<Customer> findAllCustomer() {
		List<Customer> customerList = (List<Customer>) getHibernateTemplate()
				.find("from Customer", null);
		return customerList;
	}

	@Override
	public Customer findCustomerById(Integer cid) {
		Customer customer = getHibernateTemplate().get(Customer.class, cid);
		return customer;
	}

	@Override
	public void deleteCustomer(Customer customer) {
		getHibernateTemplate().delete(customer);
	}

	@Override
	public void modifyCustomer(Customer customer) {
		getHibernateTemplate().update(customer);

	}

	// 查询总数
	@Override
	public Integer findCustomerSize() {
		List<Object> object = (List<Object>) getHibernateTemplate().find(
				"select count(*) from Customer", null);
		Object obj = object.get(0);
		Long lobj = (Long) obj;
		return lobj.intValue();
	}

	// 没有条件查询的分页查询客户
	@Override
	public List<Customer> findCustomerByPage(Integer begin, Integer pageCount) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from Customer");
		query.setFirstResult(begin);
		query.setMaxResults(pageCount);

		List<Customer> customerList = query.list();
		return customerList;
	}

	// 带条件查询的查询总数
	@Override
	public Integer findCustomerSizeByCondition(Customer customer) {
		DetachedCriteria criteria = getDetachCriteria(customer);
		criteria.setProjection(Projections.rowCount());
		List<Object> object = (List<Object>) getHibernateTemplate()
				.findByCriteria(criteria);
		Object obj = object.get(0);
		Long lobj = (Long) obj;
		return lobj.intValue();
	}

	// 待条件查询的分页查询客户
	@Override
	public List<Customer> findCustomerByPageAndCondition(Customer customer,
			Integer begin, Integer pageCount) {
		List<Customer> customerList = (List<Customer>) getHibernateTemplate()
				.findByCriteria(getDetachCriteria(customer), begin, pageCount);
		return customerList;
	}

	// 创建离线查询对象
	@SuppressWarnings("unused")
	private DetachedCriteria getDetachCriteria(Customer customer) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if (customer.getCustName() != null && !customer.getCustName().isEmpty()) {
			criteria.add(Restrictions.like("custName",
					"%" + customer.getCustName() + "%"));
		}
		if (customer.getDataDictionary() != null ) {
			criteria.add(Restrictions.eq("dataDictionary", customer.getDataDictionary()));
		}
		if (customer.getCustSource() != null
				&& !customer.getCustSource().isEmpty()) {
			criteria.add(Restrictions.like("custSource",
					"%" + customer.getCustSource() + "%"));
		}
		if (customer.getCustPhone() != null
				&& !customer.getCustPhone().isEmpty()) {
			criteria.add(Restrictions.like("custPhone",
					"%" + customer.getCustPhone() + "%"));
		}
		if (customer.getCustMoble() != null
				&& !customer.getCustMoble().isEmpty()) {
			criteria.add(Restrictions.like("custMoble",
					"%" + customer.getCustMoble() + "%"));
		}
		return criteria;
	}
	
	// 根据客户来源统计客户
	@Override
	public List findCountBySource() {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("SELECT COUNT(*) num,custSource  FROM crm_customer GROUP BY custSource");
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = query.list();
		return list;
	}
	
	// 根据客户级别查询客户
	@Override
	public List findCountByLevel() {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("SELECT c.num, d.dname FROM (SELECT COUNT(*) num, custLevel FROM crm_customer GROUP BY custLevel)c, crm_datadictionary d WHERE c.custLevel=d.did");
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = query.list();
		return list;
	}
}
