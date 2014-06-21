$(function () {
    init_load_data();
    //init_style();
    init_editor();

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
    ARTICLE.loadTags();
    ARTICLE.loadCates();
    ARTICLE.loadReplies();
}
/*
 * Initialise style
 */
function init_style() {
    var height_left = parseInt($(".blogs_container").css("height"));
    var height_right = parseInt($(".tool_container").css("height"));
    $(".main_content").css("height", height_left > height_right ? height_left : height_right);
}
/*
 * Initialise the reply editor
 */
function init_editor() {
    $("#content").xheditor({tools: "Cut,Copy,Paste,Fontface,Bold,Italic,Underline,FontColor,BackColor,Emot", skin: "o2007blue", width: "600", height: "100"});
}
/*
 * Submit reply data
 */
function reply_article(article_id) {
    if (!validate_reply()) {
        return false;
    }
    var name = trim($("#visitor").val());
    var email = trim($("#email").val());
    var website = trim($("#website").val());
    var content = trim($("#content").val());
    $.ajax({url: "article!addReply.shtml", data: {articleId: article_id, visitor: name, email: email, website: website, replyContent: content}, success: function (data) {
        var ret_data = eval("(" + data + ")");
        if (ret_data.retcode == 0) {
            $("#visitor").val("");
            $("#email").val("");
            $("#website").val("");
            $("#content").val("");
            location.reload();
            return;
        }
        alert(ret_data.mess);
    }, error: function (XMLHttpRequest, textStatus) {
        alert("\u7cfb\u7edf\u7e41\u5fd9(" + XMLHttpRequest.status + ")");
    }});
}
/*
 * Validate reply form
 */
function validate_reply() {
    return validate_name() && validate_email() && validate_website() && validate_content();
}
/*
 * Validate visitor's name
 */
function validate_name() {
    var name = trim($("#visitor").val());
    if (name != "" && name.length > 20) {
        alert("\u6635\u79f0\u6700\u5927\u957f\u5ea6\u4e3a20\uff0c\u5f53\u524d\u4e3a" + name.length);
        return false;
    }
    return true;
}
/*
 * Validate visitor's email
 */
function validate_email() {
    var email = trim($("#email").val());
    if (email != "" && email.length > 20) {
        alert("\u90ae\u7bb1\u6700\u5927\u957f\u5ea6\u4e3a30\uff0c\u5f53\u524d\u4e3a" + email.length);
        return false;
    }
    var regexp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (email != "" && !(regexp.test(email))) {
        alert("\u8bf7\u8f93\u5165\u5408\u6cd5\u7684\u90ae\u7bb1\u5730\u5740");
        return false;
    }
    return true;
}
/*
 * Validate visitor's website
 */
function validate_website() {
    var website = trim($("#website").val());
    if (website != "" && website.length > 50) {
        alert("\u7f51\u7ad9\u5730\u5740\u6700\u5927\u957f\u5ea6\u4e3a50\uff0c\u5f53\u524d\u957f\u5ea6\u4e3a" + website.length);
        return false;
    }
    var regexp = /^[a-zA-z]+:\/\/(\w+(-\w+)*)(\.(\w+(-\w+)*))*(\?\S*)?$/;
    if (website != "" && !(regexp.test(website))) {
        alert("\u8bf7\u8f93\u5165\u5408\u6cd5\u7684URL\u5730\u5740");
        return false;
    }
    return true;
}
/*
 * Validate reply content
 */
function validate_content() {
    var content = trim($("#content").val());
    if (content == "") {
        alert("\u8bc4\u8bba\u4e0d\u80fd\u4e3a\u7a7a");
        return false;
    }
    if (content.length > 500) {
        alert("\u8bc4\u8bba\u5185\u5bb9\u6700\u5927\u957f\u5ea6\u4e3a500\uff0c\u5f53\u524d\u957f\u5ea6\u4e3a" + content.length);
        return false;
    }
    return true;
}
/**
 * Delete blanks in left or right
 */
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

