package org.charlestech.beans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.struts2.ServletActionContext;
import org.charlestech.utils.MailUtils;

public class SendMailBean {
	private MailUtils mailUtils;
	private String loginRmdPath; // Login reminder content file path
	private String replyRmdAdminPath; // Reply reminder content for
	// administrator file path
	private String replyRmdUserPath; // Reply reminder content for user file
	private String findPwdPath; // Find password content file path

	// path

	/**
	 * Send login reminder email to administrator
	 * 
	 * @throws IOException
	 */
	public void sendLoginReminder(String mailto, String name, String datetime)
			throws IOException {
		String filePath = ServletActionContext.getServletContext().getRealPath(
				loginRmdPath);
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringBuilder fileContent = new StringBuilder();
		String subject = br.readLine(); // Read first line as subject
		String line;
		while ((line = br.readLine()) != null) {
			fileContent.append(line);
		}
		StringTemplate strTemplate = new StringTemplate(fileContent.toString());
		strTemplate.setAttribute("login_name", name); // Set name attribute
		strTemplate.setAttribute("datetime", datetime); // Set date time
		// attribute
		mailUtils.sendSimpleMail(mailto, null, null, subject, strTemplate
				.toString(), true);
	}

	/**
	 * Send administrator account information
	 * 
	 * @throws IOException
	 */
	public void sendAcctInfo(String mailto, String name, String passwd)
			throws IOException {
		String filePath = ServletActionContext.getServletContext().getRealPath(
				findPwdPath);
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringBuilder fileContent = new StringBuilder();
		String subject = br.readLine(); // Read first line as subject
		String line;
		while ((line = br.readLine()) != null) {
			fileContent.append(line);
		}
		StringTemplate strTemplate = new StringTemplate(fileContent.toString());
		strTemplate.setAttribute("login_name", name); // Set name attribute
		strTemplate.setAttribute("passwd", passwd); // Set password attribute
		mailUtils.sendSimpleMail(mailto, null, null, subject, strTemplate
				.toString(), true);
	}

	/**
	 * Send reply reminder for administrator
	 */
	public void sendReplyReminderAdmin() {

	}

	/**
	 * Send reply reminder for user
	 */
	public void sendReplyReminderUser() {

	}

	public void setMailUtils(MailUtils mailUtils) {
		this.mailUtils = mailUtils;
	}

	public String getLoginRmdPath() {
		return loginRmdPath;
	}

	public void setLoginRmdPath(String loginRmdPath) {
		this.loginRmdPath = loginRmdPath;
	}

	public String getReplyRmdAdminPath() {
		return replyRmdAdminPath;
	}

	public void setReplyRmdAdminPath(String replyRmdAdminPath) {
		this.replyRmdAdminPath = replyRmdAdminPath;
	}

	public String getFindPwdPath() {
		return findPwdPath;
	}

	public void setFindPwdPath(String findPwdPath) {
		this.findPwdPath = findPwdPath;
	}

	public String getReplyRmdUserPath() {
		return replyRmdUserPath;
	}

	public void setReplyRmdUserPath(String replyRmdUserPath) {
		this.replyRmdUserPath = replyRmdUserPath;
	}

}
