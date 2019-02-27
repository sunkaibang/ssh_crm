package crm.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import crm.domain.Customer;
import crm.domain.LinkMan;
import crm.domain.PageBean;
import crm.service.CustomerService;
import crm.service.LinkManService;
import crm.tools.EacheDomain;

public class LinkManAction extends ActionSupport {

	private static final long serialVersionUID = -6409691889067478635L;
	private LinkManService linkManService;
	private CustomerService customerService;
	private LinkMan linkMan;
	private List<Customer> customerList;
	private List<LinkMan> linkManList;
	private PageBean<LinkMan> pageBean;
	private String msg;

	private File upload;
	private String uploadFileName;

	// 跳转到修改联系人的页面
	public String modifyLinkManFirst() {
		msg = EacheDomain.msg;
		EacheDomain.msg = "";
		if (linkMan == null) {
			linkMan = EacheDomain.linkMan;
		}
		customerList = customerService.findAllCustomer();
		linkMan = linkManService.findLinkManById(linkMan.getLinkid());
		EacheDomain.linkMan = null;
		return "mod_linkman_page";
	}

	public String modifyLinkMan() {
		linkManService.modifyLinkMan(linkMan);
		EacheDomain.msg = "保存成功";
		EacheDomain.linkMan = linkMan;
		return "re_mod_linkman_page";
	}
	
	// 删除联系人
	public String deleleLinkMan() {
		linkManService.deleteLinkMan(linkMan);
		return "redirect_linkMan_list";
	}

	// 跳转到添加联系人的页面
	public String addLinkManPage() {
		msg = EacheDomain.msg;
		EacheDomain.msg = "";
		customerList = customerService.findAllCustomer();
		return "add_customer_page";
	}

	// 添加联系人
	public String addLinkMan() throws IOException {
		if (upload != null) {
			String filePath = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("")
					+ "/WEB-INF" + "/uploadFile/" + uploadFileName;
			System.out.println(filePath);
			FileUtils.copyFile(upload, new File(filePath));
		}
		linkManService.addLinkMan(linkMan);
		EacheDomain.msg = "保存成功";
		return "chain_add_customer_page";
	}

	// 查询所有联系人
	public String findAllLinkMan() {
		// 单纯的查询所有联系人，只有这一个就可以了
		// linkManList = linkManService.findAllLinkMan();

		HttpServletRequest request = ServletActionContext.getRequest();
		String url = getUrl(request);
		if (linkMan == null) {
			linkMan = new LinkMan();
		}
		if (request.getMethod().equalsIgnoreCase("get")) {
			linkMan = encode(linkMan);
		} else {
			if (request.getQueryString() != null
					&& !request.getQueryString().isEmpty()) {
				linkMan = encode(linkMan);
			}
		}
		if (pageBean == null) {
			pageBean = new PageBean<LinkMan>();
			url = getUrlAndParamter(url, linkMan);
		}
		pageBean.setUrl(url);
		pageBean = linkManService.findAllLinkManByCondition(pageBean, linkMan);
		return "linkman_page";
	}

	private String getUrlAndParamter(String url, LinkMan linkMan) {
		if (linkMan.getLinkName() != null && !linkMan.getLinkName().isEmpty()) {
			url += "linkMan.linkName=" + linkMan.getLinkName() + "&";
		}
		if (linkMan.getLinkGender() != null && ! linkMan.getLinkGender().isEmpty()) {
			url += "linkMan.linkGender=" + linkMan.getLinkGender() + "&";
		}
		if (linkMan.getLinkPhone() != null && ! linkMan.getLinkPhone().isEmpty()) {
			url += "linkMan.linkPhone=" + linkMan.getLinkPhone() + "&";
		}
		if (linkMan.getLinkMobile() != null && ! linkMan.getLinkMobile().isEmpty()) {
			url += "linkMan.linkMobile=" + linkMan.getLinkMobile() + "&";
		}
		if (linkMan.getCustomer() != null && linkMan.getCustomer().getCustName() != null && ! linkMan.getCustomer().getCustName().isEmpty()) {
			url += "linkMan.customer.custName=" + linkMan.getCustomer().getCustName() + "&";
		}
		return url;
	}

	private LinkMan encode(LinkMan linkMan) {
		try {
			if (linkMan.getLinkName() != null
					&& !linkMan.getLinkName().isEmpty()) {
				linkMan.setLinkName(new String(linkMan.getLinkName().getBytes(
						"iso-8859-1"), "utf-8"));
			}
			if (linkMan.getLinkGender() != null
					&& !linkMan.getLinkGender().isEmpty()) {
				linkMan.setLinkGender(new String(linkMan.getLinkGender()
						.getBytes("iso-8859-1"), "utf-8"));
			}
			if (linkMan.getCustomer() != null && linkMan.getCustomer().getCustName() != null && ! linkMan.getCustomer().getCustName().isEmpty()) {
				linkMan.getCustomer().setCustName(new String(linkMan.getCustomer().getCustName().getBytes("iso-8859-1"), "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return linkMan;
	}

	// 得到超链接后面的所有参数
	private String getUrl(HttpServletRequest request) {
		String url = "";
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		if (query == null || query.isEmpty()) {
			url = uri + "?";
		} else {
			int end = query.indexOf("pageBean.currentPage");
			if (end != -1) {
				query = query.substring(0, end);
			}
			url = uri + "?" + query;
		}
		return url;
	}

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	public LinkMan getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(LinkMan linkMan) {
		this.linkMan = linkMan;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public String getMsg() {
		return msg;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<LinkMan> getLinkManList() {
		return linkManList;
	}

	public PageBean<LinkMan> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<LinkMan> pageBean) {
		this.pageBean = pageBean;
	}
}
