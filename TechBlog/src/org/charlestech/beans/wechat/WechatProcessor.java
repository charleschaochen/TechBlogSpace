package org.charlestech.beans.wechat;

import com.sun.deploy.net.HttpResponse;
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
    void proccessText(TextMessage text, PrintWriter out);

    void proccessVoice(VoiceMessage voice, PrintWriter out);

    void proccessVideo(VideoMessage video, PrintWriter out);

    void proccessPic(PicMessage picture, PrintWriter out);

    void proccessLink(LinkMessage link, PrintWriter out);

    void proccessLocation(LocationMessage location, PrintWriter out);
}
