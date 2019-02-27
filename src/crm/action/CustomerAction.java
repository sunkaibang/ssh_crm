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
	
	// 根据客户级别统计
	public String countByLevel() {
		List list = customerService.findCountByLevel();
		ServletActionContext.getRequest().setAttribute("request_customerList", list);
		return "customer_level";
	}
	
	
	// 根据客户来源统计
	public String countBySource() {
		List list = customerService.findCountBySource();
		ServletActionContext.getRequest().setAttribute("request_customerList", list);
		return "customer_source";
	}
	
	// 添加客户
	public String addCustomer() {
		customerService.addCustomer(customer);
		EacheDomain.msg = "保存成功";
		return "add_customer_page";
	}
	// 查询所有客户
	public String findAllCustomer() throws UnsupportedEncodingException {
		// 单纯的查询所有客户，只有这一条语句
//		customerList = customerService.findAllCustomer();
		
		// 没有添加条件查询的分页查询，只有这一条语句
//		pageBean = customerService.findAllCustomerByPage(pageBean);
		
		
		// customer,pageBean做空值判断是因为从菜单点击过来的时候它们没有被创建
		HttpServletRequest request = ServletActionContext.getRequest();
		if (customer == null) {
			customer = new Customer();
		}
		// 判断是否勾上级别
		if (isSelectLevel != null && isSelectLevel.equalsIgnoreCase("no")) {
			customer.setDataDictionary(null);
		}
		if (request.getMethod().equalsIgnoreCase("GET")) {
			customer = encode(customer);
		} else {
			// 页面中有两处使用POST提交，跳转的POST提交url还有参数，所以还要做乱码处理
			if (request.getQueryString() != null && !request.getQueryString().isEmpty())
				customer = encode(customer);
		}
		String url = getUrl(request, customer);
		if (pageBean == null) {
			pageBean = new PageBean<Customer>();
			// 条件查询的时候把要查询的参数也链接上
			url = getUrlWithParamter(url, customer);
		}
		
		pageBean.setUrl(url);
 		pageBean = customerService.findAllCustomerByPageAndCondition(pageBean, customer);
		
		return "customer_list";
	}
	
	// 点击筛选后，url加上参数
	private String getUrlWithParamter(String url, Customer customer) {
		if (customer.getCustName() != null && !customer.getCustName().isEmpty()) {
			// 后面一定有当前页参数，所以可以加上"&"
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
	
	// 处理get请求中文乱码问题
	private Customer encode(Customer customer) throws UnsupportedEncodingException {
		if (customer.getCustName() != null && ! customer.getCustName().isEmpty()) {
			customer.setCustName(new String(customer.getCustName().getBytes("iso-8859-1"), "utf-8"));
		}
		if (customer.getCustSource() != null && ! customer.getCustSource().isEmpty()) {
			customer.setCustSource(new String(customer.getCustSource().getBytes("iso-8859-1"),"utf-8"));
		}
		
		return customer;
	}
	
	// 获取条件查询的参数
	private String getUrl(HttpServletRequest request, Customer customer) {
		StringBuilder str = new StringBuilder();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		if (queryString == null || queryString.isEmpty()) {
			str.append(uri + "?");
		} else {
			// 超链接上面一定当前页，但是跳转的POST提交当前页的参数并没有接上，所以如果是POST提交，参数不发生改变
			int end = queryString.indexOf("pageBean.currentPage");
			if (end != -1) {
				queryString = queryString.substring(0, queryString.indexOf("pageBean.currentPage"));
			}
			str.append(uri + "?" + queryString);
		}
		return str.toString();
	}
	
	// 删除客户
	public String deleteCustomer() {
		customerService.deleteCustomer(customer);
		return "redirect_customer_list";
	}
	
	// 修改客户第一步跳转页面
	public String moddifyCustomerFirst() {
		customer = customerService.findCustomerById(customer.getCid());
		dataDictionaryList = dataDictionaryService.findAllDataDictionary();
		return "mod_customer_page";
	}
	
	// 修改客户
	public String modifyCustomer() {
		customerService.modifyCustomer(customer);
		msg = "修改成功";
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
