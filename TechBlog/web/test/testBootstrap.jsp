<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>Bootstrap Test</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>css/bootstrap.min.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
	</head>

	<body>
		<p class="text-center">
			<button class="btn btn-primary" data-target="#getInfo"
				data-toggle="modal" id="buttonAdd">
				Click me!
			</button>
		</p>
		<!-- add Modal -->
		<div id="getInfo" class="modal hide fade" tabindex="-1" role="dialog"
			aria-labelledby="addModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3>
					Welcome
				</h3>
			</div>
			<div class="modal-body">
				<p class="text-center">
					Hello,
					<?php echo $name ?>
					. Welcome!
				</p>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
				<button class="btn btn-primary">
					Thanks!
				</button>
			</div>
		</div>
		<!-- add modal end -->
	</body>
</html>
