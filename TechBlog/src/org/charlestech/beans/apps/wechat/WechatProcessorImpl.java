package org.charlestech.beans.apps.wechat;

import org.apache.commons.lang.StringUtils;
import org.charlestech.po.wechat.*;
import org.charlestech.service.wechat.Query;
import org.charlestech.utils.XMLUtils;

import static org.charlestech.beans.apps.wechat.WechatUtils.MESS_TYPE;

import java.io.PrintWriter;
import java.util.Date;

/*
 * @Description Wechar Message Proccessor
 * @author Charles Chen
 * @date 14-2-19
 * @version 1.0
 */
public class WechatProcessorImpl implements WechatProcessor {
    private static Query query;

    public void setQuery(Query query) {
        this.query = query;
    }

    /**
     * Text message code enum
     */
    public enum Code {
        MOBILE_CODE("gsd") {
            String getResult(String mess) {
                // Mobile code query
                String mobileCode = mess.toLowerCase().replace(this.getCommand(), "").trim();
                if (StringUtils.isEmpty(mobileCode)) {
                    return EMPTY_CONTENT;
                }
                String result = query.getMobileCode(mobileCode);
                return result != null ? result : CANNOT_FIND_MOBILE_CODE;
            }
        }, PINYIN("py") {
            String getResult(String mess) {
                // Chinese Pinyin query
                String word = mess.toLowerCase().replace(this.getCommand(), "").trim();
                if (StringUtils.isEmpty(word)) {
                    return EMPTY_CONTENT;
                }
                String result = query.getAllPinyin(word);
                return result != null ? result : EXCEPTION;
            }
        };
        private String command;

        private Code(String command) {
            this.command = command;
        }

        public String getCommand() {
            return this.command;
        }

        abstract String getResult(String mess);
    }

    /**
     * Process message according to the type
     *
     * @param message
     * @param out
     */
    public void process(Message message, PrintWriter out) {
        MESS_TYPE mess_type = MESS_TYPE.valueOf(message.getType());
        switch (mess_type) {
            case TEXT:
                processText((TextMessage) message.getMess(), out);
                break;
            case IMAGE:
                processPic((PicMessage) message.getMess(), out);
                break;
            case VOICE:
                processVoice((VoiceMessage) message.getMess(), out);
                break;
            case VIDEO:
                processVideo((VideoMessage) message.getMess(), out);
                break;
            case LOCATION:
                processLocation((LocationMessage) message.getMess(), out);
                break;
            case LINK:
                processLink((LinkMessage) message.getMess(), out);
                break;
            default:
                return;
        }
    }

    /**
     * Process text message
     *
     * @param text
     * @param out
     */
    public void processText(TextMessage text, PrintWriter out) {
        String mess = text.getContent();
        if (mess == null || StringUtils.isEmpty(mess)) {
            out.print("");
            return;
        }
        String respContent = null;
        /* Start: Get response content according to the instruction */
        for (Code code : Code.values()) {
            if (mess.toLowerCase().startsWith(code.getCommand())) {
                respContent = code.getResult(mess);
                break;
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
    public void processVoice(VoiceMessage voice, PrintWriter out) {

    }

    @Override
    public void processVideo(VideoMessage video, PrintWriter out) {

    }

    @Override
    public void processPic(PicMessage picture, PrintWriter out) {

    }

    @Override
    public void processLink(LinkMessage link, PrintWriter out) {

    }

    @Override
    public void processLocation(LocationMessage location, PrintWriter out) {

    }


}
