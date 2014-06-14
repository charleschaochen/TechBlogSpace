package org.charlestech.po.wechat;

/*
 * @Description Wechat Voice Message POJO
 * @author Charles Chen
 * @date 14-2-19
 * @version 1.0
 */
public class VoiceMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType; // Message type
    private String MediaId; // Media resource id
    private String Format;  // Voice resource format
    private String MsgId;   // Message id

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
