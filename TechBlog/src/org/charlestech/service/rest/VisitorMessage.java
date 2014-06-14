package org.charlestech.service.rest;

/*
 * @Description Visitor message
 * @author Charles Chen
 * @date 14-3-23
 * @version 1.0
 */
public class VisitorMessage {
    private String time;
    private String content;

    public VisitorMessage(String time, String content) {
        this.time = time;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}