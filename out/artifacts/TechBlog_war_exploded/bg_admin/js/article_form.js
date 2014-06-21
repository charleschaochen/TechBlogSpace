$(function () {
    check_action();
    init_load_data();

    window.onbeforeunload = function (event) {
        return "你确定要离开当前页面吗？";
    }
});

/**
 * Check the action as create or update
 */
function check_action() {
    var action = get_param("action");
    if (action == "create") {
        $(".current").html("New Article");
        $("#sbt_button").bind("click", function () {
            post_article();
        });
        return;
    }
    if (action == "update") {
        // Check uid parameter
        if (get_param("uid") == "") {
            alert("Article UID is missed");
            close_page();
            return;
        }
        $(".current").html("Edit Article");
        $("#sbt_button").html("Update");
        $("#sbt_button").bind("click", function () {
            update_article();
        });
        load_article_info();
        return;
    }
    alert("Invalid Entry!");
    close_page();
    return;
}
/**
 * Load initial data
 */
function init_load_data() {
    load_categories();
}
/**
 * Load categories
 */
function load_categories() {
    var url = "article_category!getAllCategories.shtml";
    var data = {};
    $.post(url, data, function (data) {
        var ret_data = eval(data);
        var category_html = "";
        for (var i = 0; i < ret_data.length; i++) {
            category_html += "<option value='" + ret_data[i].categoryId + "'>" + ret_data[i].categoryName + "</option>";
        }
        $("#category").html(category_html);
    });
}
/**
 * Load article information when action is update
 */
function load_article_info() {
    var url = "mgArticle!getArticleInfo.shtml";
    var data = {articleUid: get_param("uid")};
    $.post(url, data, function (data) {
        var ret_data = eval("(" + data + ")");
        if (ret_data.retcode == 0) {
            var article = ret_data.article;
            $("#form_title").html("Update Article: " + article.articleTitle);
            $("#title").val(article.articleTitle);
            $("#content").val(article.articleContent);
            $("#category").val(article.category.categoryId);
            var tags = article.articleTags;
            var tags_html = "";
            for (var i = 0; i < tags.length; i++) {
                tags_html += tags[i].tagName + ";";
            }
            $("#tags").val(tags_html.substr(0, tags_html.length - 1));
            $("#top_allocated").attr("checked", article.setTop == 1 ? "true" : "");
            $("#state").val(article.articleState);
            return;
        }
        alert(ret_data.mess);
        close_page();
        return;
    });
}
/*
 * Post new article
 */
function post_article() {
    if (validate()) {
        var title = trim($("#title").val());
        var content = trim($("#content").val());
        var category = $("#category").val();
        var tag = "";
        var tags = $("#tags").val().split(";");
        for (var i = 0; i < tags.length; i++) {
            if (trim(tags[i]) != "") {
                tag += trim(tags[i]) + ";";
            }
        }
        tag = tag.substring(0, tag.length - 1);
        var settop = $("#top_allocated").attr("checked") ? 1 : 0;
        var state = $("#state").val();
        $.ajax({url: "article!addArticle.shtml", data: {title: title, content: content, category: category, tags: tag, settop: settop, state: state}, type: "post", beforeSend: function () {
            $("#sbt_button").attr("disabled", true);
            $("#sbt_button").html("Publishing...");
        }, success: function (data) {
            var ret_data = eval("(" + data + ")");
            alert(ret_data.mess);
            if (ret_data.retcode == 0) {
                $("#title").val("");
                $("#content").val("");
                $("#tags").val("");
                location.reload();
                return;
            }
        }, error: function (XMLHttpRequest, textStatus) {
            alert("\u7cfb\u7edf\u7e41\u5fd9(" + XMLHttpRequest.status + ")");
        }, complete: function () {
            $("#sbt_button").html("Publish");
            $("#sbt_button").attr("disabled", false);
        }});
    }
}
/*
 * Update new article
 */
function update_article() {
    if (validate()) {
        var title = trim($("#title").val());
        var content = trim($("#content").val());
        var category = $("#category").val();
        var tag = "";
        var tags = $("#tags").val().split(";");
        for (var i = 0; i < tags.length; i++) {
            if (trim(tags[i]) != "") {
                tag += trim(tags[i]) + ";";
            }
        }
        tag = tag.substring(0, tag.length - 1);
        var settop = $("#top_allocated").attr("checked") ? 1 : 0;
        var state = $("#state").val();
        $.ajax({url: "mgArticle!updateArticle.shtml", data: {articleUid: get_param("uid"), title: title, content: content, category: category, tags: tag, settop: settop, state: state}, type: "post", beforeSend: function () {
            $("#sbt_button").attr("disabled", true);
            $("#sbt_button").html("Updating...");
        }, success: function (data) {
            var ret_data = eval("(" + data + ")");
            alert(ret_data.mess);
            if (ret_data.retcode == 0) {
                location.href = "/bg_admin/blog/manage_article.jsp";
                return;
            }
        }, error: function (XMLHttpRequest, textStatus) {
            alert("\u7cfb\u7edf\u7e41\u5fd9(" + XMLHttpRequest.status + ")");
        }, complete: function () {
            $("#sbt_button").html("Update");
            $("#sbt_button").attr("disabled", false);
        }});
    }
}
/**
 * Validate the submitted form
 */
function validate() {
    return validate_title() && validate_content() && validate_tags();
}
/**
 * Validate title
 */
function validate_title() {
    var title = trim($("#title").val());
    if (title == "") {
        show_error("\u6807\u9898\u4e0d\u80fd\u4e3a\u7a7a");
        return false;
    }
    if (title.length > 50) {
        show_error("\u6807\u9898\u4e0d\u80fd\u8d85\u8fc750\u4e2a\u5b57\u7b26\uff0c\u4f60\u5df2\u8f93\u5165" + title.length + "\u4e2a\u5b57\u7b26");
        return false;
    }
    return true;
}
/**
 * Validate content
 */
function validate_content() {
    var content = trim($("#content").val());
    if (content == "") {
        show_error("\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a");
        return false;
    }
    if (content.length > 30000) {
        show_error("\u5185\u5bb9\u4e0d\u80fd\u8d85\u8fc730000\u4e2a\u5b57\u7b26\uff0c\u4f60\u5df2\u8f93\u5165" + content.length + "\u4e2a\u5b57\u7b26");
        return false;
    }
    return true;
}
/**
 * Validate tags
 */
function validate_tags() {
    var tags = $("#tags").val().split(";");
    if (tags.length > 3) {
        show_error("\u6807\u7b7e\u4e2a\u6570\u4e0d\u80fd\u8d85\u8fc73\u4e2a");
        return false;
    }
    return true;
}
/**
 * Close current page
 */
function close_page() {
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
            window.opener = null;
            window.close();
        } else {
            window.open("", "_top");
            window.top.close();
        }
    } else {
        if (navigator.userAgent.indexOf("Firefox") > 0) {
            window.location.href = "about:blank ";
            //window.history.go(-2);
        } else {
            window.opener = null;
            window.open("", "_self", "");
            window.close();
        }
    }
}
/**
 * Show error message
 */
function show_error(err_mess) {
    $("html").scrollTop(1);
    $("#error_mess").text(err_mess);
    $("#error_mess").show();
    window.setTimeout(function () {
        $("#error_mess").hide("slow");
    }, 3000);
}
/**
 * Delete blanks in left or right
 */
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

