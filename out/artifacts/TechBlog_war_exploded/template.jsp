<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
	<head>
		<title>Template Page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="description" content="Foreground template page">
		<%@include file="common/common_include.jsp"%>
		<style type="text/css">
.main_box {
	position: absolute;
	width: 100%;
	top: 140px;
	left: 0;
	text-align: center;
}

.main_box .main_content {
	width: 1000px;
	height: 800px;
	border: 1px solid #ccc;
	background-color: #FFF;
	margin-left: auto;
	margin-right: auto
}
</style>
	</head>

	<body>
		<jsp:include page="/common/common_header.jsp" flush="true" />
		<div class="main_box">
			<div class="main_content">
			</div>
			<jsp:include page="/common/common_footer.html" flush="true" />
		</div>

	</body>
</html>
