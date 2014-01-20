<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String sess_name = application.getInitParameter("SESSION_NAME");
	if (session.getAttribute(sess_name) == null) {
		response.sendRedirect("/bg_admin/signon.html");
		return;
	}
%>
