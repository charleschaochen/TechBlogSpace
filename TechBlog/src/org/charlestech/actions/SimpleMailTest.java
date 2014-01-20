package org.charlestech.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.charlestech.utils.MailUtils;

import com.opensymphony.xwork2.ActionSupport;

public class SimpleMailTest extends ActionSupport {
	private String mailto;
	private String subject;
	private String content;
	private MailUtils mailUtils;

	/**
	 * Simple interface for sending mail
	 */
	public String execute() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = out = response.getWriter();
		mailUtils.sendSimpleMail(mailto, null, null, subject, content, true);
		out.print("{retcode:0,mess:'Mail is sent out.'}");
		return null;
	}

	public String getMailto() {
		return mailto;
	}

	public void setMailto(String mailto) {
		this.mailto = mailto;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMailUtils(MailUtils mailUtils) {
		this.mailUtils = mailUtils;
	}

}
