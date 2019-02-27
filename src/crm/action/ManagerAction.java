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
	
	// 登录
	public String login() {
		Manager newManager = managerService.login(manager);
		if (newManager == null) {
			msg = "账号不存在";
			return "login_fail";
		} else {
			if (newManager.isState()) {
				ServletActionContext.getRequest().getSession().setAttribute("session_manager", newManager);
				return "login_success";
			} else {
				msg = "账号没激活";
				return "login_fail";
			}
			
		}
	}
	
	// 注册
	public String register() {
		Manager oldManager = managerService.findManagerByUsername(manager.getUsername());
		if (oldManager == null) {
			managerService.register(manager);
			msg = "注册成功，请先登录邮箱激活账号";
			return "login_page";
		} else {
			msg = "用户名已经存在";
			return "register_page";
		}
	}
	
	// 激活
	public String active() throws IOException {
		managerService.active(manager.getId());
		msg = "恭喜，激活成功!";
		return "register_result";
	}
	
	// 退出
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
