
/**
 * Check if date format is valid
 */
function isDateValid(str) {
	var reg = /^(\d{4})-(\d{1,2})-(\d{1,2})\s(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	var r = str.match(reg);
	if (r == null) {
		return false;
	}
	r[2] = r[2] - 1;
	var d = new Date(r[1], r[2], r[3], r[4], r[5], r[6]);
	if (d.getFullYear() != r[1]) {
		return false;
	}
	if (d.getMonth() != r[2]) {
		return false;
	}
	if (d.getDate() != r[3]) {
		return false;
	}
	if (d.getHours() != r[4]) {
		return false;
	}
	if (d.getMinutes() != r[5]) {
		return false;
	}
	if (d.getSeconds() != r[6]) {
		return false;
	}
	return true;
}
/**
 * Compare two dates
 */
function compare(start, end) {
	if (start == "" || end == "") {
		return true;
	}
	if (!isDateValid(start) || !isDateValid(end)) {
		return false;
	}
	
	// Get start time
	var start_date_str = start.split(" ")[0];	// Get date string
	var start_time_str = start.split(" ")[1]; // Get time string
	var start_date_params = start_date_str.split("-");	// Get year, month, day
	var start_time_params = start_time_str.split(":");	// Get hour, minute, second
	var start_date = new Date(start_date_params[0], start_date_params[1], start_date_params[2], start_time_params[0], start_time_params[1], start_time_params[2]);
	var start_time = start_date.getTime();
	
	// Get end time
	var end_date_str = end.split(" ")[0];	// Get date string
	var end_time_str = end.split(" ")[1]; // Get time string
	var end_date_params = end_date_str.split("-");	// Get year, month, day
	var end_time_params = end_time_str.split(":");	// Get hour, minute, second
	var end_date = new Date(end_date_params[0], end_date_params[1], end_date_params[2], end_time_params[0], end_time_params[1], end_time_params[2]);
	var end_time = end_date.getTime();
	
	// Compare
	if (start_time <= end_time) {
		return true;
	}
	return false;
}

