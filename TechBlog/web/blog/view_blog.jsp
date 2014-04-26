<%@ page language="java" import="java.util.*,org.charlestech.po.*"
         pageEncoding="UTF-8" %>
<%
    Object method = request.getAttribute("method");
    if (method == null || !"view_blog".equals(method.toString())) {
        response.sendRedirect("/blog.shtml");
        return;
    }
    Article article = (Article) request.getAttribute("article");
    List<ArticleReply> replies = (List<ArticleReply>) request
            .getAttribute("replies");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Charles's Tech Blog - <%=article.getArticleTitle()%>
    </title>

    <meta http-equiv="keywords" content="blogs">
    <meta http-equiv="description" content="Blog list displaying">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <%@include file="/common/common_include.jsp" %>
    <link rel="stylesheet" type="text/css"
          href="/css/tag_cloud/jqcloud.css"/>
    <link type="text/css" rel="stylesheet"
          href="/css/fg/shCoreDefault.css"/>
    <link rel="stylesheet" type="text/css" href="/css/fg/view_blog.css"/>
</head>

<body>
<jsp:include page="/common/common_header.jsp" flush="true"/>
<div class="main_box">
<div class="main_content">
<!-- Blog information container -->
<div class="blogs_container">

    <!-- Blog content -->
    <div class="blog_box">
        <!-- Blog title information -->
        <div class="blog_title">
            <div class="blog_post_date">
								<span class="date_1"> <%=article.getPostTime().substring(0, 7)%>
								</span>
                <br/>
                <span class="date_2"> <%=article.getPostTime().substring(8, 10)%></span>
            </div>
            <div class="blog_author_pic" style="background: url(<%=article.getAuthor().getImage()%>); background-size:55px 55px">
                <div class="shine"></div>
            </div>
            <div class="blog_title_info">
								<span class="blog_title_text"><a
                                        href="<%=basePath%>view_article.shtml?uid=<%=article.getArticleUid()%>"
                                        title="<%=article.getArticleTitle()%>"><%=article.getArticleTitle().length() > 22 ? article.getArticleTitle().substring(0, 22) + "..." : article.getArticleTitle()%>
                                </a>
								</span>
                <br/>
								<span class="blog_post_info"> By <a><%=article.getAuthor().getName()%>
                                </a>&nbsp;&nbsp;|&nbsp;&nbsp;
									Post in <a
                                            href="/blog.shtml?category=<%=article.getCategory().getCategoryId()%>"><%=article.getCategory().getCategoryName()%>
                                    </a>&nbsp;&nbsp;|&nbsp;&nbsp;
									Views(<span class="text_bold_green"><%=article.getViewTimes()%></span>)
								</span>
            </div>
            <a class="blog_reply_num" title="<%=article.getReplyTimes()%>条评论"
               href="#comments"><%=article.getReplyTimes()%>
            </a>
        </div>
        <!-- Blog title information ends -->

        <!-- Blog tags -->
        <p class="blog_tags">
            标签:&nbsp;&nbsp;
            <%
                for (ArticleTag tag : article.getArticleTags()) {
            %>
            <a href="/blog.shtml?tag=<%=tag.getTagName()%>"><%=tag.getTagName()%>
            </a>
            <%
                }
            %>
        </p>

        <div class="blog_content">
            <%=article.getArticleContent()%>
        </div>
        <!-- Baidu Share -->
        <div class="blog_share">
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
        <!-- Blog description ends -->

        <%
            if (replies.size() > 0) {
        %>
        <!-- Separator -->
        <div class="blog_separator"></div>
        <%
            }
        %>
    </div>
    <!-- Blog content ends -->

    <!-- Blog comments -->
    <div class="blog_comment_list" id="comments">
        <%
            for (ArticleReply reply : replies) {
        %>
        <div class="blog_comment">
            <div class="blog_author_pic">
                <img src="<%=reply.getVisitorImg()%>" width="90" height="90"/>
            </div>
            <div class="comment_content">
                <div class="comment_title">
                    <div class="comment_visitor">
                        <a
                                href="<%=reply.getVisitorWebsite().equals("") ? "#"
						: reply.getVisitorWebsite()%>"><%=reply.getVisitorName()%>
                        </a>
                    </div>
                    <div class="comment_time">
                        <%=reply.getReplyTime().substring(0, 19)%>
                    </div>
                </div>
                <div class="comment_text">
                    <%=reply.getReplyContent()%>
                </div>
            </div>
        </div>
        <%
            List<AdminArticleReply> adminReplies = reply.getReplies();
            for (AdminArticleReply adminReply : adminReplies) {
        %>
        <div class="blog_comment_reply">
            <div class="blog_author_pic">
                <img src="<%=adminReply.getAdmin().getImage()%>" width="90"
                     height="90"/>
            </div>
            <div class="comment_content">
                <div class="comment_title">
                    <div class="comment_visitor">
                        <%=adminReply.getAdmin().getName()%>
                    </div>
                    <div class="comment_time">
                        <%=adminReply.getReplyTime().substring(0, 19)%>
                    </div>
                </div>
                <div class="comment_text">
                    <%=adminReply.getReplyContent()%>
                </div>
            </div>
        </div>
        <%
                }
            }
        %>
        <!-- Separator -->
        <div class="blog_separator"></div>

        <!-- Reply input area -->
        <ul class="blog_leave_reply">
            <li style="font-weight: bold; font-size: 14px">
                Leave a Reply
            </li>
            <li>
                <input type="text" id="visitor"/>
                &nbsp;Name
            </li>
            <li>
                <input type="text" id="email"/>
                &nbsp;Email(Will not be published)
            </li>
            <li>
                <input type="text" id="website"/>
                &nbsp;Website
            </li>
            <li>
                <textarea id="content" name="reply"></textarea>
            </li>
            <li>
                <a class="btn btn-primary"
                   onclick="reply_article('<%=article.getArticleId()%>')">Submit</a>
            </li>
        </ul>
    </div>
    <!-- Blog comments ends -->
</div>
<!-- Blog information container ends -->

<!-- Right side tools container -->
<div class="tool_container">
    <div class="rss_box" title="订阅Tech Blog"
         onclick="location.href='/tech_blog_rss.xml'"></div>

    <!-- Tool box displaying -->
    <div class="tool_box">
        <div class="tool_title">
            <span style="color: #3399CC">TAGS</span>
            <span style="color: #666">CLOUD</span>
        </div>
        <div class="tool_content">
            <div class="tag_cloud" id="tag_cloud">
            </div>
        </div>
    </div>
    <!-- Tool box displaying ends-->

    <!-- Tool box displaying -->
    <div class="tool_box">
        <div class="tool_title">
            <span style="color: #3399CC">ARTICLE</span>
            <span style="color: #666">CATEGORIES</span>
        </div>
        <div class="tool_content">
            <ul id="article_categories">
            </ul>
        </div>
    </div>
    <!-- Tool box displaying ends-->

    <!-- Tool box displaying -->
    <div class="tool_box">
        <div class="tool_title">
            <span style="color: #3399CC">RECENT</span>
            <span style="color: #666">REPLIES</span>
        </div>
        <div class="tool_content">
            <ul id="recent_replies">
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
<!-- Xheditor include files -->
<link rel="stylesheet" type="text/css" href="/css/xheditor_plugin.css"/>
<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
        src="/js/xheditor/xheditor-1.1.14-zh-cn.min.js"></script>

<!-- Baidu Share script -->
<script>window._bd_share_config = {"common": {"bdSnsKey": {}, "bdText": "我在Charles Tech Blog上看到一篇不错的博文，详情猛戳>>", "bdMini": "2", "bdMiniList": false, "bdPic": "", "bdStyle": "0", "bdSize": "32"}, "share": {}, "image": {"viewList": ["tsina", "weixin", "sqq", "qzone", "tqq", "tqf"], "viewText": "分享到：", "viewSize": "16"}, "selectShare": {"bdContainerClass": null, "bdSelectMiniList": ["tsina", "weixin", "sqq", "qzone", "tqq", "tqf"]}};
with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=86835285.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
<!-- Baidu Share script ends -->

<script type="text/javascript"
        src="/js/tag_cloud/jqcloud-1.0.4.min.js"></script>
<script type="text/javascript" src="/js/fg/shCore.js"></script>
<script type="text/javascript" src="/js/fg/shBrushJScript.js"></script>
<script type="text/javascript">SyntaxHighlighter.all();</script>
<script type="text/javascript" src="/js/holder.js"></script>
<script src="/js/ARTICLE.js" type="text/javascript"
        charset="utf-8"></script>
<script src="/js/fg/view_blog.js" type="text/javascript"
        charset="utf-8"></script>
</body>
</html>