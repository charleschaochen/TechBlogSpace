<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="/bg_admin/common/check_login.jsp" %>
<!doctype html>
<html lang="en">

	<head>
		<meta charset="utf-8" />
		<title>Tech Blog Background - Manage Article</title>
		<link rel="stylesheet" href="/bg_admin/css/layout.css" type="text/css"
			media="screen" />
		<link rel="stylesheet" href="/bg_admin/css/stat.css" type="text/css"
			media="screen" />
		<!--[if lt IE 9]>
	<link rel="stylesheet" href="/bg_admin/css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

		<link rel="stylesheet" href="/bg_admin/css/bootstrap.min.css"
			type="text/css" media="screen" />
	</head>
	<style type="text/css">
.category {
	width: 100px;
	height: 30px;
	font-size: 12px;
	float: right;
	font-family: "微软雅黑"
}

.article_list {
	font-family: "微软雅黑";
	color: #666;
	font-size: 14px
}

.article_list a:hover {
	text-decoration: none
}

.message p a {
	font-weight: bold;
	text-decoration: none;
	margin-right: 5px
}

.message .reply_content {
	font-family: "微软雅黑";
	font-size: 14px;
	margin-left: 5px
}
</style>
	<body>

		<%@include file="/bg_admin/common/common_header.html"%>
		<%@include file="/bg_admin/common/common_sidebar.html"%>

		<section id="main" class="column">

		<h4 class="alert_info" id="welcome">
			You can manage all articles in this page.
		</h4>

		<article class="module width_3_quarter">
		<header>
		<h3 class="tabs_involved">
			Articles
		</h3>
		<select id="category" class="category">
		</select>
		</header>

		<div class="tab_container">
			<div 层 lass="tab_content">
				<table class="tablesorter" cellspacing="0">
					<thead>
						<tr>
							<th>
								Article Title
							</th>
							<th>
								Category
							</th>
							<th>
								Created On
							</th>
							<th>
								Author
							</th>
							<th>
								Actions
							</th>
						</tr>
					</thead>
					<tbody id="article_list" class="article_list">
					</tbody>
					<!-- Pagination -->
					<input type="hidden" id="pageNum" value="" />
					<input type="hidden" id="pageNo" value="" />
					<tbody id="pagination">
						<tr>
							<td colspan="5" style="text-align: right">
								<div class="blog_pagination">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- end of #tab1 -->
		</div>
		<!-- end of .tab_container -->

		</article>
		<!-- end of content manager article -->

		<article class="module width_quarter">
		<header>
		<h3>
			Resource Download Record
		</h3>
		</header>
		<div class="message_list" style="height: 490px">
			<div class="module_content" id="article_reply">
			</div>
		</div>
		</article>

		<!-- Admin Reply Modal -->
		<div id="admin_reply" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
				</button>
				<p style="font-size: 18px; line-height: 1.5em; font-weight: bold">
					Administrator Replies
				</p>
			</div>
			<div class="modal-body" id="admin_replies">
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					OK
				</button>
			</div>
		</div>
		<!-- Admin Reply Modal end -->

		<!-- end of messages article -->

		<div class="clear"></div>

		<div class="spacer"></div>
		</section>


		<script src="/js/jquery-1.9.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="/js/bootstrap.min.js"></script>
		<script src="/bg_admin/js/hideshow.js" type="text/javascript"></script>
		<script src="/bg_admin/js/jquery.tablesorter.min.js"
			type="text/javascript"></script>
		<script type="text/javascript"
			src="/bg_admin/js/jquery.equalHeight.js"></script>
		<script type="text/javascript" src="/js/arg-utils.js"></script>
		<script type="text/javascript" src="/js/json.js"></script>
		<script type="text/javascript" src="/bg_admin/js/manage_article.js"></script>

	</body>

</html>