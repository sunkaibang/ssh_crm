package crm.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import crm.domain.Customer;
import crm.domain.DataDictionary;
import crm.domain.PageBean;
import crm.service.CustomerService;
import crm.service.DataDictionaryService;
import crm.tools.EacheDomain;

public class CustomerAction extends ActionSupport {
	
	private static final long serialVersionUID = -2890914902747125859L;
	private CustomerService customerService;
	private Customer customer;
	private List<Customer> customerList;
	private String msg;
	private PageBean<Customer> pageBean;
	private DataDictionaryService dataDictionaryService;
	private List<DataDictionary> dataDictionaryList;
	private String isSelectLevel;
	
	// ���ݿͻ�����ͳ��
	public String countByLevel() {
		List list = customerService.findCountByLevel();
		ServletActionContext.getRequest().setAttribute("request_customerList", list);
		return "customer_level";
	}
	
	
	// ���ݿͻ���Դͳ��
	public String countBySource() {
		List list = customerService.findCountBySource();
		ServletActionContext.getRequest().setAttribute("request_customerList", list);
		return "customer_source";
	}
	
	// ��ӿͻ�
	public String addCustomer() {
		customerService.addCustomer(customer);
		EacheDomain.msg = "����ɹ�";
		return "add_customer_page";
	}
	// ��ѯ���пͻ�
	public String findAllCustomer() throws UnsupportedEncodingException {
		// �����Ĳ�ѯ���пͻ���ֻ����һ�����
//		customerList = customerService.findAllCustomer();
		
		// û�����������ѯ�ķ�ҳ��ѯ��ֻ����һ�����
//		pageBean = customerService.findAllCustomerByPage(pageBean);
		
		
		// customer,pageBean����ֵ�ж�����Ϊ�Ӳ˵����������ʱ������û�б�����
		HttpServletRequest request = ServletActionContext.getRequest();
		if (customer == null) {
			customer = new Customer();
		}
		// �ж��Ƿ��ϼ���
		if (isSelectLevel != null && isSelectLevel.equalsIgnoreCase("no")) {
			customer.setDataDictionary(null);
		}
		if (request.getMethod().equalsIgnoreCase("GET")) {
			customer = encode(customer);
		} else {
			// ҳ����������ʹ��POST�ύ����ת��POST�ύurl���в��������Ի�Ҫ�����봦��
			if (request.getQueryString() != null && !request.getQueryString().isEmpty())
				customer = encode(customer);
		}
		String url = getUrl(request, customer);
		if (pageBean == null) {
			pageBean = new PageBean<Customer>();
			// ������ѯ��ʱ���Ҫ��ѯ�Ĳ���Ҳ������
			url = getUrlWithParamter(url, customer);
		}
		
		pageBean.setUrl(url);
 		pageBean = customerService.findAllCustomerByPageAndCondition(pageBean, customer);
		
		return "customer_list";
	}
	
	// ���ɸѡ��url���ϲ���
	private String getUrlWithParamter(String url, Customer customer) {
		if (customer.getCustName() != null && !customer.getCustName().isEmpty()) {
			// ����һ���е�ǰҳ���������Կ��Լ���"&"
			url += "customer.custName=" + customer.getCustName() + "&";
		}
		if (customer.getCustSource() != null && ! customer.getCustSource().isEmpty()) {
			url += "customer.custSource=" + customer.getCustSource() + "&";
		}
		if (customer.getCustPhone() != null && ! customer.getCustPhone().isEmpty()) {
			url += "customer.custPhone=" + customer.getCustPhone() + "&";
		}
		if (customer.getCustMoble() != null && ! customer.getCustMoble().isEmpty()) {
			url += "customer.custMoble=" + customer.getCustMoble() + "&";
		}
		return url;
	}
	
	// ����get����������������
	private Customer encode(Customer customer) throws UnsupportedEncodingException {
		if (customer.getCustName() != null && ! customer.getCustName().isEmpty()) {
			customer.setCustName(new String(customer.getCustName().getBytes("iso-8859-1"), "utf-8"));
		}
		if (customer.getCustSource() != null && ! customer.getCustSource().isEmpty()) {
			customer.setCustSource(new String(customer.getCustSource().getBytes("iso-8859-1"),"utf-8"));
		}
		
		return customer;
	}
	
	// ��ȡ������ѯ�Ĳ���
	private String getUrl(HttpServletRequest request, Customer customer) {
		StringBuilder str = new StringBuilder();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		if (queryString == null || queryString.isEmpty()) {
			str.append(uri + "?");
		} else {
			// ����������һ����ǰҳ��������ת��POST�ύ��ǰҳ�Ĳ�����û�н��ϣ����������POST�ύ�������������ı�
			int end = queryString.indexOf("pageBean.currentPage");
			if (end != -1) {
				queryString = queryString.substring(0, queryString.indexOf("pageBean.currentPage"));
			}
			str.append(uri + "?" + queryString);
		}
		return str.toString();
	}
	
	// ɾ���ͻ�
	public String deleteCustomer() {
		customerService.deleteCustomer(customer);
		return "redirect_customer_list";
	}
	
	// �޸Ŀͻ���һ����תҳ��
	public String moddifyCustomerFirst() {
		customer = customerService.findCustomerById(customer.getCid());
		dataDictionaryList = dataDictionaryService.findAllDataDictionary();
		return "mod_customer_page";
	}
	
	// �޸Ŀͻ�
	public String modifyCustomer() {
		customerService.modifyCustomer(customer);
		msg = "�޸ĳɹ�";
		dataDictionaryList = dataDictionaryService.findAllDataDictionary();
		return "mod_customer_page";
	}
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getMsg() {
		return msg;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}
	public PageBean<Customer> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<Customer> pageBean) {
		this.pageBean = pageBean;
	}
	public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
		this.dataDictionaryService = dataDictionaryService;
	}
	public List<DataDictionary> getDataDictionaryList() {
		return dataDictionaryList;
	}
	public void setIsSelectLevel(String isSelectLevel) {
		this.isSelectLevel = isSelectLevel;
	}
}
