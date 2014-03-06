package org.charlestech.beans.wechat;

import org.apache.commons.lang.StringUtils;
import org.charlestech.po.wechat.*;
import org.charlestech.service.wechat.Query;
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
    private Query query;

    public void setQuery(Query query) {
        this.query = query;
    }

    @Override
    public void proccessText(TextMessage text, PrintWriter out) {
        String mess = text.getContent();
        if (mess == null || StringUtils.isEmpty(mess)) {
            out.print("");
            return;
        }
        String respContent = null;
        /* Start: Get response content according to the instruction */
        if (mess.toLowerCase().startsWith(query.MOBILE_CODE)) {
            // Mobile code query
            String mobileCode = mess.replace(query.MOBILE_CODE, "").trim();
            if (StringUtils.isEmpty(mobileCode)) {
                respContent = query.EMPTY_CONTENT;
            } else {
                respContent = query.getMobileCode(mobileCode);
                if (respContent == null) {
                    respContent = query.CANNOT_FIND_MOBILE_CODE;
                }
            }

        } else if (mess.toLowerCase().startsWith(query.PINYIN)) {
            // Chinese Pinyin query
            String word = mess.replace(query.PINYIN, "").trim();
            if (StringUtils.isEmpty(word)) {
                respContent = query.EMPTY_CONTENT;
            } else {
                respContent = query.getAllPinyin(word);
                if (StringUtils.isEmpty(respContent)) {
                    respContent = query.EXCEPTION;
                }
            }
        }


        /* End: Get response content according to the instruction */
        TextMessage respText = new TextMessage();
        respText.setFromUserName(text.getToUserName());
        respText.setToUserName(text.getFromUserName());
        respText.setCreateTime(String.valueOf(new Date().getTime()));
        respText.setMsgType(WechatUtils.TEXT);
        respText.setContent(respContent);
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
