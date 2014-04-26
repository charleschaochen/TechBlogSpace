/**
 * @Description Information of Current Browser
 * @author Charles Chen
 * @date 14-4-26
 * @version 1.0
 */
var browser = {
    version: function () {
        var agent = navigator.userAgent.toLowerCase();
        if (agent.indexOf("trident") > -1) {
            return "ie";
        }
        if (agent.indexOf("presto") > -1) {
            return "opera";
        }
        if (agent.indexOf("gecko") > -1 && agent.indexOf("firefox") > -1) {
            return "firefox";
        }
        if (agent.indexOf("webkit") > -1 && agent.indexOf("chrome") > -1) {
            return "chrome";
        }
        if (agent.indexOf("webkit") > -1 && agent.indexOf("safari") > -1) {
            return "safari";
        }
        return "others";
    },
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}
