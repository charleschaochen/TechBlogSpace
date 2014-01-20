package org.charlestech.actions;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.*;

import org.charlestech.beans.AdminBean;
import org.charlestech.po.*;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6213924313278298174L;

	private AdminBean ab;
	private String name;
	private String passwd;

	public String execute() {
		Map request = (Map) ActionContext.getContext().get("request");
		Admin admin = ab.getAdmin(name, passwd);
		request.put("name", admin == null ? "Invalid" : admin.getName());
		return SUCCESS;
	}

	public void setAb(AdminBean ab) {
		this.ab = ab;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
