package crm.dao;

import java.util.List;

import crm.domain.Customer;

public interface CustomerDao {

	void addCustomer(Customer customer);

	List<Customer> findAllCustomer();

	Customer findCustomerById(Integer cid);

	void deleteCustomer(Customer customer);

	void modifyCustomer(Customer customer);
	
	// 查询总数
	Integer findCustomerSize();
	
	// 没有条件查询的分页查询客户
	List<Customer> findCustomerByPage(Integer begin, Integer pageCount);
	
	// 带条件查询的查询总数
	Integer findCustomerSizeByCondition(Customer customer);
	
	// 待条件查询的分页查询客户
	List<Customer> findCustomerByPageAndCondition(Customer customer,
			Integer begin, Integer pageCount);
	
	// 根据客户来源统计客户
	List findCountBySource();
	
	// 根据客户级别查询客户
	List findCountByLevel();

}
