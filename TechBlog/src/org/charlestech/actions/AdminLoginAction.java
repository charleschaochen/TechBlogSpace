package org.charlestech.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.charlestech.beans.SendMailBean;
import org.charlestech.dao.AdminDao;
import org.charlestech.po.Admin;
import org.charlestech.po.SecureAdmin;
import org.charlestech.utils.DateUtils;
import org.charlestech.utils.MailUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminLoginAction extends ActionSupport {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -7370768067949675428L;
	private AdminDao ad;
	private SendMailBean sendMail;
	private String username;
	private String passwd;

	/**
	 * Validate login
	 */
	public String execute() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();

		Admin admin = ad.findByNameAndPwd(username, passwd);
		if (admin == null) {
			out.print("{retcode:-1,mess:'Invalid Account'}");
			return null;
		}

		Map session = ActionContext.getContext().getSession();
		session.put(ServletActionContext.getServletContext().getInitParameter(
				"SESSION_NAME"), admin.getName());
		out.print("{retcode:0,mess:'Login Successfully'}");

		// Send login reminder email
		sendMail.sendLoginReminder(admin.getEmail(), username, DateUtils
				.now_yyyy_MM_dd_HH_mm_ss());
		return null;
	}

	/**
	 * Check login state
	 * 
	 * @return
	 * @throws IOException
	 */
	public String checkLogin() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();
		Map session = ActionContext.getContext().getSession();
		// Check login state in session
		String sess_name = ServletActionContext.getServletContext()
				.getInitParameter("SESSION_NAME");
		if (session.get(sess_name) == null) {
			out.print("{retcode:-1,mess:'Login state not found'}");
			return null;
		}
		out.print("{retcode:0,mess:'Login state found: "
				+ session.get(sess_name).toString() + "'}");
		return null;
	}

	/**
	 * Logout the system
	 * 
	 * @return
	 * @throws IOException
	 */
	public String signOff() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();

		Map session = ActionContext.getContext().getSession();
		String sess_name = ServletActionContext.getServletContext()
				.getInitParameter("SESSION_NAME");
		if (session.get(sess_name) != null) {
			// Remove login session
			session.remove(sess_name);
		}
		return LOGIN;
	}

	/**
	 * Mail password to legal administrator
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findPasswd() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();
		// Get administrator account by username
		SecureAdmin admin = ad.findSecureInfo(username);
		if (admin == null) {
			out.print("{retcode:-1,mess:'Login name not found'}");
			return null;
		}
		// Send account information email
		sendMail.sendAcctInfo(admin.getEmail(), username, admin.getPasswd());
		out.print("{retcode:0,mess:'Account information mail sent'}");
		return null;
	}

	public void setAd(AdminDao ad) {
		this.ad = ad;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setSendMail(SendMailBean sendMail) {
		this.sendMail = sendMail;
	}

}
