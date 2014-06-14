$(function () {
    init_load_data();
    //init_style();
    init_pagination();

    // Create back to top icon
    backTop.createBackTop({
        bottom: "80px",
        right: "200px",
        style: 2
    });
});

/**
 * Load data from sever when initialisation
 */
function init_load_data() {
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

/**
 * Initialize data in view detail dialog
 */
function initDetail() {

}