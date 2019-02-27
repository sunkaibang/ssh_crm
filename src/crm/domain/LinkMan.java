package crm.domain;

public class LinkMan {
	
	private Integer linkid;
	private String linkName;
	private String linkGender;
	private String linkPhone;
	private String linkMobile;
	
	private Customer customer;
	
	
	
	public LinkMan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getLinkid() {
		return linkid;
	}
	public void setLinkid(Integer linkid) {
		this.linkid = linkid;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkGender() {
		return linkGender;
	}
	public void setLinkGender(String linkGender) {
		this.linkGender = linkGender;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getLinkMobile() {
		return linkMobile;
	}
	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
