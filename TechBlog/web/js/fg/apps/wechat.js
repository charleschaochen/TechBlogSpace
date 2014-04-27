/**
 * @Description Wechat monitor script
 * @author Charles Chen
 * @date 14-4-27
 * @version 1.0
 */

$(window).ready(function () {
    $("#switch-btn").click(switchButton);
    $("#send-btn").click(sendMessage);
    $("#input_text").keydown(function (e) {
        if (e.which == 13) {
            sendMessage();
        }
    });
});

function switchButton() {
    var title = $(this).attr("title");
    if (title == "menu") {
        $(this).attr("title", "keyboard");
        $(this).css("background", "url(/images/wechat-keyboard.png)");
        $("#text-area").slideUp();
        $("#menu-area").slideDown();
    } else {
        $(this).attr("title", "menu");
        $(this).css("background", "url(/images/wechat-menu.png)");
        $("#text-area").slideDown();
        $("#menu-area").slideUp();
    }
}

function sendMessage() {
    var text = $("#input_text").val().replace(/(^\s)|(\s$)/, "");
    if (text != "") {
        var message = $("<div class='right-chat-history'></div>");
        message.html("<div class='chat-pic'></div><div class='chat-content'>" + text + "</div>");
        $("#chat-history").append(message);
        message = $("<div class='left-chat-history'></div>");
        message.html("<div class='chat-pic'></div><div class='chat-content'>收到啦~</div>");
        $("#chat-history").append(message);
    }
}