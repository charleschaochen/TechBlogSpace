package org.charlestech.po.wechat;

/*
 * @Description Wechat Location Message POJO
 * @author Charles Chen
 * @date 14-2-19
 * @version 1.0
 */
public class LocationMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType; // Message type
    private double Location_X;  // Location longitude
    private double Location_Y;  // Location latitude
    private double Scale;   // Zoom scale
    private String Label;   // Location information
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

    public double getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(double location_X) {
        Location_X = location_X;
    }

    public double getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(double location_Y) {
        Location_Y = location_Y;
    }

    public double getScale() {
        return Scale;
    }

    public void setScale(double scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
