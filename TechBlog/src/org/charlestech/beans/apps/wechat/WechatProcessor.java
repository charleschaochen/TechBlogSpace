package org.charlestech.beans.apps.wechat;

import org.charlestech.po.wechat.*;

import java.io.PrintWriter;

/**
 * Created by chaoch on 14-2-19.
 */

/*
 * @Description Wechat Message Proccessor Interface
 * @author Charles Chen
 * @date 14-2-19
 * @version 1.0
 */
public interface WechatProcessor {
//    String MOBILE_CODE = "gsd";
//    String PINYIN = "py";
    String CANNOT_FIND_MOBILE_CODE = "亲的号码来自星星吗，臣妾找不到啊，重新发送试试~";
    String EMPTY_CONTENT = "臣妾猜不到您想查询什么哦，您是不是忘输入查询关键字了~";
    String EXCEPTION = "Oh~好像发生异常了，重新发送试试~";

    void process(Message message, PrintWriter out);

    void processText(TextMessage text, PrintWriter out);

    void processVoice(VoiceMessage voice, PrintWriter out);

    void processVideo(VideoMessage video, PrintWriter out);

    void processPic(PicMessage picture, PrintWriter out);

    void processLink(LinkMessage link, PrintWriter out);

    void processLocation(LocationMessage location, PrintWriter out);
}
