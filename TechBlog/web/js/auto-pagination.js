/**
 * Load pagination style, page url is with page number as tail
 * @param page_div	The div for pagination
 * @param pageNum	The total page count
 * @param maxShow	The max page count showed
 * @param param_name	The page parameter name
 * @param link		The page link
 */
function load_pagination(page_div, pageNum, maxShow, param_name, link) {
	// The name of url parameter default as "page"
	if(param_name == "") {
		param_name = "page";
	}
	// Get page number from url parameters
	var pageNo = get_param(param_name);
	if(pageNo == "") {
		// Page number default as 1
		pageNo = 1;
	}
	if (page_div == "") {
		return false;
	}
	if (isNaN(pageNum) || isNaN(maxShow)) {
		return false;
	}
	if(link.indexOf("?") > 0) {
		link += "&" + param_name + "=";
	}else {
		link += "?" + param_name + "=";
	}
	pageNum = parseInt(pageNum);
	pageNo = parseInt(pageNo);
	maxShow = parseInt(maxShow);
	var html = "<div class='auto-pagination'><ul>";
	var last = 1;
	// Add "Prev" button
	if (pageNo > 1) {
		html += "<li><a href='" + link + (pageNo - 1) + "'>Prev</a></li>";
	}
	if (pageNo <= maxShow) {
		for (var i = 1; i <= maxShow && i <= pageNum; i++) {
			if (i == pageNo) {
			// Disable current page
				html += "<li class='disabled'><a>" + i + "</a></li>";
			} else {
				html += "<li><a href='" + link + i + "'>" + i + "</a></li>";
			}
		}
		last = i - 1;
	} else {
		last = pageNo;
		html += "<li><a href='" + link + 1 + "'>1</a></li><li><a href='" + link + 2 + "'>2</a></li><li class='disabled'><a>......</a></li><li><a href='" + link + (pageNo - 1) + "'>" + (pageNo - 1) + "</a></li><li class='disabled'><a>" + pageNo + "</a></li>";
		if(pageNo + 1 <= pageNum){
			html += "<li><a href='" + link + (pageNo + 1) + "'>" + (pageNo + 1) + "</a></li>";
			last += 1;
		}
	}
	if (pageNum - last <= maxShow) {
		for (var i = last + 1; i <= pageNum; i++) {
			html += "<li><a href='" + link + i + "'>" + i + "</a></li>";
		}
	} else {
		html += "<li class='disabled'><a>......</a></li><li><a href='" + link + (pageNum - 1) + "'>" + (pageNum - 1) + "</a></li><li><a href='" + link + pageNum + "'>" + pageNum + "</a></li>";
	}
	// Add "Next" button
	if (pageNo < pageNum) {
		html += "<li><a href='" + link + (pageNo + 1) + "'>Next</a></li>";
	}
	html += "</ul></div>";
	$("." + page_div).html(html);
}

/**
 * Load pagination style
 * @param page_div	The div for pagination
 * @param pageNum	The total page count
 * @param pageNo	The current page number
 * @param maxShow	The max page count showed
 * @param link		The page link
 */
function load_pagination_pageno(page_div, pageNum, pageNo, maxShow, link) {
	if (page_div == "") {
		return false;
	}
	if(pageNo == "") {
		// Page number default as 1
		pageNo = 1;
	}
	if (isNaN(pageNum) || isNaN(pageNo) || isNaN(maxShow)) {
		return false;
	}
	pageNum = parseInt(pageNum);
	pageNo = parseInt(pageNo);
	maxShow = parseInt(maxShow);
	var html = "<div class='auto-pagination'><ul>";
	var last = 1;
	// Add "Prev" button
	if (pageNo > 1) {
		html += "<li><a href='" + link + (pageNo - 1) + "'>Prev</a></li>";
	}
	if (pageNo <= maxShow) {
		for (var i = 1; i <= maxShow && i <= pageNum; i++) {
			if (i == pageNo) {
			// Disable current page
				html += "<li class='disabled'><a>" + i + "</a></li>";
			} else {
				html += "<li><a href='" + link + i + "'>" + i + "</a></li>";
			}
		}
		last = i - 1;
	} else {
		last = pageNo;
		html += "<li><a href='" + link + 1 + "'>1</a></li><li><a href='" + link + 2 + "'>2</a></li><li class='disabled'><a>......</a></li><li><a href='" + link + (pageNo - 1) + "'>" + (pageNo - 1) + "</a></li><li class='disabled'><a>" + pageNo + "</a></li>";
		if(pageNo + 1 <= pageNum){
			html += "<li><a href='" + link + (pageNo + 1) + "'>" + (pageNo + 1) + "</a></li>";
			last += 1;
		}
	}
	if (pageNum - last <= maxShow) {
		for (var i = last + 1; i <= pageNum; i++) {
			html += "<li><a href='" + link + i + "'>" + i + "</a></li>";
		}
	} else {
		html += "<li class='disabled'><a>......</a></li><li><a href='" + link + (pageNum - 1) + "'>" + (pageNum - 1) + "</a></li><li><a href='" + link + pageNum + "'>" + pageNum + "</a></li>";
	}
	// Add "Next" button
	if (pageNo < pageNum) {
		html += "<li><a href='" + link + (pageNo + 1) + "'>Next</a></li>";
	}
	html += "</ul></div>";
	$("." + page_div).html(html);
}

/*
 * Get parameter value by name
 */
function get_param(name) {
	var args_str = location.search.substring(1);
	var args_list = args_str.split("&");
	for (var i = 0; i < args_list.length; i++) {
		var pos = args_list[i].indexOf("=");
		if (pos == -1) {
			continue;
		}
		var arg_name = args_list[i].substring(0, pos);
		if (arg_name == name) {
			var arg_value = args_list[i].substring(pos + 1);
			return decodeURIComponent(arg_value);
		}
	}
	return "";
}