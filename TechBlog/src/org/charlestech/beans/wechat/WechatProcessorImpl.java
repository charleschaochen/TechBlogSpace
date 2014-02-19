package org.charlestech.beans.wechat;

import org.charlestech.po.wechat.*;
import org.charlestech.utils.XMLUtils;

import java.io.PrintWriter;
import java.util.Date;

/*
 * @Description Wechar Message Proccessor
 * @author Charles Chen
 * @date 14-2-19
 * @version 1.0
 */
public class WechatProcessorImpl implements WechatProcessor {

    @Override
    public void proccessText(TextMessage text, PrintWriter out) {
        String content = "OK. I receive your message: " + text.getContent();
        TextMessage respText = new TextMessage();
        respText.setFromUserName(text.getToUserName());
        respText.setToUserName(text.getFromUserName());
        respText.setCreateTime(String.valueOf(new Date().getTime()));
        respText.setMsgType(WechatUtils.TEXT);
        respText.setContent(content);
        out.print(XMLUtils.objectToXml(respText));
    }

    @Override
    public void proccessVoice(VoiceMessage voice, PrintWriter out) {

    }

    @Override
    public void proccessVideo(VideoMessage video, PrintWriter out) {

    }

    @Override
    public void proccessPic(PicMessage picture, PrintWriter out) {

    }

    @Override
    public void proccessLink(LinkMessage link, PrintWriter out) {

    }

    @Override
    public void proccessLocation(LocationMessage location, PrintWriter out) {

    }
}
