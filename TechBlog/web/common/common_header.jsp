<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!doctype html>
<html lang="en">
<head>
    <title>Common header and background</title>

    <meta http-equiv="keywords" content="header, background">
    <meta http-equiv="description"
          content="The website common header and background">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--[if lt IE 9]>
    <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/css/jkmegamenu.css"/>
    <script type="text/javascript" src="/js/jkmegamenu.js"></script>
    <script type="text/javascript">
        //jkmegamenu.definemenu("anchorid", "menuid", "mouseover|click")
        /* Bind menus*/
        jkmegamenu.definemenu("blog", "megamenu_blog", "mouseover");
        //jkmegamenu.definemenu("resource", "megamenu_resource", "mouseover");
    </script>
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/fg/common_header.js"></script>
</head>
<body>
<div class="common_header">
    <!-- The banner -->
    <div class="common_banner">
        <div class="banner">
            <!-- The charles logo -->
            <div class="logo">
                <img src="<%=basePath%>images/charles.png"
                     style="margin-top: 20px; width: 200px">
            </div>

            <!-- The title -->
            <div class="title">
                Tech Blog
            </div>

            <!-- The search input area -->
            <div class="search">
                <div class="search_input">
                    <input type="text" id="search_input"/>
                </div>
                <div class="search_button" id="search" title="Click to search"></div>
            </div>
        </div>
    </div>
    <!-- The banner -->

    <!-- The navigation bar -->
    <div class="common_navi">
        <div class="navi_items">
            <div class="navi_home" title="首页">
            </div>
            <div class="navi_arrow">
            </div>

            <div class="navi_item" id="blog"
                 onclick="location.href='/blog.shtml'">
                ARTICLE
            </div>
            <div class="navi_arrow">
            </div>

            <div class="navi_item" onclick="building();">
                ALBUM
            </div>
            <div class="navi_arrow">
            </div>

            <div class="navi_item" id="resource" onclick="building();">
                RESOURCE
            </div>
            <div class="navi_arrow">
            </div>

            <div class="navi_item" onclick="building();">
                APP
            </div>
            <div class="navi_arrow" onclick="building();">
            </div>

            <div class="navi_item" onclick="building();">
                ABOUT
            </div>
            <div class="navi_arrow">
            </div>
        </div>
    </div>
    <!-- The navigation bar -->
</div>


<!-- Drop dowm menu for blog-->
<div id="megamenu_blog" class="megamenu">
</div>

<!-- Drop dowm menu for resource-->
<div id="megamenu_resource" class="megamenu">

    <div class="column">
        <h3>
            资源分类
        </h3>
        <ul>
            <li>
                <a href="#"><span class="icon-th-list "></span>&nbsp;源代码</a>
            </li>
            <li>
                <a href="#"><span class="icon-th-list "></span>&nbsp;工具软件</a>
            </li>
            <li>
                <a href="#"><span class="icon-th-list "></span>&nbsp;设计素材</a>
            </li>
            <li>
                <a href="#"><span class="icon-th-list "></span>&nbsp;资料文档</a>
            </li>
            <li>
                <a href="#"><span class="icon-th-list "></span>&nbsp;其他</a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>