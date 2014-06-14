/**
 * @Description Article Common Function Utilities
 * @author Charles Chen
 * @date 14-3-5
 * @version 1.0
 */
var ArticleLoader = function () {
    var t = this;
    t.LOAD_TAGS_URL = "/article!getAllTags.shtml";
    t.LOAD_CATES_URL = "/article_category!getAllCategories.shtml";
    t.LOAD_REPLIES_URL = "/article!getRecentArticleReplies.shtml";
}

ArticleLoader.prototype = {
    /**
     * Load article tags in foreground page
     */
    loadTags: function () {
        var t = this;
        $.post(t.LOAD_TAGS_URL, {}, function (data) {
            var ret_data = eval(data);
            var tag_list = [];
            var link = "blog.shtml?tag=";
            var max = 10, min = 1;	// Maximum size and minimal size
            for (var i = 0; i < ret_data.length; i++) {
                tag_list.push({text: ret_data[i], weight: (max - min) * Math.random() + min, link: link + encodeURI(ret_data[i])});
            }
            $("#tag_cloud").jQCloud(tag_list, {width: 250, height: 150});
        });
    },
    /**
     * Load article categories in foreground page
     */
    loadCates: function () {
        var t = this;
        $.post(t.LOAD_CATES_URL, {}, function (data) {
            var ret_data = eval(data);
            var categories_html = "";
            for (var i = 0; i < ret_data.length; i++) {
                categories_html += "<li><span class='glyphicon glyphicon-list-alt'></span>&nbsp;&nbsp;<a href='/blog.shtml?category=" + ret_data[i].categoryId + "'>" + ret_data[i].categoryName + "</a>&nbsp;(<span class='text_bold_green'>" + ret_data[i].articleCount + "</span>)</li>";
            }
            $("#article_categories").html(categories_html);
        });
    },
    /**
     * Load article replies in foreground page
     */
    loadReplies: function () {
        var t = this;
        $.post(t.LOAD_REPLIES_URL, {}, function (data) {
            var ret_data = eval(data);
            var replies_html = "";
            for (var i = 0; i < ret_data.length; i++) {
                replies_html += "<li style='height: 65px; border-bottom: 1px dashed #ddd'><div class='reply_pic'><img src='" + ret_data[i].visitorImg + "' width='90' height='90'/></div><div class='reply_content'><a href='" + ret_data[i].visitorWebsite + "'>" + ret_data[i].visitorName + "</a>\uff1a<a style='color: #333' href='/view_article.shtml?uid=" + ret_data[i].article.articleUid + "#comments'>" + (ret_data[i].replyContent.length <= 35 ? ret_data[i].replyContent : ret_data[i].replyContent + "...") + "</a></div></li>";
            }
            $("#recent_replies").html(replies_html);
        });
    },
    /**
     * Get JSON data of article tags
     * @returns {null}
     */
    getTags: function () {
        var t = this;
        $.post(t.LOAD_TAGS_URL, {}, function (data) {
            var ret_data = eval(data);
            return ret_data;
        });
        return null;
    },
    /**
     * Get JSON data of article categories
     * @returns {null}
     */
    getCates: function () {
        var t = this;
        $.post(t.LOAD_CATES_URL, {}, function (data) {
            var ret_data = eval(data);
            return ret_data;
        });
        return null;
    },
    /**
     * Get JSON data of article replies
     * @returns {null}
     */
    getReplies: function () {
        var t = this;
        $.post(t.LOAD_REPLIES_URL, {}, function (data) {
            var ret_data = eval(data);
            return ret_data;
        });
        return null;
    }
}

var ARTICLE = new ArticleLoader();