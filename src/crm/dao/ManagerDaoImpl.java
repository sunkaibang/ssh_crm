package crm.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import crm.domain.Manager;

public class ManagerDaoImpl extends HibernateDaoSupport implements ManagerDao {

	@Override
	public Manager findManagerByUsernameAndPassword(String username,
			String password) {
		@SuppressWarnings("unchecked")
		List<Manager> managerList = (List<Manager>) getHibernateTemplate().find(
				"from Manager where username=? and password=?", username,
				password);
		if (managerList != null && managerList.size() > 0) {
			return managerList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Manager> findAllManager() {
		@SuppressWarnings("unchecked")
		List<Manager> managerList = (List<Manager>) getHibernateTemplate().find("from Manager", null);
		return managerList;
	}

	@Override
	public Manager findManagerByUsername(String username) {
		List<Manager> managerList = (List<Manager>) getHibernateTemplate().find("from Manager where username=?", username);
		if (managerList != null && managerList.size() > 0) {
			return managerList.get(0);
		}
		return null;
	}

	@Override
	public Integer register(Manager manager) {
		getHibernateTemplate().save(manager);
		return manager.getId();
	}

	@Override
	public Manager findManagerById(Integer id) {
		return getHibernateTemplate().load(Manager.class, id);
	}

	@Override
	public void modifyManager(Manager manager) {
		getHibernateTemplate().update(manager);
		
	}

}
