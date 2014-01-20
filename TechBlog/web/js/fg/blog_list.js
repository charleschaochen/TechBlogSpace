
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
	load_tags();
	load_categories();
	load_replies();
}
/**
 * Load tags
 */
function load_tags() {
	$.post("article!getAllTags.shtml", {}, function (data) {
		var ret_data = eval(data);
		var tag_list = [];
		var link = "blog.shtml?tag=";
		var max = 10, min = 1;	// Maximum size and minimal size
		for (var i = 0; i < ret_data.length; i++) {
			tag_list.push({text:ret_data[i], weight:(max - min) * Math.random() + min, link:link + encodeURI(ret_data[i])});
		}
		$("#tag_cloud").jQCloud(tag_list, {width:250, height:150});
	});
}
/*
 * Load categories
 */
function load_categories() {
	var url = "article_category!getAllCategories.shtml";
	$.post(url, {}, function (data) {
		var ret_data = eval(data);
		var categories_html = "";
		for (var i = 0; i < ret_data.length; i++) {
			categories_html += "<li><span class='icon-th-list'></span>&nbsp;<a href='/blog.shtml?category="+ret_data[i].categoryId+"'>" + ret_data[i].categoryName + "</a>&nbsp;(<span class='text_bold_green'>"+ret_data[i].articleCount+"</span>)</li>";
		}
		$("#article_categories").html(categories_html);
	});
}

/*
 * Load replies
 */
function load_replies() {
	var url = "article!getRecentArticleReplies.shtml";
	$.post(url, {}, function (data) {
		var ret_data = eval(data);
		var replies_html = "";
		for (var i = 0; i < ret_data.length; i++) {
			replies_html += "<li style='height: 65px; border-bottom: 1px dashed #ddd'><div class='reply_pic'><img src='" + ret_data[i].visitorImg + "' width='90' height='90'/></div><div class='reply_content'><a href='/view_article.shtml?uid="+ret_data[i].article.articleUid+"#comments'>" + ret_data[i].visitorName + "</a>\uff1a" + (ret_data[i].replyContent.length <= 35 ? ret_data[i].replyContent : ret_data[i].replyContent + "...") + "</div></li>";
		}
		$("#recent_replies").html(replies_html);
	});
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
	load_pagination_pageno_tail("blog_pagination", $("#pageNum").val(), $("#pageNo").val(), 5, redirect_url);
}
