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
    public void proccessText(TextMessage text, PrintWriter out);

    public void proccessVoice(VoiceMessage voice, PrintWriter out);

    public void proccessVideo(VideoMessage video, PrintWriter out);

    public void proccessPic(PicMessage picture, PrintWriter out);

    public void proccessLink(LinkMessage link, PrintWriter out);

    public void proccessLocation(LocationMessage location, PrintWriter out);
}
