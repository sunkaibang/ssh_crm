package crm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import crm.dao.CustomerDao;
import crm.domain.Customer;
import crm.domain.PageBean;

@Transactional
public class CustomerService {
	private CustomerDao customerDao;
	private Integer pageCount = 3;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	// ��ӿͻ�
	public void addCustomer(Customer customer) {
		customerDao.addCustomer(customer);
	}
	
	// ��ѯ���пͻ�
	public List<Customer> findAllCustomer() {
		return customerDao.findAllCustomer();
	}
	
	// ɾ���ͻ�
	public void deleteCustomer(Customer customer) {
		Customer newCustomer = customerDao.findCustomerById(customer.getCid());
		if (newCustomer != null) {
			customerDao.deleteCustomer(newCustomer);
		}
		
	}
	
	// ���ҿͻ�ͨ��id
	public Customer findCustomerById(Integer cid) {
		return customerDao.findCustomerById(cid);
	}
	
	// �޸Ŀͻ�
	public void modifyCustomer(Customer customer) {
		customerDao.modifyCustomer(customer);
	}
	
	// ����������ѯ�ķ�ҳ
	public PageBean<Customer> findAllCustomerByPage(PageBean<Customer> pageBean) {
		// ��װ����
		PageBean<Customer> newPageBean = new PageBean<Customer>();
		newPageBean.setPageCount(pageCount);
		
		if (pageBean == null || pageBean.getCurrentPage() == null) {
			newPageBean.setCurrentPage(1);
		} else {
			newPageBean.setCurrentPage(pageBean.getCurrentPage());
		}
		
		Integer totalCount = customerDao.findCustomerSize();
		newPageBean.setTotalCount(totalCount);
		List<Customer> customerList = customerDao.findCustomerByPage(newPageBean.getBegin(), newPageBean.getPageCount());
		newPageBean.setList(customerList);
		return newPageBean;
	}
	
	
	
	
	// ��������ѯ�ķ�ҳ
	public PageBean<Customer> findAllCustomerByPageAndCondition(
			PageBean<Customer> pageBean, Customer customer) {
		// ��װpageBean
		pageBean.setPageCount(pageCount);
		if (pageBean.getCurrentPage() == null) {
			pageBean.setCurrentPage(1);
		}
		Integer totalCount = customerDao.findCustomerSizeByCondition(customer);
		pageBean.setTotalCount(totalCount);
		List<Customer> customerList = customerDao.findCustomerByPageAndCondition(customer, pageBean.getBegin(), pageBean.getPageCount());
		pageBean.setList(customerList);
		return pageBean;
	}
	
	// ���ݿͻ���Դͳ�ƿͻ�
	public List findCountBySource() {
		return customerDao.findCountBySource();
	}
	
	// ���ݿͻ������ѯ�ͻ�
	public List findCountByLevel() {
		return customerDao.findCountByLevel();
	}
	
}
