
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="shortcut icon" href="/images/logo.png" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/fg/common.css" />
<link rel="stylesheet" type="text/css" href="/css/fg/text_style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/bootstrap-v3/bootstrap.min.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap-v3/bootstrap.min.js"></script>