$(function () {
	$(".current").html("Desktop");
	$('.column').equalHeight();
        // Hide the welcome infomation bar
	window.setTimeout(function () {
		$("#welcome").hide("slow");
	}, 3000);
});
