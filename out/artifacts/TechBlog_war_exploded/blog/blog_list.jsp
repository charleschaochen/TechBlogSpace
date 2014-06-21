<%@ page language="java" import="java.util.*,org.charlestech.po.*"
         pageEncoding="UTF-8" %>
<%
    // Check invalid entry
    Object method = request.getAttribute("method");
    if (method == null || !"blog_list".equals(method.toString())) {
        response.sendRedirect("blog.shtml");
        return;
    }
    // Get data
    List<Article> articles = (ArrayList<Article>) request
            .getAttribute("articles");
    int pageNo = Integer.parseInt(request.getAttribute("pageNo")
            .toString());
    int pageNum = Integer.parseInt(request.getAttribute("pageNum")
            .toString());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Charles's Tech Blog</title>
    <meta http-equiv="keywords" content="blogs">
    <meta http-equiv="description" content="Blog list displaying">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <%@include file="/common/common_include.jsp" %>
    <link rel="stylesheet" type="text/css" href="/css/fg/blog_list.css"/>
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
        <!-- Blog list container -->
        <div class="blogs_container">
            <%
                for (Article article : articles) {
            %>
            <!-- One Blog displaying -->
            <div class="blog_box">
                <!-- Blog title information -->
                <div class="blog_title">
                    <div class="blog_post_date">
								<span class="date_1"> <%=article.getPostTime().substring(0, 7)%>
								</span>
                        <br/>
								<span class="date_2"> <%=article.getPostTime().substring(8, 10)%>
								</span>
                    </div>
                    <div class="blog_author_pic"
                         style="background: url(<%=article.getAuthor().getImage()%>); background-size:55px 55px">
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
                       href="<%=basePath%>view_article.shtml?uid=<%=article.getArticleUid()%>#comments">
                        <%=article.getReplyTimes()%>
                    </a>
                </div>
                <!-- Blog title information ends -->

                <!-- Blog description -->
                <div class="blog_description">
                    <%=article.getArticleSummary()%>
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

                </div>
                <div class="blog_view_all">
                    <a
                            href="<%=basePath%>view_article.shtml?uid=<%=article.getArticleUid()%>">阅读全文
                        >></a>
                </div>
                <!-- Blog description ends -->

                <!-- Separator -->
                <div class="blog_separator"></div>
            </div>
            <!-- One Blog displaying ends -->
            <%
                }
            %>
            <input type="hidden" id="pageNo" value="<%=pageNo%>"/>
            <input type="hidden" id="pageNum" value="<%=pageNum%>"/>

            <div class="blog_pagination" style="height: 60px">
            </div>
        </div>
        <!-- Blog list container ends -->

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
<!-- Include javascript file -->
<script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
        src="/js/tag_cloud/jqcloud-1.0.4.min.js"></script>
<script type="text/javascript" src="/js/holder.js"></script>
<script type="text/javascript" src="/js/arg-utils.js"></script>
<script type="text/javascript" src="/js/auto-pagination.min.js"></script>
<script src="/js/ARTICLE.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript" src="/js/fg/blog_list.js"></script>
</body>
</html>