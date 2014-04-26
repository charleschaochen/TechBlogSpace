
$(function () {
	$(".current").html("Manage Articles");
	$(".column").equalHeight();
        // Hide the welcome infomation bar
	window.setTimeout(function () {
		$("#welcome").hide("slow");
	}, 3000);
	init_load_data();

	// Change article categories
	$("#category").change(function () {
		load_articles($(this).val(), "");
	});
	//var article_replies = []; // Current article replies, global variable, used for show_admin_replies
});
/*
 * Load initial data
 */
function init_load_data() {
	load_categories();
	load_articles("", "");
}
/*
 * Load article categories
 */
function load_categories() {
	var url = "article_category!getAllCategories.shtml";
	var data = {};
	$.post(url, data, function (data) {
		var ret_data = eval(data);
		var category_list = [];
		category_list.push("<option value='0'>- All -</option>");
		for (var i = 0; i < ret_data.length; i++) {
			category_list.push("<option value='" + ret_data[i].categoryId + "'>" + ret_data[i].categoryName + "</option>");
		}
		$("#category").html(category_list.join(""));
	});
}
/*
 * Load article list by category ID and page NO
 */
function load_articles(category, page) {
	var url = "mgArticle!getAllArticles.shtml";
	var data;
	if (category == 0) {
		data = {categoryId:"", page:page};
	} else {
		data = {categoryId:category, page:page};
	}
	$.post(url, data, function (data) {
		var ret_data = eval("(" + data + ")");
		/* Set page NO and page count*/
		var pageNo = ret_data.pageNo;
		var pageNum = ret_data.pageNum;
		$("#pageNum").val(pageNum);
		$("#pageNo").val(pageNo);
		/* Set page NO and page count*/
		var articles = ret_data.articles;
		var articles_html = "";
		for (var i = 0; i < articles.length; i++) {
			articles_html += "<tr><td><a target='_blank' href='/view_article.shtml?uid=" + articles[i].articleUid + "'>" + articles[i].articleTitle + "</a></td><td><a target='_blank' href='/blog.shtml?category=" + articles[i].category.categoryId + "'>" + articles[i].category.categoryName + "</a></td><td>" + articles[i].postTime.substr(0, 19) + "</td><td>" + articles[i].author.name + "</td><td><a href='/bg_admin/blog/article_form.jsp?action=update&uid=" + articles[i].articleUid + "' style='text-decoration: none' title='Edit article'><img src='/bg_admin/images/icn_edit_article.png' width='11px' />&nbsp;Edit</a>&nbsp;|&nbsp;<a href=\"javascript:delete_article('" + articles[i].articleUid + "');\" style='text-decoration: none' title='Delete article'><img src='/bg_admin/images/icn_trash.png' width='11px'/>&nbsp;Delete</a>&nbsp;|&nbsp;<a href=\"javascript:load_article_replies('" + articles[i].articleUid + "');\" style='text-decoration: none' title='View article replies'><img src='/bg_admin/images/icn_search.png' width='11px' />&nbsp;Comments</a></td></tr>";
		}
		$("#article_list").html(articles_html);
		init_pagination();
	});
}
/*
 * Load article replies
 */
function load_article_replies(uid) {
	var url = "mgArticle!getArticleReplies.shtml";
	var data = {articleUid:uid};
	$.post(url, data, function (data) {
		var ret_data = eval(data);
		article_replies = ret_data;
		var replies = [];
		for (var i = 0; i < ret_data.length; i++) {
			replies.push("<div class='message'><p><a href='" + ret_data[i].visitorWebsite + "'>echo</a>\u8bc4\u8bba\u8bf4:<span class='reply_content'>" + ret_data[i].replyContent + "</span></p><p style='text-align: right'><a href=\"javascript:show_admin_replies(" + ret_data[i].replyId + ");\">Admin Reply</a>&nbsp;|&nbsp;&nbsp;<a href=\"javascript:delete_reply('" + ret_data[i].replyId + "','" + uid + "')\">Delete Reply</a></p></div>");
		}
		$("#article_reply").html(replies.join(""));
	});
}
/*
 * Delete article
 */
function delete_article(uid) {
	if (confirm("\u786e\u5b9a\u5220\u9664\u8be5\u535a\u6587\uff1f")) {
		var url = "mgArticle!deleteArticle.shtml";
		$.post(url, {articleUid:uid}, function (data) {
			var ret_data = eval("(" + data + ")");
			if (ret_data.retcode == 0) {
				load_articles("", "");
				return;
			}
			alert("\u5220\u9664\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
			return;
		});
	}
}
/**
 * Show administrator replies in dialog
 */
function show_admin_replies(reply_id) {
	var url = "mgArticle!getAdminReplies.shtml";
	var data = {replyId:reply_id};
	$.post(url, data, function (data) {
		var admin_replies = eval(data);
		var replies = [];
		for (var i = 0; i < admin_replies.length; i++) {
			replies.push("<div class='message'><p><a>" + admin_replies[i].admin.name + "</a>\u8bc4\u8bba\u8bf4:<span class='reply_content'>" + admin_replies[i].replyContent + "</span></p><p style='text-align: right'><a href=\"javascript:delete_admin_reply('" + admin_replies[i].replyId + "','" + reply_id + "')\">Delete Reply</a></p></div>");
		}
		$("#admin_replies").html(replies.join(""));
		$("#admin_reply").modal();
	});
}
/**
 * Delete article reply
 */
function delete_reply(reply_id, uid) {
	if (window.confirm("\u786e\u5b9a\u5220\u9664\u8fd9\u6761\u8bc4\u8bba\uff1f")) {
		var url = "mgArticle!deleteReply.shtml";
		var data = {replyId:reply_id};
		$.post(url, data, function (data) {
			var ret_data = eval("(" + data + ")");
			if (ret_data.retcode == 0) {
				// Delete successfully
				load_article_replies(uid);
				return;
			}
			// Delete failed, alert error message
			alert(ret_data.mess);
		});
	}
}
/*
 * Delete administrator reply
 */
function delete_admin_reply(admin_reply_id, reply_id) {
	if (window.confirm("\u786e\u5b9a\u5220\u9664\u8fd9\u6761\u8bc4\u8bba\uff1f")) {
		var url = "mgArticle!deleteAdminReply.shtml";
		var data = {replyId:admin_reply_id};
		$.post(url, data, function (data) {
			var ret_data = eval("(" + data + ")");
			if (ret_data.retcode == 0) {
				// Delete successfully
				show_admin_replies(reply_id);
				return;
			}
			// Delete failed, alert error message
			alert(ret_data.mess);
		});
	}
}
/**
 * Initialise pagination style
 */
function init_pagination() {
	load_pagination("blog_pagination", $("#pageNum").val(), $("#pageNo").val(), 5, "");
}
/**
 * Load pagination
 * @param page_div	The div for pagination
 * @param pageNum	The total page count
 * @param pageNo	The current page number
 * @param maxShow	The max page count showed
 * @param link		The page link
 */
function load_pagination(page_div, pageNum, pageNo, maxShow, link) {
	if (page_div == "") {
		return false;
	}
	if (isNaN(pageNum) || isNaN(pageNo) || isNaN(maxShow)) {
		return false;
	}
	pageNum = parseInt(pageNum);
	pageNo = parseInt(pageNo);
	maxShow = parseInt(maxShow);
	var html = "<div class='pagination'><ul>";
	var last = 1;
	// Add "Prev" button
	if (pageNo > 1) {
		html += "<li><a href='javascript:load_articles(" + $("#category").val() + "," + (pageNo - 1) + ")'>Prev</a></li>";
	}
	if (pageNo <= maxShow) {
		for (var i = 1; i <= maxShow && i <= pageNum; i++) {
			html += "<li><a href='javascript:load_articles(" + $("#category").val() + "," + i + ")'>" + i + "</a></li>";
		}
		last = i - 1;
	} else {
		last = pageNo;
		html += "<li><a href='javascript:load_articles(" + $("#category").val() + "," + 1 + ")'>1</a></li><li><a href='javascript:load_articles(" + $("#category").val() + "," + 2 + ")'>2</a></li><li class='disabled'><a>......</a></li><li><a href='javascript:load_articles(" + $("#category").val() + "," + (pageNo - 1) + ")'>" + (pageNo - 1) + "</a></li><li class='disabled'><a href='javascript:load_articles(" + $("#category").val() + "," + pageNo + ")'>" + pageNo + "</a></li>";
	}
	if (pageNum - last <= maxShow) {
		for (var i = last + 1; i <= pageNum; i++) {
			html += "<li><a href='javascript:load_articles(" + $("#category").val() + "," + i + ")'>" + i + "</a></li>";
		}
	} else {
		html += "<li><a href='javascript:load_articles(" + $("#category").val() + "," + (last + 1) + ")'>" + (last + 1) + "</a></li><li class='disabled'><a>......</a></li><li><a href='javascript:load_articles(" + $("#category").val() + "," + (pageNum - 1) + ")'>" + (pageNum - 1) + "</a></li><li><a href='javascript:load_articles(" + $("#category").val() + "," + pageNum + ")'>" + pageNum + "</a></li>";
	}
	// Add "Next" button
	if (pageNo < pageNum) {
		html += "<li><a href='javascript:load_articles(" + $("#category").val() + "," + (pageNo + 1) + ")'>Next</a></li>";
	}
	html += "</ul></div>";
	$("." + page_div).html(html);
    $("." + page_div).show();
}

