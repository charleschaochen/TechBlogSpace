
$(function () {
	header_init_load_data();
});
/**
 * Initialise header data
 */
function header_init_load_data() {
	header_load_article_categories();
}
/*
 * Load article categories for header
 */
function header_load_article_categories() {
	$.post("article_category!getAllCategories.shtml", {}, function (data) {
		var ret_data = eval(data);
		categories_html = "<div class='column'><h3>\u535a\u6587\u5206\u7c7b</h3><ul>";
		for (var i = 0; i < ret_data.length; i++) {
			if (i != 0 && i % 5 == 0) {
				categories_html += "</ul></div><div class='column'><h3>&nbsp;</h3><ul>";
			}
			categories_html += "<li><span class='glyphicon glyphicon-list-alt'></span>&nbsp;&nbsp;<a href='/blog.shtml?category=" + ret_data[i].categoryId + "'>" + ret_data[i].categoryName + "</a></li><li>";
		}
		categories_html += "</ul></div>";
		$("#megamenu_blog").html(categories_html);
	});
}
/*
 * Alert building prompt for unfinished module
 */
function building() {
	alert("\u535a\u4e3b\u6b63\u5728\u5f00\u53d1\u4e2d\uff0c\u656c\u8bf7\u671f\u5f85...");
}

