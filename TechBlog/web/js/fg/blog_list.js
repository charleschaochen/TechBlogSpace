$(function () {
    init_load_data();
    //init_style();
    init_pagination();
});
/**
 * Initialise the css
 */
function init_style() {
    var height_left = parseInt($(".blogs_container").css("height"));
    var height_right = parseInt($(".tool_container").css("height"));
    $(".main_content").css("height", height_left >= height_right ? height_left : height_right);
}
/**
 * Load data from sever when initialisation
 */
function init_load_data() {
    ARTICLE.loadTags();
    ARTICLE.loadCates();
    ARTICLE.loadReplies();
}

/**
 * Initialise pagination style
 */
function init_pagination() {
    var tag = get_param("tag");
    var category = get_param("category");
    var redirect_url = "/blog.shtml?";
    if (tag != "") {
        redirect_url += "tag=" + tag + "&";
    }
    if (category != "") {
        redirect_url += "category=" + category + "&";
    }
    redirect_url += "page=";
    load_pagination_pageno("blog_pagination", $("#pageNum").val(), $("#pageNo").val(), 5, redirect_url);
}
