package crm.domain;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	private Integer cid;
	private String custName;
	private String custLevel;
	private String custSource;
	private String custPhone;
	private String custMoble;
	
	private Set<LinkMan> linkManSet = new HashSet<LinkMan>();
	private Set<Visit> visitSet = new HashSet<Visit>();
	private DataDictionary dataDictionary;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMoble() {
		return custMoble;
	}
	public void setCustMoble(String custMoble) {
		this.custMoble = custMoble;
	}
	public Set<LinkMan> getLinkManSet() {
		return linkManSet;
	}
	public void setLinkManSet(Set<LinkMan> linkManSet) {
		this.linkManSet = linkManSet;
	}
	public Set<Visit> getVisitSet() {
		return visitSet;
	}
	public void setVisitSet(Set<Visit> visitSet) {
		this.visitSet = visitSet;
	}
	public DataDictionary getDataDictionary() {
		return dataDictionary;
	}
	public void setDataDictionary(DataDictionary dataDictionary) {
		this.dataDictionary = dataDictionary;
	}
}
