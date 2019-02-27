package crm.dao;

import java.util.List;

import crm.domain.Manager;

public interface ManagerDao {
	
	// 按用户名和密码查询
	Manager findManagerByUsernameAndPassword(String username, String password);

	List<Manager> findAllManager();

	Manager findManagerByUsername(String username);

	Integer register(Manager manager);

	Manager findManagerById(Integer id);

	void modifyManager(Manager manager);
	
}
