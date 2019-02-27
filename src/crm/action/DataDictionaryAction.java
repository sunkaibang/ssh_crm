package crm.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import crm.domain.DataDictionary;
import crm.service.DataDictionaryService;
import crm.tools.EacheDomain;

public class DataDictionaryAction extends ActionSupport {
	
	private static final long serialVersionUID = -6152922246835140285L;
	private DataDictionaryService dataDictionaryService;
	private List<DataDictionary> dataDictionaryList;
	private DataDictionary dataDictionary;
	private String msg;
	
	public String toAddCustomer() {
		dataDictionaryList = dataDictionaryService.findAllDataDictionary();
		msg = EacheDomain.msg;
		EacheDomain.msg = "";
		return "add_customer_page";
	}
	
	public String tofindByCondtionPage() {
		dataDictionaryList = dataDictionaryService.findAllDataDictionary();
		return "customer_condition_page";
	}
	
	public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
		this.dataDictionaryService = dataDictionaryService;
	}
	public List<DataDictionary> getDataDictionaryList() {
		return dataDictionaryList;
	}
	public DataDictionary getDataDictionary() {
		return dataDictionary;
	}
	public void setDataDictionary(DataDictionary dataDictionary) {
		this.dataDictionary = dataDictionary;
	}
	public String getMsg() {
		return msg;
	}
	
}
