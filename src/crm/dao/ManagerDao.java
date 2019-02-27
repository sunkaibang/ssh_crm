package crm.dao;

import java.util.List;

import crm.domain.Manager;

public interface ManagerDao {
	
	// ���û����������ѯ
	Manager findManagerByUsernameAndPassword(String username, String password);

	List<Manager> findAllManager();

	Manager findManagerByUsername(String username);

	Integer register(Manager manager);

	Manager findManagerById(Integer id);

	void modifyManager(Manager manager);
	
}
