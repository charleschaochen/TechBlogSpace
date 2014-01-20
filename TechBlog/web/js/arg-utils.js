
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
/*
 * Get all parameters
 */
function get_all_params() {
	var args = new Object();
	var args_str = location.search.substring(1);
	var args_list = args_str.split("&");
	for (var i = 0; i < args_list.length; i++) {
		var pos = args_list[i].indexOf("=");
		if (pos == -1) {
			continue;
		}
		var arg_name = args_list[i].substring(0, pos);
		var arg_value = args_list[i].substring(pos + 1);
		arg_value = decodeURIComponent(arg_value);
		args[arg_name] = arg_value;
	}
	return args;
}

