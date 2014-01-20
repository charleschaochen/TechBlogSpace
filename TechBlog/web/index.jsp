<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
response.sendRedirect("/blog.shtml");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Charles Tech Blog - Index Page</title>
    <!-- Baidu Verification -->
    <meta name="baidu-site-verification" content="s6M4VgE2GY" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  </body>
</html>
