/**
 * @Description Wechat monitor script
 * @author Charles Chen
 * @date 14-4-27
 * @version 1.0
 */

$(window).ready(function () {
    $("#switch-btn").click(Wechat.switchButton);   // Switch text input and button menu
    $("#send-btn").click(Wechat.sendMessage);  // Send text
    // Press Enter to send text
    $("#input_text").keydown(function (e) {
        if (e.which == 13) {
            Wechat.sendMessage();
        }
    });
});


var Wechat = {
    // Switch text input area and button menu area
    switchButton: function () {
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
    },
    // Send text message action
    sendMessage: function () {
        var text = $("#input_text").val().replace(/(^\s)|(\s$)/, "");
        if (text != "") {
            var message = $("<div class='right-chat-history'></div>");
            message.html("<div class='chat-pic'></div><div class='chat-content'>" + text + "</div>");
            $("#chat-history").append(message);

            var data = "<xml>" +
                "   <ToUserName><![CDATA[toUser]]></ToUserName>" +
                "   <FromUserName><![CDATA[fromUser]]></FromUserName>" +
                "   <CreateTime>1348831860</CreateTime>" +
                "   <MsgType><![CDATA[text]]></MsgType>" +
                "   <Content><![CDATA[" + text + "]]></Content>" +
                "   <MsgId>1234567890123456</MsgId>" +
                "</xml>";
            $.ajax({
                url: "/wechat_handler.shtml",
                data: data,
                type: "post",
                contentType: "text/html",
                success: function (data) {
                    var content = $(data).find("Content").html().replace("<!--[CDATA[", "").replace("]]-->", "");
                    message = $("<div class='left-chat-history'></div>");
                    message.html("<div class='chat-pic'></div><div class='chat-content'>" + content + "</div>");
                    $("#chat-history").append(message);
                    $("#chat-history").scrollTop($("#chat-history").height());
                },
                error: function () {
                    alert("Ajax Error");
                }
            });
            $("#input_text").val("");
        }
    },
    // Send button event action
    sendEvent: function (key) {
        var data = "<xml>" +
            "   <ToUserName><![CDATA[toUser]]></ToUserName>" +
            "   <FromUserName><![CDATA[fromUser]]></FromUserName>" +
            "   <CreateTime>1348831860</CreateTime>" +
            "   <MsgType><![CDATA[event]]></MsgType>" +
            "   <Event><![CDATA[CLICK]]></Event>" +
            "   <EventKey><![CDATA[" + key + "]]></EventKey>" +
            "</xml>";
        $.ajax({
                url: "/wechat_handler.shtml",
                data: data,
                type: "post",
                contentType: "text/html",
                success: function (data) {
                    var content = $(data).find("Content").html();
                    if (content != "") {
                        var content = content.replace("<!--[CDATA[", "").replace("]]-->", "");
                        message = $("<div class='left-chat-history'></div>");
                        message.html("<div class='chat-pic'></div><div class='chat-content'>" + content + "</div>");
                        $("#chat-history").append(message);
                        $("#chat-history").scrollTop($("#chat-history").height());
                    }
                },
                error: function () {
                    alert("Ajax Error");
                }
            }
        )
        ;
    },
    // Initialize wechat dialog
    initWechatDialog: function (title, menu) {
        $("#account_name").text(title);  // Initialized the account name
        if ("button" in menu) {
            var menu_html = [];
            for (var i = 0; i < menu.button.length && i < 3; i++) {
                var btn = menu.button[i];
                if ("type" in btn) {
                    if (btn.type == "view") {
                        menu_html.push("<div class='menu-item btn-group'><div class='dropdown-toggle' onclick='location.href=" + btn.url + "' target='_BLANK'>" + btn.name + "</div></div>");
                    } else if (btn.type == "click") {
                        menu_html.push("<div class='menu-item btn-group'><div class='dropdown-toggle' onclick='Wechat.sendEvent(\"" + btn.key + "\")'>" + btn.name + "</div></div>");
                    }
                } else if ("sub_button" in btn) {
                    var btn_html = "<div class='menu-item btn-group dropup'><div class='dropdown-toggle' data-toggle='dropdown'>" + btn.name + "<span class='caret'></span></div><ul class='dropdown-menu'>";
                    for (var j = 0; j < btn.sub_button.length && j < 5; j++) {
                        var sub_btn = btn.sub_button[j];
                        if ("type" in sub_btn) {
                            if (sub_btn.type == "view") {
                                btn_html += "<li><a href='" + sub_btn.url + "' target='_BLANK'>" + sub_btn.name + "</a></li>";
                            } else if (sub_btn.type == "click") {
                                btn_html += "<li><a href='javascript:Wechat.sendEvent(\"" + sub_btn.key + "\");'>" + sub_btn.name + "</a></li>";
                            }
                        }
                    }
                    btn_html += "</ul></div>";
                    menu_html.push(btn_html);
                }
            }
            $("#menu-area").html(menu_html.join(""));
        }
    }
}