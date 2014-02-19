package org.charlestech.beans.wechat;

import org.apache.commons.lang.StringUtils;
import org.charlestech.po.wechat.Message;
import org.charlestech.po.wechat.TextMessage;
import org.charlestech.utils.XMLUtils;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/*
 * @Description Wechat API Utilities
 * @author Charles Chen
 * @date 14-2-19
 * @version 1.0
 */
public class WechatUtils {
    /* Start: Wechat Message Types */
    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";
    public static final String LOCATION = "location";
    public static final String LINK = "link";
    /* End: Wechat Message Types */

    /* Start: Wechat Message XML Element Names */
    public static final String TO_USER = "ToUserName";
    public static final String FROM_USER = "FromUserName";
    public static final String TIME = "CreateTime";
    public static final String TYPE = "MsgType";
    public static final String CONTENT = "Content";
    public static final String MESS_ID = "MsgId";
    public static final String PIC_URL = "PicUrl";
    public static final String MEDIA_ID = "MediaId";
    public static final String FORMAT = "Format";
    public static final String THUMB_ID = "ThumbMediaId";
    public static final String LOCATION_X = "Location_X";
    public static final String LOCATION_Y = "Location_y";
    public static final String SCALE = "Scale";
    public static final String LABEL = "Label";
    public static final String TITLE = "Title";
    public static final String DESCRIPTION = "Description";
    public static final String URL = "Url";
    /* End: Wechat Message XML Element Names */

    /**
     * Retrieve wechat message details
     *
     * @param request HTTP Request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Message retrieveMessage(HttpServletRequest request) throws IOException, DocumentException {
        Message message = new Message();
        InputStream is = request.getInputStream();
        Map<String, String> data = XMLUtils.parseXml(is);

        // Build up message object according to the type
        String type = data.get(TYPE);
        if (StringUtils.equals(type, TEXT)) {
            message.setType(TEXT);
            message.setMess(buildTextMessage(data));
            return message;
        }
        return null;
    }

    /**
     * Build text message from data map
     *
     * @param data Data Map
     * @return
     */
    public static TextMessage buildTextMessage(Map<String, String> data) {
        TextMessage text = new TextMessage();
        text.setMsgType(data.get(TYPE));
        text.setContent(data.get(CONTENT));
        text.setToUserName(data.get(TO_USER));
        text.setFromUserName(data.get(FROM_USER));
        text.setCreateTime(data.get(TIME));
        text.setMsgId(data.get(MESS_ID));
        return text;
    }

}
