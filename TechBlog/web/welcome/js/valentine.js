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
        // Change light level
        var color = $(this).attr("class").replace("light ", "");
        var level = parseInt($(this).attr("alt")) + 1;
        if (level >= 4)level = 0;
        var pic = color + "_" + level + ".png";
        $(this).css("background", "url(/welcome/images/lights/" + pic + ")");
        $(this).attr("alt", level);

        // Change opacity of the main content
        var opacity = parseFloat($(".mask_layer").css("opacity"));
        if (opacity > 0.1) {
            opacity -= 0.1;
            $(".mask_layer").css("opacity", opacity);
        } else {
            $(".mask_layer").css("opacity", 0);
        }
    });


    disable_scroll();
});


// left: 37, up: 38, right: 39, down: 40,
// spacebar: 32, pageup: 33, pagedown: 34, end: 35, home: 36
var keys = [37, 38, 39, 40];

function preventDefault(e) {
    e = e || window.event;
    if (e.preventDefault)
        e.preventDefault();
    e.returnValue = false;
}

function keydown(e) {
    for (var i = keys.length; i--;) {
        if (e.keyCode === keys[i]) {
            preventDefault(e);
            return;
        }
    }
}

function wheel(e) {
    preventDefault(e);
}

function disable_scroll() {
    if (window.addEventListener) {
        window.addEventListener('DOMMouseScroll', wheel, false);
    }
    window.onmousewheel = document.onmousewheel = wheel;
    document.onkeydown = keydown;
}

function enable_scroll() {
    if (window.removeEventListener) {
        window.removeEventListener('DOMMouseScroll', wheel, false);
    }
    window.onmousewheel = document.onmousewheel = document.onkeydown = null;
}