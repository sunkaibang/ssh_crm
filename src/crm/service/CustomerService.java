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
	
	// 添加客户
	public void addCustomer(Customer customer) {
		customerDao.addCustomer(customer);
	}
	
	// 查询所有客户
	public List<Customer> findAllCustomer() {
		return customerDao.findAllCustomer();
	}
	
	// 删除客户
	public void deleteCustomer(Customer customer) {
		Customer newCustomer = customerDao.findCustomerById(customer.getCid());
		if (newCustomer != null) {
			customerDao.deleteCustomer(newCustomer);
		}
		
	}
	
	// 查找客户通过id
	public Customer findCustomerById(Integer cid) {
		return customerDao.findCustomerById(cid);
	}
	
	// 修改客户
	public void modifyCustomer(Customer customer) {
		customerDao.modifyCustomer(customer);
	}
	
	// 不带条件查询的分页
	public PageBean<Customer> findAllCustomerByPage(PageBean<Customer> pageBean) {
		// 封装数据
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
	
	
	
	
	// 带条件查询的分页
	public PageBean<Customer> findAllCustomerByPageAndCondition(
			PageBean<Customer> pageBean, Customer customer) {
		// 封装pageBean
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
	
	// 根据客户来源统计客户
	public List findCountBySource() {
		return customerDao.findCountBySource();
	}
	
	// 根据客户级别查询客户
	public List findCountByLevel() {
		return customerDao.findCountByLevel();
	}
	
}
