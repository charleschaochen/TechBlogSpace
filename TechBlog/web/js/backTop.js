/**
 * @Description Go back to top script
 * @author Charles Chen
 * @date 14-5-24
 * @version 1.0
 */

var backTop = {
    backTopid: "backtop",   // Back to top icon ID

    /**
     * Initialize back to top icon
     * @param obj
     */
    createBackTop: function (obj) {
        var t = this;
        var backTopDiv;
        switch (obj.style) {
            case 0:
                backTopDiv = t.buildSimpleStyleIcon(obj);
                break;
            case 1:
                backTopDiv = t.buildArrowStyleIcon(obj);
                break;
            case 2:
                backTopDiv = t.buildRocketStyleIcon(obj);
                break;
            default:
                backTopDiv = t.buildArrowStyleIcon(obj);
                break;
        }
        backTopDiv.attr("title", "返回顶部");
        backTopDiv.click(t.scrollToTop);
        $("body").append(backTopDiv);
        $(window).scroll(function () {
            t.onScroll(this.backTopid, 200);
        });
    },
    /**
     * Show or hide back to top icon while scrolling
     * @param id
     * @param maxHeight
     */
    onScroll: function (id, maxHeight) {
        // Get height after scrolling
        var scroll_height = document.documentElement.scrollTop + document.body.scrollTop;

        if (isNaN(maxHeight)) {
            maxHeight = 200;    // Default 200
        }

        if (scroll_height > maxHeight) {
            // If scroll height is larger than maxHeight, show the back to top icon
            $("#" + id).fadeIn(); // Fade in
        } else {
            // If scroll height is smaller than maxHeight, hide the back to top icon
            $("#" + id).fadeOut(); // Fade out
        }
    },

    /**
     * Scroll to the top of the page
     */
    scrollToTop: function () {
        $("html,body").animate({scrollTop: "0px"}, 200);
    },

    buildSimpleStyleIcon: function (obj) {
        var backTopDiv = $("<a id='" + this.id + "'><span>▲</span></a>");
        backTopDiv.css("width", "50px");
        backTopDiv.css("height", "50px");
        backTopDiv.css("border-radius", "6px");
        backTopDiv.css("-webkit-border-radius", "6px");
        backTopDiv.css("-moz-border-radius", "6px");
        backTopDiv.css("background-color", "#666");
        backTopDiv.css("color", "#eee");
        backTopDiv.css("font-size", "30px");
        backTopDiv.css("position", "fixed");
        backTopDiv.css("text-align", "center");
        backTopDiv.css("text-decoration", "underline");
        if (obj.bottom != undefined) {
            backTopDiv.css("bottom", obj.bottom);
        } else {
            backTopDiv.css("bottom", "50px");
        }

        if (obj.right != undefined) {
            backTopDiv.css("right", obj.right);
        } else {
            backTopDiv.css("right", "40px");
        }
        backTopDiv.css("display", "none");
        backTopDiv.hover(function () {
            $(this).css("background-color", "#aaa");
        });
        backTopDiv.mouseleave(function () {
            $(this).css("background-color", "#666");
        });
        return backTopDiv;
    },
    /**
     * Build back to top icon with rocket style
     * @param obj
     * @returns {*|jQuery|HTMLElement}
     */
    buildRocketStyleIcon: function (obj) {
        var backTopDiv = $("<a id='" + this.id + "'></a>");
        backTopDiv.css("width", "36px");
        backTopDiv.css("height", "65px");
        backTopDiv.css("background", "url(/images/rocket.png)");
        backTopDiv.css("background-position", "0px -65px");
        backTopDiv.css("position", "fixed");
        if (obj.bottom != undefined) {
            backTopDiv.css("bottom", obj.bottom);
        } else {
            backTopDiv.css("bottom", "50px");
        }

        if (obj.right != undefined) {
            backTopDiv.css("right", obj.right);
        } else {
            backTopDiv.css("right", "40px");
        }
        backTopDiv.css("display", "none");
        backTopDiv.hover(function () {
            $(this).css("background-position", "0px 0px");
        });
        backTopDiv.mouseleave(function () {
            $(this).css("background-position", "0px -65px");
        });
        return backTopDiv;
    },

    buildArrowStyleIcon: function (obj) {
        var backTopDiv = $("<a id='" + this.id + "'></a>");
        backTopDiv.css("width", "36px");
        backTopDiv.css("height", "33px");
        backTopDiv.css("background", "url(/images/backtop.gif)");
        backTopDiv.css("position", "fixed");
        if (obj.bottom != undefined) {
            backTopDiv.css("bottom", obj.bottom);
        } else {
            backTopDiv.css("bottom", "50px");
        }

        if (obj.right != undefined) {
            backTopDiv.css("right", obj.right);
        } else {
            backTopDiv.css("right", "40px");
        }
        backTopDiv.css("display", "none");
        return backTopDiv;
    }
}
