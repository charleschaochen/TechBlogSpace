/**
 * @Description Script for valentine.html
 * @author Charles Chen
 * @date 14-1-26
 * @version 1.0
 */
$(function () {
    /**
     * Light up the light
     */
    $(".light").click(function () {
        var color = $(this).attr("class").replace("light ", "");
        var level = parseInt($(this).attr("alt")) + 1;
        if (level >= 4)level = 0;
        var pic = color + "_" + level + ".png";
        $(this).css("background", "url(/welcome/images/lights/" + pic + ")");
        $(this).attr("alt", level);
    });
});
