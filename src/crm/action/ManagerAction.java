package crm.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.ws.resources.SenderMessages;

import crm.domain.Manager;
import crm.service.ManagerService;
import crm.tools.EmailUtils;

public class ManagerAction extends ActionSupport{
	
	private static final long serialVersionUID = 8355534165543535668L;
	private ManagerService managerService;
	private Manager manager;
	private String msg;

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	// ��¼
	public String login() {
		Manager newManager = managerService.login(manager);
		if (newManager == null) {
			msg = "�˺Ų�����";
			return "login_fail";
		} else {
			if (newManager.isState()) {
				ServletActionContext.getRequest().getSession().setAttribute("session_manager", newManager);
				return "login_success";
			} else {
				msg = "�˺�û����";
				return "login_fail";
			}
			
		}
	}
	
	// ע��
	public String register() {
		Manager oldManager = managerService.findManagerByUsername(manager.getUsername());
		if (oldManager == null) {
			managerService.register(manager);
			msg = "ע��ɹ������ȵ�¼���伤���˺�";
			return "login_page";
		} else {
			msg = "�û����Ѿ�����";
			return "register_page";
		}
	}
	
	// ����
	public String active() throws IOException {
		managerService.active(manager.getId());
		msg = "��ϲ������ɹ�!";
		return "register_result";
	}
	
	// �˳�
	public String exit() {
		ServletActionContext.getRequest().getSession().setAttribute("session_manager", null);
		return "login_page";
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getMsg() {
		return msg;
	}

}
