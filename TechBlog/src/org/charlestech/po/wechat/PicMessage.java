package org.charlestech.po.wechat;

/*
 * @Description Wechat Picture Message PJO
 * @author Charles Chen
 * @date 14-2-19
 * @version 1.0
 */
public class PicMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType; // Message type
    private String PicUrl;  // Picture URL
    private String MediaId; // Media resource id
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

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
