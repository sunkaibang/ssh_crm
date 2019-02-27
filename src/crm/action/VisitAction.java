package crm.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import crm.domain.Customer;
import crm.domain.Manager;
import crm.domain.PageBean;
import crm.domain.Visit;
import crm.service.CustomerService;
import crm.service.ManagerService;
import crm.service.VisitService;
import crm.tools.EacheDomain;

public class VisitAction extends ActionSupport {

	private static final long serialVersionUID = -3659050597239559986L;
	private VisitService visitService;
	private CustomerService customerService;
	private ManagerService managerService;
	private String msg;
	private List<Customer> customerList;
	private List<Manager> managerList;
	private List<Visit> visitList;
	private Visit visit;
	private PageBean<Visit> pageBean;
	
	// 跳转到添加页面
	public String addVisitPage() {
		msg = EacheDomain.msg;
		EacheDomain.msg = "";
		customerList = customerService.findAllCustomer();
		managerList = managerService.findAllManager();
		return "add_visit_page";
	}
	
	// 保存拜访信息
	public String addVisit() {
		visitService.addVisit(visit);
		EacheDomain.msg = "保存成功";
		return "re_add_visit_page";
	}
	
	// 带分页的查询所有拜访信息
	public String findAllVisit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String url = request.getRequestURI() + "?";
		if (pageBean == null) {
			pageBean = new PageBean<Visit>();
		}
		pageBean.setUrl(url);
		pageBean = visitService.findAllVisitByPage(pageBean);
		return "visit_list";
	}
	
	// 带分页和条件查询的查询拜访信息
	public String findAllVisitByCondiction() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (visit == null) {
			visit = new Visit();
		}
		if (request.getMethod().equalsIgnoreCase("get")) {
			visit = encode(visit);
		} else {
			if(request.getQueryString() != null && ! request.getQueryString().isEmpty()) {
				visit = encode(visit);
			}
		}
		String url = getUrl(request);
		if (pageBean == null) {
			pageBean = new PageBean<Visit>();
			url = getUrlAndParam(visit, url);
		}
		pageBean.setUrl(url);
		pageBean = visitService.findAllVisitByCondition(pageBean, visit);
		return "visit_list";
	}
	
	private String getUrlAndParam(Visit visit2, String url) {
		if (visit.getCustomer() != null && visit.getCustomer().getCustName() != null && ! visit.getCustomer().getCustName().isEmpty()) {
			url += "visit.customer.custName=" + visit.getCustomer().getCustName() + "&"; 
		}
		if (visit.getManager() != null && visit.getManager().getUsername() != null && ! visit.getManager().getUsername().isEmpty()) {
			url += "visit.manager.username=" + visit.getManager().getUsername() + "&"; 
		}
		if (visit.getVaddress() != null && ! visit.getVaddress().isEmpty()) {
			url += "visit.vaddress=" + visit.getVaddress() + "&"; 
		}
		if (visit.getVcontent() != null && ! visit.getVcontent().isEmpty()) {
			url += "visit.vcontent=" + visit.getVcontent() + "&"; 
		}
		return url;
	}

	private String getUrl(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		if (query == null || query.isEmpty()) {
			return uri + "?";
		} else {
			int end = query.indexOf("pageBean.currentPage");
			if (end != -1) {
				query = query.substring(0, end);
			}
			return uri + "?" + query;
		}
	}

	private Visit encode(Visit visit) throws UnsupportedEncodingException {
		if (visit.getCustomer() != null && visit.getCustomer().getCustName() != null && ! visit.getCustomer().getCustName().isEmpty()) {
			visit.getCustomer().setCustName(new String(visit.getCustomer().getCustName().getBytes("iso-8859-1"), "utf-8"));
		}
		if (visit.getManager() != null && visit.getManager().getUsername() != null && ! visit.getManager().getUsername().isEmpty()) {
			visit.getManager().setUsername(new String(visit.getManager().getUsername().getBytes("iso-8859-1"),"utf-8"));
		}
		if (visit.getVaddress() != null && ! visit.getVaddress().isEmpty()) {
			visit.setVaddress(new String(visit.getVaddress().getBytes("iso-8859-1"), "utf-8"));
		}
		if (visit.getVcontent() != null && ! visit.getVcontent().isEmpty()) {
			visit.setVcontent(new String(visit.getVcontent().getBytes("iso-8859-1"), "utf-8"));
		}
		return visit;
	}

	// 修改拜访信息第一步，跳转页面
	public String modifyVisitFirst() {
		msg = EacheDomain.msg;
		EacheDomain.msg = "";
		customerList = customerService.findAllCustomer();
		managerList = managerService.findAllManager();
		if (visit == null) {
			visit = EacheDomain.visit;
			EacheDomain.visit = null;
		}
		visit = visitService.findVisitById(visit.getVid());
		return "mod_visit_page";
	}
	
	// 修改拜访信息
	public String modifyVisit() {
		visitService.modifyVisit(visit);
		EacheDomain.msg = "保存成功";
		EacheDomain.visit = visit;
		return "re_mod_visit_page";
	}
	
	// 删除拜访信息
	public String deleteVisit() {
		visitService.deleteVisit(visit);
		return "re_visit_list";
	}

	public VisitService getVisitService() {
		return visitService;
	}

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}

	public String getMsg() {
		return msg;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public List<Manager> getManagerList() {
		return managerList;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	public List<Visit> getVisitList() {
		return visitList;
	}

	public PageBean<Visit> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Visit> pageBean) {
		this.pageBean = pageBean;
	}
	
}
