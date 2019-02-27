package crm.dao;

import java.util.List;

import crm.domain.Customer;

public interface CustomerDao {

	void addCustomer(Customer customer);

	List<Customer> findAllCustomer();

	Customer findCustomerById(Integer cid);

	void deleteCustomer(Customer customer);

	void modifyCustomer(Customer customer);
	
	// ��ѯ����
	Integer findCustomerSize();
	
	// û��������ѯ�ķ�ҳ��ѯ�ͻ�
	List<Customer> findCustomerByPage(Integer begin, Integer pageCount);
	
	// ��������ѯ�Ĳ�ѯ����
	Integer findCustomerSizeByCondition(Customer customer);
	
	// ��������ѯ�ķ�ҳ��ѯ�ͻ�
	List<Customer> findCustomerByPageAndCondition(Customer customer,
			Integer begin, Integer pageCount);
	
	// ���ݿͻ���Դͳ�ƿͻ�
	List findCountBySource();
	
	// ���ݿͻ������ѯ�ͻ�
	List findCountByLevel();

}
