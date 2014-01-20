
$(pageInit);
var editor;
// initialize the editor
function pageInit() {
	// define plugins, code and google map
	var plugins = {Code:{c:"btnCode", t:"\u63d2\u5165\u4ee3\u7801", h:1, e:function () {
		var _this = this;
		var htmlCode = "<div><select id=\"xheCodeType\"><option value=\"html\">HTML/XML</option><option value=\"js\">Javascript</option><option value=\"css\">CSS</option><option value=\"php\">PHP</option><option value=\"java\">Java</option><option value=\"py\">Python</option><option value=\"pl\">Perl</option><option value=\"rb\">Ruby</option><option value=\"cs\">C#</option><option value=\"c\">C++/C</option><option value=\"vb\">VB/ASP</option><option value=\"\">\u5176\u5b83</option></select></div><div><textarea id=\"xheCodeValue\" wrap=\"soft\" spellcheck=\"false\" style=\"width:300px;height:100px;\" /></div><div style=\"text-align:right;\"><input type=\"button\" id=\"xheSave\" value=\"\u786e\u5b9a\" /></div>";
		var jCode = $(htmlCode), jType = $("#xheCodeType", jCode), jValue = $("#xheCodeValue", jCode), jSave = $("#xheSave", jCode);
		jSave.click(function () {
			_this.loadBookmark();
			_this.pasteHTML("<pre class=\"brush: js;\">" + _this.domEncode(jValue.val()) + "</pre>");
			_this.hidePanel();
			return false;
		});
		_this.saveBookmark();
		_this.showDialog(jCode);
	}}, map:{c:"btnMap", t:"\u63d2\u5165Google\u5730\u56fe", e:function () {
		var _this = this;
		_this.saveBookmark();
		_this.showIframeModal("Google \u5730\u56fe", "/js/xheditor/plugins/googlemap/googlemap.html", function (v) {
			_this.loadBookmark();
			_this.pasteHTML("<img src=\"" + v + "\" />");
		}, 538, 404);
	}}};
	// configure the editor
	editor = $("#content").xheditor({upLinkUrl:"upload!uploadAttach.shtml", upLinkExt:"txt,rar,zip,7z", upImgUrl:"upload!uploadImg.shtml", upImgExt:"jpg,jpeg,gif,bmp,png", emots:{msn:{name:"MSN", count:40, width:22, height:22, line:8}, pidgin:{name:"Pidgin", width:22, height:25, line:8, list:{smile:"\u5fae\u7b11", cute:"\u53ef\u7231", wink:"\u7728\u773c", laugh:"\u5927\u7b11", victory:"\u80dc\u5229", sad:"\u4f24\u5fc3", cry:"\u54ed\u6ce3", angry:"\u751f\u6c14", shout:"\u5927\u9a82", curse:"\u8bc5\u5492", devil:"\u9b54\u9b3c", blush:"\u5bb3\u7f9e", tongue:"\u5410\u820c\u5934", envy:"\u7fa1\u6155", cool:"\u800d\u9177", kiss:"\u543b", shocked:"\u60ca\u8bb6", sweat:"\u6c57", sick:"\u751f\u75c5", bye:"\u518d\u89c1", tired:"\u7d2f", sleepy:"\u7761\u4e86", question:"\u7591\u95ee", rose:"\u73ab\u7470", gift:"\u793c\u7269", coffee:"\u5496\u5561", music:"\u97f3\u4e50", soccer:"\u8db3\u7403", good:"\u8d5e\u540c", bad:"\u53cd\u5bf9", love:"\u5fc3", brokenheart:"\u4f24\u5fc3"}}, ipb:{name:"IPB", width:20, height:25, line:8, list:{smile:"\u5fae\u7b11", joyful:"\u5f00\u5fc3", laugh:"\u7b11", biglaugh:"\u5927\u7b11", w00t:"\u6b22\u547c", wub:"\u6b22\u559c", depres:"\u6cae\u4e27", sad:"\u60b2\u4f24", cry:"\u54ed\u6ce3", angry:"\u751f\u6c14", devil:"\u9b54\u9b3c", blush:"\u8138\u7ea2", kiss:"\u543b", surprised:"\u60ca\u8bb6", wondering:"\u7591\u60d1", unsure:"\u4e0d\u786e\u5b9a", tongue:"\u5410\u820c\u5934", cool:"\u800d\u9177", blink:"\u7728\u773c", whistling:"\u5439\u53e3\u54e8", glare:"\u8f7b\u89c6", pinch:"\u634f", sideways:"\u4fa7\u8eab", sleep:"\u7761\u4e86", sick:"\u751f\u75c5", ninja:"\u5fcd\u8005", bandit:"\u5f3a\u76d7", police:"\u8b66\u5bdf", angel:"\u5929\u4f7f", magician:"\u9b54\u6cd5\u5e08", alien:"\u5916\u661f\u4eba", heart:"\u5fc3\u52a8"}}}, plugins:plugins, loadCSS:"<style>pre{margin-left:2em;border-left:3px solid #CCC;padding:0 1em;}</style>", shortcuts:{"ctrl+enter":submitForm}});
}

// submit the form
function submitForm() {
	$("#frm").submit();
}

