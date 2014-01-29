<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/bg_admin/common/check_login.jsp" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <title>Tech Blog Background - Category Form</title>
    <%@include file="/bg_admin/common/common_include.jsp" %>
    <style type="text/css">
        .icon-arrow-up {
            display: inline-block;
            width: 14px;
            height: 14px;
            background-image: url("../img/glyphicons-halflings.png");
            background-position: 14px 14px;
            background-repeat: no-repeat;
            background-position: -289px -96px;
            opacity: 0.5;
            cursor: pointer;
        }
    </style>
</head>


<body>

<%@include file="/bg_admin/common/common_header.html" %>
<%@include file="/bg_admin/common/common_sidebar.html" %>

<section id="main" class="column">

    <h4 class="alert_info" id="welcome">
        You can view, create, edit article categories in this page.
    </h4>

    <div class="clear"></div>

    <article class="module width_full">
        <header>
            <h3>
                All Categories
            </h3>
        </header>
        <div class="module_content">
            <fieldset>
                <table class="tablesorter" cellspacing="0">
                    <thead>
                    <tr>
                        <th>
                            Category Title
                        </th>
                        <th>
                            Create Time
                        </th>
                        <th>
                            Count of Articles
                        </th>
                        <th>
                            Top-allocated
                        </th>
                        <th>
                            Action
                        </th>
                    </tr>
                    </thead>
                    <tbody id="category_list">
                    </tbody>
                </table>
            </fieldset>

            <div class="clear"></div>
        </div>
        <footer>
            <div style="margin-top: 5px">
                <input type="submit" value="New Category" class="alt_btn"
                       onclick="new_category();" style="margin-left: 10px">
            </div>
        </footer>
    </article>


    <article id="category_form" class="module width_full">
        <header>
            <h3>
                Category Form
            </h3>
        </header>
        <div class="module_content">
            <fieldset>
                <label>
                    Category Title
                </label>
                <input type="text" id="category_title">
                <input type="hidden" id="category_id" value="">
            </fieldset>

            <div class="clear"></div>
        </div>
        <footer>
            <div class="submit_link">
                <input type="checkbox" id="top_allocated"/>
                &nbsp;Top-allocated
                <input type="submit" value="Add" class="alt_btn" id="add_btn"
                       onclick="add_category();" style="margin-left: 10px">
                <input type="submit" value="Update" class="alt_btn" id="update_btn"
                       onclick="update_category();" style="margin-left: 10px; display: none">
            </div>
        </footer>
    </article>

</section>

<script type="text/javascript" src="/bg_admin/js/article_categories.js"></script>
</body>

</html>