package org.charlestech.actions.apps.wechat;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.charlestech.beans.wechat.WechatProcessor;
import org.charlestech.beans.wechat.WechatUtils;
import org.charlestech.po.wechat.Message;
import org.charlestech.po.wechat.TextMessage;
import org.charlestech.utils.SHA1;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/*
 * @Description Interact with Wechat Open Platform
 * @author Charles Chen
 * @date 14-2-17
 * @version 1.0
 */
public class WechatHandler extends ActionSupport {
    /* Start: Verification parameters */
    private String signature;
    private String timestamp;
    private String nonce;   // Random number
    private String echostr;
    private final String TOKEN = "charleschaochen";
    /* End: Verification parameters */

    private WechatProcessor wechatProcessor;    // Wechat processor

    public void setWechatProcessor(WechatProcessor wechatProcessor) {
        this.wechatProcessor = wechatProcessor;
    }


    /**
     * Default execute
     *
     * @return
     */
    public String execute() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        Message message;

        try {
            message = WechatUtils.retrieveMessage(request); // Read message
            String type = message.getType();
            /* Start: Process message according to the type */
            // Text message
            if (StringUtils.equals(type, WechatUtils.TEXT)) {
                wechatProcessor.proccessText((TextMessage) message.getMess(), response.getWriter());
                return null;
            }
            /* End: Process message according to the type */
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Verify messages from Wechat
     */
    private void verify(PrintWriter out) throws IOException {
        String[] params = new String[]{
                TOKEN, timestamp, nonce
        };
        Arrays.sort(params);    // Dictionary sort
        String encrypted = new SHA1().getDigestOfString((params[0] + params[1] + params[2]).getBytes()).toLowerCase();  // Encrypt with SHA1
        if (StringUtils.equals(signature, encrypted)) {
            out.print(echostr);
        }
    }


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    public String getTOKEN() {
        return TOKEN;
    }

}
