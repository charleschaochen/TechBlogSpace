<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="/bg_admin/common/check_login.jsp"%>
<!doctype html>
<html lang="en">

	<head>
		<meta charset="utf-8" />
		<title>Tech Blog Background - Article Form</title>

		<link rel="stylesheet" href="/bg_admin/css/layout.css" type="text/css"
			media="screen" />
		<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
		<script src="/js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="/bg_admin/js/hideshow.js" type="text/javascript"></script>
		<script src="/bg_admin/js/jquery.tablesorter.min.js"
			type="text/javascript"></script>
		<script type="text/javascript"
			src="/bg_admin/js/jquery.equalHeight.js"></script>
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
		<script type="text/javascript">
    $(function(){
        $('.column').equalHeight();
    });
    
    function logout(){
    	if(confirm('Are you sure to logout?')){
    		location.href('');
    	}
    }
</script>
	</head>


	<body>

		<%@include file="/bg_admin/common/common_header.html"%>

		<%@include file="/bg_admin/common/common_sidebar.html"%>

		<section id="main" class="column">

		<h4 class="alert_error" id="error_mess" style="display: none">
		</h4>


		<div class="clear"></div>

		<article class="module width_full">
		<header>
		<h3 id="form_title">
			Post New Article
		</h3>
		</header>
		<div class="module_content">
			<fieldset>
				<label>
					Article Title
				</label>
				<input type="text" id="title">
			</fieldset>
			<fieldset>
				<label>
					Content
				</label>
				<textarea id="content" name="content"
					style="width: 100%; height: 500px"></textarea>
			</fieldset>
			<fieldset style="width: 48%; float: left; margin-right: 3%;">
				<!-- to make two field float next to one another, adjust values accordingly -->
				<label>
					Category
				</label>
				<select id="category" style="width: 92%;">
				</select>
			</fieldset>
			<fieldset style="width: 48%; float: left;">
				<!-- to make two field float next to one another, adjust values accordingly -->
				<label>
					Tags
				</label>
				<input type="text" id="tags" style="width: 92%;"
					title="Split with ';', 3 is upper limit" />
			</fieldset>
			<div class="clear"></div>
		</div>
		<footer>

		<div
			style="float: left; font-size: 16px; line-height: 2em; margin-left: 10px; color: #333; cursor: pointer"
			title="置顶博文">
			<input type="checkbox" id="top_allocated" />
			&nbsp;Top-allocated
		</div>

		<div class="submit_link">
			<select id="state">
				<option value="1">
					Published
				</option>
				<option value="0">
					Draft
				</option>
			</select>
			<button id="sbt_button" class="alt_btn">
				Publish
			</button>
		</div>
		</footer>
		</article>
		<!-- end of post new article -->
		</section>

		<!-- Xheditor include files -->
		<link rel="stylesheet" type="text/css" href="/css/xheditor_plugin.css" />
		<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src="/js/xheditor/xheditor-1.1.14-zh-cn.min.js"></script>
		<script type="text/javascript" src="/js/xheditor/init-xheditor.js"></script>
		<script type="text/javascript" src="/js/arg-utils.js"></script>
		<script type="text/javascript" src="/bg_admin/js/article_form.js"></script>
		<!-- Xheditor include files -->

	</body>

</html>