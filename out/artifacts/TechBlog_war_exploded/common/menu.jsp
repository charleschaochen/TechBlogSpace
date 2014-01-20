<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>导航栏菜单</title>
		<script type="text/javascript"
			src="<%=basePath %>js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jkmegamenu.css" />
		<script type="text/javascript" src="<%=basePath %>js/jkmegamenu.js"></script>
		<script type="text/javascript">
$(window).ready(function(){
	jkmegamenu.definemenu("blog", "megamenu_blog", "mouseover")
});
//jkmegamenu.definemenu("anchorid", "menuid", "mouseover|click")

</script>
	</head>

	<body>
	<a href="http://www.javascriptkit.com" id="blog">Tech Links</a>
		<!--Mega Drop Down Menu HTML. Retain given CSS classes-->
		<div id="megamenu_blog" class="megamenu">

			<div class="column">
				<h3>
					编程语言
				</h3>
				<ul>
					<li>
						<a href="#">Java/Java EE</a>
					</li>
					<li>
						<a href="#">Javascript</a>
					</li>
					<li>
						<a href="#">C#</a>
					</li>
					<li>
						<a href="#">PHP</a>
					</li>
					<li>
						<a href="#">C/C++/Object C</a>
					</li>
					<li>
						<a href="#">Python/Shell/Ruby</a>
					</li>
					<li>
						<a href="#">PL/SQL</a>
					</li>
				</ul>
			</div>

			<div class="column">
				<h3>
					技术领域
				</h3>
				<ul>
					<li>
						<a href="#">Web开发</a>
					</li>
					<li>
						<a href="#">移动终端开发</a>
					</li>
					<li>
						<a href="#">数据库</a>
					</li>
					<li>
						<a href="#">搜索技术</a>
					</li>
					<li>
						<a href="#">算法/数据结构</a>
					</li>
					<li>
						<a href="#">软件测试</a>
					</li>
					<li>
						<a href="#">设计/交互</a>
					</li>
				</ul>
			</div>

			<br style="clear: left" />

			<div class="column">
				<h3>
					个人分享
				</h3>
				<ul>
					<li>
						<a href="#">工作分享</a>
					</li>
					<li>
						<a href="#">业界动态分享</a>
					</li>
					<li>
						<a href="#">讲座分享</a>
					</li>
					<li>
						<a href="#">个人分享</a>
					</li>
				</ul>
			</div>

		</div>

	</body>
</html>