$(function () {
    $(".current").html("Categories");
    // Hide the welcome infomation bar
    window.setTimeout(function () {
        $("#welcome").hide("slow");
    }, 3000);
    init_load_data();
});
/*
 * Load initial data
 */
function init_load_data() {
    load_categories();
}
/*
 * Load article categories
 */
function load_categories() {
    var url = "article_category!getAllCategories.shtml";
    var data = {};
    $.post(url, data, function (data) {
        $("#category_list").html("");
        var ret_data = eval(data);
        var category_list = [];
        for (var i = 0; i < ret_data.length; i++) {
            var settop = "\u5426";
            if (ret_data[i].setTop == "1") {
                settop = "\u662f";
            }
            var obj = ret_data[i];
            var category = "<tr><td>" + obj.categoryName + "</td><td>" + obj.createTime.substr(0, 19) + "</td><td>" + obj.articleCount + "</td><td>" + settop + "</td><td><input type='image' src='../images/icn_edit.png' title='Edit Category' onclick='edit(" + JSON.stringify(obj) + ")'/><input type='image' src='../images/icn_trash.png' title='Delete Category' onclick='delete_category(" + JSON.stringify(obj) + ")'/><i class='icon-arrow-up' title='Elevate category' onclick='orderUp(" + obj.categoryId + ")'></i></td></tr>";
            category_list.push(category);
        }
        $("#category_list").html(category_list.join(""));
    });
}
/**
 * Initialise the edit form
 */
function edit(obj) {
    $("#category_title").val(obj.categoryName);
    var settop = "";
    if (obj.setTop == "1") {
        settop = "true";
    }
    $("#top_allocated").attr("checked", settop);
    $("#category_id").val(obj.categoryId);
    $("#add_btn").hide();
    $("#update_btn").show();
    location.href = "#category_form";
}
/*
 * Add new category
 */
function add_category() {
    var settop = $("#top_allocated").attr("checked") ? 1 : 0;
    var category_name = $("#category_title").val();
    if (category_name == "") {
        alert("Category name is empty");
        return;
    }
    if (category_name.length > 20) {
        alert("Category name length is limited in 20");
        return;
    }
    $.ajax({url: "article_category!addCategory.shtml", data: {name: $("#category_title").val(), settop: settop}, type: "post", success: function (data) {
        var ret_data = eval("(" + data + ")");
        alert(ret_data.mess);
        if (ret_data.retcode == 0) {
            location.reload();
            return;
        }
    }, error: function (XMLHttpRequest, textStatus) {
        alert("\u7cfb\u7edf\u7e41\u5fd9(" + XMLHttpRequest.status + ")");
    }});
    $("#category_title").val("");
    $("#category_title").focus();
    $("#top_allocated").attr("checked", "");
}
/*
 * Update category
 */
function update_category() {
    var settop = $("#top_allocated").attr("checked") ? 1 : 0;
    var category_name = $("#category_title").val();
    if (category_name == "") {
        alert("Category name is empty");
        return;
    }
    if (category_name.length > 20) {
        alert("Category name length is limited in 20");
        return;
    }
    $.ajax({url: "article_category!updateCategory.shtml", data: {id: $("#category_id").val(), name: $("#category_title").val(), settop: settop}, type: "post", success: function (data) {
        var ret_data = eval("(" + data + ")");
        alert(ret_data.mess);
        if (ret_data.retcode == 0) {
            location.reload();
            return;
        }
    }, error: function (XMLHttpRequest, textStatus) {
        alert("\u7cfb\u7edf\u7e41\u5fd9(" + XMLHttpRequest.status + ")");
    }});
    $("#category_title").focus();
}
/*
 * Delete category
 */
function delete_category(obj) {
    if (confirm("Are you sure to delete \"" + obj.categoryName + "\"")) {
        $.post("article_category!deleteCategory.shtml", {id: obj.categoryId}, function (data) {
            var ret_data = eval("(" + data + ")");
            alert(ret_data.mess);
            if (ret_data.retcode == 0) {
                location.reload();
                return;
            }
        });
    }
}
/*
 * Clear category form while add new category
 */
function new_category() {
    $("#category_title").val("");
    $("#category_id").val("");
    $("#add_btn").show();
    $("#update_btn").hide();
    location.href = "#category_form";
}

function orderUp(id) {
    var url = "/article_category!orderUp.shtml";
    var data = {id: id};
    $.post(url, data, function (data) {
        var ret_data = eval("(" + data + ")");
        if (ret_data.retcode == 0) {
            load_categories();
        }
    });

}