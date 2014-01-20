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
		<title>Admin Desktop</title>
		<link rel="stylesheet" href="/bg_admin/css/layout.css" type="text/css"
			media="screen" />
		<link rel="stylesheet" href="/bg_admin/css/stat.css" type="text/css"
			media="screen" />
		<!--[if lt IE 9]>
	<link rel="stylesheet" href="/bg_admin/css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"
			media="screen" />
		<script src="/js/jquery-1.9.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="/js/bootstrap.min.js"></script>
		<script src="/bg_admin/js/hideshow.js" type="text/javascript"></script>
		<script src="/bg_admin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="/bg_admin/js/jquery.equalHeight.js"></script>
		<script type="text/javascript" src="/bg_admin/js/desktop.js"></script>
	</head>
	<script type="text/javascript">
	$(document).ready(function() 
    	{ 
      	  $(".tablesorter").tablesorter(); 
   	 } 
	);
	$(document).ready(function() {

	//When page loads...
	$(".tab_content").hide(); //Hide all content
	$("ul.tabs li:first").addClass("active").show(); //Activate first tab
	$(".tab_content:first").show(); //Show first tab content

	//On Click Event
	$("ul.tabs li").click(function() {

		$("ul.tabs li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".tab_content").hide(); //Hide all tab content

		var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active ID content
		return false;
	});

});
    </script>

	<body>

		<%@include file="/bg_admin/common/common_header.html"%>
		<%@include file="/bg_admin/common/common_sidebar.html"%>

		<section id="main" class="column">

		<h4 class="alert_info" id="welcome">
			Welcome to Charles Tech Blog Background Management Platform
		</h4>

		<article class="module width_full">
		<header>
		<h3>
			Stats
		</h3>
		</header>
		<div class="module_content">
			<div class="stat_data_box">
				<div class="stat_data_visitor" title="总访问量1200">
					<div class="stat_data_text">
						Total Visitors
						<br />
						1200
					</div>
				</div>
				<div class="stat_data_message" title="博文总回复数1200">
					<div class="stat_data_text">
						Total Replies
						<br />
						1200
					</div>
				</div>
				<div class="stat_data_download" title="资源总下载量1200">
					<div class="stat_data_text">
						Total Download
						<br />
						1200
					</div>
				</div>
			</div>
			<article class="stats_graph">
			<%
				String pv = "10,25,20,33,21,41,90,81,76,85,56";
			%>
			<img
				src="http://chart.apis.google.com/chart?cht=lc&chxr=0,0,1000&chxt=y&chs=600x170&chco=76A4FB,80C65A&chd=t:<%=pv%>"
				width="600" height="170" alt="" />
			</article>

			<article class="stats_overview">
			<div class="overview_today">
				<p class="overview_day">
					Today
				</p>
				<p class="overview_count">
					1325
				</p>
				<p class="overview_type">
					VIEWS
				</p>
				<p class="overview_count">
					50
				</p>
				<p class="overview_type">
					Download
				</p>
			</div>
			<div class="overview_previous">
				<p class="overview_day">
					Yesterday
				</p>
				<p class="overview_count">
					1122
				</p>
				<p class="overview_type">
					VIEWS
				</p>
				<p class="overview_count">
					41
				</p>
				<p class="overview_type">
					DOWNLOAD
				</p>
			</div>
			</article>
			<div class="clear"></div>
		</div>
		</article>
		<!-- end of stats article -->

		<article class="module width_3_quarter">
		<header>
		<h3 class="tabs_involved">
			Content Manager
		</h3>
		<ul class="tabs">
			<li>
				<a href="#tab1">Post Record</a>
			</li>
			<li>
				<a href="#tab2">Recent Comments</a>
			</li>
		</ul>
		</header>

		<div class="tab_container">
			<div id="tab1" class="tab_content">
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
					<tbody>
						<%
							for (int i = 0; i < 10; i++) {
						%>
						<tr>
							<td>
								Java EE Study Notes
							</td>
							<td>
								Web开发
							</td>
							<td>
								2013-11-11
							</td>
							<td>
								Charles
							</td>
							<td>
								<a href="#" style="text-decoration: none" title="View article">
									<img src="/bg_admin/images/icn_search.png" width="11px" />&nbsp;View </a>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
			<!-- end of #tab1 -->

			<div id="tab2" class="tab_content">
				<table class="tablesorter" cellspacing="0">
					<thead>
						<tr>
							<th>
								Comment
							</th>
							<th>
								Posted by
							</th>
							<th>
								Website
							</th>
							<th>
								Email
							</th>
							<th>
								Posted On
							</th>
							<th>
								Actions
							</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < 10; i++) {
						%>
						<tr>
							<td>
								Thanks for sharing
							</td>
							<td>
								Echo
							</td>
							<td>
								null
							</td>
							<td>
								null
							</td>
							<td>
								5th April 2011
							</td>
							<td>
								<input type="image" src="/bg_admin/images/icn_jump_back.png"
									title="Quick Reply"
									onclick="$('#article_id').val(<%=i%>);$('#reply').modal()">
								<input type="image" src="/bg_admin/images/icn_trash.png" title="Delete">
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>

				<!-- Reply Modal -->
				<div id="reply" class="modal hide fade" tabindex="-1" role="dialog"
					aria-labelledby="addModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
						</button>
						<p style="font-size: 18px; line-height: 1.5em; font-weight: bold">
							Quick Reply
						</p>
					</div>
					<div class="modal-body">
						<input id="article_id" type="hidden" value="" />
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">
							Close
						</button>
						<button class="btn btn-primary"
							onclick="alert($('#article_id').val());">
							Submit
						</button>
					</div>
				</div>
				<!-- Reply Modal end -->
			</div>
			<!-- end of #tab2 -->

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
			<div class="module_content">
				<%
					for (int i = 0; i < 10; i++) {
				%>
				<div class="message">
					<p>
						Resource "MySQL驱动" has been downloaded at
						<strong>2013-11-11 18:00</strong>
					</p>
					<p>
						<a href="#"><strong>View Resource</strong> </a>
					</p>
				</div>
				<%
					}
				%>
			</div>
		</div>
		</article>
		<!-- end of messages article -->

		<div class="clear"></div>

		<div class="spacer"></div>
		</section>


	</body>

</html>