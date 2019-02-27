package crm.domain;

import java.util.HashSet;
import java.util.Set;

public class Manager {
	private Integer id;
	private String username;
	private String password;
	private String telnumber;
	private String email;
	private boolean state = false;
	
	private Set<Visit> visitSet = new HashSet<Visit>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelnumber() {
		return telnumber;
	}
	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Set<Visit> getVisitSet() {
		return visitSet;
	}
	public void setVisitSet(Set<Visit> visitSet) {
		this.visitSet = visitSet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
