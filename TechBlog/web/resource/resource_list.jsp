<%@ page language="java" import="java.util.*,org.charlestech.po.*"
         pageEncoding="UTF-8" %>
<%
    //    // Check invalid entry
//    Object method = request.getAttribute("method");
//    if (method == null || !"resource_list".equals(method.toString())) {
//        response.sendRedirect("resource.shtml");
//        return;
//    }
//    // Get data
//    List<Article> articles = (ArrayList<Article>) request
//            .getAttribute("articles");
//    int pageNo = Integer.parseInt(request.getAttribute("pageNo")
//            .toString());
//    int pageNum = Integer.parseInt(request.getAttribute("pageNum")
//            .toString());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Charles's Tech Blog - Resources</title>
    <meta http-equiv="keywords" content="resources">
    <meta http-equiv="description" content="Resource list displaying">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <%@include file="/common/common_include.jsp" %>
    <link rel="stylesheet" type="text/css" href="/css/fg/resource_list.css"/>
    <link rel="stylesheet" type="text/css"
          href="/css/auto-pagination.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="/css/tag_cloud/jqcloud.css"/>
    <script type="text/javascript" src="/js/backTop.js"></script>
</head>

<body>
<jsp:include page="/common/common_header.jsp" flush="true"/>
<div class="main_box">
    <div class="main_content">
        <!-- resource list container -->
        <div class="resources_container">
            <!-- One resource displaying -->
            <div class="resource_box">
                <!-- resource title information -->
                <div class="resource_title">
                    <div class="resource_post_date">
								<span class="date_1">2014-06
								</span>
                        <br/>
								<span class="date_2"> 12
								</span>
                    </div>
                    <div class="resource_author_pic"
                         style="background: url(/images/visitor_pic.png); background-size:55px 55px">
                        <div class="shine"></div>
                    </div>
                    <div class="resource_title_info">
								<span class="resource_title_text"><a
                                        href="#"
                                        title="">Ruby中文教程
                                </a>
								</span>
                        <br/>
								<span class="resource_post_info"> By <a>charles
                                </a>&nbsp;&nbsp;|&nbsp;&nbsp;
									Category: <a
                                            href="#">技术文档
                                    </a>&nbsp;&nbsp;|&nbsp;&nbsp;
									Views(<span class="text_bold_green">15</span>)
								</span>
                    </div>
                    <a class="resource_download_num" title="15次下载，点击直接下载"
                       href="#">
                        15
                    </a>
                </div>
                <!-- resource title information ends -->

                <!-- resource description -->
                <div class="resource_description">
                    Ruby，一种为简单快捷的面向对象编程（面向对象程序设计）而创的脚本语言，在20世纪90年代由日本人松本行弘（まつもとゆきひろ/Yukihiro Matsumoto）开发，遵守GPL协议和Ruby
                    License...
                </div>
                <div class="resource_view_all">
                    <a href="#" data-toggle="modal" data-target="#resource_detail" onclick="initDetail()">查看资源详情 >> </a>
                </div>
                <!-- resource description ends -->

                <!-- Separator -->
                <div class="resource_separator"></div>
            </div>
            <!-- One resource displaying ends -->

            <input type="hidden" id="pageNo" value="1"/>
            <input type="hidden" id="pageNum" value="15"/>

            <div class="resource_pagination" style="height: 60px">
            </div>
        </div>
        <!-- resource list container ends -->

        <!-- Right side tools container -->
        <div class="tool_container">
            <!-- Tool box displaying -->
            <div class="tool_box">
                <div class="tool_title">
                    <span style="color: #3399CC">RESOURCE</span>
                    <span style="color: #666">CATEGORIES</span>
                </div>
                <div class="tool_content">
                    <ul id="article_categories">
                        <li><span class='glyphicon glyphicon-list-alt'></span>&nbsp;&nbsp;<a
                                href='#'>代码资源</a>&nbsp;(<span class='text_bold_green'>5</span>)
                        </li>
                    </ul>
                </div>
            </div>
            <!-- Tool box displaying ends-->

            <!-- Tool box displaying -->
            <div class="tool_box">
                <div class="tool_title">
                    <span style="color: #3399CC">RECENT</span>
                    <span style="color: #666">DOWNLOAD</span>
                </div>
                <div class="tool_content">
                    <ul id="resource_category">
                        <li>
                            <span class='glyphicon glyphicon-cloud-download'></span>&nbsp;<a href='#'>Ruby中文教程</a>
                            <br/>

                            <div style="text-align: right"><a>2014-06-12 18:00</a></div>
                        </li>
                        <li>
                            <span class='glyphicon glyphicon-cloud-download'></span>&nbsp;<a href='#'>Ruby中文教程</a>
                            <br/>

                            <div style="text-align: right"><a>2014-06-12 18:00</a></div>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- Tool box displaying ends-->
        </div>
        <!-- Right side tools container ends -->
        <div class="clear"></div>
    </div>
    <jsp:include page="/common/common_footer.html" flush="true"/>
</div>


<!-- Resource details modal dialog -->
<div class="modal fade" id="resource_detail" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="resource_title">Ruby中文教程</h4>
            </div>
            <div class="modal-body">
                <h4>资源大小：<span id="resource_size">614KB</span></h4>
                <h4>上传时间：<span id="upload_date">2014-01-24</span></h4>
                <h4>下载次数：<span id="download_times">15</span></h4>
                <h4>上传作者：<span id="upload_admin">charles</span></h4>
                <br/>
                <ul class="nav nav-tabs">
                    <li class="active"><a>资源简介</a></li>
                </ul>
                <h5 id="resource_description">
                    Ruby，一种为简单快捷的面向对象编程（面向对象程序设计）而创的脚本语言，在20世纪90年代由日本人松本行弘（まつもとゆきひろ/Yukihiro Matsumoto）开发，遵守GPL协议和Ruby
                    License。它的灵感与特性来自于 Perl、Smalltalk、Eiffel、Ada 以及 Lisp 语言。由 Ruby 语言本身还发展出了JRuby（Java 平台）、IronRuby（.NET
                    平台）等其他平台的 Ruby
                    语言替代品。Ruby的作者于1993年2月24日开始编写Ruby，直至1995年12月才正式公开发布于fj（新闻组）。因为Perl发音与6月诞生石pearl（珍珠）相同，因此Ruby以7月诞生石ruby（红宝石）命名。
                </h5>

                <!-- Baidu Share -->
                <div class="resource_share">
                    <div class="bdsharebuttonbox">
                        <A class=bds_more href="#" data-cmd="more"></A><A
                            class=bds_tsina title=分享到新浪微博 href="#" data-cmd="tsina"></A><A
                            class=bds_weixin title=分享到微信 href="#" data-cmd="weixin"></A><A
                            class=bds_sqq title=分享到QQ好友 href="#" data-cmd="sqq"></A><A
                            class=bds_qzone title=分享到QQ空间 href="#" data-cmd="qzone"></A><A
                            class=bds_tqq title=分享到腾讯微博 href="#" data-cmd="tqq"></A><A
                            class=bds_tqf title=分享到腾讯朋友 href="#" data-cmd="tqf"></A>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="download">下载资源</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- Resource details modal dialog ends -->

<!-- Include javascript file -->
<script type="text/javascript" src="/js/holder.js"></script>
<script type="text/javascript" src="/js/arg-utils.js"></script>
<script type="text/javascript" src="/js/auto-pagination.min.js"></script>
<script src="/js/ARTICLE.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript" src="/js/fg/resource_list.js"></script>

<!-- Baidu Share script -->
<script>window._bd_share_config = {"common": {"bdSnsKey": {}, "bdText": "我在Charles Tech Blog上下载了一个不错的资源，详情猛戳>>", "bdMini": "2", "bdMiniList": false, "bdPic": "", "bdStyle": "0", "bdSize": "32"}, "share": {}, "image": {"viewList": ["tsina", "weixin", "sqq", "qzone", "tqq", "tqf"], "viewText": "分享到：", "viewSize": "16"}, "selectShare": {"bdContainerClass": null, "bdSelectMiniList": ["tsina", "weixin", "sqq", "qzone", "tqq", "tqf"]}};
with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=86835285.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
<!-- Baidu Share script ends -->
</body>
</html>