
$(function () {
	check_login();
	$("#login_name,#passwd").keydown(function (e) {
		if (e.which == 13) {
			admin_login();
		}
	});
});
/*
 * Check login state, if logon, locate to admin desktop page
 */
function check_login() {
	var url = "login!checkLogin.shtml";
	$.post(url, [], function (data) {
		var ret_data = eval("(" + data + ")");
		if (ret_data.retcode == 0) {
			location.href = "/bg_admin/desktop.jsp";
		}
	});
}
/*
 * Login
 */
function admin_login() {
	if (validate_input()) {
		var login_name = trim($("#login_name").val());
		var passwd = trim($("#passwd").val());
		var url = "login.shtml";
		var data = {username:login_name, passwd:passwd};
		$.ajax({url:url, data:data, type:"post", beforeSend:function () {
			$("#login_btn").html("logining...");
			$("#login_btn").attr("disabled", true);
		}, success:function (data) {
			var ret_data = eval("(" + data + ")");
			if (ret_data.retcode == 0) {
				location.href = "/bg_admin/desktop.jsp";
				return;
			}
			alert("\u767b\u5f55\u8d26\u53f7\u6216\u5bc6\u7801\u9519\u8bef\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165");
			$("#login_name").val("");
			$("#passwd").val("");
			$("#login_name").focus();
			return;
		}, error:function (XMLHttpRequest, textStatus) {
			alert("Error:" + XMLHttpRequest.status);
		}, complete:function () {
			$("#login_btn").attr("disabled", false);
			$("#login_btn").html("login");
		}});
	}
}
/*
 * Validate user input
 */
function validate_input() {
	return validate_login_name() && validate_passwd();
}
/*
 * Validate login name
 */
function validate_login_name() {
	var login_name = trim($("#login_name").val());
	if (login_name == "") {
		show_error("\u8bf7\u8f93\u5165\u7528\u6237\u540d");
		return false;
	}
	if (isInvalid(login_name)) {
		show_error("\u767b\u5f55\u540d\u4e0d\u80fd\u5305\u542b\u975e\u6cd5\u5b57\u7b26");
		return false;
	}
	return true;
}
/*
 * Validate password
 */
function validate_passwd() {
	var passwd = trim($("#passwd").val());
	if (passwd == "") {
		show_error("\u8bf7\u8f93\u5165\u5bc6\u7801");
		return false;
	}
	return true;
}
/*
 * Verify the string is invalid or not
 */
function isInvalid(str) {
	var regexp = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
	return regexp.test(str);
}
/**
 * Show error message
 */
function show_error(err_mess) {
	$("#error_mess").text(err_mess);
	$("#error_mess").show();
	window.setTimeout(function () {
		$("#error_mess").hide("slow");
	}, 3000);
}
/*
 * Find password
 */
function find_passwd() {
	if (validate_admin_name()) {
		var url = "login!findPasswd.shtml";
		var data = {username:trim($("#admin_name").val())};
		$.ajax({url:url, data:data, beforeSend:function () {
			$("#find_pwd_sbt").html("Processing...");
			$("#find_pwd_sbt").attr("disabled", true);
		}, success:function (data) {
			var ret_data = eval("(" + data + ")");
			if (ret_data.retcode == 0) {
				alert("\u5bc6\u7801\u5df2\u7ecf\u53d1\u9001\u81f3\u90ae\u7bb1\uff0c\u8bf7\u67e5\u6536\u5e76\u59a5\u5584\u4fdd\u7ba1\u8d26\u6237\u4fe1\u606f");
				$("#find_pwd").hide();
				return;
			}
			if (ret_data.retcode == -1) {
				alert("\u627e\u4e0d\u5230\u8be5\u767b\u5f55\u540d\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165");
			} else {
				alert("\u627e\u56de\u5bc6\u7801\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
			}
			$("#admin_name").val("");
			$("#admin_name").focus();
		}, complete:function () {
			$("#find_pwd_sbt").html("Submit");
			$("#find_pwd_sbt").attr("disabled", false);
		}});
	}
}
/*
 * Validate admin name
 */
function validate_admin_name() {
	var admin_name = trim($("#admin_name").val());
	if (admin_name == "") {
		alert("\u8bf7\u8f93\u5165\u767b\u5f55\u540d");
		return false;
	}
	if (isInvalid(admin_name)) {
		alert("\u767b\u5f55\u540d\u5305\u542b\u975e\u6cd5\u5b57\u7b26");
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

