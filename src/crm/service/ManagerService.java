package crm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.ManagerDao;
import crm.domain.Manager;
import crm.tools.EmailUtils;

@Transactional
public class ManagerService {
	
	private ManagerDao managerDao;

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}
	
	// 登录
	public Manager login(Manager manager) {
		Manager newManager = managerDao.findManagerByUsernameAndPassword(manager.getUsername(), manager.getPassword());
		return newManager;
	}
	
	// 查询所有用户
	public List<Manager> findAllManager() {
		return managerDao.findAllManager();
	}

	public Manager findManagerByUsername(String username) {
		return managerDao.findManagerByUsername(username);
	}

	public void register(Manager manager) {
		Integer id = managerDao.register(manager);
		
		EmailUtils em = new EmailUtils();
		em.sendEamil(manager.getEmail(), new String[]{id.toString()});
	}

	public void active(Integer id) {
		Manager manager = managerDao.findManagerById(id);
		manager.setState(true);
		managerDao.modifyManager(manager);
	}


}
