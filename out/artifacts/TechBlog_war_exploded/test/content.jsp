<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>显示内容</title>
		<script type="text/javascript" src="../js/fg/shCore.js"></script>
		<script type="text/javascript" src="../js/fg/shBrushJScript.js"></script>
		<link type="text/css" rel="stylesheet"
			href="../css/fg/shCoreDefault.css" />
		<script type="text/javascript">SyntaxHighlighter.all();</script>
		<style type="text/css">
body {
	margin: 5px;
	border: 2px solid #ccc;
	padding: 5px;
	font: 12px tahoma, arial, sans-serif;
	line-height: 1.2
}
</style>
	</head>
	<body>
		<%=request.getParameter("content")%>
	</body>
</html>